package paraman.com.recyclerviewcodelabs;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.LinkedList;

public class MainActivity extends AppCompatActivity{
    private final LinkedList<String> mWordList = new LinkedList<>();
    private final LinkedList<String> mInitialList = new LinkedList<>();
    private final LinkedList<String> mWordHeadingList = new LinkedList<>();


    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Put initial data into the word list.
        for (int i = 0; i < 10; i++) {
            mInitialList.addLast("Dummy Word Dummy Word Dummy Word Dummy Word Dummy Word Dummy Word Dummy Word Dummy Word Dummy Word" +
                    "Dummy Word" + i);
            mWordHeadingList.addLast("Dummy heading");
        }
        mWordList.addAll(mInitialList);
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
    // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList, mWordHeadingList);
    // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));

    }


}
