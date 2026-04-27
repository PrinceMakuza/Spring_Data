package com.ecommerce.ui;

import com.ecommerce.SmartECommerceApplication;
import com.ecommerce.ui.screen.DashboardController;
import com.ecommerce.ui.screen.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

import java.io.IOException;

/**
 * Dedicated JavaFX entry point.
 * Starts a non-web Spring context for UI-side bean wiring only.
 */
public class FrontendApplication extends Application {

    private Stage primaryStage;
    private Scene mainScene;
    private ConfigurableApplicationContext springContext;
    private static FrontendApplication instance;

    public static FrontendApplication getInstance() {
        return instance;
    }

    @Override
    public void init() {
        springContext = new SpringApplicationBuilder(SmartECommerceApplication.class)
            .web(WebApplicationType.NONE)
            .profiles("dev")
            .properties("spring.main.web-application-type=none")
            .run();
    }

    @Override
    public void start(Stage primaryStage) {
        instance = this;
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Smart E-Commerce System");

        showLogin();

        primaryStage.setMinWidth(900);
        primaryStage.setMinHeight(600);
        primaryStage.centerOnScreen();
        primaryStage.show();
    }

    private void showLogin() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/login.fxml"));
            Parent root = loader.load();

            LoginController controller = loader.getController();
            controller.setOnLoginSuccess(this::showDashboard);

            if (mainScene == null) {
                mainScene = new Scene(root, 1050, 700);
                applyStyles(mainScene);
                primaryStage.setScene(mainScene);
            } else {
                mainScene.setRoot(root);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showDashboard() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/dashboard.fxml"));
            Parent root = loader.load();

            DashboardController controller = loader.getController();
            controller.setOnLogout(this::showLogin);

            mainScene.setRoot(root);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void applyStyles(Scene scene) {
        String cssPath = getClass().getResource("/css/styles.css") != null
            ? getClass().getResource("/css/styles.css").toExternalForm()
            : null;
        if (cssPath != null) {
            scene.getStylesheets().add(cssPath);
        }
    }

    @Override
    public void stop() {
        if (springContext != null) {
            springContext.close();
        }
        Platform.exit();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
