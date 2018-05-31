package numberrain;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

import com.example.app2.R;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/22
 */
public class NumberRainItem extends View {
    private Paint paint;
    private Context context;
    private float textSize = 15;
    private int startOffset = 0;
    private int hightLightColor = Color.GREEN;
    private int normalColor = Color.RED;
    private int nowHeight = 0;
    private int hightLightNumIndex = 0;

    public NumberRainItem(Context context) {
        this(context, null);
    }

    public NumberRainItem(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberRainItem(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        parseAttrs(attrs);
        initPaint();
    }

    private void parseAttrs(AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRainItem);
        hightLightColor = typedArray.getColor(R.styleable.NumberRainItem_hightLightColor, Color.GREEN);
        normalColor = typedArray.getColor(R.styleable.NumberRainItem_normalColor, Color.RED);
        textSize = typedArray.getDimension(R.styleable.NumberRainItem_textSize, textSize);
        startOffset = typedArray.getInt(R.styleable.NumberRainItem_startOffset, startOffset);
        typedArray.recycle();
    }

    private void initPaint() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        configPaint();
        if (isShowAllNumber()) {
            drawTotalNumbers(canvas);
        } else {
            drawPartNumbers(canvas);
        }
    }

    private void drawPartNumbers(Canvas canvas) {
        int count = (int) (nowHeight / textSize);
        nowHeight += textSize;
        drawNumbers(canvas, count);
    }

    private boolean isShowAllNumber() {
        return nowHeight >= getHeight();
    }

    private void drawTotalNumbers(Canvas canvas) {
        int count = (int) (getHeight() / textSize);
        drawNumbers(canvas, count);
    }

    private void drawNumbers(Canvas canvas, int count) {
        if (count == 0) {
            postInvalidateDelayed(startOffset);
        } else {
            int offSet = 0;
            for (int i = 0; i < count; i++) {
                String text = String.valueOf(Math.random() * 9);
                if (hightLightNumIndex == i) {
                    paint.setColor(hightLightColor);
                    paint.setShadowLayer(10f, 0f, 0f, hightLightColor);
                } else {
                    paint.setColor(normalColor);
                    paint.setShadowLayer(10f, 0f, 0f, normalColor);
                }
                canvas.drawText(text, 0f, textSize + offSet, paint);
                offSet += textSize;
            }
            if (!isShowAllNumber()) {
                hightLightNumIndex++;
            } else {
                hightLightNumIndex = (++hightLightNumIndex) % count;
            }
            postInvalidateDelayed(1l);
        }
    }

    private void configPaint() {
        paint.setTextSize(textSize);
        paint.setColor(normalColor);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setTextSize(float size) {
        textSize = size;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setNormalColor(int normal) {
        normalColor = normal;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setStartOffset(int start) {
        startOffset = start;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    public void setHightLightColor(int hightLight) {
        hightLightColor = hightLight;
        if (isAttachedToWindow()) {
            postInvalidate();
        }
    }
}
