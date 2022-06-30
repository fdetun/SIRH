package sample.Controllers;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Main extends Application {
    public static Stage stage;

    @Override
    public void start(Stage primaryStage) throws Exception{
        String currentPath = new java.io.File(".").getCanonicalPath();
        System.out.println("Current dir:" + currentPath);

        this.stage=primaryStage;
        FXMLLoader fxml = new FXMLLoader();
        fxml.setLocation(getClass().getResource("/Views/Login.fxml"));
        primaryStage.setTitle("login page ");
        primaryStage.setScene(new Scene(fxml.load(),700, 500));
        //LoginController controller=fxml.getController();
        //controller.Main(stage);
        primaryStage.setResizable(false);
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        LoginController.loginStage=primaryStage;

    }


    public static void main(String[] args) {
        launch(args);
    }
}
