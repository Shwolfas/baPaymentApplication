package com.bapayment.controllers;

import com.bapayment.service.QueryService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class QueryController {

    private final QueryService queryService;

    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }


    @GetMapping("/search/getById/{id}")
    public ResponseEntity<String> getById(@PathVariable Long id) {
        return queryService.getById(id);
    }

    @GetMapping("/search/getAllValid")
    public ResponseEntity<String> getAllValid(){
        return queryService.getAllValid();
    }

    @GetMapping("/search/getAllValid/amount/{amount}")
    public ResponseEntity<String> getAllValidByAmount(@PathVariable String amount){
        return queryService.getAllValidByAmount(amount);
    }

}
