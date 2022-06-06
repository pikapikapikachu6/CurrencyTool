package controller;

import javafx.stage.Stage;
import model.Tool;
import model.conversion.Converse;
import model.conversion.ConverseOffline;
import model.conversion.ConverseOnline;
import model.conversion.DataSQL;
import model.currency.Currency;
import model.currency.CurrencyOffline;
import model.currency.CurrencyOnline;
import model.report.Report;
import model.report.ReportOffline;
import model.report.ReportOnline;
import view.View;

import java.io.FileNotFoundException;

/**
 * Controller part
 */
public class Controller {
    public View view;

    /**
     * Based on the online / offline to generated model
     * generated view
     * @param primaryStage stage
     * @param input input mode type
     * @param output output mode type
     * @throws FileNotFoundException
     */
    public Controller(Stage primaryStage, String input, String  output) throws FileNotFoundException {
        this.view = new View(primaryStage);
        Tool tool = new Tool();
        DataSQL dataSQL = new DataSQL();
        Currency currency = new CurrencyOnline(tool);
        Converse converse = new ConverseOnline(tool, dataSQL);
        Report report = new ReportOnline();
        if (input.equalsIgnoreCase("offline")) {
            currency = new CurrencyOffline();
            converse = new ConverseOffline();
        }
        if (output.equalsIgnoreCase("offline")) report = new ReportOffline();
        view.setCurrency(currency);
        view.setConverse(converse);
        view.setReport(report);
    }

    /**
     * used to show front-end, the first page is loading entering
     * @throws FileNotFoundException
     */
    public void display() throws FileNotFoundException {
        view.loading();
    }
}
