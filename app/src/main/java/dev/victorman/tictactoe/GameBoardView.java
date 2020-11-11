package dev.victorman.tictactoe;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.Log;
import android.view.View;

import java.util.Observable;
import java.util.Observer;

public class GameBoardView extends View implements Observer {

    private final String TAG = GameBoardView.class.getCanonicalName();
    private final GameModel gameModel;
    private Paint paint;
    private Rect[][] rects;
    private float cellWidth;
    private float cellHeight;
    private Rect vbar1;
    private Rect vbar2;
    private Rect hbar1;
    private Rect hbar2;

    public GameBoardView(Context context, GameModel gameModel) {
        super(context);

        this.gameModel = gameModel;
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
                if (gameModel.gameBoard[i][j] == 0) {
                            canvas.drawText("X",((i * 4 + 2) * cellWidth), ((j * 4 + 2) * cellHeight), paint);
                }
                if (gameModel.gameBoard[i][j] == 1) {
                    canvas.drawText("O",((i * 4 + 2) * cellWidth), ((j * 4 + 2) * cellHeight), paint);
                }
            }
        }
    }

    public Point enterPlayerMove(int x, int y) {
//        Log.i(TAG, "move: " + x + " " + y + " player " + currentPlayer);
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                if (rects[i][j].contains(x,y)) {
                   return new Point(i,j);
                }
            }
        }
        return null;
    }

    public void initBoard(float width, float height) {
        for (int i=0;i<3;i++) {
            for (int j=0;j<3;j++) {
                rects[i][j] = new Rect((int)((i * 4 + 1) * width),
                                        (int)((j * 4 + 1) * height),
                                        (int)((i * 4 + 4) * width),
                                        (int)((j * 4 + 4) * height));
            }
        }
    }


    @Override
    public void update(Observable o, Object arg) {

        invalidate();
    }
}
