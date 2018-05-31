package numberrain;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import com.example.app2.R;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/22
 */

public class NumberRain extends LinearLayout {
    private int normalColor = Color.GREEN;
    private int hightLightColor = Color.YELLOW;
    private float textSize = 15;
    private Context context;

    public NumberRain(Context context) {
        this(context, null);
    }

    public NumberRain(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NumberRain(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        parseAttrs(context, attrs);
    }

    private void parseAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.NumberRain);
        normalColor = typedArray.getColor(R.styleable.NumberRain_normalColor, Color.GREEN);
        hightLightColor = typedArray.getColor(R.styleable.NumberRain_hightLightColor, Color.RED);
        textSize = typedArray.getDimension(R.styleable.NumberRain_textSize, textSize);
        typedArray.recycle();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            addRainItems();
        }
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @RequiresApi(api = Build.VERSION_CODES.KITKAT)
    private void addRainItems() {
        int count = (int) (getMeasuredWidth() / textSize);
        for (int i = 0; i < count; i++) {
            NumberRainItem numberRainItem = new NumberRainItem(context);
            numberRainItem.setTextSize(textSize);
            numberRainItem.setHightLightColor(hightLightColor);
            numberRainItem.setNormalColor(normalColor);
            numberRainItem.setStartOffset((int) (Math.random() * 1000));
            LayoutParams layoutParams = new LayoutParams((int) (textSize + 60), getMeasuredHeight());
            addView(numberRainItem, layoutParams);
        }
    }
}
