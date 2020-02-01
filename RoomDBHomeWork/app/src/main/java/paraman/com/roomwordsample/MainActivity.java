package paraman.com.roomwordsample;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private WordViewModel mWordViewModel;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;
    public static final String TEXTVIEW_DOCUMENT_EXTRAS = "paraman.com.roomwordsample.textViewDocument";
    private final String LOG_TAG ="Main-Activity-Log";
    private TextView mTextView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mWordViewModel = new ViewModelProvider(this).get(WordViewModel.class);
        mTextView = findViewById(R.id.textView2);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, NewWordActivity.class);
                intent.putExtra(TEXTVIEW_DOCUMENT_EXTRAS,mTextView.getText() );
                startActivityForResult(intent, NEW_WORD_ACTIVITY_REQUEST_CODE);
            }
        });
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == NEW_WORD_ACTIVITY_REQUEST_CODE && resultCode == RESULT_OK) {
            Word word = new Word(1,data.getStringExtra(NewWordActivity.EXTRA_REPLY));
            mWordViewModel.update(word);
        } else {
            Toast.makeText(
                    getApplicationContext(),
                    R.string.empty_not_saved,
                    Toast.LENGTH_LONG).show();
        }
    }


    @Override
    protected void onResume() {
        super.onResume();

        mWordViewModel.getAllWords().observe(this, new Observer<Word>() {
            @Override
            public void onChanged(@Nullable final Word words) {
                // Update the cached copy of the words in the adapter.
//                adapter.setWords(words);
                if(words!=null) {
                    Log.d(LOG_TAG, "String Changed on resume "+mWordViewModel.getAllWords().getValue().getWord());
                    mTextView.setText(words.getWord());
                }
            }
        });
    }
}
