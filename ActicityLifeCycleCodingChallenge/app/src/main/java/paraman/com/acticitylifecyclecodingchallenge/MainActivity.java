package paraman.com.acticitylifecyclecodingchallenge;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG =
            MainActivity.class.getSimpleName();

    public static final int TEXT_REQUEST = 1;
    private ArrayList<TextView> mTextViewList = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        for(int i=0;i<10;i++){
            String name = "textView"+i;
            int id = getResources().getIdentifier(name, "id", getPackageName());
            if (id != 0) {
                TextView textView =  findViewById(id);
                mTextViewList.add((textView));
            }
        }

        if (savedInstanceState != null) {
            for(int i=0;i<mTextViewList.size();i++)
            {
                boolean isVisible =
                        savedInstanceState.getBoolean("textView_item_visibility_"+i);
                if (isVisible) {
                    mTextViewList.get(i).setText(savedInstanceState.getString("textView_item_"+i));
                    mTextViewList.get(i).setVisibility(View.VISIBLE);
                } else {
                    Log.d(LOG_TAG, "Item not selected for textView");
                }
            }
        }


    }

    public void addItems(View view) {
        launchSecondActivity(view);
    }

    public void launchSecondActivity(View view) {
        Log.d(LOG_TAG, "launchSecondActivity Button clicked!");
        Intent intent = new Intent(this, SecondActivity.class);
        startActivityForResult(intent, TEXT_REQUEST);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        for (int i=0; i<mTextViewList.size();i++){
            if (mTextViewList.get(i).getVisibility() == View.INVISIBLE) {
                outState.putString(mTextViewList.get(i).toString(), null);
            } else {
                outState.putString("textView_item_"+i, mTextViewList.get(i).getText().toString());
                outState.putBoolean("textView_item_visibility_"+i,true);
            }
        }

    }


    @Override
    public void onActivityResult(int requestCode,
                                 int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == TEXT_REQUEST) {
            if (resultCode == RESULT_OK) {
                String item =
                        data.getStringExtra(SecondActivity.EXTRA_RETURN_ITEM);
                int id = data.getIntExtra(SecondActivity.EXTRA_RETURN_ITEM_ID,0);
                for(int i=0; i < Constants.buttonViewIDs.length-1; i++) {
                    if(id==Constants.buttonViewIDs[i]) {
                        mTextViewList.get(i).setText(item);
                        mTextViewList.get(i).setVisibility(View.VISIBLE);
                        Log.d(LOG_TAG,"Item text activity result()"+mTextViewList.get(i).getVisibility()+i);

                    }
                }
            }
        }
    }

}
