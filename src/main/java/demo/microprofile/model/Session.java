package demo.microprofile.model;

public class Session {
    private int id;
    private String title;
    private String level;
    private int speakerId;

    public Session() {
    }

    public Session(int id, String title, String level, int speakerId) {
        this.id = id;
        this.title = title;
        this.level = level;
        this.speakerId = speakerId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getLevel() {
        return level;
    }

    public void setLevel(String level) {
        this.level = level;
    }

    public int getSpeakerId() {
        return speakerId;
    }

    public void setSpeakerId(int speakerId) {
        this.speakerId = speakerId;
    }
}
