package de.thbingen.UserService.dto;

public class MessageDto {

    private int id;
    private String receiverEmail;
    private String senderEmail;
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

    public MessageDto(int id, String receiverEmail, String senderEmail, String message) {
        this.id = id;
        this.receiverEmail = receiverEmail;
        this.senderEmail = senderEmail;
        this.message = message;
    }

    public MessageDto() {
    }

    @Override
    public String toString() {
        return "MessageDto{" +
                "id='" + id + '\'' +
                ", receiverEmail='" + receiverEmail + '\'' +
                ", senderEmail='" + senderEmail + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
