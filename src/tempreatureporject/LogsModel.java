/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 *
 * @author abbasalshaqaq
 */
public class LogsModel {

    private SimpleIntegerProperty logId;
    private SimpleStringProperty logTime;
    private SimpleStringProperty logType;
    private SimpleStringProperty logDescription;

    public LogsModel(Integer logId, String logType, String logDescription, String logTime) {
        this.logId = new SimpleIntegerProperty(logId);
        this.logType = new SimpleStringProperty(logType);
        this.logDescription = new SimpleStringProperty(logDescription);
        this.logTime = new SimpleStringProperty(logTime);
    }

    public int getLogId() {
        return logId.get();
    }

    public void setLogId(int logId) {
        this.logId = new SimpleIntegerProperty(logId);
    }

    public String getLogType() {
        return logType.get();
    }

    public void setLogType(String logType) {
        this.logType = new SimpleStringProperty(logType);
    }

    public String getLogDescription() {
        return logDescription.get();
    }

    public void setLogDescription(String logDescription) {
        this.logDescription = new SimpleStringProperty(logDescription);
    }

    public String getLogTime() {
        return logTime.get();
    }

    public void setLogTime(String logTime) {
        this.logTime = new SimpleStringProperty(logTime);
    }

}
