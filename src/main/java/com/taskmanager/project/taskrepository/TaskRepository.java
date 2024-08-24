package com.taskmanager.project.taskrepository;

import com.taskmanager.project.taskmodel.TaskModel;
import org.springframework.data.mongodb.repository.MongoRepository;


public interface TaskRepository extends MongoRepository<TaskModel, Long> {
}
