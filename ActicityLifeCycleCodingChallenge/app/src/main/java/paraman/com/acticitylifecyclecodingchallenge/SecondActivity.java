package paraman.com.acticitylifecyclecodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class SecondActivity extends AppCompatActivity {


    public static final String EXTRA_RETURN_ITEM =
            "com.example.android.twoactivities.extra.ITEM";
    public static final String EXTRA_RETURN_ITEM_ID ="com.example.android.twoactivities.extra.ITEM.ID";
    private static final String LOG_TAG =
            SecondActivity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }

    public void returnItems(View view) {
        Button selectedItem = (Button) view;
        String item = selectedItem.getText().toString();
        Intent returnIntent = new Intent();
        returnIntent.putExtra(EXTRA_RETURN_ITEM, item);
        returnIntent.putExtra(EXTRA_RETURN_ITEM_ID, view.getId());
        setResult(RESULT_OK, returnIntent);
        Log.d(LOG_TAG, "End SecondActivity");
        finish();
    }
}
