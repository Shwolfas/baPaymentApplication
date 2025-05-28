package com.bapayment.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class BaPaymentController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
