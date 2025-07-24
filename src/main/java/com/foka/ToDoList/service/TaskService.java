package com.foka.ToDoList.service;

import com.foka.ToDoList.model.Task;
import com.foka.ToDoList.model.TaskStatus;
import com.foka.ToDoList.repository.TaskRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TaskService {
    private final TaskRepository taskRepository;

    public Task createTask(Task task) {
        if (task.getStatus() == null) {
            task.setStatus(TaskStatus.A_FAIRE);
        }
        return taskRepository.save(task);
    }

    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    public List<Task> getTasksByStatus(TaskStatus status) {
        return taskRepository.findByStatus(status);
    }

    public Task updateTask(Long id, Task taskDetails) {
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Tâche non trouvée"));

        if (taskDetails.getTitle() != null) {
            task.setTitle(taskDetails.getTitle());
        }

        if (taskDetails.getDescription() != null) {
            task.setDescription(taskDetails.getDescription());
        }

        if (taskDetails.getStatus() != null) {
            task.setStatus(taskDetails.getStatus());
        }

        return taskRepository.save(task);
    }

    public void deleteTask(Long id) {
        taskRepository.deleteById(id);
    }
}
