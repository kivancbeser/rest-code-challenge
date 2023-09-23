package com.pixelbet.restcodechallenge.model;

import java.time.LocalDate;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionModel {

    private Integer id;
    private String type;
    private LocalDate date;

    @Min(value = 0, message = "Amount Should be greater than zero")
    private double amount;

    @NotNull(message = "Description should not be null")
    private String description;
}