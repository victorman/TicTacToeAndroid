package dev.victorman.tictactoe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

public class MainActivity extends AppCompatActivity {

    private static final String DEBUG_TAG = MainActivity.class.getCanonicalName();
    private Context context;
    private ConstraintLayout constraintLayout;
    private boolean end = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = getApplicationContext();
        constraintLayout = new ConstraintLayout(context);

        final GameBoardView view = new GameBoardView(context);
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
                            view.resetBoard();
                            view.invalidate();
                            end = false;
                        } else {
                            view.enterPlayerMove(x,y);
                            end = view.checkEnd();
                        }
                        break;

                }
                view.invalidate();

                return true;
            }
        });
        constraintLayout.addView(view);


        setContentView(constraintLayout);
    }


}