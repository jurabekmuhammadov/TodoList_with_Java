import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
  public static void main(String[] args) {
    Scanner scanner = new Scanner(System.in);
    TodoList toDoList = new TodoList();

    boolean running = true;
    System.out.println("I'm Todo List project!!!\n" + "Management guide:");
    while (running) {
      System.out.println("1. Add Task");
      System.out.println("2. Remove Task");
      System.out.println("3 Mark Task as Completed");
      System.out.println("4. Mark Task as Uncompleted");
      System.out.println("5. Set Task Priority");
      System.out.println("6. Set Task Deadline");
      System.out.println("7. Add Category to Task");
      System.out.println("8. Remove Category from Task");
      System.out.println("9. Filter Tasks");
      System.out.println("10. View Tasks");
      System.out.println("11. Save Tasks to File");
      System.out.println("12. Load Tasks from File");
      System.out.println("13. Exit");
      System.out.print("Enter your choice: ");

      int choice = scanner.nextInt();
      scanner.nextLine();

      switch (choice) {
        case 1:
          System.out.print("Enter task description: ");
          String desc = scanner.nextLine();
          toDoList.addTask(new Task(desc));
          break;
        case 2:
          System.out.print("Enter task index to remove: ");
          int removeIndex = scanner.nextInt();
          toDoList.removeTask(removeIndex - 1);
          break;
        case 3:
          System.out.print("Enter task index to mark as completed: ");
          int completeIndex = scanner.nextInt();
          toDoList.markTaskAsCompleted(completeIndex - 1);
          break;
        case 4:
          int unCompleteIndex = scanner.nextInt();
          toDoList.markTaskAsUnCompleted(unCompleteIndex - 1);
          break;
        case 5:
          System.out.print("Enter task index to set priority: ");
          int priorityIndex = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter priority (High, Medium, Low):");
          String priority = scanner.nextLine();
          toDoList.setTaskPriority(priorityIndex - 1, priority);
          break;
        case 6:
          System.out.print("Enter task index to set deadline: ");
          int deadlineIndex = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter deadline (YYY-MM-DD): ");
          String deadlineInput = scanner.nextLine();
          try {
            LocalDate deadline = LocalDate.parse(deadlineInput);
            toDoList.setTaskDeadline(deadlineIndex - 1, deadline);
          } catch (DateTimeException e) {
            System.out.println("Error: Invalid deadline format. Please enter the deadline in the format YYYY-MM-DD.");
          }
          break;
        case 7:
          System.out.print("Enter the index of the task: ");
          int taskIndexToAddCategory = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter the category to add: ");
          String categoryToAdd = scanner.nextLine();
          toDoList.addCategoryToTask(taskIndexToAddCategory - 1, categoryToAdd);
          break;
        case 8:
          System.out.print("Enter the index of the task: ");
          int taskIndexToRemoveCategory = scanner.nextInt();
          scanner.nextLine();
          System.out.print("Enter the category to remove: ");
          String categoryToRemove = scanner.nextLine();
          toDoList.removeCategoryFromTask(taskIndexToRemoveCategory - 1, categoryToRemove);
          break;
        case 9:
          filterTasks(toDoList, scanner);
          break;
        case 10:
          toDoList.viewTasks();
          break;
        case 11:
          System.out.print("Enter file name to save tasks: ");
          String saveFileName = scanner.nextLine();
          toDoList.saveTasksToFile(saveFileName);
          break;
        case 12:
          System.out.print("Enter file name to load tasks from: ");
          String loadFileName = scanner.nextLine();
          toDoList.loadTasksFromFile(loadFileName);
          break;
        case 13:
          running = false;
          System.out.println("Exiting...");
          break;
        default:
          System.out.println("Invalid choice. Please enter a number between 1 and 14.");
      }
    }
    scanner.close();
  }

  private static void filterTasks(TodoList toDoList, Scanner scanner) {
    System.out.print("Enter completion status (If you leave blank, it will check for false): ");
    String completedInput = scanner.nextLine().trim();
    Boolean completed = false;
    if (!completedInput.isEmpty()) {
      completed = Boolean.parseBoolean(completedInput);
    }

    System.out.print("Enter priority (High, Medium, Low, or leave blank): ");
    String priorityInput = scanner.nextLine().trim();
    String priority = null;
    if (!priorityInput.isEmpty()) {
      priority = priorityInput;
    }
    System.out.print("Enter Category or leave blank: ");
    String categoryInput = scanner.nextLine().trim();
    String category = null;
    if (!categoryInput.isEmpty()) {
      category = categoryInput;
    }

    System.out.print("Enter deadline (YYYY-MM-DD or leave blank): ");
    String deadlineInput = scanner.nextLine().trim();
    LocalDate deadline = null;
    if (!deadlineInput.isEmpty()) {
      deadline = LocalDate.parse(deadlineInput);
    }

    toDoList.filterTasks(completed, priority, deadline, category);
  }

}
