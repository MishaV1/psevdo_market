package com.example.magazine.mishas_market;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/market")
public class MishasMarketController {

    @GetMapping()
    String getMainPage(){
        return "main";
    }
}

