package view;

import javafx.scene.control.Alert;

/**
 * All the alert pop-up
 * helper functions: make the different type alert easier
 */
public class AlertTool {
    /**
     * Used to: show information type alert
     * @param information alert content
     */
    public static void alertInformation(String information) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setContentText(information);
        a.showAndWait();
    }

    /**
     * Used to: show warning type alert
     * @param warning alert content
     */
    public static void alertWarning(String warning) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.WARNING);
        a.setContentText(warning);
        a.showAndWait();
    }

    /**
     * Used to: show error type alert
     * @param error alert content
     */
    public static void alertError(String error) {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.ERROR);
        a.setContentText(error);
        a.showAndWait();
    }
}
