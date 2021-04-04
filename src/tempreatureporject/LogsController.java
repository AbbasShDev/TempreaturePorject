/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author danml
 */
public class LogsController implements Initializable {

    @FXML
    private TableView<LogsModel> logsTabelData;

    @FXML
    private TableColumn<LogsModel, Integer> logId;

    @FXML
    private TableColumn<LogsModel, String> logName;

    @FXML
    private TableColumn<LogsModel, String> logType;

    @FXML
    private TableColumn<LogsModel, String> logDescription;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadLogs();
    }

    private ObservableList<LogsModel> logsModels = FXCollections.observableArrayList(
            new LogsModel(100001, "Name info 1", "Type info 1", "Description info goes here 1"),
            new LogsModel(100002, "Name info 2", "Type info 2", "Description info goes here 2"),
            new LogsModel(100003, "Name info 3", "Type info 3", "Description info goes here 3"),
            new LogsModel(100004, "Name info 4", "Type info 4", "Description info goes here 4"),
            new LogsModel(100005, "Name info 5", "Type info 5", "Description info goes here 5"),
            new LogsModel(100006, "Name info 6", "Type info 6", "Description info goes here 6"),
            new LogsModel(100007, "Name info 7", "Type info 7", "Description info goes here 7"),
            new LogsModel(100008, "Name info 8", "Type info 8", "Description info goes here 8"),
            new LogsModel(100009, "Name info 9", "Type info 9", "Description info goes here 9")
    );

    private void loadLogs() {
        logId.setCellValueFactory(new PropertyValueFactory<LogsModel, Integer>("logId"));
        logName.setCellValueFactory(new PropertyValueFactory<LogsModel, String>("logName"));
        logType.setCellValueFactory(new PropertyValueFactory<LogsModel, String>("logType"));
        logDescription.setCellValueFactory(new PropertyValueFactory<LogsModel, String>("logDescription"));
        logsTabelData.setItems(logsModels);
    }

}
