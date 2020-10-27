package application.service;


import application.DTO.MessageDTO;
import application.entity.Message;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    //Variables
    private List<Message> messageList;

    //Controller
    public MessageService() {
        this.messageList = messageListCreator();
    }

    //Getter & Setter Functions
    public List<Message> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<Message> messageList) {
        this.messageList = messageList;
    }

    //Functions
    private List<Message> messageListCreator() {
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(2L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(3L, "user3", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(4L, "user4", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(5L, "user5", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(6L, "user6", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(7L, "user7", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(8L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(9L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(10L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(11L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(12L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(13L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(14L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(15L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(16L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(17L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(18L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(19L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(20L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        return messageList;
    }

    /**
     * Returns with "List<Message>" arranged by "arguments"
     **/
    public List<Message> getArrangedMessageList(Integer limit, String orderBy, String direction) {
        List<Message> currentMessageList = getMessageList();

        currentMessageList = setValueOrderBy(orderBy, direction, currentMessageList);
        currentMessageList = limitReturnList(limit, currentMessageList);
        return currentMessageList;

    }

    private List<Message> setValueOrderBy(String orderBy, String direction, List<Message> currentMessageList) {
        Comparator<Message> comparing = orderByComparator(orderBy);
        if (direction.equals("asc")) {
            return currentMessageList
                    .stream()
                    .sorted(comparing)
                    .collect(Collectors.toList());
        } else if (direction.equals("desc")) {
            return currentMessageList
                    .stream()
                    .sorted(comparing.reversed())
                    .collect(Collectors.toList());
        } else {
            return currentMessageList
                    .stream()
                    .sorted(comparing)
                    .collect(Collectors.toList());
        }
    }

    private List<Message> limitReturnList(Integer limit, List<Message> listToLimit) {
        return listToLimit
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }

    private Comparator<Message> orderByComparator(String orderBy) {
        switch (orderBy) {
            case "date":
                return Comparator.comparing(Message::getTimeOfSending);
            case "author":
                return Comparator.comparing(Message::getAuthor);
            case "receiver":
                return Comparator.comparing(Message::getRecipient);
            case "message":
                return Comparator.comparing(Message::getMessage);
            case "topic":
                return Comparator.comparing(Message::getSubject);
            case "messageId":
                return Comparator.comparing(Message::getMessageId);
        }
        return Comparator.comparing(Message::getTimeOfSending);
    }

    /**
     * Returns with selected "Message" from DB by "messageId"
     **/
    public Message showSelectedMessage(Long messageId) {
        for (Message message : messageList) {
            if (message.getMessageId().equals(messageId)) {
                return message;
            }
        }
        return null;
    }

    /**
     * Add a new Message to DataBase
     **/
    public void addNewMessageToDataBase(MessageDTO messageDTO) {
        Message messageToAdd = new Message(messageDTO);
        this.messageList.add(messageToAdd);
    }
}
