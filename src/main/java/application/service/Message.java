package application.service;

import java.time.LocalDateTime;

public class Message {
    //Variables
    private Long messageId;
    private String sender;
    private String receiver;
    private LocalDateTime timeOfSend;
    private String message;
    private String topic;

    //Constructor
    public Message(Long messageId, String sender, String receiver, LocalDateTime timeOfSend, String message, String topic ){
        this.messageId=messageId;
        this. sender = sender;
        this.receiver = receiver;
        this.timeOfSend = timeOfSend;
        this.message = message;
        this.topic=topic;
    }

    //Getter and Setter functions
    public String getSender(){
        return sender;
    }
    public void setSender(String sender){
        this.sender = sender;
    }

    public String getReceiver() {
        return receiver;
    }
    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public LocalDateTime getTimeOfSend() {
        return timeOfSend;
    }
    public void setTimeOfSend(LocalDateTime timeOfSend) {
        this.timeOfSend = timeOfSend;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public Long getMessageId() {
        return messageId;
    }
    public void setMessageId(Long messageId) {
        this.messageId = messageId;
    }

    public String getTopic() {
        return topic;
    }
    public void setTopic(String topic) {
        this.topic = topic;
    }
}
