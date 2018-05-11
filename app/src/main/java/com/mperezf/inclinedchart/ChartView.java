package com.mperezf.inclinedchart;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.view.View;

public class ChartView extends View {

    public static int CHART_SPACE = 10;

    ChartData mChartData_1;
    ChartData mChartData_2;
    public ChartView(Context context) {
        super(context);
    }

    @Override
    public void onDraw(Canvas canvas) {
        printChart(canvas, Color.RED, getBottomLeftPointChart1(), getBottomRightPointChart1(), getTopRightPointChart1(),
                getTopLeftPointChart1());

        printChart(canvas, Color.BLUE, getBottomLeftPointChart2(), getBottomRightPointChart2(),
                getTopRightPointChart2(),
                getTopLeftPointChart2());

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
        point.x = (getWidth() / 3) - CHART_SPACE;
        return point;
    }


    public Point getTopRightPointChart1() {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * mChartData_1.percent / 100);
        point.x = getBottomRightPointChart1().x + (
                ((((getWidth() / 3) * 2) - CHART_SPACE) - getBottomRightPointChart1().x) * mChartData_1.percent / 100);
        return point;
    }

    public Point getTopLeftPointChart1() {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * mChartData_1.percent / 100);
        point.x = (getWidth() / 3) - CHART_SPACE;
        point.x = (((getWidth() / 3) - CHART_SPACE) - getBottomLeftPointChart1().x) * mChartData_1.percent / 100;
        return point;
    }

    //SecondChart
    public Point getBottomLeftPointChart2() {
        Point point = new Point();
        point.y = getHeight();
        point.x = (getWidth() / 3) + CHART_SPACE;
        return point;
    }

    public Point getBottomRightPointChart2() {
        Point point = new Point();
        point.y = getHeight();
        point.x = ((getWidth() / 3) * 2) + CHART_SPACE;
        return point;
    }

    public Point getTopRightPointChart2() {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * mChartData_2.percent / 100);
        point.x = getBottomRightPointChart2().x + ((getWidth() - getBottomRightPointChart2().x) * mChartData_2.percent / 100);
        return point;
    }

    public Point getTopLeftPointChart2() {
        Point point = new Point();
        point.y = getHeight() - (getHeight() * mChartData_2.percent / 100);
        point.x = getBottomLeftPointChart2().x + (
                (getBottomRightPointChart2().x - getBottomLeftPointChart2().x) * mChartData_2.percent / 100);
        return point;
    }


    public void setChartData_1(ChartData chartData_1) {
        mChartData_1 = chartData_1;
    }

    public void setChartData_2(ChartData chartData_2) {
        mChartData_2 = chartData_2;
    }


    public static class ChartData {

        int percent;
        int color;

        public ChartData(int percent, int color) {
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