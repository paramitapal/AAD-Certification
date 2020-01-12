package paraman.com.recyclerviewbasic.dbUtil;

import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

/**
 * Created by guendouz on 15/02/2018.
 */

@Entity
public class Post {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;

    public Post() {
    }

    @Ignore
    public Post(String title) {
        this.title = title;
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

}
