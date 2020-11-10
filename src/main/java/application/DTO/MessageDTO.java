package application.DTO;

import org.springframework.security.core.context.SecurityContextHolder;

import java.time.LocalDateTime;

public class MessageDTO {
    //Variables
    private String author;
    private LocalDateTime timeOfSending;
    private String recipient;
    private String subject;
    private String message;

    //Constructor
    public MessageDTO(String recipient, String subject, String message) {
        this.author = SecurityContextHolder.getContext().getAuthentication().getName();
        this.recipient = recipient;
        this.subject = subject;
        this.message = message;
        this.timeOfSending = LocalDateTime.now();
    }

    //Getter & setter
    public String getAuthor() {
        return author;
    }
    public void setAuthor(String author) {
        this.author = author;
    }

    public LocalDateTime getTimeOfSending() {
        return timeOfSending;
    }
    public void setTimeOfSending(LocalDateTime timeOfSending) {
        this.timeOfSending = timeOfSending;
    }

    public String getRecipient() {
        return recipient;
    }
    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getSubject() {
        return subject;
    }
    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }
}
