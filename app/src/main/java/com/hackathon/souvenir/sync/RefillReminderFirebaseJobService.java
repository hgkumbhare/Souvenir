package com.hackathon.souvenir.sync;

import android.annotation.SuppressLint;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import android.content.Context;
import android.os.AsyncTask;

/**
 * Created by gunjan on 08/03/18.
 */

public class RefillReminderFirebaseJobService extends JobService {
    private AsyncTask mBackgroundTask;
    @SuppressLint("StaticFieldLeak")
    @Override
    public boolean onStartJob(final JobParameters job) {
        mBackgroundTask = new AsyncTask() {
            @Override
            protected Object doInBackground(Object[] objects) {
                Context context = RefillReminderFirebaseJobService.this;
                ReminderTask.executeTask(context, ReminderTask.ACTION_REFILL_ITEMS);
                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                jobFinished(job, false);
            }
        };
        mBackgroundTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(com.firebase.jobdispatcher.JobParameters job) {
        if ( mBackgroundTask != null ) mBackgroundTask.cancel(true);
        return true;
    }
}
