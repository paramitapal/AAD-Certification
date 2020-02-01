package paraman.com.notificationscheduler;

import android.app.job.JobParameters;
import android.app.job.JobService;
import android.os.AsyncTask;
import android.util.Log;

public class NotificationJobService extends JobService {
    private static final String TAG = "ExampleJobService";
    private boolean jobCancelled = false;
    private JobParameters jobParameters;
    private DoItTask doItTask;

    @Override
    public boolean onStartJob(JobParameters params) {
        Log.d(TAG, "Job started");
        this.jobParameters = params;
        doItTask = new DoItTask();
        doItTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters params) {
        Log.d(TAG, "Job cancelled before completion");
        jobCancelled = doItTask.cancel(true);
        return true;
    }
    private class DoItTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected void onPostExecute(Void aVoid) {
        Log.d("DoItTask", "Clean up the task here and call jobFinished...");
        jobFinished(jobParameters, false);
        super.onPostExecute(aVoid);
    }

    @Override
    protected Void doInBackground(Void... params) {
        Log.d("DoItTask", "Working here...");
        for (int i = 0; i < 10; i++) {
            Log.d(TAG, "run: " + i);
            if (jobCancelled) {
                return null;
            }

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return null;
    }
}

        }