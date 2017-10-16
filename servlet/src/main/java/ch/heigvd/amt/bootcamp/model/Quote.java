/**
 * file: Quote.java
 * authors:
 *  - Christopher MEIER
 *  - Daniel PALUMBO
 * date: 16.10.2017
 */
package ch.heigvd.amt.bootcamp.model;

public class Quote {
    public final static String DEFAULT_CATEGORY = "(No Category)";
    public final static String DEFAULT_AUTHOR = "Unknown";


    private final int id;
    private final String text;
    private final String author;
    private final Integer date;
    private final String source;
    private final String category;

    public Quote(int id, String text, String author, Integer date, String source, String category) {
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

    public String getText() {
        return text;
    }

    public String getAuthor() {
        return author;
    }

    public Integer getDate() {
        return date;
    }

    public String getSource() {
        return source;
    }

    public String getCategory() {
        return category;
    }
}
