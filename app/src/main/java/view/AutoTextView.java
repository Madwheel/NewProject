package view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * 描述：
 * 作者：小辉
 * 时间：2018/05/07
 */

public class AutoTextView extends TextView {
    private Paint mPaint;
    private float mTextSize;
    private float mMinTextSize;

    public AutoTextView(Context context) {
        this(context, null);
    }

    public AutoTextView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AutoTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setLines(1);//设置单行显示
        initViewType();//初始化view的配置
    }

    //初始化view的配置
    private void initViewType() {
        mPaint = new TextPaint();
        mPaint.set(this.getPaint());//设置默认样式
        if (mTextSize <= 0) {
            mTextSize = this.getTextSize();
        }
    }

    @Override
    protected void onTextChanged(CharSequence text, int start, int lengthBefore, int lengthAfter) {
        setTextFitWidth(text.toString(), this.getWidth());
        super.onTextChanged(text, start, lengthBefore, lengthAfter);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        if (w != oldw) {
            setTextFitWidth(this.getText().toString(), this.getWidth());
        }
    }

    private void setTextFitWidth(String text, int textWidth) {
        if (textWidth < 0) {
            return;
        }
        int width = textWidth - this.getPaddingLeft() - this.getPaddingRight();//文本的实际宽度
        float size = mTextSize;
        mPaint.setTextSize(size);
        while (mPaint.measureText(text) > width) {//如果按照size的大小填写的文本宽度大于文本的实际宽度
            size -= 1;
            if (size < mMinTextSize) {
                size = mMinTextSize;
                break;
            }
            mPaint.setTextSize(size);
        }
        setTextSize(px2sp(getContext(), size));
    }

    private float px2sp(Context context, float size) {
        float scaledDensity = context.getResources().getDisplayMetrics().scaledDensity;
        return (size / scaledDensity);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }
}
