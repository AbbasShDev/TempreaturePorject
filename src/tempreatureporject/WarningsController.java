/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import com.jfoenix.controls.JFXButton;
import com.mysql.jdbc.Connection;
import java.net.URL;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ResourceBundle;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import static tempreatureporject.ActiveCableInterface.activeCable;
import static tempreatureporject.DBInfo.DB_NAME_WITH_ENCODING;
import static tempreatureporject.DBInfo.PASSWORD;
import static tempreatureporject.DBInfo.USER;
import static tempreatureporject.ExecutorService.scheduledExecutorServiceWarnings;

/**
 * FXML Controller class
 *
 * @author abbasalshaqaq
 */
public class WarningsController implements Initializable, ExecutorService, ActiveCableInterface {

    @FXML
    private TableView<WarningsModel> warningsTabelData;

    @FXML
    private TableColumn<WarningsModel, Integer> warningId;

    @FXML
    private TableColumn<WarningsModel, Integer> warningCableId;

    @FXML
    private TableColumn<WarningsModel, String> warningTime;

    @FXML
    private TableColumn<WarningsModel, String> warningsDescription;

    @FXML
    private HBox cabelButtons;

    private SpecialAlert alert = new SpecialAlert();

    private ObservableList<WarningsModel> warningsModels = FXCollections.observableArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadwarnings();
        addCabelsButtons();

    }

    public void loadCabeleData(int cabelId) {

        activeCable.setActiveCabelId(cabelId);

        loadwarnings();
        addCabelsButtons();
    }

    private void loadwarnings() {

        // Update warnings list per second
        scheduledExecutorServiceWarningsList.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {

                warningsModels.clear();

                Connection con = getConnection();

                String query = "SELECT * FROM `warnings` "
                        + "WHERE cable_id = " + activeCable.getActiveCabelId() + " "
                        + "ORDER BY time DESC ";
                Statement st;
                ResultSet rs;

                try {

                    st = con.createStatement();
                    rs = st.executeQuery(query);

                    while (rs.next()) {

                        warningsModels.add(new WarningsModel(rs.getInt("id"), rs.getInt("cable_id"), rs.getString("description"), rs.getTimestamp("time").toString()));
                    }

                    con.close();
                } catch (SQLException e) {
                    alert.show("Error", e.getMessage(), Alert.AlertType.ERROR);
                }

            });
        }, 0, 1, TimeUnit.SECONDS);

        warningId.setCellValueFactory(new PropertyValueFactory<WarningsModel, Integer>("warningId"));
        warningCableId.setCellValueFactory(new PropertyValueFactory<WarningsModel, Integer>("warningCableId"));
        warningsDescription.setCellValueFactory(new PropertyValueFactory<WarningsModel, String>("warningDescription"));
        warningTime.setCellValueFactory(new PropertyValueFactory<WarningsModel, String>("warningTime"));
        warningsTabelData.setItems(warningsModels);
    }

    public void addCabelsButtons() {

        cabelButtons.getChildren().clear();

        Connection con = getConnection();

        String query = "SELECT * FROM `cables`";
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String stringId = Integer.toString(id);

                JFXButton button = new JFXButton();

                button.setPrefWidth(90);
                button.setPrefHeight(30);

                button.setText("Cabel-" + stringId);

                button.setFont(new Font("Roboto", 14));

                button.setTextFill(Color.WHITE);

                // Dettect which cable button is active
                scheduledExecutorServiceSetActiveBtnStyle.scheduleAtFixedRate(() -> {

                    // Update the the buttons style
                    Platform.runLater(() -> {

                        if (id == activeCable.getActiveCabelId()) {
                            button.setStyle("-fx-background-color: #808080");
                            button.setDisable(true);
                        } else {
                            button.setStyle("-fx-background-color: #cc9a06");
                            button.setDisable(false);
                        }

                    });
                }, 0, 1, TimeUnit.SECONDS);

                button.setOnAction(e -> this.loadCabeleData(id));

                cabelButtons.getChildren().add(button);
            }

            con.close();
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), Alert.AlertType.ERROR);
        }

    }

    private Connection getConnection() {
        Connection con;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);
            return con;
        } catch (ClassNotFoundException ex) {
            alert.show("Connection Error", ex.getMessage(), Alert.AlertType.ERROR);
            return null;
        } catch (SQLException e) {
            alert.show("Connection Error", e.getMessage(), Alert.AlertType.ERROR);
            return null;
        }
    }

}
