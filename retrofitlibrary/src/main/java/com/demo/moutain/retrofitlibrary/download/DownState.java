package com.demo.moutain.retrofitlibrary.download;

/**
 * Created by MOUTAIN on 2017/4/10.
 */

public enum  DownState {
    START(0),
    DOWN(1),
    PAUSE(2),
    STOP(3),
    ERROR(4),
    FINISH(5);
    private int state;

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    DownState(int state) {
        this.state = state;
    }
}
