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
public class WarningsModel {

    private SimpleIntegerProperty warningId;
    private SimpleIntegerProperty warningCableId;
    private SimpleStringProperty warningDescription;
    private SimpleStringProperty warningTime;

    public WarningsModel(Integer warningId, Integer warningCableId, String warningDescription, String warningTime) {
        this.warningId = new SimpleIntegerProperty(warningId);
        this.warningCableId = new SimpleIntegerProperty(warningCableId);
        this.warningDescription = new SimpleStringProperty(warningDescription);
        this.warningTime = new SimpleStringProperty(warningTime);
    }

    public int getWarningId() {
        return warningId.get();
    }

    public void setWarningId(int warningId) {
        this.warningId = new SimpleIntegerProperty(warningId);
    }

    public int getWarningCableId() {
        return warningCableId.get();
    }

    public void setWarningCableId(int warningCableId) {
        this.warningCableId = new SimpleIntegerProperty(warningCableId);
    }

    public String getWarningDescription() {
        return warningDescription.get();
    }

    public void setWarningDescription(String warningDescription) {
        this.warningDescription = new SimpleStringProperty(warningDescription);
    }

    public String getWarningTime() {
        return warningTime.get();
    }

    public void setwWrningTime(String warningTime) {
        this.warningTime = new SimpleStringProperty(warningTime);
    }

}
