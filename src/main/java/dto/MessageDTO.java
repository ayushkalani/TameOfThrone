package dto;

import java.io.Serializable;

public class MessageDTO implements Serializable {
  private static final long serialVersionUID = -8089546464690389739L;

  private String to;
  private String from;
  private String message;

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }


}
