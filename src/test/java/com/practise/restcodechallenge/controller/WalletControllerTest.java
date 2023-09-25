package com.practise.restcodechallenge.controller;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.practise.restcodechallenge.entity.CustomerEntity;
import com.practise.restcodechallenge.entity.TransactionEntity;
import com.practise.restcodechallenge.entity.WalletEntity;
import com.practise.restcodechallenge.exception.BadRequestFoundException;
import com.practise.restcodechallenge.model.TransactionModel;
import com.practise.restcodechallenge.repository.CustomerRepository;
import com.practise.restcodechallenge.repository.WalletRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class WalletControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CustomerRepository customerRepository;

    @MockBean
    private WalletRepository walletRepository;

    @Test
    void getTotalWalletAmountByUserId() throws Exception {
        setCustomerEntityWithWallet();

        this.mockMvc.perform(get("/wallet/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.payload.balance").value("111.0"));
    }

    private void setCustomerEntityWithWallet() {
        CustomerEntity customerEntity = new CustomerEntity();
        customerEntity.setId(1);
        customerEntity.setName("test");
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setId(1);
        walletEntity.setBalance(111);

        List<TransactionEntity> transactionEntities = new ArrayList<>();
        transactionEntities.add(new TransactionEntity());
        transactionEntities.add(new TransactionEntity());
        walletEntity.setTransactions(transactionEntities);
        customerEntity.setWallet(walletEntity);

        when(customerRepository.findById(anyInt())).thenReturn(Optional.of(customerEntity));
    }

    @Test
    void getAllTransactionByUserId() throws Exception {
        setCustomerEntityWithWallet();

        this.mockMvc.perform(get("/wallet/all-transactions/1"))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.payload", hasSize(2)));
    }

    @Test
    void addAmountToWallet() throws Exception {
        //initial wallet amount = 111.0
        setCustomerEntityWithWallet();

        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(1);
        expectedCustomerEntity.setName("test");
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setId(1);
        walletEntity.setBalance(121);
        expectedCustomerEntity.setWallet(walletEntity);
        when(customerRepository.save(any(CustomerEntity.class)))
                .thenReturn(expectedCustomerEntity);

        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAmount(10);
        transactionModel.setDescription("Got from friend");
        String jsonBody = new ObjectMapper().writeValueAsString(transactionModel);

        this.mockMvc.perform(post("/wallet/add/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.payload.balance").value("121.0"));
    }

    @Test
    void deductAmountFromWallet() throws Exception {
        //initial wallet amount = 111.0
        setCustomerEntityWithWallet();

        //Expected Entity after deduction of amount from wallet
        CustomerEntity expectedCustomerEntity = new CustomerEntity();
        expectedCustomerEntity.setId(1);
        expectedCustomerEntity.setName("test");
        WalletEntity walletEntity = new WalletEntity();
        walletEntity.setId(1);
        walletEntity.setBalance(12);
        expectedCustomerEntity.setWallet(walletEntity);
        when(customerRepository.save(any(CustomerEntity.class)))
                .thenReturn(expectedCustomerEntity);

        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAmount(99);
        transactionModel.setDescription("Buy Sweet Bonanza");
        String jsonBody = new ObjectMapper().writeValueAsString(transactionModel);

        this.mockMvc.perform(post("/wallet/deduct/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isOk())
                .andDo(print())
                .andExpect(jsonPath("$.payload.balance").value("12.0"));
    }

    @Test
    void testDeductAmountGreaterThanAvailableFromWallet() throws Exception {
        //initial wallet amount = 111.0
        setCustomerEntityWithWallet();

        TransactionModel transactionModel = new TransactionModel();
        transactionModel.setAmount(115);
        transactionModel.setDescription("Buy Sweet Bonanza Bonus Game");
        String jsonBody = new ObjectMapper().writeValueAsString(transactionModel);

        this.mockMvc.perform(post("/wallet/deduct/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(jsonBody))
                .andExpect(status().isBadRequest())
                .andExpect(result -> assertTrue(
                        result.getResolvedException() instanceof BadRequestFoundException));
    }
}
