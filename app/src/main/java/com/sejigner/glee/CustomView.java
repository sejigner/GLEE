package com.sejigner.glee;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.sql.Array;
import java.util.ArrayList;


public class CustomView extends View {
    private static final float TOUCH_TOLERANCE = 4;
    private float mX, mY;
    private Path drawPath;
    private Paint canvasPaint;
    private Paint drawPaint;
    private int paintColor = 0xFF00FF0C;
    private Canvas drawCanvas;
    private Bitmap canvasBitmap;
    private float currentBrushSize, lastBrushSize;
    private ArrayList<Path> paths = new ArrayList<Path>();
    private ArrayList<Path> undonePaths = new ArrayList<Path>();

    public CustomView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        currentBrushSize = getResources().getInteger(R.integer.medium_size);
        lastBrushSize = currentBrushSize;

        drawPath = new Path();
        drawPaint = new Paint();
        drawPaint.setColor(paintColor);
        drawPaint.setAntiAlias(true);
        drawPaint.setStrokeWidth(currentBrushSize);
        drawPaint.setStyle(Paint.Style.STROKE);
        drawPaint.setStrokeJoin(Paint.Join.ROUND);
        drawPaint.setStrokeCap(Paint.Cap.ROUND);

        canvasPaint = new Paint(Paint.DITHER_FLAG);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        getParent().requestDisallowInterceptTouchEvent(true);
        for (Path p : paths) {
            canvas.drawPath(p, drawPaint);
        }
        canvas.drawPath(drawPath, drawPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        // create canvas of certain device size.
        super.onSizeChanged(w, h, oldw, oldh);

        // create Bitmap of certain w, h
        canvasBitmap = Bitmap.createBitmap(w, h, Bitmap.Config.ARGB_8888);

        // apply bitmap to graphic to start drawing.
        drawCanvas = new Canvas(canvasBitmap);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float touchX = event.getX();
        float touchY = event.getY();
        // respond to down, move and up events
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                touch_start(touchX, touchY);
                invalidate();
                break;
            case MotionEvent.ACTION_MOVE:
                touch_move(touchX, touchY);
                invalidate();
                break;
            case MotionEvent.ACTION_UP:
                touch_up();
                invalidate();
                break;
            default:
                return false;
        }
        return true;
    }

    private void touch_up() {
        drawPath.lineTo(mX, mY);
        drawCanvas.drawPath(drawPath, drawPaint);
        paths.add(drawPath);
        drawPath = new Path();
    }

    private void touch_move(float x, float y) {
        float dx = Math.abs(x - mX);
        float dy = Math.abs(y - mY);
        if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
            drawPath.quadTo(mX, mY, (x + mX) / 2, (y + mY) / 2);
            mX = x;
            mY = y;
        }
    }

    private void touch_start(float x, float y) {
        undonePaths.clear();
        drawPath.reset();
        drawPath.moveTo(x, y);
        mX = x;
        mY = y;
    }

    public void onClickUndo() {
        if (paths.size() > 0) {
            undonePaths.add(paths.remove(paths.size() - 1));
            invalidate();
        }
    }

    public void onClickRedo() {
        if (undonePaths.size() > 0) {
            paths.add(undonePaths.remove(undonePaths.size() - 1));
            invalidate();
        }
    }


    public void setBrushSize(float newSize) {
        float pixelAmount = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, newSize, getResources().getDisplayMetrics());
        currentBrushSize = pixelAmount;
        canvasPaint.setStrokeWidth(newSize);
    }

    public void setLastBrushSize(float lastSize) {
        lastBrushSize=lastSize;
    }

    public float getLastBrushSize() {
        return lastBrushSize;
    }


}
