package onlexnet.webapi.openai;

import java.util.List;
import java.util.Map;

import com.azure.ai.openai.models.ChatChoice;
import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestFunctionMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatResponseMessage;
import com.azure.ai.openai.models.CompletionsFinishReason;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

interface Choice {

  interface Handler {

    /**
     * @return true when can handle the case, otherwisee false.
     */
    boolean canProcess(ChatChoice chatChoice);

    ProcessResult process(ChatChoice chatChoice);
  }

  sealed interface ProcessResult {

    record ForUser(ChatResponseMessage message) implements ProcessResult {
    }

    record ForAssistant(Iterable<ChatRequestMessage> message) implements ProcessResult {
    }

  }

  @RequiredArgsConstructor
  @Slf4j
  final class FunctionChoiceHandler implements Handler {

    private final Map<String, Func> functionsByNames;

    @Override
    public boolean canProcess(ChatChoice chatChoice) {
      // We are looking for finish_reason = "function call".
      return CompletionsFinishReason.FUNCTION_CALL.equals(chatChoice.getFinishReason());
    }

    @Override
    public ProcessResult process(ChatChoice chatChoice) {
      var choiceMessage = chatChoice.getMessage();
      var functionCall = choiceMessage.getFunctionCall();
      var functionName = functionCall.getName();
      var function = functionsByNames.get(functionName);

      log.info("Function name: {}, arguments: {}", functionCall.getName(), functionCall.getArguments());

      var msg1 = new ChatRequestAssistantMessage("").setFunctionCall(functionCall);

      var functionInvocationArgs = functionCall.getArguments();
      var funcResult = function.invoke(functionInvocationArgs);
      var msg2 = new ChatRequestFunctionMessage(functionName, funcResult);

      var result = List.of(msg1, msg2);
      return new ProcessResult.ForAssistant(result);
    }
  }

  @RequiredArgsConstructor
  final static class TransferResponseChoiceHandler implements Handler {

    @Override
    public boolean canProcess(ChatChoice chatChoice) {
      return CompletionsFinishReason.STOPPED.equals(chatChoice.getFinishReason());
    }

    @Override
    public ProcessResult process(ChatChoice chatChoice) {
      var choiceMessage = chatChoice.getMessage();
      return new ProcessResult.ForUser(choiceMessage);
    }
  }
}
