package application.controller;

import application.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class MessageController {
    //Variables
    private MessageService messageService;

    //Constructor
    @Autowired
    public MessageController(MessageService messageService){
        this.messageService = messageService;
    }

    //Getter & Setter
    public MessageService getMessageService() {
        return messageService;
    }
    public void setMessageService(MessageService messageService) {
        this.messageService = messageService;
    }

    /**
     * Show all my messages
     */
    @RequestMapping(value = "/messages", method = RequestMethod.GET)
    public String showMessages(Model model) {
        model.addAttribute("messageList", messageService.getMessageList());
        return "messages";
    }

    /**
     * Show selected message
     */
    @RequestMapping(value = "/messages/{}", method = RequestMethod.GET)
    public String showMessageDetails() {
        return null;
    }

}
