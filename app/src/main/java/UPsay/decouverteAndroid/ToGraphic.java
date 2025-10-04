package UPsay.decouverteAndroid;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;
import android.os.Handler;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class ToGraphic extends View {

    Handler timerHandler = new Handler();
    float xText, yText;
    int cpt;
    public ToGraphic(Context context, @Nullable AttributeSet attrs) {

        super(context, attrs);
        setXYText(550,1200);
        timerHandler.postDelayed(updateTimerThread, 10);
        Toast.makeText(getContext(),"Bonjour", Toast.LENGTH_SHORT).show();
    }

    Runnable updateTimerThread = new Runnable() {
        public void run() {
            timerHandler.postDelayed(this, 1000);
            cpt++;
            invalidate(); // appel de onDraw pour redessiner
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
        canvas.drawText(scpt, xText, yText+100, p);
        Bitmap b = BitmapFactory.decodeResource(getResources(), R.drawable.jack_o_lantern);
        b = Bitmap.createBitmap(b,0,0,310,310);
        canvas.drawBitmap(b, 380,450,p);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event){
        xText = event.getX();
        yText = event.getY();
        invalidate();
        return false;
    }

}
