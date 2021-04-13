/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tempreatureporject;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author danml
 */
public class Main extends Application implements ExecutorService {

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("FXMLDoc.fxml"));

        Scene scene = new Scene(root);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        scheduledExecutorServiceChart.shutdownNow();
        scheduledExecutorServiceLastTemp.shutdownNow();
        scheduledExecutorServiceWarnings.shutdownNow();
        scheduledExecutorServiceWarningsList.shutdownNow();
        scheduledExecutorServiceLogsList.shutdownNow();
    }

}
