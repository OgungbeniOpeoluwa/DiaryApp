package diary;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Entry {
    private LocalDateTime dateCreated;

    public Entry(int id,String title,String body) {
        this.id = id;
        this.title = title;
        this.body = body;
    }
    private int id;

    private String title;
    private String body;

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

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getDateCreated() {
        dateCreated = LocalDateTime.now();
        DateTimeFormatter formart =DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
        return dateCreated.format(formart);
    }


    public String toString(){
        System.out.println();
        return  String.format("""
                %s
                \t%s
                %s""",getDateCreated(),getTitle(),getBody());
    }
}
