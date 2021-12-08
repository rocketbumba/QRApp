package rocketbumba.com.myapplication.model;

public class BookAuthen {
    private String id;
    private int authen;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAuthen() {
        return authen;
    }

    public void setAuthen(int authen) {
        this.authen = authen;
    }

    public BookAuthen() {
    }

    @Override
    public String toString() {
        return "BookAuthen{" +
                "id='" + id + '\'' +
                ", authen='" + authen + '\'' +
                '}';
    }
}
