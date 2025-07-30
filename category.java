// Category.java
public class Category {
    private int categoryId;     
    private String categoryName; 

    public Category(int categoryId, String categoryName) {
        this.categoryId = categoryId; // Identifier for the category
        this.categoryName = categoryName; // Name of the category
    }

    // Getters
    public int getCategoryId() {    
        return categoryId;
    }

    public String getCategoryName() { 
        return categoryName;
    }

    // Setters
    public void setCategoryId(int categoryId) { 
        this.categoryId = categoryId;
    }

    public void setCategoryName(String categoryName) { 
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return categoryName; 
    }
}
