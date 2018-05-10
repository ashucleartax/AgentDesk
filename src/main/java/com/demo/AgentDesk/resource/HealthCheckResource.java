package com.demo.AgentDesk.resource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by ashutoshpandey on 10/05/18.
 */
@RestController
@Slf4j
public class HealthCheckResource {

    @RequestMapping(value = "/ping", method = RequestMethod.GET)
    public ResponseEntity<String> ping(){
        log.info("NOW YOU SEE ME");
        return new ResponseEntity<String>("pong",HttpStatus.OK);
    }

}
