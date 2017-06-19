package com.Forum.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * Created by Tomal on 2017-05-13.
 */
@Controller
public class WebController {

    @RequestMapping(value={"/","home"})
    public String home(){
        return "home";
    }

    @RequestMapping(value="/403")
    public String Error403(){
        return "403";
    }


}