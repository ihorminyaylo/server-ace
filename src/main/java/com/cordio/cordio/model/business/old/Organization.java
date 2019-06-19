package com.cordio.cordio.model.business.old;

import com.cordio.cordio.model.AbstractEntity;


public class Organization extends AbstractEntity {
    private String name;
    private Boolean isHMO;
    private Boolean isBlocked;
    private Organization organization;
}
