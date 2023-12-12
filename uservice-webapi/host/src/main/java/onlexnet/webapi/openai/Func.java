package onlexnet.webapi.openai;

import com.azure.ai.openai.models.FunctionDefinition;

public interface Func {

  String functionName();

  FunctionDefinition definition();

  String invoke(String args);
}
