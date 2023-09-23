package com.practise.restcodechallenge.repository;

import com.practise.restcodechallenge.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {

}
