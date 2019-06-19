package com.cordio.cordio.model.business.old;

import com.cordio.cordio.model.AbstractEntity;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class SessionInfo extends AbstractEntity {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private String appVersion;
    private String model;
    private String os;
    private Double weight;
    private Integer satu;
    private Integer pulse;
    private Boolean hasErrors;
    //organization, patient
}
