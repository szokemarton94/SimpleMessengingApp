package application.controller;

import application.service.Message;
import application.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MessageController {
    //Variables
    private MessageService messageService;

    //Constructor
    @Autowired
    public MessageController(MessageService messageService) {
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
    public String showMessages(
            @RequestParam(value = "limit", name = "limit", required = false, defaultValue = "20") Integer limit,
            @RequestParam(value = "orderBy", name = "orderBy", required = false, defaultValue = "date") String orderBy,
            @RequestParam(value = "direction", name = "direction", required = false, defaultValue = "asc") String direction,
            Model model) {

        model.addAttribute("messageList", messageService.getArrangedMessageList(limit, orderBy, direction));
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
