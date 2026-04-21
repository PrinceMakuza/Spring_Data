package com.ecommerce.ui.component;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;

public final class DialogBuilder {

    private DialogBuilder() {
    }

    public static void showInfo(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }

    public static void showError(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR, message, ButtonType.OK);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.show();
    }
}
