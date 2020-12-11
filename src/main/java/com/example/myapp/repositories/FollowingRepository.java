package com.example.myapp.repositories;

import com.example.myapp.models.Following;
import com.example.myapp.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowingRepository extends CrudRepository<Following, Integer> {
    @Query(value = "SELECT * FROM following where follower_id=:follower", nativeQuery = true)
    public List<Following> findFollowingsByFollower(
            @Param("follower") int follower);

    @Query(value = "SELECT * FROM following where creator_id=:creator", nativeQuery = true)
    public List<Following> findFollowersByCreator(
            @Param("creator") int creator);

    @Query(value = "SELECT * FROM following where creator_id=:creator order by time desc", nativeQuery = true)
    public List<Following> findRecentFollowersForCreator(
            @Param("creator") int creator);

}
