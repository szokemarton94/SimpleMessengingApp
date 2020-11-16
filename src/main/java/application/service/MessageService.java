package application.service;


import application.DTO.MessageDTO;
import application.entity.Message;
import application.interfaces.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MessageService {

    @Autowired
    MessageRepository messageRepository;

    @PersistenceContext
    EntityManager entityManager;

    /**
     * Returns with "List<Message>" arranged by "arguments"
     **/
    public List<Message> getArrangedMessageList(Integer limit, String orderBy, String direction) {
        String userName = SecurityContextHolder.getContext().getAuthentication().getName();
        List<Message> currentMessageList =
                messageRepository.findAllByAuthorAndIsFlaggedAsDeletedOrderByAuthor(userName, false);
        currentMessageList = setValueOrderBy(orderBy, direction, currentMessageList);
        currentMessageList = limitReturnList(limit, currentMessageList);
        return currentMessageList;
    }

    //TODO put it on the server side with repository querys
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
     * Returns selected "Message"
     */
    public Message showSelectedMessage(Long messageId) {
        return messageRepository.findById(messageId).orElse(null);
    }

    /**
     * Add a new Message
     */
    @Transactional
    public void addNewMessageToDataBase(MessageDTO messageDTO) {
        Message messageToAdd = new Message(messageDTO);
        entityManager.persist(messageToAdd);
    }

    /**
     * Set selected "Message" as "flaggedAsDeleted"
     */
    public void setMessageAsDeleted(Long messageId) {

//       Message selectedMessage =  entityManager.find(Message.class,messageId);
//       selectedMessage.setFlaggedAsDeleted(true);
//
//       Message returnMessage = new Message(selectedMessage);
//       entityManager.merge(selectedMessage);
    }

    /**
     * Req: AdminAuth
     * Delete Message from database
     */
    public void deleteMessageFromCb(Long messageId) {
        messageRepository.deleteById(messageId);
    }
}
