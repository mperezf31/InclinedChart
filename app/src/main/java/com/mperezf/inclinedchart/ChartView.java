package com.mperezf.inclinedchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class ChartView extends View {

    public static int DEFAULT_SPACE = 10;

    private int mChartSpace = DEFAULT_SPACE;
    private ChartData mChartData_1;
    private ChartData mChartData_2;

    public ChartView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        printChart(canvas, mChartData_1.getColor(), getBottomLeftPointChart1(), getBottomRightPointChart1(),
                getTopRightPointChart1(mChartData_1.percent),
                getTopLeftPointChart1(mChartData_1.percent));

        printChart(canvas, mChartData_2.getColor(), getBottomLeftPointChart2(), getBottomRightPointChart2(),
                getTopRightPointChart2(mChartData_2.percent),
                getTopLeftPointChart2(mChartData_2.percent));

        showPercentChart1(canvas);
        showPercentChart2(canvas);

    }

    public void printChart(Canvas canvas, int color, Point point1, Point point2, Point point3, Point point4) {
        Paint paint = new Paint();
        paint.setColor(color);
        paint.setStyle(Paint.Style.FILL);

        Path path = new Path();
        path.moveTo(point1.x, point1.y);
        path.lineTo(point2.x, point2.y);
        path.lineTo(point3.x, point3.y);
        path.lineTo(point4.x, point4.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    //FirstChart
    public Point getBottomLeftPointChart1() {
        Point point = new Point();
        point.y = getHeight();
        point.x = 0;
        return point;
    }

    public Point getBottomRightPointChart1() {
        Point point = new Point();
        point.y = getHeight();
        point.x = (getWidth() / 3) - mChartSpace;
        return point;
    }


    public Point getTopRightPointChart1(int percent) {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * percent / 100);
        point.x = getBottomRightPointChart1().x + (
                ((((getWidth() / 3) * 2) - mChartSpace) - getBottomRightPointChart1().x) * mChartData_1.percent / 100);
        return point;
    }

    public Point getTopLeftPointChart1(int percent) {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * percent / 100);
        point.x = (getWidth() / 3) - mChartSpace;
        point.x = (((getWidth() / 3) - mChartSpace) - getBottomLeftPointChart1().x) * mChartData_1.percent / 100;
        return point;
    }

    //SecondChart
    public Point getBottomLeftPointChart2() {
        Point point = new Point();
        point.y = getHeight();
        point.x = (getWidth() / 3) + mChartSpace;
        return point;
    }

    public Point getBottomRightPointChart2() {
        Point point = new Point();
        point.y = getHeight();
        point.x = ((getWidth() / 3) * 2) + mChartSpace;
        return point;
    }

    public Point getTopRightPointChart2(int percent) {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * percent / 100);
        point.x = getBottomRightPointChart2().x + ((getWidth() - getBottomRightPointChart2().x) * mChartData_2.percent
                / 100);
        return point;
    }

    public Point getTopLeftPointChart2(int percent) {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * percent / 100);
        point.x = getBottomLeftPointChart2().x + (
                (getBottomRightPointChart2().x - getBottomLeftPointChart2().x) * mChartData_2.percent / 100);
        return point;
    }


    public void showPercentChart1(Canvas canvas) {
        int textPosition = 15;
        if (mChartData_1.getPercent() > 40) {
            textPosition = -15;
        }
        Point pointLeft = getTopLeftPointChart1(mChartData_1.getPercent() + textPosition);
        Point pointRight = getTopRightPointChart1(mChartData_1.getPercent() + textPosition);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(String.valueOf(mChartData_1.getPercent()) + "%",
                pointLeft.x + (pointRight.x - pointLeft.x) / 2 , pointLeft.y, textPaint);
    }


    public void showPercentChart2(Canvas canvas) {
        int textPosition = 15;
        if (mChartData_2.getPercent() > 40) {
            textPosition = -15;
        }
        Point pointLeft = getTopLeftPointChart2(mChartData_2.getPercent() + textPosition);
        Point pointRight = getTopRightPointChart2(mChartData_2.getPercent() + textPosition);

        Paint textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(50);
        textPaint.setTextAlign(Paint.Align.CENTER);

        canvas.drawText(String.valueOf(mChartData_2.getPercent()) + "%",
                pointLeft.x + (pointRight.x - pointLeft.x) / 2, pointLeft.y, textPaint);
    }

    public void setChartData_1(ChartData chartData_1) {
        mChartData_1 = chartData_1;
    }

    public void setChartData_2(ChartData chartData_2) {
        mChartData_2 = chartData_2;
    }

    public void setDefaultSpace(int chartSpace) {
        mChartSpace = chartSpace;
    }

    public static class ChartData {

        private int percent;
        private int color;

        ChartData(int percent, int color) {
            this.percent = percent;
            this.color = color;
        }

        public int getPercent() {
            return percent;
        }

        public int getColor() {
            return color;
        }
    }

}