package com.taskmanager.project.taskservice;

import com.taskmanager.project.taskmodel.TaskModel;
import org.springframework.scheduling.config.Task;

import java.util.ArrayList;
import java.util.*;

public interface TaskService {
     List<TaskModel> ViewTask();
     String AddTask(TaskModel task);
     String DeleteTask(long taskId);
     String UpdateTask(TaskModel task, long taskId);
}
