package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.PointF;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ListView;

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        createMenu(menu);
        return true;
    }

    private void createMenu(Menu menu) {
        menu.setQwertyMode(true);
        MenuItem list = menu.add(0, 0, 0, "お小遣い帳");
        MenuItem add = menu.add(1, 1, 1, "追加");
        MenuItem setting = menu.add(2, 2, 2, "設定");
        MenuItem checkDatabase = menu.add(3, 3, 3, "Database");
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        super.onOptionsItemSelected(item);
        return menuChoice(item);
    }

    private boolean menuChoice(MenuItem item) {
        if (item.getItemId() == 0) {
            startActivity(new Intent(this, ListActivity.class));
            return true;
        } else if (item.getItemId() == 1) {
            startActivity(new Intent(this, AddExpenseActivity.class));
            return true;
        } else if (item.getItemId() == 2) {
            startActivity(new Intent(this, SettingActivity.class));
            return true;
        } else if (item.getItemId() == 3) {
            startActivity(new Intent(this, AndroidDatabaseManager.class));
            return true;
        }
        return false;
    }

}
