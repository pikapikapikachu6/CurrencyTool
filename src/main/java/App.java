import controller.*;
import javafx.application.Application;
import javafx.stage.Stage;

public class App extends Application{
    private static String input;
    private static String output;

    /**
     *  check the args arguments legal or not
     */
    public static void main(String[] args) {
        if (args.length != 2) throw new RuntimeException("Incorrect number of arguments given");
        if (args[0].equals("online")) input = "online";
        else if (args[0].equals("offline")) input = "offline";
        else throw new RuntimeException("Input API must be online or offline");

        if (args[1].equals("online")) output = "online";
        else if (args[1].equals("offline")) output = "offline";
        else throw new RuntimeException("Input API must be online or offline");

        Application.launch(args);
    }

    /**
     * Used to: generated the APP controller and start to show front end
     * @param stage the primary satge
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Controller controller = new Controller(stage, input, output);
        controller.display();
    }
}
