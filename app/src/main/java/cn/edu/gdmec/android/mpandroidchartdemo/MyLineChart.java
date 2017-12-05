package cn.edu.gdmec.android.mpandroidchartdemo;

import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;

import java.util.*;

/**
 * Created by 52626 on 2017/12/5.
 */

public class MyLineChart extends AppCompatActivity{
    public ArrayList<String> x = new ArrayList<>();
    public ArrayList<Entry> y = new ArrayList<>();
    public ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
    public LineData lineData = null;
    public MyLineChart(){

    }
    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count, float range) {
        for (int i = 0; i < count; i++) {  //X轴显示的数据
            x.add(i + "");
        }
        for (int i = 0; i < count; i++) {//y轴的数据
            float result = (float) (Math.random() * range) + 3;
            y.add(new Entry(result, i));
        }
        LineDataSet lineDataSet = new LineDataSet(y, "随机产生的折线图");//y轴数据集合
        lineDataSet.setLineWidth(1f);//线宽
        lineDataSet.setCircleSize(Color.BLUE);//圆形颜色
        lineDataSet.setCircleSize(2f);//现实圆形大小
        lineDataSet.setColor(Color.RED);//现实颜色
        lineDataSet.setHighLightColor(Color.BLACK);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData = new LineData(x,lineDataSet);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showLineChart(LineChart lineChart) {
        lineChart.setDrawBorders(false);//是否添加边框
        lineChart.setDescription("随机生成的数据");//数据描述
        lineChart.setNoDataTextDescription("我需要数据");//没数据显示
        lineChart.setDrawGridBackground(true);//是否显示表格颜色
        lineChart.setBackgroundColor(Color.GRAY);//背景颜色
        lineChart.setData(lineData);//设置数据
        Legend legend = lineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.SQUARE);//样式
        legend.setFormSize(10f);//字体
        legend.setTextColor(Color.BLUE);//设置颜色
        lineChart.animateX(2000);//X轴的动画
    }

}
