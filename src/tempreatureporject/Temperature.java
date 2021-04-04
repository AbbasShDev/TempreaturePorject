package tempreatureporject;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Temperature {

    SimpleIntegerProperty id;
    SimpleIntegerProperty deviceId;
    SimpleStringProperty measurement;
    SimpleStringProperty time;

    public Temperature() {
        this.id = new SimpleIntegerProperty(0);
        this.deviceId = new SimpleIntegerProperty(0);
        this.measurement = new SimpleStringProperty("");
        this.time = new SimpleStringProperty("");

    }

    public Temperature(int id, int deviceId, String measurement, String time) {
        this.id = new SimpleIntegerProperty(0);
        this.deviceId = new SimpleIntegerProperty(deviceId);
        this.measurement = new SimpleStringProperty(measurement);
        this.time = new SimpleStringProperty(time);

    }

    public int getId() {
        return id.getValue();
    }

    public void setId(int id) {
        this.id = new SimpleIntegerProperty(id);
    }

    public int getDeviceId() {
        return deviceId.getValue();
    }

    public void setDeviceId(int deviceId) {
        this.deviceId = new SimpleIntegerProperty(deviceId);
    }

    public String getMeasurement() {
        return measurement.getValue();
    }

    public void setMeasurement(String measurement) {
        this.measurement = new SimpleStringProperty(measurement);
    }

    public String getTime() {
        return time.getValue();
    }

    public void setTime(String time) {
        this.time = new SimpleStringProperty(time);
    }

}
