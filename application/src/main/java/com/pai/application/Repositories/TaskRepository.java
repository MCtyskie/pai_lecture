package com.pai.application.Repositories;

import com.pai.application.Enums.Status;
import com.pai.application.Enums.Type;
import com.pai.application.Models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {

    @Query(nativeQuery = true,value = "SELECT * FROM tasks ORDER BY date_added DESC")
    List<Task> findAllOrdersByDateDesc();
    List<Task> findByTitle(String title);
    List<Task> findByStatus(Status status);
    List<Task> findByType(Type type);
}
