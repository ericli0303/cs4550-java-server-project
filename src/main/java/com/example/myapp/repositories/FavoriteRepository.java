package com.example.myapp.repositories;

import com.example.myapp.models.Favorite;
import com.example.myapp.models.Following;
import com.example.myapp.models.User;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FavoriteRepository extends CrudRepository<Favorite, Integer> {
    @Query(value = "SELECT * FROM favorites where follower_id=:followerId", nativeQuery = true)
    public List<Favorite> findFavoritesByFollowerId(
            @Param("followerId") int followerId);

    @Query(value = "SELECT * FROM favorites where follower_id=:followerId order by time desc", nativeQuery = true)
    public List<Favorite> findRecentFavoritesByFollowerId(
            @Param("followerId") int followerId);
}
