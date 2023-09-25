package com.practise.restcodechallenge.controller;

import com.practise.restcodechallenge.model.Response;
import com.practise.restcodechallenge.model.TransactionModel;
import com.practise.restcodechallenge.service.WalletService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/wallet")
public class WalletController {

    @Autowired
    WalletService walletService;
    
    @GetMapping("/{customerId}")
    public Response getTotalWalletAmountByCustomerId(@PathVariable Integer customerId) {
        return Response.ok().setPayload(walletService.getTotalWalletAmount(customerId));
    }

    /**
     * @param customerId
     * @return
     */
    @GetMapping("/all-transactions/{customerId}")
    public Response getAllTransactionByCustomerId(@PathVariable Integer customerId) {
        return Response.ok().setPayload(walletService.getAllTransactionByCustomerId(customerId));
    }

    /**
     * @param customerId
     * @param transactionModel
     * @return
     */
    @PostMapping("/add/{customerId}")
    public Response addAmountToWallet(@PathVariable Integer customerId,
        @Valid @RequestBody TransactionModel transactionModel) {
        return Response.ok().setPayload(walletService.addAmountToWallet(customerId, transactionModel));
    }

    /**
     * @param customerId
     * @param transactionModel
     * @return
     */
    @PostMapping("/deduct/{customerId}")
    public Response deductAmountFromWallet(@PathVariable Integer customerId,
        @Valid @RequestBody TransactionModel transactionModel) {
        return Response.ok()
            .setPayload(walletService.deductAmountFromWallet(customerId, transactionModel));
    }

}
