package onlexnet.webapi.domain;

public interface ViewChange {
  
  enum Type {
    CHAT,
    VIEW1
  }

  record Notification(Type type) { }
}
