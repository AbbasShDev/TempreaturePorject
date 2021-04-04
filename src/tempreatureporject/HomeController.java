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
public class HomeController implements Initializable, DBInfo {

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
    private VBox logsList;

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

    ObservableList<Temperature> data = FXCollections.observableArrayList();

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        setTemperatureStatisticCharttData();
        setCabelChartData();
        addToWarningsList();
        addToLogsList();
        setCabelInfo();
        addCabelsButtons();

    }

    private void setCabelChartData() {

        CabelChartY.setAutoRanging(false);
        CabelChartY.setTickUnit(20);

        XYChart.Series series = new XYChart.Series();

        Connection con = getConnection();

        String query = "SELECT *, DATE_FORMAT(time, '%H:%i') hours_minutes FROM `temperatures` WHERE DATE(time) = DATE(NOW())";
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                //populating the series with data
                series.setName("Cable temperature today");
                series.getData().add(new XYChart.Data(rs.getString("hours_minutes"), Integer.parseInt(rs.getString("measurement"))));
            }

            con.close();
            CabelChart.getData().add(series);
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }

    }

    private void setTemperatureStatisticCharttData() {

        XYChart.Series series = new XYChart.Series();

        Connection con = getConnection();
        String query = "SELECT device_id, DATE(time) date, ROUND(AVG(measurement), 2) day_avg_temp "
                + "FROM `temperatures` "
                + "WHERE DATE(time) <= DATE(NOW()) AND DATE(time) > DATE_SUB(Date(NOW()), INTERVAL 7 DAY) "
                + "GROUP by date "
                + "ORDER BY date ASC";
        Statement st;
        ResultSet rs;

        try {

            st = con.createStatement();
            rs = st.executeQuery(query);

            while (rs.next()) {
                //populating the series with data
                series.setName("Cable daily average temperature over past week");
                series.getData().add(new XYChart.Data(rs.getDate("date").toString(), rs.getDouble("day_avg_temp")));
            }
            con.close();
            TemperatureStatisticChart.getData().add(series);
        } catch (SQLException e) {
            alert.show("Error", e.getMessage(), AlertType.ERROR);
        }

//        //populating the series with data
//        series.setName("Min");
//        series.getData().add(new XYChart.Data("Hour", 40));
//        series.getData().add(new XYChart.Data("Day", 70));
//        series.getData().add(new XYChart.Data("Week", 50));
//        series.getData().add(new XYChart.Data("Mounth", 30));
//        TemperatureStatisticChart.getData().add(series);
//
//        XYChart.Series series1 = new XYChart.Series();
//
//        //populating the series with data
//        series1.setName("Averge");
//        series1.getData().add(new XYChart.Data("Hour", 50));
//        series1.getData().add(new XYChart.Data("Day", 90));
//        series1.getData().add(new XYChart.Data("Week", 60));
//        series1.getData().add(new XYChart.Data("Mounth", 60));
//        TemperatureStatisticChart.getData().add(series1);
//
//        XYChart.Series series2 = new XYChart.Series();
//
//        //populating the series with data
//        series2.setName("Max");
//        series2.getData().add(new XYChart.Data("Hour", 60));
//        series2.getData().add(new XYChart.Data("Day", 100));
//        series2.getData().add(new XYChart.Data("Week", 80));
//        series2.getData().add(new XYChart.Data("Mounth", 70));
//        TemperatureStatisticChart.getData().add(series2);
    }

    private void addToWarningsList() {

        Label label = new Label("First warning");

        label.setPrefSize(465, 20);

        label.setPadding(new Insets(3, 0, 3, 15));

        label.setFont(new Font("Arial", 12));

        label.setTextFill(Color.WHITE);

        label.setBackground(new Background(new BackgroundFill(Color.web(("0xDD4B39")), new CornerRadii(4), Insets.EMPTY)));

        warningsList.getChildren().add(label);

        Label label2 = new Label("Second warning");

        label2.setPrefSize(465, 20);

        label2.setPadding(new Insets(3, 0, 3, 15));

        label2.setFont(new Font("Arial", 12));

        label2.setTextFill(Color.WHITE);

        label2.setBackground(new Background(new BackgroundFill(Color.web(("0xDD4B39")), new CornerRadii(4), Insets.EMPTY)));

        warningsList.getChildren().add(label2);

        Label label3 = new Label("Third warning");

        label3.setPrefSize(465, 20);

        label3.setPadding(new Insets(3, 0, 3, 15));

        label3.setFont(new Font("Arial", 12));

        label3.setTextFill(Color.WHITE);

        label3.setBackground(new Background(new BackgroundFill(Color.web(("0xDD4B39")), new CornerRadii(4), Insets.EMPTY)));

        warningsList.getChildren().add(label3);
    }

    private void addToLogsList() {

        Label label = new Label("First log");

        label.setPrefSize(465, 20);

        label.setPadding(new Insets(3, 0, 3, 15));

        label.setFont(new Font("Arial", 12));

        label.setTextFill(Color.WHITE);

        label.setBackground(new Background(new BackgroundFill(Color.web(("0x808080")), new CornerRadii(4), Insets.EMPTY)));

        logsList.getChildren().add(label);

        Label label2 = new Label("Second log");

        label2.setPrefSize(465, 20);

        label2.setPadding(new Insets(3, 0, 3, 15));

        label2.setFont(new Font("Arial", 12));

        label2.setTextFill(Color.WHITE);

        label2.setBackground(new Background(new BackgroundFill(Color.web(("0x808080")), new CornerRadii(4), Insets.EMPTY)));

        logsList.getChildren().add(label2);

        Label label3 = new Label("Third log");

        label3.setPrefSize(465, 20);

        label3.setPadding(new Insets(3, 0, 3, 15));

        label3.setFont(new Font("Arial", 12));

        label3.setTextFill(Color.WHITE);

        label3.setBackground(new Background(new BackgroundFill(Color.web(("0x808080")), new CornerRadii(4), Insets.EMPTY)));

        logsList.getChildren().add(label3);
    }

    public void setCabelInfo() {
        cabelName.setText("Cabel1");

        CabelIDValue.setText("888439");

        cabelTypeValue.setText("Copper");

        CabelLocationValue.setText("Third floor");

    }

    public void addCabelsButtons() {

        JFXButton button = new JFXButton();

        button.setPrefWidth(90);
        button.setPrefHeight(30);

        button.setText("Cabel1");

        button.setFont(new Font("Roboto", 14));

        button.setTextFill(Color.WHITE);
        button.setStyle("-fx-background-color: #00A65A");

        cabelButtons.getChildren().add(button);

        JFXButton butto2 = new JFXButton();

        butto2.setPrefWidth(90);
        butto2.setPrefHeight(30);

        butto2.setText("Cabel2");

        butto2.setFont(new Font("Roboto", 14));

        butto2.setTextFill(Color.WHITE);
        butto2.setStyle("-fx-background-color: #00A65A");

        cabelButtons.getChildren().add(butto2);

        JFXButton button3 = new JFXButton();

        button3.setPrefWidth(90);
        button3.setPrefHeight(30);

        button3.setText("Cabel3");

        button3.setFont(new Font("Roboto", 14));

        button3.setTextFill(Color.WHITE);
        button3.setStyle("-fx-background-color: #00A65A");

        cabelButtons.getChildren().add(button3);
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
