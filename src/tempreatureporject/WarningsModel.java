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
    private SimpleStringProperty warningLevel;
    private SimpleStringProperty warningDescription;
    private SimpleStringProperty warningTime;

    public WarningsModel(Integer warningId, String warningLevel, String warningDescription, String warningTime) {
        this.warningId = new SimpleIntegerProperty(warningId);
        this.warningLevel = new SimpleStringProperty(warningLevel);
        this.warningDescription = new SimpleStringProperty(warningDescription);
        this.warningTime = new SimpleStringProperty(warningTime);
    }

    public int getWarningId() {
        return warningId.get();
    }

    public void setWarningId(int warningId) {
        this.warningId = new SimpleIntegerProperty(warningId);
    }

    public String getWarningLevel() {
        return warningLevel.get();
    }

    public void setWarningLevel(String warningLevel) {
        this.warningLevel = new SimpleStringProperty(warningLevel);
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
