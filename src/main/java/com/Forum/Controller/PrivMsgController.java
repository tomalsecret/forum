package com.Forum.Controller;

import com.Forum.Entity.PrivMsg;
import com.Forum.Service.PrivMsgService;
import com.Forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Collection;
import java.util.List;

/**
 * Created by Tomal on 2017-05-19.
 */
@RequestMapping("/private_messages")
@Controller
public class PrivMsgController {

    @Autowired
    private UserService userService;

    @Autowired
    private PrivMsgService privMsgService;


    @RequestMapping("/")
    public String showReceivedPrivateMessages(Model model) {

        String user_name = userService.getUserName();

        Collection<PrivMsg> receivedMessages = privMsgService.showReceivedPrivateMessages(user_name);
        model.addAttribute("receivedMessages", receivedMessages);


        return "private_messages";
    }

    @RequestMapping("/sent")
    public String showSentPrivateMessages(Model model) {

        String user_name = userService.getUserName();

        model.addAttribute("sentMessages", privMsgService.showSentPrivateMessages(user_name));


        return "sent_private_messages";
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public String sendPrivateMessage(@RequestParam(value = "user", defaultValue = " ") String target_name, Model model) {

        String user_name = userService.getUserName();

        if (userService.checkIfUserExists(target_name)) {
            model.addAttribute("target_name", target_name);
            model.addAttribute("user_name", user_name);

            return "send_private_message";
        } else return "error";
    }

    @RequestMapping(value = "/send", method = RequestMethod.POST)
    public String sendPrivateMessage(@RequestParam(value = "topic", defaultValue = " ") String topic,
                                     @RequestParam(value = "content", defaultValue = " ") String content,
                                     @RequestParam(value = "user", defaultValue = " ") String target_name,
                                     @RequestParam(value = "key", defaultValue = "0") String keyString) {

        String user_name = userService.getUserName();



        int key;
        try {
            key = Integer.parseInt(keyString);
        } catch (Exception e) {
            String redirectUrl = "/private_messages/";
            return "redirect:" + redirectUrl;
        }
        StringBuilder ciphertext = new StringBuilder(content);

        for (int i = 0; i < ciphertext.length(); i++) {
            char c = ciphertext.charAt(i);
            ciphertext.setCharAt(i, (char) (c + key));
        }

        String tekst = ciphertext.toString();

        privMsgService.sendPrivateMessage(user_name, target_name, topic, tekst);


        String redirectUrl = "/private_messages/";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showEntireMessage(@PathVariable("id") int id, Model model) {

        String user_name = userService.getUserName();

        int check;
        check = privMsgService.checkIfMessageBelongsToUser(id, user_name);

        if (check == 0) return "403";


        Collection<PrivMsg> message = privMsgService.showPrivateMessage(id);

        model.addAttribute("private", message);

        return "private_message";


    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String decypherPOST(@RequestParam(value = "content", defaultValue = " ") String cypheredtext,
                               @RequestParam(value = "key", defaultValue = "0") String keyString,
                               @RequestParam(value = "id", defaultValue = "0") int id, Model model) {


        String user_name = userService.getUserName();

        int check;
        check = privMsgService.checkIfMessageBelongsToUser(id, user_name);

        if (check == 0) return "403";


        Collection<PrivMsg> message = privMsgService.showPrivateMessage(id);

        model.addAttribute("private", message);

        int key;
        try {
            key = Integer.parseInt(keyString);
        } catch (Exception e) {
            return "cipher";
        }
        StringBuilder deciphertext = new StringBuilder(cypheredtext);


        for (int i = 0; i < deciphertext.length(); i++)

        {
            char c = deciphertext.charAt(i);
            deciphertext.setCharAt(i, (char) (c - key));
        }

        model.addAttribute("decypher", deciphertext);

        return "private_message";
    }
}
