package tempreatureporject;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class LogsModel {

    private SimpleIntegerProperty logId;
    private SimpleStringProperty logTime;
    private SimpleIntegerProperty logCableId;
    private SimpleStringProperty logDescription;

    public LogsModel(Integer logId, Integer logCableId, String logDescription, String logTime) {
        this.logId = new SimpleIntegerProperty(logId);
        this.logCableId = new SimpleIntegerProperty(logCableId);
        this.logDescription = new SimpleStringProperty(logDescription);
        this.logTime = new SimpleStringProperty(logTime);
    }

    public int getLogId() {
        return logId.get();
    }

    public void setLogId(int logId) {
        this.logId = new SimpleIntegerProperty(logId);
    }

    public int getLogCableId() {
        return logCableId.get();
    }

    public void setLogCableId(int logCableId) {
        this.logCableId = new SimpleIntegerProperty(logCableId);
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
