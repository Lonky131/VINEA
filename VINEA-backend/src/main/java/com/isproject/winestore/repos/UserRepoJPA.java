package com.isproject.winestore.repos;

import com.isproject.winestore.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepoJPA extends JpaRepository<User, Long> {
}
