package paraman.com.activityandintentcodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first);

        TextView textView = findViewById(R.id.textView_second_activity_display_text);
        Intent intent = getIntent();
        int activityId =  intent.getIntExtra(MainActivity.EXTRA_ACTIVITY_ID,0);
        switch (activityId)
        {
            case 1:
                Log.d(LOG_TAG,"Case 1");
                textView.setText(getResources().getString(R.string.str_text_passage_one));
                break;
            case 2:
                Log.d(LOG_TAG,"Case 2");
                textView.setText(getResources().getString(R.string.str_text_passage_two));
                break;
            case 3:
                Log.d(LOG_TAG,"Case 3");
                textView.setText(getResources().getString(R.string.str_text_passage_third));
                break;
                default:
                    Log.d(LOG_TAG,"Default condition");
        }

    }
}
