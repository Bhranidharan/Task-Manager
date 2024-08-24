package com.taskmanager.project.taskcontroller;

import com.taskmanager.project.taskmodel.TaskModel;
import com.taskmanager.project.taskservice.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.config.Task;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/api/public/task")
    public ResponseEntity<?> viewTask(){
        try{
            List<TaskModel> list = taskService.ViewTask();
            return new ResponseEntity<>(list, HttpStatus.OK);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<>("No Task Found", e.getStatusCode());
        }
    }

    @PostMapping("/api/public/task")
    public ResponseEntity<String> addTask(@RequestBody TaskModel taskModel){
        try {
            String s = taskService.AddTask(taskModel);
            return new ResponseEntity<>(s, HttpStatus.CREATED);
        }catch (ResponseStatusException e){
            return new ResponseEntity<>(e.getReason(), e.getStatusCode());
        }
    }

    @DeleteMapping("/api/admin/task/{taskId}")
    public ResponseEntity<String> deleteTask(@PathVariable long taskId){
        try{
            String s = taskService.DeleteTask(taskId);
            return new ResponseEntity<String>(s, HttpStatus.OK);
        }catch (ResponseStatusException e) {
            return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
        }
    }

    @PutMapping("/api/admin/task/{taskId}")
    public ResponseEntity<String> UpdateTask(@RequestBody TaskModel taskModel, @PathVariable long taskId){
        try{
            String s = taskService.UpdateTask(taskModel, taskId);
            return new ResponseEntity<>(s, HttpStatus.OK);
        }catch(ResponseStatusException e){
            return new ResponseEntity<String>(e.getReason(), e.getStatusCode());
        }
    }

}
