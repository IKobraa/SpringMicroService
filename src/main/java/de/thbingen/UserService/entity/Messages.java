package de.thbingen.UserService.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "user_messages")
public class Messages {

    @Id
    @Column(name = "com-id", length = 45)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @Column(name = "receiver-Email", length = 45)
    private String receiverEmail;
    @Column(name = "sender-Email", length = 45)
    private String senderEmail;
    @Column(name = "message", length = 45)
    private String message;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getReceiverEmail() {
        return receiverEmail;
    }

    public void setReceiverEmail(String receiverEmail) {
        this.receiverEmail = receiverEmail;
    }

    public String getSenderEmail() {
        return senderEmail;
    }

    public void setSenderEmail(String senderEmail) {
        this.senderEmail = senderEmail;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Messages(int id, String receiverEmail, String senderEmail, String message) {
        this.id = id;
        this.receiverEmail = receiverEmail;
        this.senderEmail = senderEmail;
        this.message = message;
    }
    public Messages() {

    }

    @Override
    public String toString() {
        return "Messages{" +
                "id=" + id +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
