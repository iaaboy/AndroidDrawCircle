package dunkin.androiddrawcircle;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }
}

//ImageView를 상속 받아 activity_main.xml에 custom view를 그린다.
class MyView extends ImageView {
    public int pos= 0;
    final String TAG = "MyView";
    MyView mMyview;
    Handler mHandler;
    int mAngle;

    public MyView(Context context, AttributeSet att) {
        super(context, att);
        init();
    }

    public void init(){
        mMyview = (MyView)findViewById(R.id.imageView);
        mHandler = new Handler();
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int x = (int)event.getX();
        int y = (int)event.getY();

        int centerX = getWidth()/2;
        int centerY = getHeight()/2;

        //중심점과 touch한 점 사이의 각도를 구한다.
        mAngle = (int)getAngle(centerX, centerY, x, y);
        Log.d(TAG, "angle " + mAngle);

        //1초 후 화면이 사라지도록 view animte
        mMyview.setAlpha(1f);
        mMyview.invalidate();
        mMyview.animate().alpha(0f).setDuration(1000);

        return super.onTouchEvent(event);

    }

    private static double getAngle(int x1,int y1, int x2,int y2){
        int dx = x2 - x1;
        int dy = y2 - y1;

        double rad= Math.atan2(dx, dy);
        double degree = (rad*180)/Math.PI ;

        degree = 180 + degree;

        return degree;
    }

    @Override
    public void onDraw(Canvas canvas) {


        Log.d(TAG, "It is called onDraw");
        Paint Pnt = new Paint();
        RectF rect = new RectF();
        Pnt.setColor(Color.BLUE);

        // mAngle로부터 시작하여 30도 만큼 크기 부채꼴 그리기
        float x,y,size;
        x = getWidth()/ 2;
        y = getHeight()/ 2;

        Log.d(TAG, "x y" + x + " " + y);
        size = 300;
        rect.set(x, x + size, y, y + size);
        float startAngle, sweepAngle;
        startAngle = -(mAngle-23);
        sweepAngle = 45;
        canvas.drawArc(rect, startAngle, sweepAngle, true, Pnt);

    }
}

