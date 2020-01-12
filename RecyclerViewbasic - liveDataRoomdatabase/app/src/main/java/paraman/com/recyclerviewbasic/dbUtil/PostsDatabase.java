package paraman.com.recyclerviewbasic.dbUtil;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Executors;

/**
 * Created by guendouz on 15/02/2018.
 */

@Database(entities = {Post.class}, version = 1)
public abstract class PostsDatabase extends RoomDatabase {

    private static PostsDatabase INSTANCE;

    private final static List<Post> POSTS = Arrays.asList(
            new Post("Winter Olympics: Aksel Lund Svindal wins downhill gold at 35 in Pyeongchang"),
            new Post("Winter Olympics: Britain's Elise Christie 'fearless' despite fall in 500m final"),
            new Post("Chinese woman joins handbag in X-ray machine")
    );

    public abstract PostDao postDao();

    private static final Object sLock = new Object();

    public static PostsDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                        PostsDatabase.class, "Posts.db")
                        .allowMainThreadQueries()
                        .addCallback(new Callback() {
                            @Override
                            public void onCreate(@NonNull SupportSQLiteDatabase db) {
                                super.onCreate(db);
                                Executors.newSingleThreadExecutor().execute(
                                        () -> getInstance(context).postDao().saveAll(POSTS));
                            }
                        })
                        .build();
            }
            return INSTANCE;
        }
    }
}
