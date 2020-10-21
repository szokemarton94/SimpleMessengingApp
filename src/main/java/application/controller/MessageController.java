package application.controller;

import application.DTO.MessageDTO;
import application.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    @RequestMapping(value = "/messages/{messageId}", method = RequestMethod.GET)
    public String showMessageDetails(
        @PathVariable("messageId") Long messageId, Model model){
            model.addAttribute("message", messageService.showSelectedMessage(messageId));
            return "messageDetail";
    }

    /**
     * Show "NewMessage" page
     */
    @RequestMapping(value="/messages/new", method = RequestMethod.GET)
    public String showNewMessage(
            @ModelAttribute("messageDTO") MessageDTO messageDTO
    ){
        return "newMessage";
    }

    /**
     * Add new Message to DB
     */
    @RequestMapping(value = "/messages/new/add", method = RequestMethod.POST)
    public String addNewMessageToDataBase(
            @ModelAttribute MessageDTO messageDto
    ){
        messageService.addNewMessageToDataBase(messageDto);
        return "redirect:/messages";
    }


}
