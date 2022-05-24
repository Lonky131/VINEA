package com.isproject.winestore.services;

import com.isproject.winestore.repos.UserRepoJPA;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepoJPA userRepoJPA;

    public UserService(UserRepoJPA userRepoJPA) {
        this.userRepoJPA = userRepoJPA;
    }
}
