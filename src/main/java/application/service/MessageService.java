package application.service;


import application.DTO.MessageDTO;
import application.entity.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class MessageService {
    //Variables
    EntityManager entityManager;

    //Constructor
    @Autowired
    public MessageService(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    //Functions


    /**
     * Returns with "List<Message>" arranged by "arguments"
     **/
    public List<Message> getArrangedMessageList(Integer limit, String orderBy, String direction) {
        List<Message> currentMessageList = entityManager.createQuery("SELECT messages FROM Message messages", Message.class).getResultList();

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
    @Transactional
    public Message showSelectedMessage(Long messageId) {
        return entityManager.find(Message.class,messageId);
    }

    /**
     * Add a new Message to DataBase
     **/
    @Transactional
    public void addNewMessageToDataBase(MessageDTO messageDTO) {
        Message messageToAdd = new Message(messageDTO);
        entityManager.persist(messageToAdd);
    }
}
