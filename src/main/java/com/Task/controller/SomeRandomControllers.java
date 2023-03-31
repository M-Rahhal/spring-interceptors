package com.Task.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SomeRandomControllers {

    @RequestMapping(value = "/endpoint/first")
    public ResponseEntity<Object> firstEndpoint() {
        return new ResponseEntity<>("Some random data from the first endpoint!", HttpStatus.OK);
    }
    @RequestMapping(value = "/endpoint/second")
    public ResponseEntity<Object> secondEndpoint() {
        return new ResponseEntity<>("Some random data from the second endpoint!", HttpStatus.OK);
    }
    @RequestMapping(value = "/endpoint/third")
    public ResponseEntity<Object> thirdEndpoint() {
        return new ResponseEntity<>("Some random data from the third endpoint!", HttpStatus.OK);
    }

}
