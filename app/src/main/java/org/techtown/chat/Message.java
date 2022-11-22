package org.techtown.chat;

// 메세지 데이터를 저장하는 객체(Value Object)
public class Message {
    private int id;
    private String message;
    private String registerDate;
    private MessageType messageType;

    public Message(String message) {
        this.message = message;
    }

    public Message(int id, String message, String registerDate, MessageType messageType) {
        this.id = id;
        this.message = message;
        this.registerDate = registerDate;
        this.messageType = messageType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getRegisterDate() {
        return registerDate;
    }

    public void setRegisterDate(String registerDate) {
        this.registerDate = registerDate;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", registerDate='" + registerDate + '\'' +
                ", messageType=" + messageType +
                '}';
    }
}
