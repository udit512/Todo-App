package com.simpad.kuchthokaro.Model;

public class TaskInfo {
    private int done;
    private String createdon;


    public TaskInfo() {
    }

    public TaskInfo(String createdon, int done) {
        this.createdon = createdon;
        this.done = done;
    }

    public String getCreatedon() {
        return createdon;
    }

    public void setCreatedon(String createdon) {
        this.createdon = createdon;
    }

    public int getDone() {
        return done;
    }

    public void setDone(int done) {
        this.done = done;
    }
}
