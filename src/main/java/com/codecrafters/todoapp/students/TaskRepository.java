package com.codecrafters.todoapp.students;

import com.codecrafters.todoapp.config.db.CollectionsNames;
import com.codecrafters.todoapp.config.db.DBCredentials;
import com.codecrafters.todoapp.config.db.DatabasesNames;
import com.mongodb.client.*;
import com.mongodb.client.model.Filters;
import org.bson.Document;
import org.bson.conversions.Bson;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

@Repository
public class TaskRepository {

  private final MongoClient client;

  public TaskRepository(MongoClient client) {
    this.client = client;
  }

  public Task createTask(TaskCreationDTO task) {
    var todoDB = client.getDatabase(DatabasesNames.TODO);
    var tasksCollection = todoDB.getCollection(CollectionsNames.TASKS);
    var result = tasksCollection.insertOne(task.asDocument());
    return task.withId(Objects.requireNonNull(result.getInsertedId()).asObjectId().getValue());
  }

  public void updateTask(Task task) {
    var todoDB = client.getDatabase(DatabasesNames.TODO);
    var tasksCollection = todoDB.getCollection(CollectionsNames.TASKS);
    Bson idFilter = Filters.eq("_id", task.id());
    tasksCollection.updateOne(idFilter, task.asDocument());
  }

  public List<Task> findAllTasks() {
    try (MongoClient mongoClient = MongoClients.create(DBCredentials.DB_URL)) {
      MongoDatabase todoDB = mongoClient.getDatabase(DatabasesNames.TODO);
      MongoCollection<Document> tasksCollection = todoDB.getCollection(CollectionsNames.TASKS);
      FindIterable<Document> iterableCollection = tasksCollection.find();
      Iterator<Document> it = iterableCollection.iterator();
      List<Task> tasksList = new ArrayList<>();
      while (it.hasNext()) {
        Task task = Task.fromDocument(it.next());
        tasksList.add(task);
      }
      return tasksList;
    }
  }

  public void deleteTask(ObjectId taskId) {
    var todoDB = client.getDatabase(CollectionsNames.TASKS);
    var tasksCollection = todoDB.getCollection(CollectionsNames.TASKS);
    Bson idFilter = Filters.eq("_id", taskId);
    tasksCollection.deleteOne(idFilter);
  }
}
