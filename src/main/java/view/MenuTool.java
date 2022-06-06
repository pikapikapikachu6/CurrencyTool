package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import model.Tool;

/**
 * The menu contents
 */
public class MenuTool {
    /**
     * Used to: generated MenuBar
     * @param online the mode is online or not influenced show the cache button or not
     * @return MenuBar
     */
    public static MenuBar menus(Boolean online) {
        MenuBar menuBar = new MenuBar();
        menuBar.setPrefSize(800,30);

        Menu help = new Menu("Help");
        MenuItem aboutItm = new MenuItem("About");
        aboutItm.setOnAction((event)->aboutPage());
        help.getItems().addAll(aboutItm);
        menuBar.getMenus().add(help);

        if (online) {
            Menu clean = new Menu("Cache");
            MenuItem cleanItem = new MenuItem("Clean Cache");
            cleanItem.setOnAction((event -> {
                System.out.println("clean here");
                Tool.cleanCache();
                AlertTool.alertInformation("Clean Finished!");
            }));
            clean.getItems().addAll(cleanItem);
            menuBar.getMenus().add(clean);
        }

        return menuBar;
    }

    /**
     * Used to generated about page
     */
    static void aboutPage() {
        Alert a = new Alert(Alert.AlertType.NONE);
        a.setAlertType(Alert.AlertType.INFORMATION);
        a.setTitle("About");
        a.setHeaderText(Tool.aboutData().get(0));
        a.setContentText(Tool.aboutData().get(1));
        a.getDialogPane().setPrefSize(400,250);
        a.showAndWait();
    }
}
