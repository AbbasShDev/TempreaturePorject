/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.animation.FadeTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.layout.AnchorPane;
import javafx.util.Duration;

/**
 *
 * @author danml
 */
public class FXMLDocumentController implements Initializable {

    @FXML
    private AnchorPane holderPane;

    @FXML
    private JFXButton btnHome;

    @FXML
    private JFXButton btnLogs;

    @FXML
    private JFXButton btnWarnings;

    AnchorPane home, logs, warnings;

    @FXML
    private JFXButton btnControls;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //Load all fxmls in a cache
        try {
            home = FXMLLoader.load(getClass().getResource("Home.fxml"));
            logs = FXMLLoader.load(getClass().getResource("Logs.fxml"));
            warnings = FXMLLoader.load(getClass().getResource("Warnings.fxml"));

            setNode(home);

        } catch (IOException ex) {
            Logger.getLogger(FXMLDocumentController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Set selected node to a content holder
    private void setNode(Node node) {

        holderPane.getChildren().clear();
        holderPane.getChildren().add((Node) node);

        FadeTransition ft = new FadeTransition(Duration.millis(1500));
        ft.setNode(node);
        ft.setFromValue(0.1);
        ft.setToValue(1);
        ft.setCycleCount(1);
        ft.setAutoReverse(false);
        ft.play();
    }

    @FXML
    void switchHome(ActionEvent event) {
        setNode(home);
    }

    @FXML
    void switchLogs(ActionEvent event) {
        setNode(logs);
    }

    @FXML
    void switchWarnings(ActionEvent event) {
        setNode(warnings);
    }

}
