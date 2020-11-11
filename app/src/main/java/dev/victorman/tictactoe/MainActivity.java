package dev.victorman.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = MainActivity.class.getCanonicalName();
    private static final String TAG = MainActivity.class.getCanonicalName();
    private Context context;
    private ConstraintLayout constraintLayout;
    private boolean end = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        constraintLayout = new ConstraintLayout(context);

        final GameModel gameModel = new GameModel();
        final GameBoardView view = new GameBoardView(context);
        gameModel.addObserver(view);

        view.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT));



        view.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int x = (int) event.getX();
                int y = (int) event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_MOVE:
                    case MotionEvent.ACTION_DOWN:
                        break;
                    case MotionEvent.ACTION_UP:

                        if (end) {
                            gameModel.resetBoard();
                            end = false;
                        } else {
                            Point p =  view.enterPlayerMove(x,y);
                            if(p != null) {
                                gameModel.enterPlayerMove(p.x, p.y);
                                end = gameModel.checkEnd();
                            }
                        }
                        break;

                }

                return true;
            }
        });
        constraintLayout.addView(view);


        setContentView(constraintLayout);
    }


}