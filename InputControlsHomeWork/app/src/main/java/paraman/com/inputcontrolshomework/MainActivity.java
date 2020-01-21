package paraman.com.inputcontrolshomework;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private String mCheckboxText = "";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void displayToast(String message) {

        Toast.makeText(getApplicationContext(), message,
                Toast.LENGTH_SHORT).show();
    }

    public void onSubmit(View view) {

        // Is the button now checked?
        boolean checked = ((CheckBox) view).isChecked();
        String currentCheckboxText = ((CheckBox) view).getText().toString();
        if (checked && (!mCheckboxText.contains(currentCheckboxText))) {
            mCheckboxText = mCheckboxText +" "+ currentCheckboxText;
        }
        else if (mCheckboxText.contains(currentCheckboxText)){
            mCheckboxText = mCheckboxText.replace(currentCheckboxText,"");
        }
//        showToast();
//        displayToast(mCheckboxText);
    }

    public void showToast(View view) {
        if(mCheckboxText!="")
        displayToast(mCheckboxText);
    }
}
