package view;
import com.google.zxing.WriterException;
import javafx.application.Application;
import javafx.application.HostServices;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import model.QrCodeUtils;
import model.Tool;
import model.conversion.Converse;
import model.conversion.ConverseOnline;
import model.currency.Currencies;
import model.currency.Currency;
import model.currency.CurrencyOffline;
import model.report.Report;
import org.controlsfx.control.WorldMapView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicReference;

/**
 * The main view class
 */
public class View extends Application {
    private Stage primaryStage;
    private Scene scene;
    private Currency currency;
    private Converse converse;
    private Report reportModel;
    private Pane pane = new Pane();

    public View(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * Used to: set up the ‘splash’ image
     * @throws FileNotFoundException
     */
    public void loading() throws FileNotFoundException {
        FileInputStream inputStream = new FileInputStream("src/main/resource/loading.png");
        Image image = new Image(inputStream);
        ImageView view = new ImageView(image);
        view.setFitHeight(400);
        view.setFitWidth(600);

        AnchorPane page = new AnchorPane();
        page.getChildren().addAll(view);

        primaryStage.setTitle("Entering");
        primaryStage.setScene(new Scene(page));
        primaryStage.setWidth(600);
        primaryStage.setHeight(500);
        primaryStage.show();

        new Thread(() -> {
        try {
            Float[] value = new Float[] {0f, 0.1f, 0.2f, 0.3f, 0.4f, 0.5f, 0.6f, 0.7f, 0.8f, 0.9f, 1.0f};
            final Label label = new Label();
            label.setText("progress:");
            final ProgressBar pb = new ProgressBar();
            final ProgressIndicator pin = new ProgressIndicator();
            for (int i = 0; i < 11; i ++) {
                int finalI = i;
                Platform.runLater(() -> {
                    Float values = value[finalI];
                    pb.setProgress(values);
                    final HBox hb = new HBox();
                    hb.setSpacing(20);
                    hb.setAlignment(Pos.CENTER);
                    hb.getChildren().addAll(label, pb, pin);
                    hb.setLayoutX(220);
                    hb.setLayoutY(400);
                    page.getChildren().add(hb);
                });
                Thread.sleep(1364);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Platform.runLater(() -> {
            try {
                primaryStage.hide();
                init();
            } catch (Throwable e) {
                e.printStackTrace();
            }
        });
    }).start();
    }

    /**
     * Used to: set up the homepage content
     * Include all the buttons
     */
    public void init() {
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.show();
        primaryStage.setTitle("Concurrency Conversion App");

        MenuBar menuBar;
        if (converse.getClass() == ConverseOnline.class) menuBar = MenuTool.menus(true);
        else menuBar = MenuTool.menus(false);
        pane.getChildren().add(menuBar);
        MusicPlay.musicSet();
        MusicPlay.musicPlay();

        Button addCurrency = new Button("Add Currency");
        addCurrency.setFont(Font.font(null, 15));
        addCurrency.setLayoutX(20.0);
        addCurrency.setLayoutY(50.0);
        addCurrency.setOnAction((addEvent) -> {addCurrencyPage();});
        pane.getChildren().add(addCurrency);

        Button deleteCurrency = new Button("Delete Currency");
        deleteCurrency.setFont(Font.font(null, 15));
        deleteCurrency.setLayoutX(220.0);
        deleteCurrency.setLayoutY(50.0);
        deleteCurrency.setOnAction(event -> {deleteCurrencyPage();});
        pane.getChildren().add(deleteCurrency);

        Button cleanCurrency = new Button("Clean table");
        cleanCurrency.setFont(Font.font(null, 15));
        cleanCurrency.setLayoutX(420.0);
        cleanCurrency.setLayoutY(50.0);
        cleanCurrency.setOnAction(event -> {
            String result = currency.remove();
            if (result.equals("empty")) AlertTool.alertInformation("Already Empty!");
            else if (result.equals("true")) addTableList();
        });
        pane.getChildren().add(cleanCurrency);

        Button musicStop = new Button("Music Play/Stop");
        musicStop.setFont(Font.font(null, 15));
        musicStop.setLayoutX(660.0);
        musicStop.setLayoutY(450.0);
        musicStop.setOnAction(event -> { MusicPlay.musicPlay();});
        pane.getChildren().add(musicStop);

        Button about = new Button("About");
        about.setFont(Font.font(null, 15));
        about.setLayoutX(20.0);
        about.setLayoutY(450.0);
        about.setOnAction(event -> {MenuTool.aboutPage();});
        pane.getChildren().add(about);

        scene = new Scene(pane);
        primaryStage.setScene(scene);
        addTableList();
    }


    @Override
    public void start(Stage primaryStage) throws Exception {}

    /**
     * Used to: show conversion button
     */
    public void conversion() {
        if (currency.getCurrencies().size() >= 2) {
            Button conversion = new Button("Conversion");
            conversion.setFont(Font.font(null, 15));
            conversion.setLayoutX(620.0);
            conversion.setLayoutY(50.0);
            conversion.setOnAction(event -> {conversionPage();});
            pane.getChildren().add(conversion);
        }
    }

    /**
     * Used to show the add currency page
     */
    private void addCurrencyPage() {
        AtomicReference<Stage> stage = new AtomicReference<>(new Stage());
        if (currency.getClass() == CurrencyOffline.class) {
            Pane paneDummy = new Pane();
            Label choiceLabel = new Label("Country:");
            choiceLabel.setFont(Font.font(null, FontWeight.BOLD, 18));
            choiceLabel.setLayoutX(80);
            choiceLabel.setLayoutY(80);
            paneDummy.getChildren().add(choiceLabel);

            ChoiceBox<String> choiceCountry = new ChoiceBox<>();
            choiceCountry.getItems().add("AU");
            choiceCountry.getItems().add("CN");
            choiceCountry.getItems().add("US");
            choiceCountry.getItems().add("CA");
            choiceCountry.setLayoutX(200.0);
            choiceCountry.setLayoutY(80.0);
            choiceCountry.setOnAction(e-> {
                ProgressIndicator progressIndicator = new ProgressIndicator();
                progressIndicator.setLayoutX(paneDummy.getWidth() / 2);
                progressIndicator.setLayoutY(paneDummy.getHeight() / 2);
                paneDummy.getChildren().add(progressIndicator);
                progressIndicator.setVisible(true);
                new Thread(()-> {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException interruptedException) {
                        interruptedException.printStackTrace();
                    }
                    try {
                        Thread.sleep(2500);
                        String cur = choiceCountry.getValue();
                        String result = currency.addCurrency(cur);
                        Platform.runLater(() -> {
                            afterGetAbbreviation(result);
                            progressIndicator.setVisible(false);
                            stage.get().close();
                        });
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                }).start();
            });
            paneDummy.getChildren().add(choiceCountry);
            Scene scene = new Scene(paneDummy);
            stage.get().setScene(scene);
            stage.get().setWidth(400);
            stage.get().setHeight(300);
        } else {
            Pane paneOnline = new Pane();
            WorldMapView worldMapView = new WorldMapView();
            paneOnline.getChildren().add(worldMapView);
            worldMapView.setOnMouseClicked(country -> {
                ProgressIndicator progressIndicator = new ProgressIndicator();
                progressIndicator.setLayoutX(paneOnline.getWidth()/2);
                progressIndicator.setLayoutY(paneOnline.getHeight()/2);
                progressIndicator.setStyle(" -fx-progress-color: red;");
                paneOnline.getChildren().add(progressIndicator);
                progressIndicator.setVisible(true);
                new Thread(()-> {
                    String current = worldMapView.getSelectedCountries().toString();
                    String result = null;
                    if (current != null) {
                        String cur = current.substring(1, current.length() - 1);
                        result = currency.addCurrency(cur);
                    }
                    String finalResult = result;
                    Platform.runLater(() -> {
                        if (current!= null) { afterGetAbbreviation(finalResult);}
                        progressIndicator.setVisible(false);
                        stage.get().close();
                    });
                }).start();
            });
            Scene scene = new Scene(paneOnline);
            stage.get().setScene(scene);
        }
        stage.get().show();
        stage.get().setTitle("World Map");
    }

    /**
     * Used to check the result about abbreviation
     * @param result result from API
     */
    public void afterGetAbbreviation(String result) {
        if (result == null) AlertTool.alertError("Get API error!");
        else if (result.equals("no currency")) AlertTool.alertWarning("API no chosen currency!");
        else if (result.equals("exist")) AlertTool.alertInformation("Chosen currency existence!");
        else if (result.equals("true")) addTableList();
    }

    /**
     * Used to: show the currency into table list include add new currency
     */
    @SuppressWarnings("unchecked")
    private void addTableList() {
        List<Currencies> currencies = currency.getCurrencies();
        TableView tableView = new TableView();
        TableColumn<Currency, String> column1 = new TableColumn<>("code");
        column1.setCellValueFactory(new PropertyValueFactory<>("code"));

        TableColumn<Currency, String> column2 = new TableColumn<>("name");
        column2.setCellValueFactory(new PropertyValueFactory<>("name"));

        tableView.getColumns().add(column1);
        tableView.getColumns().add(column2);

        for (int i = 0; i < currencies.size(); i ++) tableView.getItems().add(new Currencies(currencies.get(i).code, currencies.get(i).name));

        tableView.setPlaceholder(new Label("No data!"));
        tableView.setLayoutX(150.0);
        tableView.setLayoutY(150.0);
        tableView.setMinWidth(500.0);
        pane.getChildren().add(tableView);
        conversion();
    }

    /**
     * Used to: show the delete currency page
     * Include no chosen currency error handling
     */
    private void deleteCurrencyPage() {
        AtomicReference<Stage> stage = new AtomicReference<>(new Stage());
        Group rootCur = new Group();
        Label nameLabel = new Label("Currency Code:");
        nameLabel.setFont(Font.font(null, FontWeight.BOLD, 18));
        nameLabel.setLayoutX(20);
        nameLabel.setLayoutY(80);
        rootCur.getChildren().add(nameLabel);

        TextField nameText = new TextField();
        nameText.setLayoutX(150);
        nameText.setLayoutY(80);
        rootCur.getChildren().add(nameText);

        Button delete = new Button("Delete");
        delete.setFont(Font.font(null, 15));
        delete.setLayoutX(150.0);
        delete.setLayoutY(120.0);
        rootCur.getChildren().add(delete);
        delete.setOnAction(event1 -> {
            String result = currency.delete(nameText.getText());
            if (result == null) AlertTool.alertWarning("No chosen currency");
            else if (result.equals("true")) {
                addTableList();
                stage.get().close();
            }
        });
        Scene scene = new Scene(rootCur, 350.0, 200.0, Color.WHITE);
        stage.get().setScene(scene);
        stage.get().show();
        stage.get().setTitle("Delete Currency");
        conversion();
    }

    /**
     * Used to: show conversion page
     * Include: conversion and report two functions
     */
    @SuppressWarnings("unchecked")
    private void conversionPage() {
        List<String> currenciesCode = currency.getCurrenciesCode();
        Pane pane1 = new Pane();
        MenuBar menuBar;
        if (converse.getClass() == ConverseOnline.class) menuBar = MenuTool.menus(true);
        else menuBar = MenuTool.menus(false);
        pane1.getChildren().add(menuBar);
        AtomicReference<Stage> stage = new AtomicReference<>(new Stage());

        Label token1 = new Label("From Currency:");
        token1.setFont(Font.font(null, FontWeight.BOLD, 18));
        token1.setLayoutX(50.0);
        token1.setLayoutY(50.0);
        pane1.getChildren().add(token1);

        ChoiceBox choiceBox1 = new ChoiceBox(FXCollections.observableArrayList(currenciesCode));
        choiceBox1.setLayoutX(250.0);
        choiceBox1.setLayoutY(50.0);
        pane1.getChildren().add(choiceBox1);

        Label amount1 = new Label("Amount:");
        amount1.setFont(Font.font(null, FontWeight.BOLD, 18));
        amount1.setLayoutX(450.0);
        amount1.setLayoutY(50);
        pane1.getChildren().add(amount1);

        TextField count1 = new TextField();
        count1.textProperty().addListener((observable, oldValue, newValue) -> {
            if (!newValue.matches("\\d*")) {
                count1.setText(newValue.replaceAll("[^\\d]", ""));
            }
        });
        count1.setLayoutX(550.0);
        count1.setLayoutY(50);
        pane1.getChildren().add(count1);

        Label token2 = new Label("Conversion to Currency:");
        token2.setFont(Font.font(null, FontWeight.BOLD, 18));
        token2.setLayoutX(50.0);
        token2.setLayoutY(200.0);
        pane1.getChildren().add(token2);

        ChoiceBox choiceBox2 = new ChoiceBox(FXCollections.observableArrayList(currenciesCode));
        choiceBox2.setLayoutX(250.0);
        choiceBox2.setLayoutY(200.0);
        pane1.getChildren().add(choiceBox2);

        Button conversionButton = new Button("Conversion");
        conversionButton.setFont(Font.font(null, 18));
        conversionButton.setLayoutX(350.0);
        conversionButton.setLayoutY(250.0);
        conversionButton.setOnAction(event -> {
            if (choiceBox1.getValue() == null || choiceBox2.getValue() == null || count1.getText().length() == 0) AlertTool.alertWarning("Please choose currencies and input amount");
            else {
                ProgressIndicator progressIndicator = new ProgressIndicator();
                progressIndicator.setLayoutX(pane1.getWidth() / 2);
                progressIndicator.setLayoutY(pane1.getHeight() / 2);
                pane1.getChildren().add(progressIndicator);
                progressIndicator.setVisible(true);


                String from = choiceBox1.getValue().toString();
                String to = choiceBox2.getValue().toString();
                String amount = count1.getText();
                AtomicReference<String> mess = new AtomicReference<>("");
                AtomicReference<Boolean> cache = new AtomicReference<>(false);
                AtomicReference<Boolean> dataExist = new AtomicReference<>(false);

                Thread threadMain = new Thread() {
                    @Override
                    public void run() {
                        String rate;
                        String converseResult;
                        if (cache.get() == true){
                            rate = converse.getData(from, to);
                            converseResult = converse.conversion(from, to, amount, true);
                        } else {
                            rate = converse.latest(from, to);
                            converseResult = converse.conversion(from, to, amount, false);
                            Platform.runLater(() -> {
                                if (!dataExist.get() && converse.getClass() == ConverseOnline.class && rate != null && !rate.equals("get failed")) {
                                    if (converse.save(from, to, rate.substring(20))) AlertTool.alertInformation(
                                            "saved rate data into cache");
                                    else AlertTool.alertError("saved rate data into cache failed");
                                }
                            });
                        }
                        Platform.runLater(() -> {
                            if (rate == null) AlertTool.alertError("Get API error!");
                            else if (rate.equals("get failed")) AlertTool.alertWarning("API get failed!");
                            else mess.set("Exchange rate: " + rate);
                            if (converseResult == null) AlertTool.alertError("Get API error!");
                            else if (converseResult.equals("get failed")) AlertTool.alertWarning("API get failed!");
                            else mess.set(mess.get() + '\n' + "Can conversion to: " + converseResult);
                            AlertTool.alertInformation(mess.get());
                            Tool.setReportDate(mess.get());
                            progressIndicator.setVisible(false);
                        });
                    }
                };

                Thread thread = new Thread() {
                    @Override
                    public void run() {
                        String exist = converse.checkData(from, to);
                        Platform.runLater(() -> {
                            if (exist.equals("1")) {
                                dataExist.set(true);
                                Alert a = new Alert(Alert.AlertType.NONE);
                                a.setHeaderText(Tool.cacheData().get(0));
                                a.setContentText(Tool.cacheData().get(1));
                                a.getDialogPane().setPrefSize(400,250);
                                a.getButtonTypes().removeAll();
                                a.getButtonTypes().add(ButtonType.YES);
                                a.getButtonTypes().add(ButtonType.NO);
                                Optional<ButtonType> result = a.showAndWait();
                                ButtonType buttonType = result.get();
                                if (buttonType == ButtonType.YES) cache.set(true);
                                try {
                                    Thread.sleep(1);
                                } catch (InterruptedException e) {
                                    e.printStackTrace();
                                }
                                threadMain.setName("threadMain");
                                threadMain.start();
                            } else {
                                threadMain.setName("threadMain");
                                threadMain.start();
                            }
                        });
                    }
                };

                new Thread(()-> {
                    if (converse.getClass() == ConverseOnline.class) {
                        thread.setName("thread1");
                        thread.start();
                    } else {
                        threadMain.setName("threadMain");
                        threadMain.start();
                    }
                }).start();

            }
        });
        pane1.getChildren().add(conversionButton);

        Button report = new Button("Report");
        report.setFont(Font.font(null, 15));
        report.setLayoutX(20.0);
        report.setLayoutY(520.0);
        pane1.getChildren().add(report);
        report.setOnAction(event -> {
            String reportMess = "";
            String from = choiceBox1.getValue().toString();
            String to = choiceBox2.getValue().toString();
            String amount =  count1.getText();
            if (from != null || to != null || amount != null) {
                List<Currencies> currencies = currency.getCurrencies();
                for (Currencies current: currencies) {
                    if (current.code.equals(from)) reportMess = reportMess + "from: " + current.name + " ("+ from +
                            " )" + "\n";
                }
                for (Currencies current: currencies) {
                    if (current.code.equals(to)) reportMess = reportMess + "to: " + current.name + " ("+ to +
                            " )" + "\n";
                }

                reportMess = reportMess + "starting value: " + amount + "\n" + Tool.getReportData();
                System.out.println(reportMess);

                try {
                    String path = "./report.png";
                    Boolean qrResult = QrCodeUtils.generateQRCodeImage(reportMess, 350, 350, path);
                    if (!qrResult) AlertTool.alertError("Get API error!");
                    else {
                        ProgressIndicator progressIndicator = new ProgressIndicator();
                        progressIndicator.setLayoutX(20);
                        progressIndicator.setLayoutY(450);
                        progressIndicator.setStyle(" -fx-progress-color: red;");
                        pane1.getChildren().add(progressIndicator);
                        progressIndicator.setVisible(true);
                        new Thread(()-> {
                            try {
                                Thread.sleep(2000);
                            } catch (InterruptedException e) {
                                e.printStackTrace();
                            }
                            try {
                                String result = reportModel.sendReport(path);
                                Platform.runLater(() -> {
                                    if (result == null) {
                                        String mess = "something wrong with generate QR link";
                                        Text showText = new Text(100.0, 520.0, mess);
                                        showText.setFont(Font.font(null, 15));
                                        pane1.getChildren().add(showText);
                                    } else {
                                        Hyperlink link = new Hyperlink();
                                        link.setText(result);
                                        link.setOnAction(new EventHandler<ActionEvent>() {
                                            @Override
                                            public void handle(ActionEvent event) {
                                                HostServices hostServices = getHostServices();
                                                hostServices.showDocument(link.getText());
                                            }
                                        });
                                        link.setFont(Font.font(null, 15));
                                        link.setLayoutX(100.0);
                                        link.setLayoutY(520.0);
                                        pane1.getChildren().add(link);
                                    }
                                    progressIndicator.setVisible(false);
                                });
                            } catch (IOException e) {
                                e.printStackTrace();
                            }

                        }).start();
                    }
                } catch (IOException | WriterException e) {
                    e.printStackTrace();
                }
            }
        });

        Scene scene1 = new Scene(pane1);
        stage.get().setScene(scene1);
        stage.get().setTitle("Conversion Currency");
        stage.get().setWidth(800);
        stage.get().setHeight(600);
        stage.get().show();
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public void setConverse(Converse converse) {
        this.converse = converse;
    }

    public void setReport(Report report) {
        this.reportModel = report;
    }

    public Currency getCurrency() {
        return currency;
    }

    public Converse getConverse() {
        return converse;
    }

    public Report getReportModel() {
        return reportModel;
    }
}
