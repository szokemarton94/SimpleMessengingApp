package application.entity;

import application.DTO.MessageDTO;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Message {
    //Variables
    @Id
    @Column(name = "MESSAGE_ID")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long messageId;
    @Column(name = "AUTHOR")
    private String author;
    //TODO - more than 1 recipient
    @Column(name = "RECIPIENT" )
    private String recipient;
    @Column(name = "TIME_OF_SENDING")
    private LocalDateTime timeOfSending;
    @Column(name = "MESSAGE" )
    private String message;
    @Column(name = "SUBJECT" )
    private String subject;
    @Column(name = "IS_DELETED")
    private Boolean isDeleted;

    //Constructors
    public Message() {
    }

    public Message(Long messageId, String author, String recipient, LocalDateTime timeOfSending, String message, String subject) {
        this.messageId = messageId;
        this.author = author;
        this.recipient = recipient;
        this.timeOfSending = timeOfSending;
        this.message = message;
        this.subject = subject;
        this.isDeleted = false;
    }

    public Message(MessageDTO messageDTO) {
        this.messageId = (long) (Math.random() * 100);
        this.author = messageDTO.getAuthor();
        this.recipient = messageDTO.getRecipient();
        this.timeOfSending = messageDTO.getTimeOfSending();
        this.message = messageDTO.getMessage();
        this.subject = messageDTO.getSubject();
        this.isDeleted = false;
    }



    //Getter and Setter functions
    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
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
