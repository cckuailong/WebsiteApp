package view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.widget.ImageView;

/**
 * Created by Administrator on 2015/9/23.
 */
public class CircleImageView extends ImageView {
    public CircleImageView(Context context) {
        super(context);
        init(null);
    }

    public CircleImageView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(attrs);
    }

    public CircleImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(attrs);
    }

    private void init(AttributeSet attrs) {

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        if (drawable instanceof BitmapDrawable) {
            Bitmap bmp = ((BitmapDrawable) drawable).getBitmap();
            if (bmp != null) {
                bmp = getCircleBitmap(bmp);
                canvas.drawBitmap(bmp, 0, 0, null);
                return;
            }
        }
        super.onDraw(canvas);
        
    }

    private Bitmap getCircleBitmap(Bitmap src) {
        int w = getWidth();
        int h = getHeight();
        int size = w > h ? h : w;
        Bitmap output = Bitmap.createBitmap(size, size, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        canvas.drawARGB(0, 0, 0, 0);
        Paint p = new Paint();
        p.setAntiAlias(true);
        p.setColor(Color.WHITE);
        canvas.drawCircle(size / 2, size / 2, size / 2, p);
        p.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        Rect srcRect = new Rect(0, 0, src.getWidth(), src.getHeight());
        RectF dstRect = new RectF(0, 0, size, size);
        canvas.drawBitmap(src, srcRect, dstRect, p);
        return output;
    }
}
