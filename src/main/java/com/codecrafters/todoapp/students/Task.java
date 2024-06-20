package com.codecrafters.todoapp.students;

import org.bson.Document;
import org.bson.types.ObjectId;

import java.time.LocalDateTime;
import java.util.List;

public record Task(
    ObjectId id,
    String title,
    String description,
    LocalDateTime dueDate,
    String category,
    Boolean completed,
    List<String> tags) {

  public static Task fromDocument(Document document) {
    return new Task(
        document.getObjectId("_id"),
        document.get("title", String.class),
        document.get("description", String.class),
        document.get("dueDate", LocalDateTime.class),
        document.get("category", String.class),
        document.get("completed", Boolean.class),
        document.get("tags", List.class));
  }

  public Document asDocument() {
    return new Document()
        .append("id", id)
        .append("title", title)
        .append("description", description)
        .append("dueDate", dueDate)
        .append("category", category)
        .append("completed", completed)
        .append("tags", tags);
  }
}
