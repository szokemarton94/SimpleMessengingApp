package application.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "CONNECTIONS")
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "CONNECTION_ID")
    private Long connectionId;


    @OneToOne
    private User owner;


    @OneToOne
    private User guest;

    @OneToMany
    private List<Message> messages;

    public Long getConnectionId() {
        return connectionId;
    }

    public void setConnectionId(Long connectionId) {
        this.connectionId = connectionId;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public User getGuest() {
        return guest;
    }

    public void setGuest(User guest) {
        this.guest = guest;
    }

    public List<Message> getMessages() {
        return messages;
    }

    public void setMessages(List<Message> messages) {
        this.messages = messages;
    }

    public void addMessage(Message newMessage) {
        this.messages.add(newMessage);
    }
}
