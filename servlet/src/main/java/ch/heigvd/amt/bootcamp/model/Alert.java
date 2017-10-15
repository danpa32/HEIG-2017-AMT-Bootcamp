package ch.heigvd.amt.bootcamp.model;

public class Alert {
    public enum Level {
        DANGER, SUCCESS, WARNING, PRIMARY, SECONDARY, INFO, LIGHT, DARK
    }

    public Alert(Level level, String title, String message) {
        this.level = level;
        this.title = title;
        this.message = message;
    }

    public String getLevel() {
        return level.toString().toLowerCase();
    }

    public Level getLevelEnum() {
        return level;
    }

    public String getTitle() {
        return title;
    }

    public String getMessage() {
        return message;
    }

    private final Level level;
    private final String title;
    private final String message;
}
