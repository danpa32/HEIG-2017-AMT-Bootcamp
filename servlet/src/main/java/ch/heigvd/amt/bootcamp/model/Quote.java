package ch.heigvd.amt.bootcamp.model;

public class Quote {
    public static String[] CATEGORIES = {"Philosophy", "War", "Life"};

    private String title;
    private String description;

    public Quote(String title, String description) {
        this.title = title;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
