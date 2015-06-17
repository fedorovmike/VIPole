package com.fedorovmike.vipole.vipole;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.ProgressBar;

/**
 * Created by Mikhail on 17.06.2015.
 */
public class ProcessRunTask extends AsyncTask<Void, Integer, Void> {
    private static final String LOG_TAG = ProcessRunTask.class.getSimpleName();
    final ItemInfo  mInfo;

    public ProcessRunTask(ItemInfo info) {
        mInfo = info;
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        mInfo.setProgress(values[0]);
        ProgressBar bar = mInfo.getProgressBar();
        if(bar != null) {
            bar.setProgress(mInfo.getProgress());
            bar.invalidate();
        }
    }

    @Override
    protected Void doInBackground(Void... params) {
        mInfo.setProcessState(ItemInfo.ProcessState.DOWNLOADING);
        for (int i = 0; i <= 100; ++i) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i);
        }
        mInfo.setProcessState(ItemInfo.ProcessState.COMPLETE);
        return null;
    }

    @Override
    protected void onPostExecute(Void result) {
        mInfo.setProcessState(ItemInfo.ProcessState.COMPLETE);
    }

    @Override
    protected void onPreExecute() {
        mInfo.setProcessState(ItemInfo.ProcessState.DOWNLOADING);
    }

}
