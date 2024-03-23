import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Task {
  private String description;
  private boolean completed;
  private String priority;
  private LocalDate deadline;
  private List<String> categories;

  public Task(String theDesc) {
    this.description = theDesc;
    this.completed = false;
    this.priority = "Normal";
    this.deadline = null;
    this.categories = new ArrayList<>();

  }

  public String getDesc() {
    return description;
  }

  public void setDesc(String theDesc) {
    this.description = theDesc;
  }

  public boolean getCompleted() {
    return completed;
  }

  public void setCompleted(boolean theCompleted) {
    this.completed = theCompleted;
  }

  public String getPriority() {
    return priority;
  }

  public void setPriority(String priority) {
    this.priority = priority;
  }

  public LocalDate getDeadline() {
    return deadline;
  }

  public void setDeadline(LocalDate deadline) {
    this.deadline = deadline;
  }

  public List<String> getCategories() {
    return categories;
  }

  public void addCategory(String category) {
    categories.add(category);
  }

  public void removeCategory(String category) {
    categories.remove(category);
  }
}