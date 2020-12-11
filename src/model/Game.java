package model;

import control.Controller;
import javafx.application.Application;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;
import view.chessPane;


public class Game extends Application {

    private int xGrid = 15;
    private int yGrid = 15;

    private boolean RobotBlack = false;
    private boolean RobotWhite = false;

    @FXML
    private Button RobotBlackButton;

    @FXML
    private Button RobotWhiteButton;

    @FXML
    private TextField XGrid;

    @FXML
    private TextField YGrid;

    @Override
    public void start(Stage stage) throws Exception {

        Parent root = FXMLLoader.load(getClass().getResource("../view/start.fxml"));
        stage.setTitle("五子棋");
        stage.setScene(new Scene(root, 700, 400));
        stage.show();


    }

    public void okMouseClicked(MouseEvent mouseEvent) throws Exception {

        try{
            xGrid = Integer.parseInt(XGrid.getText());
        } catch (Exception e)
        {

        }

        try{
            yGrid = Integer.parseInt(YGrid.getText());
        } catch (Exception e)
        {

        }

        startGame(new Stage());

    }

    public void BlackRobotClicked(MouseEvent mouseEvent) throws Exception {
        RobotBlack = !RobotBlack;

        if(RobotBlack)
        {
            RobotBlackButton.setText("黑方为Robot: 是");
            if(RobotWhite) {
                RobotWhite = !RobotWhite;
                RobotWhiteButton.setText("白方为Robot: 否");
            }
        }
        else
        {
            RobotBlackButton.setText("黑方为Robot: 否");
        }

    }

    public void WhiteRobotClicked(MouseEvent mouseEvent) throws Exception {
        RobotWhite = !RobotWhite;

        if(RobotWhite)
        {
            RobotWhiteButton.setText("白方为Robot: 是");
            if(RobotBlack) {
                RobotBlack = !RobotBlack;
                RobotBlackButton.setText("黑方为Robot: 否");
            }
        }
        else
        {
            RobotWhiteButton.setText("白方为Robot: 否");
        }

    }

    private void startGame(Stage stage) throws Exception {
        Gobang gobang = new Gobang(xGrid, yGrid, RobotBlack, RobotWhite);
        chessPane chessPane = new chessPane(gobang);
        Robot robot = new Robot(gobang);

        chessPane.setOnMouseClicked(new Controller(gobang, chessPane, robot));

        stage.setTitle("五子棋");

        stage.setScene(new Scene(chessPane, 200 + gobang.celllen * (yGrid - 1), 200 + gobang.celllen * (xGrid - 1)));
        stage.show();

    }

}