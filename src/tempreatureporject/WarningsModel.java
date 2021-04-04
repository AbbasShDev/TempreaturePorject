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
    private SimpleStringProperty warningName;
    private SimpleStringProperty warningType;
    private SimpleStringProperty warningDescription;

    public WarningsModel(Integer warningId, String warningName, String warningType, String warningDescription) {
        this.warningId = new SimpleIntegerProperty(warningId);
        this.warningName = new SimpleStringProperty(warningName);
        this.warningType = new SimpleStringProperty(warningType);
        this.warningDescription = new SimpleStringProperty(warningDescription);
    }

    public int getWarningId() {
        return warningId.get();
    }

    public void setWarningId(int warningId) {
        this.warningId = new SimpleIntegerProperty(warningId);
    }

    public String getWarningName() {
        return warningName.get();
    }

    public void setWarningName(String warningName) {
        this.warningName = new SimpleStringProperty(warningName);
    }

    public String getWarningType() {
        return warningType.get();
    }

    public void setWarningType(String warningType) {
        this.warningType = new SimpleStringProperty(warningType);
    }

    public String getWarningDescription() {
        return warningDescription.get();
    }

    public void setWarningDescription(String warningDescription) {
        this.warningDescription = new SimpleStringProperty(warningDescription);
    }

}
