package com.nalepka.controller;

import com.nalepka.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    UserServiceImpl userService;

    @RequestMapping(value = "create/{email}", method = RequestMethod.POST)
    public ResponseEntity<Long> createUser(@PathVariable("email") String email, @RequestBody String password){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(userService.addUser(email, password), headers, HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "login/{email}", method = RequestMethod.POST)
    public ResponseEntity<Long> loginUser(@PathVariable("email") String email, @RequestBody String password){
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type", "application/json; charset=utf-8");
        return new ResponseEntity<>(userService.validateCredentials(email, password), headers, HttpStatus.ACCEPTED);
    }
}
