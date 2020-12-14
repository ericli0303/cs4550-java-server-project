package com.example.myapp.repositories;

import com.example.myapp.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<User, Integer> {
    @Query(value = "SELECT * FROM users where username=:username limit 1", nativeQuery = true)
    public List<User> findUserByUsername(
            @Param("username") String username);

    @Query(value = "SELECT * FROM users where user_type=:type", nativeQuery = true)
    public List<User> findUserByType(
            @Param("type") String type);
}
