package ru.kata.spring.boot_security.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.model.User;
import ru.kata.spring.boot_security.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("rest/admin")
public class AdminRestController {

    private final UserService userService;

    @Autowired
    public AdminRestController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/allPage")
    public ResponseEntity<List<User>> getAllUserRest() {
        List<User> listOfUsers = userService.getAllUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }
//
//    @GetMapping("/users/{id}")
//    public ResponseEntity<User> getUserByIdRest(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(userService.getUserById(id), HttpStatus.OK);
//    }
//
//    @PostMapping("/new")
//    public ResponseEntity<Void> createUserRest(@RequestBody User user) {
//        userService.saveUser(user);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @DeleteMapping("/users/{id}")
//    public ResponseEntity<Void> deleteUserRest(@PathVariable("id") Long id) {
//        userService.deleteUserById(id);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @PutMapping("/users/{id}")
//    public ResponseEntity<Void> updateUserRest(@PathVariable("id") Long id, @RequestBody User user) {
//        userService.updateUser(id, user);
//        return new ResponseEntity<>(HttpStatus.OK);
//    }
//
//    @GetMapping("/personalPage")
//    public ResponseEntity<User> showAdminPersonalPage(Principal principal) {
//        User user = userService.getUserByUsername(principal.getName());
//        return new ResponseEntity<>(user, HttpStatus.OK);
//    }


    @GetMapping("/personalPage")
    public ResponseEntity<User> showAdminPersonalPage(Principal principal) {
        User user = userService.getUserByUsername(principal.getName());
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @GetMapping("/generalPage")
    public ResponseEntity<List<User>> showAdminGeneralPage() {
        List<User> listOfUsers = userService.getAllUsers();
        return new ResponseEntity<>(listOfUsers, HttpStatus.OK);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> showUser(@PathVariable("id") Long id) {
        User user = userService.getUserById(id);
        return new ResponseEntity<>(user, HttpStatus.OK);
    }

    @PostMapping("/new")
    public ResponseEntity<HttpStatus> addUser(@RequestBody User user) {
        userService.saveUser(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PutMapping("/users/{id}")
    public ResponseEntity<HttpStatus> editUser(@RequestBody User user, @PathVariable("id") Long id) {
        userService.updateUser(id, user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<HttpStatus> deleteUser(@PathVariable("id") Long id) {
        userService.deleteUserById(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
