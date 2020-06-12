package com.codecool.virtualstylist.stylization;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StylizationService {

    private final StylizationDataAccess stylizationDataAccess;

    @Autowired
    public StylizationService(StylizationDataAccess stylizationDataAccess) {
        this.stylizationDataAccess = stylizationDataAccess;
    }
}
