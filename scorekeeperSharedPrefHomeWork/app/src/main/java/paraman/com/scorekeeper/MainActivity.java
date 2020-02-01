package paraman.com.scorekeeper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int mScore1;
    private int mScore2;
    private TextView mScoreText1;
    private TextView mScoreText2;
    static final String STATE_SCORE_1 = "Team 1 Score";
    static final String STATE_SCORE_2 = "Team 2 Score";

    private SharedPreferences mPreferences;
    private String sharedPrefFile =
            "paraman.com.scorekeeper.sharedPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mPreferences = getSharedPreferences(sharedPrefFile, MODE_PRIVATE);

        //Find the TextViews by ID
        mScoreText1 = findViewById(R.id.score_1);
        mScoreText2 = findViewById(R.id.score_2);

        mScore1 = mPreferences.getInt(STATE_SCORE_1, 0);
        mScore2 = mPreferences.getInt(STATE_SCORE_2, 0);

        mScoreText1.setText(String.format("%s",mScore1));
        mScoreText2.setText(String.format("%s",mScore2));


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        int nightMode = AppCompatDelegate.getDefaultNightMode();

        if(nightMode == AppCompatDelegate.MODE_NIGHT_YES){
            menu.findItem(R.id.night_mode).setTitle(R.string.day_mode);
        } else{
            menu.findItem(R.id.night_mode).setTitle(R.string.night_mode);
        }
        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Check if the correct item was clicked
        if (item.getItemId() == R.id.night_mode) {
            // Get the night mode state of the app.
            int nightMode = AppCompatDelegate.getDefaultNightMode();
            Log.d("TAG","Option menu has selected inside mode "+ AppCompatDelegate.MODE_NIGHT_YES
            );
            //Set the theme mode for the restarted activity
            if (nightMode == AppCompatDelegate.MODE_NIGHT_YES) {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_NO);
            } else {
                AppCompatDelegate.setDefaultNightMode
                        (AppCompatDelegate.MODE_NIGHT_YES);
            }

    // Recreate the activity for the theme change to take effect.
            recreate();
        }
        return true;
    }



//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        // Save the scores.
//        outState.putInt(STATE_SCORE_1, mScore1);
//        outState.putInt(STATE_SCORE_2, mScore2);
//        super.onSaveInstanceState(outState);
//    }


    @Override
    protected void onPause(){
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt(STATE_SCORE_1, mScore1);
        preferencesEditor.putInt(STATE_SCORE_2, mScore2);
        preferencesEditor.apply();
    }

        public void decreaseScore (View view){

            // Get the ID of the button that was clicked
            int viewID = view.getId();
            switch (viewID) {
                //If it was on Team 1
                case R.id.decreaseTeam1:
                    //Decrement the score and update the TextView
                    mScore1--;
                    mScoreText1.setText(String.valueOf(mScore1));
                    break;
                //If it was Team 2
                case R.id.decreaseTeam2:
                    //Decrement the score and update the TextView
                    mScore2--;
                    mScoreText2.setText(String.valueOf(mScore2));
            }
        }

        public void increaseScore (View view){

            //Get the ID of the button that was clicked
            int viewID = view.getId();
            switch (viewID) {
                //If it was on Team 1
                case R.id.increaseTeam1:
                    //Increment the score and update the TextView
                    mScore1++;
                    mScoreText1.setText(String.valueOf(mScore1));
                    break;
                //If it was Team 2
                case R.id.increaseTeam2:
                    //Increment the score and update the TextView
                    mScore2++;
                    mScoreText2.setText(String.valueOf(mScore2));
            }
        }

    public void resetScore(View view) {

        // Reset count
        mScore1 = 0;
        mScore2 = 0;

        mScoreText1.setText(String.format("%s", mScore1));
        mScoreText2.setText(String.format("%s", mScore2));

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();
    }
}
