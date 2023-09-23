package com.practise.restcodechallenge.controller;

import com.practise.restcodechallenge.model.CustomerModel;
import com.practise.restcodechallenge.model.Response;
import com.practise.restcodechallenge.service.CustomerService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/user")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/get-all")
    public Response getAllUsers() {
        return Response.ok().setPayload(customerService.getAllUsers());
    }

    @GetMapping("/{userId}")
    public Response getUserById(@PathVariable Integer userId) {
        return Response.ok().setPayload(customerService.getUserById(userId));
    }

    @PostMapping
    public Response createNewUser(@Valid @RequestBody CustomerModel customerModel) {
        return Response.ok().setPayload(customerService.createNewUser(customerModel));
    }

    @PutMapping("/{userId}")
    public Response updateUser(@Valid @RequestBody CustomerModel customerModel,
        @PathVariable Integer userId) {
        return Response.ok().setPayload(customerService.updateUser(userId, customerModel));
    }
}
