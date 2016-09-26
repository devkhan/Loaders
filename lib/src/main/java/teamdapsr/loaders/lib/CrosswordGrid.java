package teamdapsr.loaders.lib;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

import java.util.Random;

import teamdapsr.loaders.lib.utils.MeasureUtils;

/**
 * Created by Devesh on 08-Jun-15.
 */
public class CrosswordGrid extends View {
    private int squareSide;
    private Paint mSquarePaint;
    private Paint mSquarePaintFill;

    private int GRID_ROWS;
    private int GRID_COLUMNS;
    private boolean coordinates[][];

    public CrosswordGrid(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.CrosswordGrid,
                0, 0
        );

        try {
            squareSide = a.getInt(R.styleable.CrosswordGrid_squareSide, 10);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            a.recycle();
        }

        GRID_ROWS = GRID_COLUMNS = 10;
        coordinates = new boolean[GRID_ROWS][GRID_COLUMNS];
        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLUMNS; j++) {
                coordinates[i][j] = false;
            }
        }

        init();
    }


    public CrosswordGrid(Context context, int squareSide) {
        super(context);
        this.squareSide = squareSide;

    }

    /**
     * Returns a pseudo-random number between min and max, inclusive.
     * The difference between min and max can be at most
     * <code>Integer.MAX_VALUE - 1</code>.
     *
     * @param min Minimum value
     * @param max Maximum value.  Must be greater than min.
     * @return Integer between min and max, inclusive.
     * @see java.util.Random#nextInt(int)
     */
    public static int randInt(int min, int max) {

        // NOTE: Usually this should be a field rather than a method
        // variable so that it is not re-seeded every call.
        Random rand = new Random();

        // nextInt is normally exclusive of the top value,
        // so add 1 to make it inclusive
        int randomNum = rand.nextInt((max - min) + 1) + min;

        return randomNum;
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        // Get the width measurement
        int widthSize = MeasureUtils.getMeasurement(widthMeasureSpec, getDesiredWidth());

        // Get the height measurement
        int heightSize = MeasureUtils.getMeasurement(heightMeasureSpec, getDesiredHeight());

        //MUST call this to store the measurements
        setMeasuredDimension(widthSize + 10, heightSize + 10);
    }

    private int getDesiredWidth() {
        // TO-DO Calculate width from child components.

        return 2 * squareSide + 200;
    }

    private int getDesiredHeight() {
        // TO-DO Calculate height from chile components.
        return 2 * squareSide + 200;
    }

    public int getSquareSide() {
        return squareSide;
    }

    public void setSquareSide(int side) {
        this.squareSide = side;
        invalidate();
        requestLayout();
    }

    private void init() {
        mSquarePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSquarePaint.setColor(0xff101010);
        mSquarePaint.setStyle(Paint.Style.FILL);
        mSquarePaintFill = new Paint(Paint.ANTI_ALIAS_FLAG);
        mSquarePaintFill.setColor(0xffffffff);
        mSquarePaintFill.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLUMNS; j++) {
                int left = i * (10);
                int top = j * (10);
                int right = left + 10;
                int bottom = top + 10;
                if (coordinates[i][j]) {
                    canvas.drawRect(new Rect(left, top, right, bottom),
                            mSquarePaint);
                } else {
                    canvas.drawRect(new Rect(left, top, right, bottom),
                            mSquarePaintFill);
                }
            }
        }
        try {
            Thread.sleep(150);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        generateRandomCoordinates();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    private void generateRandomCoordinates() {

        for (int i = 0; i < GRID_ROWS; i++) {
            for (int j = 0; j < GRID_COLUMNS; j++) {
                coordinates[i][j] = false;
            }
        }

        for (int i = 0; i < 40; i++) {
            coordinates[randInt(0, GRID_ROWS - 1)][randInt(0, GRID_COLUMNS - 1)
                    ] = true;
        }
        invalidate();
        requestLayout();
    }

}
