package com.example.currencyexchangeapp.repository;

import com.example.currencyexchangeapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    @Query(
            value = "SELECT * FROM USERS u WHERE u.email IS EMPTY",
            nativeQuery = true)
    Collection<User> findAllActiveUsersNative();

    //todo zapoznać się z java persistence query langue!!
}
