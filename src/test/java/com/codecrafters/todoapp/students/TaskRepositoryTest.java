package com.codecrafters.todoapp.students;

import com.mongodb.assertions.Assertions;
import com.mongodb.client.MongoClients;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

class TaskRepositoryTest {

  TaskRepository repository =
      new TaskRepository(
          MongoClients.create(
              "mongodb+srv://javierediazs:CodeCrafters@cluster0.dtac6gq.mongodb.net/?retryWrites=true&w=majority&appName=Cluster0"));

  @Test
  void createTask() {
    var taskToCreate =
        new TaskCreationDTO(
            "title",
            "description",
            LocalDateTime.of(2024, 4, 3, 5, 30),
            "main",
            false,
            List.of("home", "university"));

    repository.createTask(taskToCreate);

    var result =
        repository.findAllTasks().stream()
            .filter(task -> TaskCreationDTO.fromTask(task).equals(taskToCreate))
            .findFirst();

    Assertions.assertTrue(result.isPresent());
  }

  @Test
  void updateTask() {}

  @Test
  void findAllTasks() {}

  @Test
  void deleteTask() {}
}
