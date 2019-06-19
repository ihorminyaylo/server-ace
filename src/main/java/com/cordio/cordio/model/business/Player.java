package com.cordio.cordio.model.business;

import com.cordio.cordio.model.AbstractEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor


@Entity
@Table
public class Player extends AbstractEntity {

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String patronymic;

    @Column
    private String town;

    @Column
    private String telephoneNumber;

    @Column
    private LocalDate birthDate;

    @Column
    private Integer rating = 1200;

    //private List<Game> games;

}
