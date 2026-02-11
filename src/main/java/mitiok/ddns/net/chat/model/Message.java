package mitiok.ddns.net.chat.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "messages")
public class Message {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  private String sender;    // username отправителя

  @Column(nullable = false)
  private String recipient; // username получателя

  @Column(nullable = false)
  private String content;

  @Column(nullable = false)
  private LocalDateTime timestamp = LocalDateTime.now();

  public Message() {}

  public Message(String sender, String recipient, String content) {
    this.sender = sender;
    this.recipient = recipient;
    this.content = content;
    this.timestamp = LocalDateTime.now();
  }

  // ======== Геттеры и сеттеры ========
  public Long getId() { return id; }
  public void setId(Long id) { this.id = id; }

  public String getSender() { return sender; }
  public void setSender(String sender) { this.sender = sender; }

  public String getRecipient() { return recipient; }
  public void setRecipient(String recipient) { this.recipient = recipient; }

  public String getContent() { return content; }
  public void setContent(String content) { this.content = content; }

  public LocalDateTime getTimestamp() { return timestamp; }
  public void setTimestamp(LocalDateTime timestamp) { this.timestamp = timestamp; }
}
