package UPsay.decouverteAndroid;

import static androidx.core.content.ContextCompat.getSystemService;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.os.Handler;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ToGraphic extends View {

    Handler timerHandler = new Handler();
    float xText, yText;
    int acc_x, acc_y;
    int cpt;
    public ToGraphic(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        setXYText(550,1200);

        timerHandler.postDelayed(updateTimerThread, 10);
        setOnTouchListener(onTouchListener);
        Sensor	accelerometre;
        SensorManager	m	=	(SensorManager)	context.getSystemService(Context.SENSOR_SERVICE);
        accelerometre	=	m.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        m.registerListener(mSensorEventListener,	accelerometre, SensorManager.SENSOR_DELAY_UI);
    }



    Runnable updateTimerThread = new Runnable() {
        public void run() {
            timerHandler.postDelayed(this, 1000);
            cpt++;
            invalidate(); // appel de onDraw pour redessiner
        }
    };

    OnTouchListener onTouchListener = new OnTouchListener() {
        float x1, x2, y1, y2;
        @Override
        public boolean onTouch(View v, MotionEvent event) {
//            float dx, dy;
//            String direction;
//            switch(event.getAction()) {
//                case(MotionEvent.ACTION_DOWN):
//                    x1 = event.getX();
//                    y1 = event.getY();
//                    Log.i("pacman", "appuyé");
//                    break;
//                case(MotionEvent.ACTION_UP): {
//                    x2 = event.getX();
//                    y2 = event.getY();
//                    dx = x2-x1;
//                    dy = y2-y1;
//                    // Use dx and dy to determine the direction of the move
//                    if(Math.abs(dx) > Math.abs(dy)) {
//                        if(dx>0)
//                            direction = "right";
//                        else
//                            direction = "left";
//                    } else {
//                        if(dy>0)
//                            direction = "down";
//                        else
//                            direction = "up";
//                    }
//                    Log.i("pacman", "laché " + direction);
//                    Log.i("pacman", "dx = " + dx +"; dy = " + dy);
//                    break;
//                }
//            }
//            invalidate();
            xText = event.getX();
            yText = event.getY();
            Toast.makeText(getContext(),"Bonjour", Toast.LENGTH_SHORT).show();
            invalidate();
            return true;
        }
    };

    final SensorEventListener mSensorEventListener	=	new SensorEventListener()	{
        public	void	onAccuracyChanged(Sensor	sensor,	int	accuracy)	{
        }
        public	void	onSensorChanged(SensorEvent sensorEvent){
            //	Que	faire	en	cas	d'évènements	sur	le	capteur	?
            acc_x=-(int)(sensorEvent.values[0]);
            acc_y=(int)(sensorEvent.values[1]);
            xText+=acc_x;
            yText+=acc_y;
            invalidate();

        }
    };

    public void setXYText (float x, float y){
        xText = x;
        yText = y;
    }

    @Override
    public void onDraw(@NonNull Canvas canvas) {
        super.onDraw(canvas);
        Paint p = new Paint();
        /*définir la couleur de l’objet de dessin */
        p.setColor(Color.BLACK);
        /*définir son style en remplissage*/
        p.setStyle(android.graphics.Paint.Style.FILL);
        /*dessiner un rectangle qui occupe la totalité du View*/
        canvas.drawRect(0,0,getWidth(),getHeight(), p);
        /*définir une autre couleur pour dessiner un texte*/
        p.setColor(Color.GREEN);
        /*définir la taille du texte*/
        p.setTextSize(100);
        /*définir le centre du texte comme étant son origine*/
        p.setTextAlign(android.graphics.Paint.Align.CENTER);
        /*dessiner le texte en positionnant son origine au centre du View */
        String texte = "Bonjour MONDE";
        canvas.drawText(texte, xText, yText, p);
        String scpt= String.valueOf(cpt);
        String sacc = String.format("x=%d    y=%d",acc_x,acc_y);
        canvas.drawText(scpt, xText, yText+100, p);
        canvas.drawText(sacc, xText, yText+200, p);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.jack_o_lantern);
        b = Bitmap.createBitmap(b,0,0,280,280);
        canvas.drawBitmap(b, 380,450,p);
    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event){
//        xText = event.getX();
//        yText = event.getY();
//        invalidate();
//        Toast.makeText(getContext(),"Bonjour", Toast.LENGTH_SHORT).show();
//        return false;
//    }
}
