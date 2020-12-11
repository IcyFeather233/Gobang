package view;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import model.Gobang;


public class chessPane extends Pane
{
    public Canvas canvas;
    public GraphicsContext gc;
    public Gobang gobang;

    public chessPane(Gobang gobang)
    {
        this.gobang = gobang;
        drawPane(this.gobang.celllen);
        drawChess(this.gobang.celllen);
        drawCurrentPlayer();
        getChildren().add(canvas);
    }

    public void drawPane(double cell)
    {
        canvas = new Canvas(800,700);
        gc = canvas.getGraphicsContext2D();
        gc.clearRect(0,0,canvas.getWidth(),canvas.getHeight());
        //棋盘底色
        gc.setFill(Color.BURLYWOOD);
        gc.fillRect(100, 100,  (gobang.getYGrid()-1)*cell, (gobang.getXGrid()-1)*cell);
        gc.setStroke(Color.BLACK);
        for(int i = 0; i < gobang.getYGrid() - 1; i++)
        {
            for(int j = 0; j < gobang.getXGrid() - 1; j++)
            {
                gc.strokeRect(100+i*cell,100+cell*j,cell,cell);
            }
        }
        //画天元
        if(gobang.getXGrid()==gobang.getYGrid() && gobang.getXGrid()%2==1) {
            gc.setFill(Color.BLACK);
            gc.fillOval(100+cell*(int)(gobang.getXGrid()/2)-cell/8, 100+cell*(int)(gobang.getYGrid()/2)-cell/8,cell/4,cell/4);
        }
        //外边框加粗
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(3.0f);
        gc.strokeRect(100,100,cell*(gobang.getYGrid()-1),cell*(gobang.getXGrid()-1));
    }

    public void drawChess(double cell)
    {
        int[][] chess = gobang.getChess();

        for (int i = 0; i < gobang.getYGrid(); i++)
        {
            for (int j = 0; j < gobang.getXGrid(); j++)
            {
                if(chess[i][j] != 0)
                {
                    if(chess[i][j] == 1)
                    {
                        gc.setFill(Color.BLACK);
                        gc.fillOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                    }
                    else
                    {
                        gc.setFill(Color.WHITE);

                        gc.fillOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                        gc.strokeOval(100+i*cell-cell/2,100+j*cell-cell/2, cell, cell);
                    }
                }
            }
        }
    }

    public void drawCurrentPlayer()
    {
        double cell = this.gobang.celllen;
        if(this.gobang.getCurrentPlayer())
        {
            gc.setFill(Color.BLACK);
        }
        else
        {
            gc.setFill(Color.WHITE);
        }
        gc.fillOval(100+this.gobang.getYGrid()*cell-cell/2,100, cell, cell);
    }
}
