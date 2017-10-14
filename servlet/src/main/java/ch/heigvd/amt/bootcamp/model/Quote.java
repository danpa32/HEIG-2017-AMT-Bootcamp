package ch.heigvd.amt.bootcamp.model;

public class Quote {
    public static String[] CATEGORIES = {"Philosophy", "War", "Life"};

    private int id;

    private String text;
    private String author;
    private int date;
    private String source;
    private String category;

    public Quote(int id, String text, String author, int date, String source, String category) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.date = date;
        this.source = source;
        this.category = category;
    }

    public enum FIELDS {
        ID, QUOTE, AUTHOR, DATE, SOURCE, CATEGORY
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public int getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getCategory() {
        return category;
    }
}