package com.cordio.cordio.model.business.old;

import com.cordio.cordio.model.AbstractEntity;
import com.cordio.cordio.model.security.User;

import java.time.LocalDate;

public class Patient extends AbstractEntity {
    private LocalDate firstRecDate;
    private LocalDate lastRecDate;
    private Boolean inclInReport;
    private LocalDate notIncludeSince;
    private User user;
    //organization, language
}
