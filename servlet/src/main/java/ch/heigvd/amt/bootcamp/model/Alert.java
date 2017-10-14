package ch.heigvd.amt.bootcamp.model;

public class Alert {
    public enum Level {
        PRIMARY, SECONDARY, SUCCESS, DANGER, WARNING, INFO, LIGHT, DARK
    }

    public Alert(Level level, String title, String message) {
        this.level = level;
        this.title = title;
        this.message = message;
    }

    public String getLevel() {
        return level.toString().toLowerCase();
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    private Level level;
    private String title;
    private String message;
}
