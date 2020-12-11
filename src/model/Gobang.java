package model;

public class Gobang {

    public double celllen = 30;

    private int XGrid;
    private int YGrid;

    private boolean currentPlayer;

    /**
     * Chess data
     *     0 == 空
     *     1 == 黑
     *     2 == 白
     */
    private int[][] chess;

    private boolean BlackRobot;
    private boolean WhiteRobot;

    public void setChess(int[][] chess)
    {
        this.chess = chess;
    }

    public int getXGrid() {
        return XGrid;
    }

    public int getYGrid() { return YGrid; }

    public int[][] getChess() {
        return chess;
    }

    public boolean getCurrentPlayer() { return currentPlayer; }

    public boolean isCurrentPlayerRobot()
    {
        if(getCurrentPlayer())
        {
            return BlackRobot;
        }
        else
        {
            return WhiteRobot;
        }
    }

    public Gobang(int XGrid, int YGrid, boolean BlackRobot, boolean WhiteRobot)
    {
        this.XGrid = XGrid;
        this.YGrid = YGrid;
        this.currentPlayer = true;
        this.chess = new int[YGrid][XGrid];
        this.BlackRobot = BlackRobot;
        this.WhiteRobot = WhiteRobot;

        if(BlackRobot)
        {
            move((int)(Math.random()*YGrid), (int)(Math.random()*XGrid));
        }
    }

    public void changePlayer()
    {
        this.currentPlayer = !this.currentPlayer;
    }

    public void move(int x, int y)
    {
        if(chess[y][x] == 0)
        {
            if(this.currentPlayer)
            {
                chess[y][x] = 1;
            }
            else {
                chess[y][x] = 2;
            }
            changePlayer();
        }
    }

    public int checkWin()
    {
        int temp = xDirectionCheck();
        if(temp != 0)
        {
            return temp;
        }
        temp = yDirectionCheck();
        if(temp != 0)
        {
            return temp;
        }
        temp = SlashCheck();
        if(temp != 0)
        {
            return temp;
        }
        temp = reverseSlashCheck();
        return temp;
    }

    private int xDirectionCheck()
    {
        for (int i = 0; i < getYGrid(); i++)
        {
            int temp = checkForFiveInARow(chess[i]);
            if(temp != 0)
            {
                return temp;
            }
        }
        return 0;
    }

    private int yDirectionCheck()
    {
        int[] data = new int[getYGrid()];
        for (int i = 0; i < getXGrid(); i++)
        {
            for (int j = 0; j < getYGrid(); j++)
            {
                data[j] = chess[j][i];
            }
            int temp = checkForFiveInARow(data);
            if(temp != 0)
            {
                return temp;
            }
        }
        return 0;
    }

    private int SlashCheck()
    {
        for (int i = 0; i < getYGrid() - 4; i++)
        {
            int temp = checkForFiveInARow(getRightSlashData(i,0));
            if(temp != 0)
            {
                return temp;
            }
        }
        for (int i = 0; i < getXGrid() - 4; i++)
        {
            int temp = checkForFiveInARow(getRightSlashData(0, i));
            if(temp != 0)
            {
                return temp;
            }
        }
        return 0;
    }

    private int reverseSlashCheck()
    {
        int[][] storeChess = chess;
        int[][] chessReverse = new int[chess.length][chess[0].length];
        for (int i = 0; i < chess.length; i++)
        {
            for (int j = 0; j < chess[0].length; j++)
            {
                chessReverse[i][j] = chess[i][chess[0].length - j - 1];
            }
        }
        chess = chessReverse;
        int check = SlashCheck();
        chess = storeChess;
        return check;
    }

    private int checkForFiveInARow(int[] data)
    {
        for (int i = 0; i < data.length - 4; i++)
        {
            if(data[i] != 0)
            {
                if(data[i] == 1)
                {
                    boolean win = true;
                    for (int j = i; j < i + 5; j++)
                    {
                        if (data[j] != 1)
                        {
                            win = false;
                            break;
                        }
                    }
                    if(win) { return 1; }
                }
                else
                {
                    boolean win = true;
                    for (int j = i; j < i + 5; j++)
                    {
                        if(data[j] != 2)
                        {
                            win = false;
                            break;
                        }
                    }
                    if (win) { return 2; }
                }
            }
        }
        return 0;
    }

    private int[] getRightSlashData(int y, int x)
    {
        int[] data = new int[Math.min(getXGrid(),getYGrid())];
        for (int i = 0; i < data.length; i++)
        {
            if(y < getYGrid() && x < getXGrid())
            {
                data[i] = chess[y][x];
                y++;
                x++;
            }
            else
            {
                data[i] = 0;
            }
        }
        return data;
    }
}
