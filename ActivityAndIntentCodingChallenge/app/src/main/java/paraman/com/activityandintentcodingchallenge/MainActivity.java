package paraman.com.activityandintentcodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    public static final String EXTRA_ACTIVITY_ID =
            "com.example.android.activity.intent.extra.MESSAGE";
    private Intent mIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mIntent = new Intent(this, SecondActivity.class);
    }

    public void launchActivityFromThirdButton(View view) {
        mIntent.putExtra(EXTRA_ACTIVITY_ID, 3);
        startActivity(mIntent);
    }

    public void launchActivityFromSecondButton(View view) {
        mIntent.putExtra(EXTRA_ACTIVITY_ID, 2);
        startActivity(mIntent);

    }

    public void launchActivityFromFirstButton(View view) {
        mIntent.putExtra(EXTRA_ACTIVITY_ID, 1);
        startActivity(mIntent);

    }
}
