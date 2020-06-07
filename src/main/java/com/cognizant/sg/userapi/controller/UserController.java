package com.cognizant.sg.userapi.controller;

import com.cognizant.sg.userapi.entity.User;
import com.cognizant.sg.userapi.model.UserResponse;
import com.cognizant.sg.userapi.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

/**
 * API for Users
 *
 * @author Adrian Alegre
 */
@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {

    private final UserService userService;

    /**
     * Constructor Injection
     * @param userService
     */
    UserController(final UserService userService) {
        this.userService = userService;
    }

    /**
     * Get All Users
     * @return ResponseEntity<UserResponse>
     */
    @GetMapping
    public ResponseEntity<UserResponse> getUsers() {
        List<User> userList = this.userService.findAll();
        return new ResponseEntity<UserResponse>(new UserResponse(userList), HttpStatus.OK);
    }

    /**
     *  Get All Users within the salary range
     * @param fromSalary
     * @param toSalary
     * @return ResponseEntity<UserResponse>
     */
    @GetMapping("/salary")
    public ResponseEntity<UserResponse> getUsersBySalary(final @RequestParam("from") BigDecimal fromSalary,
                                                         final @RequestParam("to") BigDecimal toSalary) {

        List<User> userList = userService.findBySalary(fromSalary, toSalary);
        return new ResponseEntity<UserResponse>(new UserResponse(userList), HttpStatus.OK);
    }

    /**
     *  Get User By Id
     * @param id
     * @return ResponseEntity<User>
     */
    @GetMapping(value = "/{id}")
    public ResponseEntity<User> getUser(final @PathVariable("id") long id){
        User user = userService.findById(id);
        return new ResponseEntity<User>(user, HttpStatus.OK);

    }
}
