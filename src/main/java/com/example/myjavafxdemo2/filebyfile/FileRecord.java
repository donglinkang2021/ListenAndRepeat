package com.example.myjavafxdemo2.filebyfile;

import javafx.beans.property.Property;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;

public class FileRecord {

    private String filePath="";
    private SimpleStringProperty fileNameProperty;
    private SimpleStringProperty stepDelayProperty;
    private SimpleStringProperty loopDelayProperty;
    private SimpleStringProperty loopTimesProperty;

    public FileRecord() {
        fileNameProperty = new SimpleStringProperty("");
        stepDelayProperty = new SimpleStringProperty("200");
        loopDelayProperty = new SimpleStringProperty("500");
        loopTimesProperty = new SimpleStringProperty("1");
    }


    public void setFileNameProperty(String fileNameProperty) {
        this.fileNameProperty.set(fileNameProperty);
    }

    public void setStepDelayProperty(String stepDelayProperty) {
        this.stepDelayProperty.set(stepDelayProperty);
    }

    public void setLoopDelayProperty(String loopDelayProperty) {
        this.loopDelayProperty.set(loopDelayProperty);
    }

    public void setLoopTimesProperty(String loopTimesProperty) {
        this.loopTimesProperty.set(loopTimesProperty);
    }

    public SimpleStringProperty getFileNameProperty() {
        return fileNameProperty;
    }

    public SimpleStringProperty getStepDelayProperty() {
        return stepDelayProperty;
    }

    public SimpleStringProperty getLoopDelayProperty() {
        return loopDelayProperty;
    }

    public SimpleStringProperty getLoopTimesProperty() {
        return loopTimesProperty;
    }



    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }



    public int getLoopTimes() {
        return Integer.parseInt(loopTimesProperty.get());
    }

    public int getStepDelay() {
        return Integer.parseInt(stepDelayProperty.get());
    }

    public int getLoopDelay() {
        return Integer.parseInt(loopDelayProperty.get());
    }


    @Override
    public String toString() {
        return "FileRecord{" +
                "\n\tfilePath='" + filePath + '\'' +
                ", \n\tfileNameProperty=" + fileNameProperty +
                ", \n\tstepDelayProperty=" + stepDelayProperty +
                ", \n\tloopDelayProperty=" + loopDelayProperty +
                ", \n\tloopTimesProperty=" + loopTimesProperty +
                "\n}";
    }
}
