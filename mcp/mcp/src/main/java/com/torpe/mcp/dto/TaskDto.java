package com.torpe.mcp.dto;

public class TaskDto {

    private String startDate;
    private boolean isDone;
    private String title;

    public TaskDto (String startDate, boolean isDone, String title){
        this.startDate = startDate;
        this.isDone = isDone;
        this.title = title;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
