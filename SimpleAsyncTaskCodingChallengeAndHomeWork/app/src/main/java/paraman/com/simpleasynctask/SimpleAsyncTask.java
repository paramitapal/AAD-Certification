package paraman.com.simpleasynctask;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.lang.ref.WeakReference;
import java.util.Random;

public class SimpleAsyncTask extends AsyncTask<Void,Integer,String> {

    private WeakReference<TextView> mTextView;
    private WeakReference<ProgressBar> mProgressBar;

    SimpleAsyncTask(TextView tv, ProgressBar progressbar) {
        mTextView = new WeakReference<>(tv);
        mProgressBar = new WeakReference<>(progressbar);
    }

    @Override
    protected String doInBackground(Void... voids) {

        Random r = new Random();
        int n = r.nextInt(11);
        int s = n * 1000;


        try {
            if(s!=0){
            int progressChunks = s/100;   //dividing sleep time into chunks
            int loopLimit = s/progressChunks;

            for (int i = 0; i < loopLimit; i++) {
                publishProgress(i);
                Thread.sleep(progressChunks);
            }
            }


        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        return "Awake at last after sleeping for " + s + " milliseconds!";

    }


    protected void onPostExecute(String result) {
        mTextView.get().setText(result);
        mProgressBar.get().setProgress(0);
        mProgressBar.get().setVisibility(View.INVISIBLE);

    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mProgressBar.get().setVisibility(View.VISIBLE);
        mTextView.get().setText( "\nCompleted...." + values[0] + "%");
        mProgressBar.get().setProgress(values[0]);
    }
}
