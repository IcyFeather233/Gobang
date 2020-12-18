package model;

import java.util.HashMap;

public class Robot {

    private int[][] chess;
    private int[][] chessValue;
    private int x,y;

    public Robot(Gobang gobang)
    {
        this.chess = gobang.getChess();
        this.chessValue = new int[gobang.getYGrid()][gobang.getXGrid()];
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    private HashMap<String, Integer> hm = new HashMap<String, Integer>();

    public void setValue() {
        hm.put("1", 20);
        hm.put("11", 200);
        hm.put("111", 2000);
        hm.put("1111", 3000);
        hm.put("12", 10);
        hm.put("112", 100);
        hm.put("1112", 1000);
        hm.put("11112", 2000);
    }

    public void AI(int plaColor) {
        setValue();
        for (int i = 0; i < chess.length; i++) {
            for (int j = 0; j < chess[i].length; j++) {
                if (chess[i][j] == 0) {
                    String code = "";
                    int color = 0;
                    // 向右
                    for (int k = i + 1; k < chess.length; k++) {
                        if (chess[k][j] == 0) {
                            break;
                        } else {
                            if (color == plaColor) { // 右边第一颗棋子
                                color = chess[k][j]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[k][j] == plaColor) { // 右边第二，三..同颜色棋子
                                code += 1; // 保存棋局
                            } else { // 右边不同颜色
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    Integer value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value;// 权值累加
                    }

                    // 向左
                    for (int k = i - 1; k >= 0; k--) {
                        if (chess[k][j] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[k][j]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[k][j] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }

                    // 向下
                    for (int k = j + 1; k < chess[i].length; k++) {
                        if (chess[i][k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i][k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i][k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }

                    // 向上
                    for (int k = j - 1; k > 0; k--) {
                        if (chess[i][k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i][k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i][k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }
                    // 左斜向上
                    for (int k = 1; i-k >0&&j-k>0 ; k++) {
                        if (chess[i-k][j-k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i-k][j-k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i-k][j-k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }
                    // 右斜向下
                    for (int k = 1; i+k<chess.length&&j+k<chess[i].length ; k++) {
                        if (chess[i+k][j+k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i+k][j+k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i+k][j+k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }
                    // 右斜向上
                    for (int k = 1; j-k>0&&i+k<chess.length ; k++) {
                        if (chess[i+k][j-k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i+k][j-k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i+k][j-k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }
                    // 左斜向下
                    for (int k = 1; j+k<chess[i].length&&i-k>0; k++) {
                        if (chess[i-k][j+k] == 0) {
                            break;
                        } else {
                            if (color == plaColor) {
                                color = chess[i-k][j+k]; // 保存颜色
                                code += 1; // 保存棋局
                            } else if (chess[i-k][j+k] == plaColor) {
                                code += 1; // 保存棋局
                            } else {
                                code += 2; // 保存棋局
                                break;
                            }
                        }
                    }
                    // 根据code取出hm对应的权值
                    value = hm.get(code);
                    if (value != null) {
                        chessValue[i][j] += value; // 权值累加
                    }
                }
                else{
                    chessValue[i][j] = 0;
                }
            }

        }
        int max=0;
        for(int i = 0; i<chessValue.length-1;i++){
            for(int j=0; j<chessValue[i].length-1; j++){
                if(max<chessValue[i][j]){
                    max = chessValue[i][j];
                    x=i;
                    y=j;
                }
            }
        }
    }
}
