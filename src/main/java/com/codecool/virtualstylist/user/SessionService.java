package com.codecool.virtualstylist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

public class SessionService {

    private SessionDataAccess sessionDataAccess;

    @Autowired
    public SessionService(@Qualifier("sessionPostgresAccess") SessionDataAccess sessionDataAccess){
        this.sessionDataAccess = sessionDataAccess;
    }

    public void saveSession(Session session){
        sessionDataAccess.save(session);
    }
}
