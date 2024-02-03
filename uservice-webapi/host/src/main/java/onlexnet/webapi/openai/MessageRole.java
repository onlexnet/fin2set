package onlexnet.webapi.openai;

import lombok.Getter;

public enum MessageRole {
  
  ASSISTANT("assistant"),
  USER("user");

  @Getter
  private String text;

  MessageRole(String asText) {
    text = asText;
  }
}
