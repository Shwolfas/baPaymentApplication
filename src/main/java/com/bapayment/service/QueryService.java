package com.bapayment.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
@Service
public interface QueryService {

    ResponseEntity<String> getById(Long id);

    ResponseEntity<String> getAllValid();

    ResponseEntity<String> getAllValidByAmount(String amount);

}
