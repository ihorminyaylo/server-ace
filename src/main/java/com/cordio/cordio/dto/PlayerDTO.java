package com.cordio.cordio.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class PlayerDTO {

    private String id;

    private String firstName;

    private String lastName;

    private String patronymic;

    private String town;

    private String telephoneNumber;

    private String birthDate;

    private Integer rating;
}
