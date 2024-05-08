package com.teetech.teetech.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.teetech.teetech.User;
import com.teetech.teetech.service.UserService;

//@RestController
@RequestMapping("/api/users")
public class UserController {

  @Autowired
  private UserService userService;

  // CREATE
  @PostMapping
  public ResponseEntity<User> createUser(@RequestBody User user) {
      User savedUser = userService.saveUser(user);
      return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
  }

  // READ all
  @GetMapping
  public List<User> getAllUsers() {
      return userService.findAllUsers();
  }

  // READ one
  @GetMapping("/{id}")
  public ResponseEntity<User> getUserById(@PathVariable Long id) {
      User user = userService.findUserById(id);
      return new ResponseEntity<>(user, user != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
  }

  // UPDATE
  @PutMapping("/{id}")
  public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
      User updatedUser = userService.updateUser(id, userDetails);
      return new ResponseEntity<>(updatedUser, HttpStatus.OK);
  }

  // DELETE
  @DeleteMapping("/{id}")
  public ResponseEntity<HttpStatus> deleteUser(@PathVariable Long id) {
      boolean deleted = userService.deleteUser(id);
      return new ResponseEntity<>(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
  }
}
