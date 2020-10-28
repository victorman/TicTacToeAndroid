package dev.victorman.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

public class GameBoardView extends View {

    private final String TAG = GameBoardView.class.getCanonicalName();
    private Bitmap verticalFenceBitmap;
    private Bitmap horizontalFenceBitmap;
    private Paint paint;
    private int[][] gameBoard;
    private Rect[][] rects;
    private int currentPlayer;
    private float cellWidth;
    private float cellHeight;
    private Rect vbar1;
    private Rect vbar2;
    private Rect hbar1;
    private Rect hbar2;

    public GameBoardView(Context context) {
        super(context);
        gameBoard = new int[3][3];

        currentPlayer = 0;

        //
        paint = new Paint();
        paint.setStrokeWidth(2.0f);
        paint.setColor(Color.BLACK);
        paint.setTextSize(80.0f);
    }



    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        cellWidth = getWidth() / 13.0f;
        cellHeight = getHeight() / 13.0f;
        if (vbar1 == null)
            vbar1 = new Rect((int)(cellWidth * 4.0f), (int)(cellHeight * 1.0f), (int)(cellWidth * 5.0f), (int)(cellHeight * 12.0f));
        if (vbar2 == null)
            vbar2 = new Rect((int)(cellWidth * 8.0f), (int)(cellHeight * 1.0f), (int)(cellWidth * 9.0f), (int)(cellHeight * 12.0f));
        if (hbar1 == null)
            hbar1 = new Rect((int)(cellWidth * 1.0f), (int)(cellHeight * 4.0f), (int)(cellWidth * 12.0f), (int)(cellHeight * 5.0f));
        if (hbar2 == null)
            hbar2 = new Rect((int)(cellWidth * 1.0f), (int)(cellHeight * 8.0f), (int)(cellWidth * 12.0f), (int)(cellHeight * 9.0f));

        if (rects == null) {
            rects = new Rect[3][3];
            initBoard(cellWidth, cellHeight);
        }
        canvas.drawRect(vbar1, paint);
        canvas.drawRect(vbar2, paint);
        canvas.drawRect(hbar1, paint);
        canvas.drawRect(hbar2, paint);

        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (gameBoard[i][j] == 0) {
                            canvas.drawText("X",((i * 4 + 2) * cellWidth), ((j * 4 + 2) * cellHeight), paint);
                }
                if (gameBoard[i][j] == 1) {
                    canvas.drawText("O",((i * 4 + 2) * cellWidth), ((j * 4 + 2) * cellHeight), paint);
                }
            }
        }
    }

    public void enterPlayerMove(int x, int y) {
//        Log.i(TAG, "move: " + x + " " + y + " player " + currentPlayer);
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (rects[i][j].contains(x,y)) {
                    Log.i(TAG, "move: " + i + " " + j + " player " + currentPlayer);
                    gameBoard[i][j] = currentPlayer;
                    currentPlayer = (currentPlayer+1) % 2;
                }
            }
        }

    }

    public void initBoard(float width, float height) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                gameBoard[i][j] = -1;
                rects[i][j] = new Rect((int)((i * 4 + 1) * width),
                                        (int)((j * 4 + 1) * height),
                                        (int)((i * 4 + 4) * width),
                                        (int)((j * 4 + 4) * height));
            }
        }
    }

    public boolean checkWin() {
        boolean win = false;
        win = checkH();
        if (win)
            return true;
        win = checkV();
        if (win)
            return true;
        win = checkD();
        return win;
    }

    private boolean checkD() {
        if ((gameBoard[0][0] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][2]) && (gameBoard[0][0] == 0 || gameBoard[0][0] == 1)) {
            return true;
        }
        if ((gameBoard[0][2] == gameBoard[1][1]) && (gameBoard[1][1] == gameBoard[2][0]) && (gameBoard[0][2] == 0 || gameBoard[0][2] == 1)) {
            return true;
        }
        return false;
    }

    private boolean checkV() {
        for(int i=0;i<3;i++) {
            if ((gameBoard[0][i] == gameBoard[1][i]) && (gameBoard[1][i] == gameBoard[2][i]) && (gameBoard[0][i] == 0 || gameBoard[0][i] == 1)) {
                return true;
            }
        }
        return false;
    }

    private boolean checkH() {
        for(int i=0;i<3;i++) {
            if ((gameBoard[i][0] == gameBoard[i][1]) && (gameBoard[i][1] == gameBoard[i][2]) && (gameBoard[i][0] == 0 || gameBoard[i][0] == 1)) {
                return true;
            }
        }
        return false;
    }

    public void resetBoard() {
        currentPlayer = 0;
        rects = null;
    }
}
