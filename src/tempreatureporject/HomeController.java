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
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

/**
 *
 * @author danml
 */
public class HomeController implements Initializable, DBInfo, ExecutorService, ActiveCableInterface {

    @FXML
    private LineChart<CategoryAxis, NumberAxis> CabelChart;

    @FXML
    private CategoryAxis CabelChartX;

    @FXML
    private NumberAxis CabelChartY;

    @FXML
    private BarChart<CategoryAxis, NumberAxis> TemperatureStatisticChart;

    @FXML
    private CategoryAxis CurrentChartX;

    @FXML
    private NumberAxis CurrentChartY;

    @FXML
    private VBox warningsList;

    @FXML
    private Label lastRecordedTemp;

    @FXML
    private Label lastRecordedTempDate;

    @FXML
    private Label lastRecordedTempTime;

    @FXML
    private Label cabelName;

    @FXML
    private Label CabelLocationValue;

    @FXML
    private Label CabelIDValue;

    @FXML
    private Label cabelTypeValue;

    @FXML
    private FlowPane cabelButtons;

    SpecialAlert alert = new SpecialAlert();

    final int WINDOW_SIZE = 10;

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        loadCabeleData(1);

    }

    private void loadCabeleData(int cabelId) {

        activeCable.setActiveCabelId(cabelId);

        setCabelChartData();
        setTemperatureStatisticCharttData();
        addToWarningsList();
        setLastRecordedTemp();
        addCabelsButtons();
        setCabelInfo();

    }

    private void setCabelChartData() {

        // put dummy data onto graph per second
        scheduledExecutorServiceChart.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {
                // get current time

                CabelChart.getData().clear();
                CabelChart.setAnimated(false);

                CabelChartY.setAutoRanging(false);
                CabelChartY.setTickUnit(20);

                XYChart.Series CabelChartSeries = new XYChart.Series();

                CabelChartSeries.getData().clear();

                Connection con = getConnection();

                String query = "SELECT *, DATE_FORMAT(time, '%H:%i') hours_minutes FROM `temperatures` "
                        + "WHERE DATE(time) = DATE(NOW()) "
                        + "AND cable_id = " + activeCable.getActiveCabelId() + " "
                        + "ORDER BY time ASC";
                Statement st;
                ResultSet rs;

                try {

                    st = con.createStatement();
                    rs = st.executeQuery(query);

                    while (rs.next()) {
                        //populating the series with data
                        CabelChartSeries.setName("Cable temperature today");
                        CabelChartSeries.getData().addAll(new XYChart.Data(rs.getString("hours_minutes"), Integer.parseInt(rs.getString("measurement"))));
                    }

                    con.close();
                    CabelChart.getData().add(CabelChartSeries);
                } catch (SQLException e) {
                    alert.show("Error", e.getMessage(), AlertType.ERROR);
                }

                //resize the chart line when the data is large
                if (CabelChartSeries.getData().size() > WINDOW_SIZE) {
                    CabelChartSeries.getData().remove(0);
                }
            });
        }, 0, 1, TimeUnit.SECONDS);

    }

    private void setTemperatureStatisticCharttData() {

        TemperatureStatisticChart.getData().clear();

        XYChart.Series TemperatureSeries = new XYChart.Series();

        TemperatureSeries.getData().clear();

        Connection con = getConnection();
        String query = "SELECT cable_id, DATE(time) date, ROUND(AVG(measurement), 2) day_avg_temp  "
                + "FROM `temperatures` "
                + "WHERE DATE(time) <= DATE(NOW()) AND DATE(time) > DATE_SUB(Date(NOW()), INTERVAL 7 DAY) "
                + "AND cable_id = " + activeCable.getActiveCabelId() + " "
                + "GROUP by date, cable_id "
                + "ORDER BY `date` ASC";
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {

                //populating the series with data
                TemperatureSeries.setName("Cable daily average temperature over past week");
                TemperatureSeries.getData().add(new XYChart.Data(rs.getDate("date").toString(), rs.getDouble("day_avg_temp")));
            }
            con.close();
            TemperatureStatisticChart.getData().add(TemperatureSeries);
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }

    }

    private void addToWarningsList() {

        // Update warnings list per second
        scheduledExecutorServiceWarnings.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {

                warningsList.getChildren().clear();

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
                        Label label = new Label(rs.getString("description"));

                        label.setPrefSize(465, 20);

                        label.setPadding(new Insets(3, 0, 3, 15));

                        label.setFont(new Font("Arial", 12));

                        label.setTextFill(Color.WHITE);

                        label.setBackground(new Background(new BackgroundFill(Color.web(("0xDD4B39")), new CornerRadii(4), Insets.EMPTY)));

                        warningsList.getChildren().add(label);

                    }

                    con.close();
                } catch (SQLException e) {
                    alert.show("Error", e.getMessage(), AlertType.ERROR);
                }

            });
        }, 0, 1, TimeUnit.SECONDS);

    }

    private void setLastRecordedTemp() {

        // Update last temp per second
        scheduledExecutorServiceLastTemp.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {
                Connection con = getConnection();

                String query = "SELECT * FROM `temperatures` "
                        + "WHERE cable_id = " + activeCable.getActiveCabelId() + " "
                        + "ORDER BY time DESC "
                        + "LIMIT 1";
                Statement st;
                ResultSet rs;

                try {

                    st = con.createStatement();
                    rs = st.executeQuery(query);

                    while (rs.next()) {

                        lastRecordedTemp.setText("");
                        lastRecordedTempDate.setText("");
                        lastRecordedTempTime.setText("");;

                        lastRecordedTemp.setText(rs.getString("measurement") + "°");
                        lastRecordedTempDate.setText(rs.getDate("time").toString());
                        lastRecordedTempTime.setText(rs.getTime("time").toString());
                    }

                    con.close();
                } catch (SQLException e) {
                    alert.show("Error", e.getMessage(), AlertType.ERROR);
                }

            });
        }, 0, 1, TimeUnit.SECONDS);

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
                button.setStyle("-fx-background-color: #00A65A");

                if (id == activeCable.getActiveCabelId()) {
                    button.setStyle("-fx-background-color: #808080");
                } else {
                    button.setStyle("-fx-background-color: #00A65A");
                }

                button.setOnAction(e -> this.loadCabeleData(id));

                cabelButtons.getChildren().add(button);
            }

            con.close();
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }
    }

    public void setCabelInfo() {

        Connection con = getConnection();

        String query = "SELECT * FROM `cables` WHERE id = " + activeCable.getActiveCabelId();
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                int id = rs.getInt("id");
                String stringId = Integer.toString(id);
                String type = rs.getString("type");
                String location = rs.getString("location");

                cabelName.setText("Cabel-" + stringId);
                CabelIDValue.setText(stringId);
                cabelTypeValue.setText(type);
                CabelLocationValue.setText(location);
            }

            con.close();
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }

    }

    private Connection getConnection() {
        Connection con;

        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = (Connection) DriverManager.getConnection(DB_NAME_WITH_ENCODING, USER, PASSWORD);
            return con;
        } catch (ClassNotFoundException ex) {
            alert.show("Connection Error", ex.getMessage(), AlertType.ERROR);
            return null;
        } catch (SQLException e) {
            alert.show("Connection Error", e.getMessage(), AlertType.ERROR);
            return null;
        }
    }

}
