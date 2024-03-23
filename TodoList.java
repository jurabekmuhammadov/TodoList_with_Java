import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@SuppressWarnings("unchecked")

public class TodoList {
  private List<Task> tasks;

  public TodoList() {
    tasks = new ArrayList<>();
  }

  public void addTask(Task task) {
    tasks.add(task);
    System.out.println("Your task has been added successfully!");
  }

  public void markTaskAsCompleted(int index) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task yet to complete!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.get(index).setCompleted(true);
      System.out.println("Your task has been completed successfully!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void setTaskPriority(int index, String priority) {
    String thePriority = priority;
    if (tasks.isEmpty()) {
      System.out.println("There is no task to prioritize yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      switch (thePriority) {
        case "High":
          tasks.get(index).setPriority(thePriority);
          System.out.println("Your task priority has been set successfully!");
          break;
        case "Medium":
          tasks.get(index).setPriority(thePriority);
          System.out.println("Your task priority has been set successfully!");
          break;
        case "Low":
          tasks.get(index).setPriority(thePriority);
          System.out.println("Your task priority has been set successfully!");
          break;
        default:
          System.out.println("Invalid task priority");
      }
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void markTaskAsUnCompleted(int index) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task to complete yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.get(index).setCompleted(false);
      System.out.println("Your task has been uncompleted successfuly!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void filterTasks(boolean completed, String priority, LocalDate deadline, String category) {
    List<Task> filteredTasks = tasks.stream().filter(task -> task.getCompleted() == completed)
        .filter(task -> priority == null || task.getPriority().equals(priority))
        .filter(task -> deadline == null || task.getDeadline().equals(deadline))
        .filter(task -> category == null || task.getCategories().contains(category))
        .collect(Collectors.toList());

    if (filteredTasks.isEmpty()) {
      System.out.println("No tasks found matching the filter criteria.");
    } else {
      System.out.println("Filtered Tasks:");
      for (int i = 0; i < filteredTasks.size(); i++) {
        Task task = filteredTasks.get(i);
        System.out.println((i + 1) + ". " + task.getDesc() + " - Completed: " + task.getCompleted()
            + " - Priority: " + task.getPriority() + " - Deadline: " + task.getDeadline() + " - Categories: "
            + task.getCategories());
      }
    }
  }

  public void setTaskDeadline(int index, LocalDate deadline) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task to set deadline yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.get(index).setDeadline(deadline);
      System.out.println("Your task deadline has been set successfully!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void addCategoryToTask(int index, String category) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task to add category yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.get(index).addCategory(category);
      System.out.println("Category has been added successfully to your task!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void removeCategoryFromTask(int index, String category) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task to remove category yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.get(index).removeCategory(category);
      System.out.println("Category has been removed successfully from your task!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void removeTask(int index) {
    if (tasks.isEmpty()) {
      System.out.println("There is no task to remove yet!!!");
    } else if (index >= 0 && index < tasks.size()) {
      tasks.remove(index);
      System.out.println("Your task has been removed successfully!");
    } else {
      System.out.println("Invalid task index.");
    }
  }

  public void viewTasks() {
    if (tasks.isEmpty()) {
      System.out.println("There are no tasks yet");
    } else {
      System.out.println("Tasks:");
      for (int i = 0; i < tasks.size(); i++) {
        Task task = tasks.get(i);
        System.out.println(
            "Task " + (i + 1) + ". " + task.getDesc() + " - Completed: " + task.getCompleted() + " - Priority: "
                + task.getPriority() + " - Deadline: " + task.getDeadline() + " - Category: " + task.getCategories());
      }
    }
  }

  public void saveTasksToFile(String filename) {
    try (FileOutputStream fileOut = new FileOutputStream(filename);
        ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {
      objectOut.writeObject(tasks);
      System.out.println("Tasks saved to " + filename);
    } catch (IOException e) {
      System.err.println("Error saving tasks: " + e.getMessage());
    }
  }

  public void loadTasksFromFile(String filename) {
    try (FileInputStream fileIn = new FileInputStream(filename);
        ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {
      List<Task> loadedTasks = (List<Task>) objectIn.readObject();
      tasks.clear();
      tasks.addAll(loadedTasks);
      System.out.println("Tasks load from " + filename);
    } catch (IOException | ClassNotFoundException e) {
      System.err.println("Error loading tasks: " + e.getMessage());
    }
  }

}
