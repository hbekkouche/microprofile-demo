package demo.microprofile.model;

public class Speaker {
    private int id;
    private String fullname;
    private String link;
    private String position;
    private String photo;

    public Speaker() {
    }

    public Speaker(int id, String fullname, String link, String position, String photo) {
        this.id = id;
        this.fullname = fullname;
        this.link = link;
        this.position = position;
        this.photo = photo;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
