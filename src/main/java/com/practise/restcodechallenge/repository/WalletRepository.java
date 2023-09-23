package com.pixelbet.restcodechallenge.repository;

import com.pixelbet.restcodechallenge.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WalletRepository extends JpaRepository<WalletEntity, Integer> {

}
