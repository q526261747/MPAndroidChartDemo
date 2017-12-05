package cn.edu.gdmec.android.mpandroidchartdemo;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private LineChart mLineChart;
    private LineData lineData;
    private PieChart mPieChart;
    private List<View> Mview = new ArrayList<>();
    private LayoutInflater inflater;
    //定义一个点集合
    private List<View> dots;
    private int oldPosition = 0;// 记录上一次点的位置

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        inflater = getLayoutInflater();

        setView();
        showPieChart(mPieChart,getPieData(4));
        getLineData(23, 100);
        showmLineChart(mLineChart);
    }
    public void showPieChart(PieChart pieChart, PieData pieData) {
        //pieChart.setHoleColorTransparent(true);

        pieChart.setHoleRadius(60f);  //半径*
        pieChart.setTransparentCircleRadius(64f); // 半透明圈
        //pieChart.setHoleRadius(0);  //实心圆

        pieChart.setDescription("测试饼状图");//*

        // mPieChart.setDrawYValues(true);
        //pieChart.setDrawCenterText(true);  //饼状图中间可以添加文字

        pieChart.setDrawHoleEnabled(true);//是否空心

        //pieChart.setRotationAngle(90); // 初始旋转角度

        // draws the corresponding description value into the slice
        // mPieChart.setDrawXValues(true);

        // enable rotation of the chart by touch
        //pieChart.setRotationEnabled(true); // 可以手动旋转(true)

        // display percentage values
        //pieChart.setUsePercentValues(true);  //显示成百分比(true)
        // mPieChart.setUnit(" €");
        // mPieChart.setDrawUnitsInChart(true);

        // add a selection listener
//      mPieChart.setOnChartValueSelectedListener(this);
        // mPieChart.setTouchEnabled(false);

//      mPieChart.setOnAnimationListener(this);

        pieChart.setCenterText("Quarterly Revenue");  //饼状图中间的文字*

        //设置数据
        pieChart.setData(pieData);//*

        // undo all highlights
//      pieChart.highlightValues(null);
//      pieChart.invalidate();

        Legend mLegend = pieChart.getLegend();  //设置比例图
        mLegend.setPosition(Legend.LegendPosition.RIGHT_OF_CHART);  //最右边显示(default底部)
//      mLegend.setForm(LegendForm.LINE);  //设置比例图的形状，默认是方形
        mLegend.setXEntrySpace(7f);
    //    mLegend.setYEntrySpace(5f);//y数据的距离

        pieChart.animateXY(1000, 1000);  //设置动画*
        // mPieChart.spin(2000, 0, 360);
    }

    /**
     *
     * @param count 分成几部分
     */
    public PieData getPieData(int count) {

        ArrayList<String> xValues = new ArrayList<>();  //xVals用来表示每个饼块上的内容

        for (int i = 0; i < count; i++) {
            xValues.add("Quarterly" + (i + 1));  //饼块上显示成Quarterly1, Quarterly2, Quarterly3, Quarterly4
        }

        ArrayList<Entry> yValues = new ArrayList<>();  //yVals用来表示封装每个饼块的实际数据

        /**
         * 饼图数据
         * 将一个饼形图分成四部分， 四部分的数值比例为14:14:34:38
         * 所以 14代表的百分比就是14%
         */
        float quarterly1 = 14;
        float quarterly2 = 14;
        float quarterly3 = 34;
        float quarterly4 = 38;

        yValues.add(new Entry(quarterly1, 0));
        yValues.add(new Entry(quarterly2, 1));
        yValues.add(new Entry(quarterly3, 2));
        yValues.add(new Entry(quarterly4, 3));

        //y轴的集合
        PieDataSet pieDataSet = new PieDataSet(yValues, "Quarterly Revenue 2014"/*显示在比例图上*/);
        pieDataSet.setSliceSpace(3f); //设置个饼状图之间的距离
        ArrayList<Integer> colors = new ArrayList<>();

        // 饼图颜色
        colors.add(Color.rgb(205, 205, 205));
        colors.add(Color.rgb(114, 188, 223));
        colors.add(Color.rgb(255, 123, 124));
        colors.add(Color.rgb(57, 135, 200));

        pieDataSet.setColors(colors);

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        float px = 5 * (metrics.densityDpi / 160f);
        pieDataSet.setSelectionShift(px); // 选中态多出的长度

        return new PieData(xValues, pieDataSet);
    }
    /**
     * 初始化数据
     * count 表示坐标点个数，range表示等下y值生成的范围
     */
    public LineData getLineData(int count, float range) {
        //X轴显示的数据
        ArrayList<String> x = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            x.add(i + "");
        }
        //y轴的数据
        ArrayList<Entry> y = new ArrayList<>();
        for (int i = 0; i < count; i++) {
            float result = (float) (Math.random() * range) + 3;
            y.add(new Entry(result, i));
        }
        //y轴数据集合
        ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
        LineDataSet lineDataSet = new LineDataSet(y, "随机产生的折线图");
        lineDataSet.setLineWidth(1f);//线宽
        lineDataSet.setCircleColor(Color.RED);//圆形颜色
        lineDataSet.setCircleSize(3f);//现实圆形大小
        lineDataSet.setColor(Color.GREEN);//现实颜色
        lineDataSet.setHighLightColor(Color.BLACK);//高度线的颜色
        lineDataSets.add(lineDataSet);
        lineData = new LineData(x,lineDataSet);
        return lineData;
    }
    /**
     * 设置样式
     */
    public void showmLineChart(LineChart mLineChart) {
        //mLineChart.setDrawBorders(false);//是否添加边框(无)
        mLineChart.setDescription("随机生成的数据");//数据描述*
        mLineChart.setNoDataTextDescription("我需要数据");//没数据显示*
        //mLineChart.setDrawGridBackground(true);//是否显示表格颜色
        mLineChart.setBackgroundColor(Color.WHITE);//背景颜色
        mLineChart.setData(lineData);//设置数据*
        Legend legend = mLineChart.getLegend();//设置比例图片标示，就是那一组Y的value
        legend.setForm(Legend.LegendForm.SQUARE);//样式
        legend.setFormSize(10f);//字体
        legend.setTextColor(Color.BLUE);//设置颜色
        mLineChart.animateX(2000);//X轴的动画
    }
    private void setView() {
        //初始化viewPager
        ViewPager vp = (ViewPager) findViewById(R.id.viewPager);

        //把这两个点的ID找到并添加到list集合中，统一管理；下面是简写。你也可以创建两个对象，添加到集合中
        dots = new ArrayList<>();
        dots.add(findViewById(R.id.dot_1));
        dots.add(findViewById(R.id.dot_2));

        //并且，默认第一个是选中状态
        dots.get(0).setBackgroundResource(R.drawable.dot_focused);

        View item_line = inflater.inflate(R.layout.chart_line, null);
        View item_pie = inflater.inflate(R.layout.chart_pie,null);
        mPieChart = (PieChart) item_pie.findViewById(R.id.spread_pie_chart);
        mLineChart = (LineChart) item_line.findViewById(R.id.spread_line_chart);
        //把两个View布局对象加载到list中，这些就是item的数据
        Mview.add(item_line);
        Mview.add(item_pie);

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {

                //下面就是获取上一个位置，并且把点的状体设置成默认状体
                dots.get(oldPosition).setBackgroundResource(R.drawable.dot_normal);
                //获取到选中页面对应的点，设置为选中状态
                dots.get(position).setBackgroundResource(R.drawable.dot_focused);
                //下面是记录本次的位置，因为在滑动，他就会变成过时的点了
                oldPosition = position;
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        //把数据传递给适配器中，进行数据处理。
        SlidePagerAdapter adapter = new SlidePagerAdapter(this, Mview);
        vp.setAdapter(adapter);
    }
}
