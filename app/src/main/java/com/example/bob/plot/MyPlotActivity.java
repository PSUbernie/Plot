package com.example.bob.plot;
import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import com.androidplot.xy.SimpleXYSeries;
import com.androidplot.xy.XYSeries;
import com.androidplot.xy.*;
import java.util.Arrays;

/**
 * A straightforward example of using AndroidPlot to plot some data.
 */
public class MyPlotActivity extends Activity
{
    private XYPlot plot;

    private OnClickListener XmlListener = new OnClickListener() {
        @Override
        public void onClick(View v) {

        }
    };

    private OnClickListener plotListener = new OnClickListener() {
        @Override
        public void onClick(View v) {
            initializePlot();
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        startApplication();
    }

    private void startApplication() {

        setContentView(R.layout.activity_my_plot);
        Button xmlButton = (Button)findViewById(R.id.xmlButton);
        xmlButton.setOnClickListener(null);

        Button plotButton = (Button)findViewById(R.id.plotButton);
        plotButton.setOnClickListener(plotListener);
        //initializePlot();
    }

    private void initializePlot() {
        // fun little snippet that prevents users from taking screenshots
        // on ICS+ devices :-)
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE,
                WindowManager.LayoutParams.FLAG_SECURE);

        // The layout as described in the activity_my_plot xml file
        setContentView(R.layout.plot_layout);

        // initialize our XYPlot reference:
        plot = (XYPlot) findViewById(R.id.mySimpleXYPlot);

        // Create a couple arrays of y-values to plot:
        Number[] series1Numbers = {1, 8, 5, 2, 7, 4};
        Number[] series2Numbers = {4, 6, 3, 8, 2, 10};

        // Turn the above arrays into XYSeries':
        XYSeries series1 = new SimpleXYSeries(
                Arrays.asList(series1Numbers),          // SimpleXYSeries takes a List so turn our array into a List
                SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, // Y_VALS_ONLY means use the element index as the x value
                "Series1");                             // Set the display title of the series

        // same as above
        XYSeries series2 = new SimpleXYSeries(Arrays.asList(series2Numbers), SimpleXYSeries.ArrayFormat.Y_VALS_ONLY, "Series2");

        // add a new series' to the xyplot:
        plot.addSeries(series1, new LineAndPointFormatter(Color.rgb(200, 0, 0), Color.rgb(200, 0, 0), null, new PointLabelFormatter(Color.RED)));
        plot.addSeries(series2, new LineAndPointFormatter(Color.rgb(0, 200, 0), Color.rgb(0, 100, 0), null, new PointLabelFormatter(Color.GREEN)));

        // reduce the number of range labels
        plot.setTicksPerRangeLabel(3);
        plot.getGraphWidget().setDomainLabelOrientation(-45);
    }
}