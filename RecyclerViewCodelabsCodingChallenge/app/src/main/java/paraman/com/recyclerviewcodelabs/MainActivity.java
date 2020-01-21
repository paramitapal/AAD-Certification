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

    private RecyclerView mRecyclerView;
    private WordListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        // Put initial data into the word list.
        for (int i = 0; i < 20; i++) {
            mInitialList.addLast("Word " + i);
        }
        mWordList.addAll(mInitialList);
        // Get a handle to the RecyclerView.
        mRecyclerView = findViewById(R.id.recyclerview);
    // Create an adapter and supply the data to be displayed.
        mAdapter = new WordListAdapter(this, mWordList);
    // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);
    // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));



        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                int wordListSize = mWordList.size();
                // Add a new word to the wordList.
                mWordList.addLast("+ Word " + wordListSize);
                // Notify the adapter, that the data has changed.
                mRecyclerView.getAdapter().notifyItemInserted(wordListSize);
                Log.d("TAG","Data size "+mRecyclerView.getAdapter());

                // Scroll to the bottom.
                mRecyclerView.smoothScrollToPosition(wordListSize);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_reset) {
//            mAdapter = new WordListAdapter(this, mInitialList);
            Log.d("TAG","Data size in menu item "+mAdapter);

            // Connect the adapter with the RecyclerView.
            mRecyclerView.setAdapter(mAdapter);
            mAdapter.notifyDataSetChanged();
            mWordList.clear();
            mWordList.addAll(mInitialList);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
