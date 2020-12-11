package com.example.myapp.repositories;

import java.util.List;

import com.example.myapp.models.Widget;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;


public interface WidgetRepository extends CrudRepository<Widget, Integer> {

    @Query(value = "SELECT * FROM widgets where topic_id=:tid", nativeQuery = true)
    public List<Widget> findWidgetsByTopicId(
        @Param("tid") String topicId);
}
