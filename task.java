// Task.java
import java.time.LocalDate;

public class Task {
    private int taskId;
    private String title;
    private String description;
    private LocalDate dueDate;
    private String priority;
    private String status;
    private Category category; 

    // Constructor - used to create a new Task object and set its initial values
    public Task(int taskId, String title, String description, LocalDate dueDate, String priority, String status, Category category) {
        this.taskId = taskId;
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.priority = priority;
        this.status = status;
        this.category = category; 
    }

    // Getters - these are methods that allow other parts of the program to access the task's private data.
    public int getTaskId() {
        return taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public String getPriority() {
        return priority;
    }

    public String getStatus() {
        return status;
    }

    public Category getCategory() { 
        return category;
    }

    // Setters - these are methods that allow other parts of the program to update the task's private data.
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public void setPriority(String priority) {
        this.priority = priority;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCategory(Category category) { 
        this.category = category;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId +
               ", Title: " + title +
               ", Due Date: " + dueDate +
               ", Priority: " + priority +
               ", Status: " + status +
               ", Category: " + (category != null ? category.getCategoryName() : "N/A"); 
    }
}
