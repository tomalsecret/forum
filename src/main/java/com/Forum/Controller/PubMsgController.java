package com.Forum.Controller;

import com.Forum.Service.PubMsgService;
import com.Forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Created by Tomal on 2017-05-14.
 */
@RequestMapping("/forum")
@Controller
public class PubMsgController {

    @Autowired
    private PubMsgService pubMsgService;

    @Autowired
    private UserService userService;

    @RequestMapping("/")
    public String showPosts(@RequestParam(value = "sort_by", defaultValue = "date") String sort_by,
                            @RequestParam(value = "category", defaultValue = "wszystkie") String category,
                            Model model) {


        model.addAttribute("posts", pubMsgService.showPosts(sort_by, category));

        return "forum";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String showAnswers(@PathVariable("id") int id, Model model) {

        model.addAttribute("post", pubMsgService.showSinglePost(id));
        model.addAttribute("answers", pubMsgService.showAnswers(id));

        String user_name = userService.getUserName();

        model.addAttribute("if_liked", pubMsgService.showIfLiked(id, user_name));


        return "answers";
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String addAnswer(@PathVariable("id") int id,
                            @RequestParam(value = "content", defaultValue = " ") String answer, Model model) {

        String user_name = userService.getUserName();

        if (!(answer.equals(" "))) pubMsgService.addAnswer(id, user_name, answer);


        String redirectUrl = "{id}";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/like", method = RequestMethod.POST)
    public String addLike(@RequestParam(value = "id", defaultValue = "0") int id) {

        String user_name = userService.getUserName();


        if (!(id == 0)) pubMsgService.addLike(id, user_name);


        String redirectUrl = "/forum/";
        return "redirect:" + redirectUrl + id;
    }


    @RequestMapping(value = "/add_pub_msg/", method = RequestMethod.GET)
    public String add_pub_msg() {


        return "add_pub_msg";
    }

    @RequestMapping(value = "/add_pub_msg/", method = RequestMethod.POST)
    public String add_pub_msg(@RequestParam(value = "content", defaultValue = " ") String content,
                              @RequestParam(value = "category", defaultValue = "Ogolny") String category,
                              @RequestParam(value = "topic", defaultValue = " ") String topic) {

        String user_name = userService.getUserName();

        if (!(content.equals(" ")) && !(topic.equals(" ")))
            pubMsgService.addPubMsg(category, user_name, content, topic);

        String redirectUrl = "/forum/";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/delete/post", method = RequestMethod.POST)
    public String deletePublicMessage(@RequestParam(value = "id", defaultValue = " ") int id) {

        pubMsgService.deletePublicMessage(id);

        String redirectUrl = "/forum/";
        return "redirect:" + redirectUrl;


    }

    @RequestMapping(value = "/delete/answer", method = RequestMethod.POST)
    public String deleteAnswer(@RequestParam(value = "answer_id", defaultValue = " ") int answer_id,
                               @RequestParam(value = "public_message_id", defaultValue = " ") int public_message_id) {

        pubMsgService.deleteAnswer(answer_id);

        String redirectUrl = "/forum/";
        return "redirect:" + redirectUrl + public_message_id;


    }


    @Scheduled(fixedRate = 60000)
    public void deleteDislikedPosts() {

        pubMsgService.deleteDislikedPosts();

    }


}
