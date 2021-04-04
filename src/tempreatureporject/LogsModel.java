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
    private SimpleStringProperty logName;
    private SimpleStringProperty logType;
    private SimpleStringProperty logDescription;

    public LogsModel(Integer logId, String logName, String logType, String logDescription) {
        this.logId = new SimpleIntegerProperty(logId);
        this.logName = new SimpleStringProperty(logName);
        this.logType = new SimpleStringProperty(logType);
        this.logDescription = new SimpleStringProperty(logDescription);
    }

    public int getLogId() {
        return logId.get();
    }

    public void setLogId(int logId) {
        this.logId = new SimpleIntegerProperty(logId);
    }

    public String getLogName() {
        return logName.get();
    }

    public void setLogName(String logName) {
        this.logName = new SimpleStringProperty(logName);
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

}
