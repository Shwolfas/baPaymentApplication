package com.bapayment.controllers;

import com.bapayment.entities.BasePayment;
import com.bapayment.handlers.Type1PaymentHandler;
import com.bapayment.handlers.Type2PaymentHandler;
import com.bapayment.handlers.Type3PaymentHandler;
import com.bapayment.repositories.PaymentHandler;
import org.apache.catalina.connector.Response;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class TransactionController {

    private final Map<String, PaymentHandler> handlers;

    public TransactionController(List<PaymentHandler> handlerList){
        handlers = new HashMap<>();
        for (PaymentHandler handler : handlerList) {
            handlers.put(handler.getType().toString(), handler);
        }
    }

    @PostMapping("/save")
    public int save (@RequestBody BasePayment payment){
        PaymentHandler handler = handlers.get(payment.getType().toString());
        handler.save(payment);
        return Response.SC_ACCEPTED;
    }
}
