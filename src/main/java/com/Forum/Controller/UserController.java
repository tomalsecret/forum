package com.Forum.Controller;

import com.Forum.Entity.User;
import com.Forum.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.ui.Model;


import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Collection;
import java.util.regex.Pattern;

/**
 * Created by Tomal on 2017-05-13.
 */
@RequestMapping("/user")
@Controller
public class UserController {


    @Autowired
    private UserService userService;

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String addUserPanel() {
        return "register";
    }


    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String addUser(@RequestParam(value = "username", defaultValue = " ") String username,
                          @RequestParam(value = "password", defaultValue = " ") String pass) {


        String redirectUrlError = "/user/register";
        String redirectUrlSuccess = "/user/login";



        Pattern p = Pattern.compile("[a-zA-Z0-9]*");
        if (!p.matcher(username).matches()) return "redirect:" + redirectUrlError;
        if (!p.matcher(pass).matches()) return "redirect:" + redirectUrlError;


        userService.addUser(username, pass);
        return "redirect:" + redirectUrlSuccess;

    }


    @RequestMapping(value = {"/login"})
    public String login() {
        return "login";
    }

    @RequestMapping(value = "/admin")
    public String admin() {
        return "admin";
    }


    @RequestMapping(value = "/listall", method = RequestMethod.GET)
    public String getAllUsers(Model model) {
        model.addAttribute("allusers", userService.getAllUsers());
        return "listall";
    }

    @RequestMapping(value = "/admin/disable", method = RequestMethod.GET)
    public String disableUser() {
        return "disable_user";
    }

    @RequestMapping(value = "/admin/enable", method = RequestMethod.GET)
    public String enableUser() {
        return "enable_user";
    }

    @RequestMapping(value = "/admin/disable", method = RequestMethod.POST)
    public String disableUserByName(@RequestParam(value = "user_name", defaultValue = "") String user_name) {
        if (!(user_name.equals("admin"))) userService.disableUserByName(user_name);
        String redirectUrl = "/user/listall/";
        return "redirect:" + redirectUrl;
    }

    @RequestMapping(value = "/admin/enable", method = RequestMethod.POST)
    public String enableUserByName(@RequestParam(value = "user_name", defaultValue = "") String user_name) {
        if (!(user_name.equals("admin"))) userService.enableUserByName(user_name);
        String redirectUrl = "/user/listall/";
        return "redirect:" + redirectUrl;
    }


    @RequestMapping(value = "/my_profile", method = RequestMethod.GET)
    public String myProfile(Model model) {

        String user_name = userService.getUserName();

        model.addAttribute("user_name", user_name);

        return "my_profile";


    }

    @RequestMapping(value = "/user_profile/{user_name}", method = RequestMethod.GET)
    public String userProfile(@PathVariable("user_name") String target_name, Model model) {

        String user_name = userService.getUserName();

        if (userService.checkIfUserExists(target_name)) {

            model.addAttribute("user_name", user_name);
            model.addAttribute("target_name", target_name);
            return "user_profile";
        } else return "error";

    }




}
