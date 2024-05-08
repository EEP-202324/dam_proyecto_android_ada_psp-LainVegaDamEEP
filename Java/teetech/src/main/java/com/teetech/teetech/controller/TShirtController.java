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
import org.springframework.web.bind.annotation.RestController;

import com.teetech.teetech.TShirt;
import com.teetech.teetech.service.TShirtService;

@RestController
@RequestMapping("/tshirts")
public class TShirtController {

    @Autowired
    private TShirtService tShirtService;

    // CREATE
    @PostMapping
    public ResponseEntity<TShirt> createTShirt(@RequestBody TShirt tShirt) {
        TShirt savedTShirt = tShirtService.saveTShirt(tShirt);
        return new ResponseEntity<>(savedTShirt, HttpStatus.CREATED);
    }

    // READ all
    @GetMapping
    public List<TShirt> getAllTShirts() {
        return tShirtService.findAllTShirts();
    }

    // READ one
    @GetMapping("/{id}")
    public ResponseEntity<TShirt> getTShirtById(@PathVariable Long id) {
        TShirt tShirt = tShirtService.findTShirtById(id);
        return new ResponseEntity<>(tShirt, tShirt != null ? HttpStatus.OK : HttpStatus.NOT_FOUND);
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<TShirt> updateTShirt(@PathVariable Long id, @RequestBody TShirt tShirtDetails) {
        TShirt updatedTShirt = tShirtService.updateTShirt(id, tShirtDetails);
        return new ResponseEntity<>(updatedTShirt, HttpStatus.OK);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteTShirt(@PathVariable Long id) {
        boolean deleted = tShirtService.deleteTShirt(id);
        return new ResponseEntity<>(deleted ? HttpStatus.NO_CONTENT : HttpStatus.NOT_FOUND);
    }
}