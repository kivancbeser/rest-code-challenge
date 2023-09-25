package com.practise.restcodechallenge.controller;

import com.practise.restcodechallenge.model.CustomerModel;
import com.practise.restcodechallenge.model.Response;
import com.practise.restcodechallenge.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @GetMapping("/get-all")
    public Response getAllCustomers() {
        return Response.ok().setPayload(customerService.getAllCustomers());
    }

    @GetMapping("/{id}")
    public Response getCustomerById(@PathVariable Integer id) {
        return Response.ok().setPayload(customerService.getCustomerById(id));
    }

    @PostMapping
    public Response createNewCustomer(@Valid @RequestBody CustomerModel customerModel) {
        return Response.ok().setPayload(customerService.createNewCustomer(customerModel));
    }

    @PutMapping("/{id}")
    public Response updateCustomer(@Valid @RequestBody CustomerModel customerModel,
                                   @PathVariable Integer id) {
        return Response.ok().setPayload(customerService.updateCustomer(id, customerModel));
    }
}
