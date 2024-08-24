package com.taskmanager.project.taskservice;

import com.taskmanager.project.taskmodel.TaskModel;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class TaskServiceImpl implements TaskService {
    private List<TaskModel> list = new ArrayList<>();
    private long id = 1L;


    @Override
    public List<TaskModel> ViewTask() {
        if (list.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NO_CONTENT, "No tasks available");
        }
        return list;
    }

    @Override
    public String AddTask(TaskModel task) {
        if (task.getTaskName() == null || task.getTaskName().isEmpty()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Task name cannot be null or empty");
        }
        task.setTaskId(id++);
        list.add(task);
        return "Task Added Successfully";
    }

    @Override
    public String DeleteTask(long taskId) {
        TaskModel task = list.stream()
                .filter(t -> t.getTaskId() == taskId)
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));

        list.remove(task);

        return "Task " + task.getTaskName() + " deleted Successfully";
    }

    @Override
    public String UpdateTask(TaskModel task, long taskId) {
        TaskModel Task = list.stream()
                .filter(t -> t.getTaskId() == taskId)
                .findFirst()
                .orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND, "Task Not Found"));
        Task.setTaskName(task.getTaskName());

      //  list.add(Task);

        return "Task with id: "+ Task.getTaskId() +" name: "+ Task.getTaskName() +" Updated Successfully";
    }
}
