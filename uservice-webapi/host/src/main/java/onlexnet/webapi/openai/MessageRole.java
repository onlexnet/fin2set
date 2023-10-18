package onlexnet.webapi.openai;

import lombok.Getter;

public enum MessageRole {
  
  ASSISTANT("assistant"),
  USER("user"),
  SYSTEM("system");

  @Getter
  private String text;

  MessageRole(String asText) {
    text = asText;
  }
}
