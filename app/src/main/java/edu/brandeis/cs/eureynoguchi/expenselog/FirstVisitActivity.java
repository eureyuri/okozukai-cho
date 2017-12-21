package edu.brandeis.cs.eureynoguchi.expenselog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;

import com.github.paolorotolo.appintro.AppIntro2;
import com.github.paolorotolo.appintro.AppIntro2Fragment;

/**
 * Created by eureyuri on 2017/12/20.
 */

public class FirstVisitActivity extends AppIntro2 {

//    https://github.com/apl-devs/AppIntro
//    TODO restrict to portrait

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addSlide(AppIntro2Fragment.newInstance("Slide 1", "Description",
                R.mipmap.ic_launcher, ContextCompat.getColor(getApplicationContext(), R.color.introSlide1)));
        addSlide(AppIntro2Fragment.newInstance("Slide 2", "Description2",
                R.mipmap.ic_launcher, ContextCompat.getColor(getApplicationContext(), R.color.introSlide2)));

        showSkipButton(false);
    }

    @Override
    public void onDonePressed(Fragment currentFragment) {
        super.onDonePressed(currentFragment);
        startActivity(new Intent(this, SetPassActivity.class));
    }
}
