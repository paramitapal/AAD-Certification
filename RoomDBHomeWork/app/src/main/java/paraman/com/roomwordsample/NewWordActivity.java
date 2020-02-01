package paraman.com.roomwordsample;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

public class NewWordActivity extends AppCompatActivity {

    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    private EditText mEditWordView;
    boolean isTextUpdated = false;
    private final String LOG_TAG ="Edit-Word-Activity-Log";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_word);
        mEditWordView = findViewById(R.id.edit_word);
        Button button = findViewById(R.id.button_save);
        Intent intent = getIntent();
        String previousTextContent = intent.getStringExtra(MainActivity.TEXTVIEW_DOCUMENT_EXTRAS);
        mEditWordView.setText(previousTextContent);

        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent replyIntent = new Intent();
                if (previousTextContent.compareTo(mEditWordView.getText().toString()) != 0) {
                    isTextUpdated = true;
                } else {
                    isTextUpdated = false;
                }

                if (TextUtils.isEmpty(mEditWordView.getText()) || (!isTextUpdated)) {
                    setResult(RESULT_CANCELED, replyIntent);
                    Log.d(LOG_TAG, "String is empty or not updated" +isTextUpdated);
                } else {
                    String updatedText = mEditWordView.getText().toString();
                    replyIntent.putExtra(EXTRA_REPLY, updatedText);
                    setResult(RESULT_OK, replyIntent);
                    Log.d(LOG_TAG, "String updated "+isTextUpdated);
                }
                finish();
            }
        });
    }

}
