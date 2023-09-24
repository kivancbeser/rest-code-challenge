package com.practise.restcodechallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.practise.restcodechallenge.entity.CustomerEntity;
import com.practise.restcodechallenge.entity.WalletEntity;
import com.practise.restcodechallenge.model.CustomerModel;
import com.practise.restcodechallenge.model.WalletModel;
import com.practise.restcodechallenge.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Optional;
import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository repository;

    @Test
    public void testGetUserById() throws Exception {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1);
        customerEntity.setName("test");
        when(repository.findById(anyInt())).thenReturn(Optional.of(customerEntity));

        this.mockMvc.perform(get("/user/1"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }

    @Test
    public void testCreateNewUser() throws Exception {
        CustomerModel customerModel = new CustomerModel();
        customerModel.setName("test");
        customerModel.setMobileNumber("9876543210");
        customerModel.setEmail("test@gmail.com");
        WalletModel walletModel = new WalletModel();
        walletModel.setWalletId(1);
        walletModel.setBalance(0);
        customerModel.setWallet(walletModel);


        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1);
        customerEntity.setName("test");
        customerEntity.setMobileNumber("9876543210");
        customerEntity.setEmail("test@gmail.com");
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setId(1);
        walletEntity.setBalance(0);
        walletEntity.setCustomer(customerEntity);
        customerEntity.setWallet(walletEntity);
        when(repository.save(any(CustomerEntity.class))).thenReturn(customerEntity);

        this.mockMvc.perform(post("/user")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsString(customerModel)))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("test")));
    }

}
