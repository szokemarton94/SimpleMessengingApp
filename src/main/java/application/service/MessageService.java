package application.service;


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
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user3", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user4", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user5", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user6", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user7", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        messageList.add(new Message(1L, "user1", "user2", LocalDateTime.of(2020, 10, 8, 22, 19), "Hello world!", "null"));
        messageList.add(new Message(1L, "user2", "user1", LocalDateTime.of(2020, 10, 8, 22, 25), "Good bye world!", "null"));
        return messageList;
    }

    public List<Message> getArrangedMessageList(Integer limit, String orderBy, String direction) {
        List<Message> currentMessageList = getMessageList();

        currentMessageList = setValueOrderBy(orderBy, direction, currentMessageList);
        currentMessageList = limitReturnList(limit, currentMessageList);
        return currentMessageList;

    }

//    private List<Message> setDirection(String direction, List<Message> currentMessageList) {
//
//        if (direction.equals("asc")){
//            return currentMessageList.stream().sorted(Comparator.naturalOrder())
//        }
//    }

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
        } else { return currentMessageList
                .stream()
                .sorted(comparing)
                .collect(Collectors.toList());}
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
                return Comparator.comparing(Message::getTimeOfSend);
            case "author":
                return Comparator.comparing(Message::getSender);
            case "receiver":
                return Comparator.comparing(Message::getReceiver);
            case "message":
                return Comparator.comparing(Message::getMessage);
            case "topic":
                return Comparator.comparing(Message::getTopic);
            case "messageId":
                return Comparator.comparing(Message::getMessageId);
        }
        return Comparator.comparing(Message::getTimeOfSend);
    }
}
