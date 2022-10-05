package ru.rsreu.photostudio.models;

import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import lombok.Data;
import org.hibernate.validator.constraints.CreditCardNumber;

@Data
public class Order {

    @NotBlank(message="Name is required")
    private String name;

    @CreditCardNumber(message="Not a valid credit card number")
    private String ccNumber;

    @Pattern(regexp="^(0?[1-9]|[12][0-9]|3[01])([\\/])(0[1-9]|1[0-2])([\\/])([1-9][0-9])$",
            message="Must be formatted DD/MM/YY")
    private String ccExpiration;

    @Digits(integer=3, fraction=0, message="Invalid CVV")
    private String ccCVV;
}
