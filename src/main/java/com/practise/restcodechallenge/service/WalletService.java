package com.practise.restcodechallenge.service;

import java.time.LocalDate;
import java.util.List;
import com.practise.restcodechallenge.converter.ModelEntityConverter;
import com.practise.restcodechallenge.entity.CustomerEntity;
import com.practise.restcodechallenge.entity.TransactionEntity;
import com.practise.restcodechallenge.entity.WalletEntity;
import com.practise.restcodechallenge.enums.TransactionType;
import com.practise.restcodechallenge.exception.BadRequestFoundException;
import com.practise.restcodechallenge.model.TransactionModel;
import com.practise.restcodechallenge.model.WalletTransactionModel;
import com.practise.restcodechallenge.repository.CustomerRepository;
import com.practise.restcodechallenge.repository.WalletRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
public class WalletService {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private ModelEntityConverter modelEntityConverter;

    @Autowired
    private WalletRepository walletRepository;

    @Autowired
    private CustomerRepository customerRepository;

    /**
     *
     * @param customerId
     * @return
     */
    public WalletTransactionModel getTotalWalletAmount(Integer customerId) {
        CustomerEntity userEntity = customerService.getCustomerEntity(customerId);
        return modelEntityConverter.convert(userEntity.getWallet(), WalletTransactionModel.class);
    }

    /**
     * @param customerId
     * @param transactionModel
     * @return
     */
    public WalletTransactionModel addAmountToWallet(Integer customerId,
        TransactionModel transactionModel) {
        CustomerEntity userEntity = customerService.getCustomerEntity(customerId);
        WalletEntity walletEntity = userEntity.getWallet();

        createCreditTransactionInWallet(transactionModel, walletEntity);
        walletRepository.save(walletEntity);

        CustomerEntity saveCustomerEntity = customerRepository.save(userEntity);
        log.info("User Id: " + userEntity.getId() + " addAmountToWallet is success");
        return modelEntityConverter.convert(saveCustomerEntity.getWallet(),
            WalletTransactionModel.class);
    }

    public WalletTransactionModel deductAmountFromWallet(Integer customerId,
        TransactionModel transactionModel) {
        CustomerEntity userEntity = customerService.getCustomerEntity(customerId);
        WalletEntity walletEntity = userEntity.getWallet();

        createDebitTransactionInWallet(transactionModel, walletEntity);
        walletRepository.save(walletEntity);

        CustomerEntity saveCustomerEntity = customerRepository.save(userEntity);
        log.info("Wallet Id: " + walletEntity.getId() + " deductAmountFromWallet is success");
        return modelEntityConverter.convert(saveCustomerEntity.getWallet(),
            WalletTransactionModel.class);
    }

    /**
     * @param transactionModel
     * @param walletEntity
     */
    private void createCreditTransactionInWallet(TransactionModel transactionModel,
        WalletEntity walletEntity) {
        TransactionEntity transactionEntity = TransactionEntity.builder()
            .date(LocalDate.now())
            .type(TransactionType.CREDIT.name())
            .description(transactionModel.getDescription())
            .amount(transactionModel.getAmount())
            .wallet(walletEntity)
            .build();

        double balance = walletEntity.getBalance();
        double newBalance = balance + transactionEntity.getAmount();

        walletEntity.setBalance(newBalance);
        walletEntity.getTransactions().add(transactionEntity);
    }

    /**
     * @param transactionModel
     * @param walletEntity
     */
    private void createDebitTransactionInWallet(TransactionModel transactionModel,
        WalletEntity walletEntity) {
        double balance = walletEntity.getBalance();
        if (balance <= 0 || balance < transactionModel.getAmount()) {
            throw new BadRequestFoundException("User not have sufficient amount in his wallet. "
                + "Kindly try again later.");
        }

        TransactionEntity transactionEntity = TransactionEntity.builder()
            .date(LocalDate.now())
            .type(TransactionType.DEBIT.name())
            .description(transactionModel.getDescription())
            .amount(transactionModel.getAmount())
            .wallet(walletEntity)
            .build();

        double newBalance = balance - transactionEntity.getAmount();
        walletEntity.setBalance(newBalance);

        walletEntity.getTransactions().add(transactionEntity);
    }

    /**
     * @param userId
     * @return
     */
    public List<TransactionModel> getAllTransactionByCustomerId(Integer userId) {
        CustomerEntity userEntity = customerService.getCustomerEntity(userId);
        List<TransactionEntity> transactionEntities = userEntity.getWallet().getTransactions();
        return modelEntityConverter.mapList(transactionEntities, TransactionModel.class);
    }
}
