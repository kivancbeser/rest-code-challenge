package com.practise.restcodechallenge.model;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionModel {

    private Integer walletId;
    private double balance;
    List<TransactionModel> transactions;
}
