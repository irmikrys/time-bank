package timebank.model;

public class ErrorMessage {

  private String messageKey;

  public ErrorMessage(String messageKey) {
    this.messageKey = messageKey;
  }

  public String getMessageKey() {
    return messageKey;
  }

}
