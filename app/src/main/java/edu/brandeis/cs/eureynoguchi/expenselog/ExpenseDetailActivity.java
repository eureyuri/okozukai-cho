package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

/**
 * Created by eureyuri on 2017/12/20.
 */

public class ExpenseDetailActivity extends AppCompatActivity {

    ViewPager viewPager;
    ViewPagerAdapter adapter;

    private String[] images = {
            "https://static.pexels.com/photos/186077/pexels-photo-186077.jpeg",
            "https://static.pexels.com/photos/106399/pexels-photo-106399.jpeg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.expense_detail_activity);

        startSlideShow();

        Button next = (Button)findViewById(R.id.nextButton);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ExpenseDetailActivity.this, MainActivity.class));
            }
        });

    }

    private void startSlideShow() {
        viewPager = (ViewPager)findViewById(R.id.slideshowViewPager);
        adapter = new ViewPagerAdapter(ExpenseDetailActivity.this, images);
        viewPager.setAdapter(adapter);

    }

}
