package control;

import javafx.event.EventHandler;
import javafx.scene.control.Alert;
import javafx.scene.input.MouseEvent;
import model.Gobang;
import model.Robot;
import view.chessPane;


public class Controller implements EventHandler<MouseEvent> {

    private Gobang gobang;
    private chessPane chessPane;
    private Robot robot;

    public Controller(Gobang gobang, view.chessPane chessPane, Robot robot)
    {
        this.chessPane = chessPane;
        this.gobang = gobang;
        this.robot = robot;
    }

    @Override
    public void handle(MouseEvent mouseEvent) {

        double cell = this.gobang.celllen;

        double x = mouseEvent.getX();
        double y = mouseEvent.getY();

        int j = (int)((x-100+cell/2)/cell);
        int i = (int)((y-100+cell/2)/cell);

        try
        {
            gobang.move(i,j);
            chessPane.drawChess(cell);
            chessPane.drawCurrentPlayer();
        } catch (Exception ignored)
        {

        }

        int win = gobang.checkWin();
        if(win != 0)
        {
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("END");
            alert.setHeaderText("游戏结束");
            if(win == 1)
            {
                alert.setContentText("黑方胜利");
            }
            if(win == 2)
            {
                alert.setContentText("白方胜利");
            }
            alert.showAndWait();
        }
        else
        {
            if(gobang.isCurrentPlayerRobot())
            {
                if(this.gobang.getCurrentPlayer())
                {
                    robot.AI(2);
                }
                else
                {
                    robot.AI(1);
                }
                gobang.move(robot.getY(),robot.getX());
                chessPane.drawChess(cell);
                chessPane.drawCurrentPlayer();
            }

            win = gobang.checkWin();
            if(win != 0)
            {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("END");
                alert.setHeaderText("游戏结束");
                if(win == 1)
                {
                    alert.setContentText("黑方胜利");
                }
                if(win == 2)
                {
                    alert.setContentText("白方胜利");
                }
                alert.showAndWait();
            }
        }

    }
}
