package com.bapayment.controllers;

import com.bapayment.entities.BasePayment;
import com.bapayment.repositories.PaymentRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class BaPaymentController {
    @RequestMapping("/")
    public String index(){
        return "index.html";
    }
}
