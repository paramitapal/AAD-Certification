package paraman.com.recyclerviewcodelabs;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ChildActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_child);

        Intent intent = getIntent();
        String text = intent.getStringExtra("HEADING");
        TextView textView = findViewById(R.id.textView2);
        textView.setText(text);
    }
}
