package paraman.com.roomwordsample;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "word_table")
public class Word {


    public void setWordId(int wordId) {
        this.wordId = wordId;
    }

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    private int wordId;

    @ColumnInfo(name = "word")
    private String mWord;

    public Word(@NonNull String word) {this.mWord = word;}

    @Ignore
    public Word(int id, @NonNull String word) {
        this.wordId = id;
        this.mWord = word;
    }

    public String getWord(){return this.mWord;}

    public int getWordId() {
        return wordId;
    }

}
