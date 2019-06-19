package com.cordio.cordio.model.business.old;

import com.cordio.cordio.model.AbstractEntity;

import java.time.LocalDate;

public class StatisticOrganization extends AbstractEntity {
    private LocalDate date;
    private Integer uploadCount;
    private Integer notUploadCount;
    private Integer notIncludedInReport;
    // organization
}
