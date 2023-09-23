package com.practise.restcodechallenge.controller;


import com.practise.restcodechallenge.model.Response;
import com.practise.restcodechallenge.model.TransactionModel;
import com.practise.restcodechallenge.service.WalletService;
import io.swagger.annotations.Api;
import jakarta.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;

    /**
     * @param userId
     * @return
     */
    @GetMapping("/{userId}")
    public Response getTotalWalletAmountByUserId(@PathVariable Integer userId) {
        return Response.ok().setPayload(walletService.getTotalWalletAmount(userId));
    }

    /**
     * @param userId
     * @return
     */
    @GetMapping("/all-transactions/{userId}")
    public Response getAllTransactionByUserId(@PathVariable Integer userId) {
        return Response.ok().setPayload(walletService.getAllTransactionByUserId(userId));
    }

    /**
     * @param userId
     * @param transactionModel
     * @return
     */
    @PostMapping("/add/{userId}")
    public Response addAmountToWallet(@PathVariable Integer userId,
        @Valid @RequestBody TransactionModel transactionModel) {
        return Response.ok().setPayload(walletService.addAmountToWallet(userId, transactionModel));
    }

    /**
     * @param userId
     * @param transactionModel
     * @return
     */
    @PostMapping("/deduct/{userId}")
    public Response deductAmountFromWallet(@PathVariable Integer userId,
        @Valid @RequestBody TransactionModel transactionModel) {
        return Response.ok()
            .setPayload(walletService.deductAmountFromWallet(userId, transactionModel));
    }

}
