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
 * FXML Controller class
 *
 * @author abbasalshaqaq
 */
public class WarningsController implements Initializable {

    @FXML
    private TableView<WarningsModel> warningsTabelData;

    @FXML
    private TableColumn<WarningsModel, Integer> warningId;

    @FXML
    private TableColumn<WarningsModel, String> warningName;

    @FXML
    private TableColumn<WarningsModel, String> warningType;

    @FXML
    private TableColumn<WarningsModel, String> warningsDescription;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        loadwarnings();
    }

    private ObservableList<WarningsModel> warningsModels = FXCollections.observableArrayList(
            new WarningsModel(100001, "Name info 1", "Type info 1", "Description info goes here 1"),
            new WarningsModel(100002, "Name info 2", "Type info 2", "Description info goes here 2"),
            new WarningsModel(100003, "Name info 3", "Type info 3", "Description info goes here 3"),
            new WarningsModel(100004, "Name info 4", "Type info 4", "Description info goes here 4"),
            new WarningsModel(100005, "Name info 5", "Type info 5", "Description info goes here 5"),
            new WarningsModel(100006, "Name info 6", "Type info 6", "Description info goes here 6"),
            new WarningsModel(100007, "Name info 7", "Type info 7", "Description info goes here 7"),
            new WarningsModel(100008, "Name info 8", "Type info 8", "Description info goes here 8"),
            new WarningsModel(100009, "Name info 9", "Type info 9", "Description info goes here 9")
    );

    private void loadwarnings() {
        warningId.setCellValueFactory(new PropertyValueFactory<WarningsModel, Integer>("warningId"));
        warningName.setCellValueFactory(new PropertyValueFactory<WarningsModel, String>("warningName"));
        warningType.setCellValueFactory(new PropertyValueFactory<WarningsModel, String>("warningType"));
        warningsDescription.setCellValueFactory(new PropertyValueFactory<WarningsModel, String>("warningDescription"));
        warningsTabelData.setItems(warningsModels);
    }

}
