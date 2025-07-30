// TaskManager.java
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class TaskManager {
    private List<Task> tasks;
    private int nextTaskId;
    private int nextCategoryId; 

    public TaskManager() {
        this.tasks = new ArrayList<>();
        this.nextTaskId = 1;
        this.nextCategoryId = 1; 
    }

    private int generateTaskId() {
        return nextTaskId++;
    }

    public int generateCategoryId() { 
        return nextCategoryId++;
    }

    // Add a new task 
    public void addTask(String title, String description, LocalDate dueDate, String priority, String status, Category category) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Task title cannot be empty.");
            return;
        }
        if (dueDate == null) {
            System.out.println("Error: Due date cannot be null.");
            return;
        }
        if (status == null || status.trim().isEmpty()) {
            System.out.println("Error: Status cannot be empty");
            return;
        }
        if (priority == null || priority.trim().isEmpty()) {
            System.out.println("Error: Priority cannot be empty");
            return;
        }
        if (category == null) { 
            System.out.println("Error: Category cannot be null");
            return;
        }

        // Task constructor call updated 
        Task newTask = new Task(generateTaskId(), title, description, dueDate, priority, status, category);
        tasks.add(newTask);
        System.out.println("Task added: " + newTask.getTitle());
    }

    // Remove a task by its ID
    public void removeTask(int taskId) {
        boolean removed = tasks.removeIf(task -> task.getTaskId() == taskId);
        if (removed) {
            System.out.println("Task with ID " + taskId + " removed.");
        } else {
            System.out.println("Error: Task with ID " + taskId + " not found.");
        }
    }

    // Update an existing task 
    public void updateTask(int taskId, String title, String description, LocalDate dueDate, String priority, String status, Category category) {
        if (title == null || title.trim().isEmpty()) {
            System.out.println("Error: Task title cannot be empty during update.");
            return;
        }
        if (dueDate == null) {
            System.out.println("Error: Due date cannot be null during update.");
            return;
        }
        if (priority == null || priority.trim().isEmpty()) {
            System.out.println("Error: Priority cannot be empty during update.");
            return;
        }
        if (status == null || status.trim().isEmpty()) {
            System.out.println("Error: Status cannot be empty during update");
            return;
        }
        if (category == null) { 
            System.out.println("Error: Category cannot be null during update");
            return;
        }

        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setTitle(title);
                task.setDescription(description);
                task.setDueDate(dueDate);
                task.setPriority(priority);
                task.setStatus(status);
                task.setCategory(category); 
                System.out.println("Task with ID " + taskId + " updated.");
                return;
            }
        }
        System.out.println("Error: Task with ID " + taskId + " not found for update.");
    }

    // Get all tasks
    public List<Task> getAllTasks() {
        return new ArrayList<>(tasks);
    }

    // Sort tasks by due date
    public List<Task> sortTasksByDueDate() {
        return tasks.stream()
                .sorted(Comparator.comparing(Task::getDueDate, Comparator.nullsLast(LocalDate::compareTo)))
                .collect(Collectors.toList());
    }

    // Sort tasks by priority (e.g., High, Medium, Low)
    public List<Task> sortTasksByPriority() {
        Comparator<Task> priorityComparator = (task1, task2) -> {
            int p1 = getPriorityOrder(task1.getPriority());
            int p2 = getPriorityOrder(task2.getPriority());
            return Integer.compare(p1, p2);
        };
        return tasks.stream()
                .sorted(priorityComparator)
                .collect(Collectors.toList());
    }

    private int getPriorityOrder(String priority) {
        if (priority == null) return Integer.MAX_VALUE;
        return switch (priority.toLowerCase()) {
            case "high" -> 1;
            case "medium" -> 2;
            case "low" -> 3;
            default -> Integer.MAX_VALUE;
        };
    }

    public List<Task> filterTasksByCategory(Category category) { // filterTasksByCategory() 
        if (category == null) {
            System.out.println("Error: Category for filtering cannot be null");
            return new ArrayList<>();
        }
        return tasks.stream()
                .filter(task -> task.getCategory() != null && task.getCategory().getCategoryId() == category.getCategoryId())
                .collect(Collectors.toList());
    }

    // Filter tasks by completion status
    public List<Task> filterTasksByStatus(String status) {
        if (status == null || status.trim().isEmpty()) {
            System.out.println("Error: Status for filtering cannot be empty.");
            return new ArrayList<>();
        }
        return tasks.stream()
                .filter(task -> task.getStatus() != null && task.getStatus().equalsIgnoreCase(status))
                .collect(Collectors.toList());
    }

    // Mark a task as completed
    public void markTaskAsCompleted(int taskId) {
        for (Task task : tasks) {
            if (task.getTaskId() == taskId) {
                task.setStatus("Completed");
                System.out.println("Task with ID " + taskId + " marked as Completed.");
                return;
            }
        }
        System.out.println("Error: Task with ID " + taskId + " not found to mark as completed.");
    }

    // View completed tasks separately
    public List<Task> getCompletedTasks() {
        return filterTasksByStatus("Completed");
    }

    // View incomplete tasks
    public List<Task> getIncompleteTasks() {
        return tasks.stream()
                .filter(task -> task.getStatus() != null && !task.getStatus().equalsIgnoreCase("Completed"))
                .collect(Collectors.toList());
    }
}
