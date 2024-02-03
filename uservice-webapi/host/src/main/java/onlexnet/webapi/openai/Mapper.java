package onlexnet.webapi.openai;

import com.azure.ai.openai.models.ChatRequestAssistantMessage;
import com.azure.ai.openai.models.ChatRequestMessage;
import com.azure.ai.openai.models.ChatRequestUserMessage;

interface Mapper {
  
  static ChatRequestMessage toDto(Message message) {
    var messageText = message.text();
    return switch (message.role()) {
      case ASSISTANT -> new ChatRequestAssistantMessage(messageText);
      case USER -> new ChatRequestUserMessage(messageText);
    };
  }

}
