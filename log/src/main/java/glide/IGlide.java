package glide;

import android.content.Context;
import android.widget.ImageView;

/**
 * 描述：Glide加载图片封装接口
 * 作者：小辉
 * 时间：2018/05/21
 */

public interface IGlide {

    /**
     * 加载图片
     *
     * @param context   上下文（Activity、Fragment）
     * @param url       图片的资源（url、bitmap、uri等）
     * @param bitmap    图片默认占位图
     * @param error     加载错误时显示图片
     * @param imageView 图片控件
     */
    void loadImg(Context context, Object url, int bitmap, int error, ImageView imageView);

    /**
     * 加载图片（可缩放）
     *
     * @param context   上下文（Activity、Fragment）
     * @param url       图片的资源（url、bitmap、uri等）
     * @param bitmap    图片默认占位图
     * @param error     加载错误时显示图片
     * @param thumbnail 图片缩放大小（如：0.2f）
     * @param imageView 图片控件
     */
    void loadImg(Context context, Object url, int bitmap, int error, float thumbnail, ImageView imageView);

    /**
     * 加载圆形图片
     *
     * @param context   上下文（Activity、Fragment）
     * @param url       图片的资源（url、bitmap、uri等）
     * @param bitmap    图片默认占位图
     * @param error     加载错误时显示图片
     * @param imageView 图片控件
     */
    void loadCircularImg(Context context, Object url, int bitmap, int error, ImageView imageView);
}
