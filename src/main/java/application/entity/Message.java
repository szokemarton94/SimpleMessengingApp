package application.entity;

import application.DTO.MessageDTO;

import java.time.LocalDateTime;
//TODO Entity-fy
public class Message {
    //Variables
    private Long messageId;
    private String author;
    private String recipient;
    private LocalDateTime timeOfSending;
    private String message;
    private String subject;
    private Boolean isDeleted;

    //Constructor
    public Message(Long messageId, String author, String recipient, LocalDateTime timeOfSending, String message, String subject){
        this.messageId=messageId;
        this.author = author;
        this.recipient = recipient;
        this.timeOfSending = timeOfSending;
        this.message = message;
        this.subject = subject;
        this.isDeleted=false;
    }

    public Message(MessageDTO messageDTO) {
        this.messageId = (long)(Math.random()*100);
        this.author = messageDTO.getAuthor();
        this.recipient = messageDTO.getRecipient();
        this.timeOfSending = messageDTO.getTimeOfSending();
        this.message = messageDTO.getMessage();
        this.subject = messageDTO.getSubject();
        this.isDeleted=false;
    }

    //Getter and Setter functions
    public String getAuthor(){
        return author;
    }
    public void setAuthor(String author){
        this.author = author;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public LocalDateTime getTimeOfSending() {
        return timeOfSending;
    }
    public void setTimeOfSending(LocalDateTime timeOfSending) {
        this.timeOfSending = timeOfSending;
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

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public Boolean getDeleted() {
        return isDeleted;
    }
    public void setDeleted(Boolean deleted) {
        isDeleted = deleted;
    }
}
