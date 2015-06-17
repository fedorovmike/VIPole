package com.fedorovmike.vipole.vipole;

import android.widget.ProgressBar;

/**
 * Created by Mikhail on 17.06.2015.
 */
public class ItemInfo {
    private static final String LOG_TAG = ItemInfo.class.getSimpleName();

    public enum ProcessState {
        NOT_STARTED,
        QUEUED,
        DOWNLOADING,
        COMPLETE
    }

    private volatile ProcessState mProcessState = ProcessState.NOT_STARTED;
    private final String mTitle;
    private volatile Integer mProgress;
    private final String mType;
    private final Integer mId;
    private volatile ProgressBar mProgressBar;

    public ItemInfo(String title, String type, Integer id) {
        mTitle = title;
        mProgress = 0;
        mType = type;
        mId = id;
        mProgressBar = null;
    }

    public ProgressBar getProgressBar() {
        return mProgressBar;
    }
    public void setProgressBar(ProgressBar progressBar) {
        mProgressBar = progressBar;
    }

    public void setProcessState(ProcessState state) {
        mProcessState = state;
    }
    public ProcessState getProcessState() {
        return mProcessState;
    }

    public Integer getProgress() {
        return mProgress;
    }

    public void setProgress(Integer progress) {
        this.mProgress = progress;
    }

    public Integer getId() {
        return mId;
    }

    public String getTitle() {
        return mTitle;
    }

    public String getType() {
        return mType;
    }
}
