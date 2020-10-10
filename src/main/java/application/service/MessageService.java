package application.service;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    //Variables
    private List<Message> messageList;

    //Controller
    public MessageService(){
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
    private List<Message> messageListCreator(){
        List<Message>messageList = new ArrayList<>();
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
        messageList.add(new Message(1L,"user1","user2",LocalDateTime.of(2020,10,8,22,19),"Hello world!","null"));
        messageList.add(new Message(1L,"user2","user1",LocalDateTime.of(2020,10,8,22,25),"Good bye world!", "null"));
    return messageList;
    }

    public List<Message> getArrangedMessageList(Integer limit){
//        List<Message>returnList = new ArrayList<>();
        return this.getMessageList()
                .stream()
                .limit(limit)
                .collect(Collectors.toList());
    }
}
