package com.bapayment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Map;
@Service
public interface TransactionService {

    ResponseEntity<String> save (Map<String, Object> payload);

    ResponseEntity<String> cancel (Long id);
}
