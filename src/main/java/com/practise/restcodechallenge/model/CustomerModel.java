package com.pixelbet.restcodechallenge.model;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CustomerModel {

    private Integer customerId;

    @Pattern(regexp = "[0-9]{10}", message = "Mobile No is Invalid")
    private String mobileNumber;

    @NotNull(message = "Name should not be null")
    private String name;

    @Email(message = "Email format is wrong")
    @NotNull(message = "Email should not be null")
    private String email;
    private WalletModel wallet;
}
