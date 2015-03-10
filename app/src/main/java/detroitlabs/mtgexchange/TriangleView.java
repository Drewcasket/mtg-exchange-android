package detroitlabs.mtgexchange;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;


public class TriangleView extends View {
    public static int EMPTY_TRIANGLE=1;
    public static int FULL_TRIANGLE=0;
    private float STROKE_WIDTH=4.0f;
    private boolean isGreen = true;

    private Paint trianglePaint;
    private int triangleStyle;

    public TriangleView(Context context) {
        super(context);

    }

    public void setArrowGreen(final boolean isGreen){
        this.isGreen = isGreen;
        setStyle();
    }

    private int getTriangleColor(){
        if (isGreen) return R.color.value_change_arrow_green;

        return R.color.value_change_arrow_red;
    }

    public void setStyle()
    {
        if(triangleStyle==EMPTY_TRIANGLE)
        {
            this.trianglePaint=getEmptyTriangleStyle(getTriangleColor());
        }
        else if(triangleStyle==FULL_TRIANGLE){
            this.trianglePaint=getFullTriangleStyle(getTriangleColor());
        }
    }

    public int getTriangleStyle() {
        return triangleStyle;
    }

    public void setTriangleStyle(int triangleStyle) {
        this.triangleStyle = triangleStyle;
        invalidate();
        requestLayout();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        setStyle();
        Path trianglePath=makeTriangle(this.getHeight(),this.getWidth());
        canvas.drawPath(trianglePath,this.trianglePaint);

    }

    private Paint getFullTriangleStyle(final int color)
    {
        Paint trianglePaint = new Paint();
        trianglePaint.setStyle(Paint.Style.FILL_AND_STROKE);
        trianglePaint.setAntiAlias(true);
        trianglePaint.setStrokeWidth(STROKE_WIDTH);
        trianglePaint.setColor(getResources().getColor(color));
        return trianglePaint;

    }

    private Paint getEmptyTriangleStyle(final int color)
    {
        Paint trianglePaint = new Paint();
        trianglePaint.setStyle(Paint.Style.STROKE);
        trianglePaint.setAntiAlias(true);
        trianglePaint.setStrokeWidth(STROKE_WIDTH);
        trianglePaint.setColor(getResources().getColor(color));
        return trianglePaint;

    }

    public TriangleView(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.TriangleView,
                0, 0);
        try{
            this.triangleStyle= a.getInt(R.styleable.TriangleView_triangle_style, 0);

        }finally {
            a.recycle();
        }

    }

    private Path makeTriangle(Integer height,Integer width)
    {
        Path trianglePath=new Path();
        trianglePath.moveTo( STROKE_WIDTH/2,STROKE_WIDTH/2);
        trianglePath.lineTo( STROKE_WIDTH/2,height-(STROKE_WIDTH/2));
        trianglePath.lineTo(width-(STROKE_WIDTH/2),(height/2));
        trianglePath.lineTo(STROKE_WIDTH/2,STROKE_WIDTH/2);
        trianglePath.close();
        return trianglePath;
    }
}
