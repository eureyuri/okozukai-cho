package edu.brandeis.cs.eureynoguchi.expenselog;

import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.OvershootInterpolator;

import com.hookedonplay.decoviewlib.DecoView;
import com.hookedonplay.decoviewlib.charts.SeriesItem;
import com.hookedonplay.decoviewlib.charts.SeriesLabel;
import com.hookedonplay.decoviewlib.events.DecoEvent;

/**
 * Created by eureyuri on 2017/12/21.
 * https://github.com/PhilJay/MPAndroidChart
 * https://github.com/bmarrdev/android-DecoView-charting
 */

public class DashboardActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard_activity);

        createChart();
    }

    private void createChart() {
        DecoView decoView = (DecoView)findViewById(R.id.dynamicArcView);

        // Background
        decoView.addSeries(new SeriesItem.Builder(Color.argb(255, 218, 218, 218))
                .setRange(0, 100, 100)
                .setInitialVisibility(false)
                .setLineWidth(32f)
                .build());

        // Data
        SeriesItem seriesItem1 = new SeriesItem.Builder(Color.argb(255, 64, 196, 0))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .setSeriesLabel(new SeriesLabel.Builder("%.0f%%").build())
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .setInset(new PointF(20f, 20f))
                .build();
        SeriesItem seriesItem2 = new SeriesItem.Builder(Color.argb(255, 153, 204, 255))
                .setRange(0, 100, 0)
                .setLineWidth(32f)
                .setSeriesLabel(new SeriesLabel.Builder("%.0f%%").build())
                .setInterpolator(new AccelerateDecelerateInterpolator())
                .build();

        int series1Index = decoView.addSeries(seriesItem1);
        int series2Index = decoView.addSeries(seriesItem2);

        // Animation
        decoView.addEvent(new DecoEvent.Builder(DecoEvent.EventType.EVENT_SHOW, true)
                .setDelay(500)
                .setDuration(1000)
                .build());
        decoView.addEvent(new DecoEvent.Builder(100).setIndex(series1Index).setDelay(1000).build());
        decoView.addEvent(new DecoEvent.Builder(50).setIndex(series2Index).setDelay(1000).build());

        decoView.configureAngles(360, 0);
    }

}
