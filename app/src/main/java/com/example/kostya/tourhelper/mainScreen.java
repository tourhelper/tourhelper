package com.example.kostya.tourhelper;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.ImageSwitcher;
import android.widget.ImageView;
import android.widget.ViewSwitcher;


public class mainScreen extends Activity implements ViewSwitcher.ViewFactory {

    ImageSwitcher imageSwitcher;
    int position = 0;

    private Integer[] mImageIds = { R.drawable.ploshad, R.drawable.isakii};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_screen);

        imageSwitcher = (ImageSwitcher)findViewById(R.id.imageSwitcher);
        imageSwitcher.setFactory(this);
        Animation inAnim = new AlphaAnimation(0, 1);
        inAnim.setDuration(1000);
        Animation outAnim = new AlphaAnimation(1, 0);
        outAnim.setDuration(1000);

        imageSwitcher.setInAnimation(inAnim);
        imageSwitcher.setOutAnimation(outAnim);

        imageSwitcher.setImageResource(mImageIds[0]);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main_screen, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void onSwitcherClick(View v){
        setPositionNext();
    }

    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonForward:
                setPositionNext();
                imageSwitcher.setImageResource(mImageIds[position]);
                break;
            case R.id.buttonPrev:
                setPositionPrev();
                imageSwitcher.setImageResource(mImageIds[position]);
                break;

            default:
                break;
        }
    }

    public void setPositionNext() {
        position++;
        if (position > mImageIds.length - 1) {
            position = 0;
        }
    }

    public void setPositionPrev() {
        position--;
        if (position < 0) {
            position = mImageIds.length - 1;
        }
    }

    @Override
    public View makeView() {
        // TODO Auto-generated method stub
        ImageView imgview = new ImageView(this);
        imgview.setScaleType(ImageView.ScaleType.FIT_CENTER);
        imgview.setLayoutParams(new
                ImageSwitcher.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        imgview.setBackgroundColor(0xFF000000);
        return imgview;
    }
}
