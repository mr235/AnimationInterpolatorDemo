package com.mr235.animationinterpolatordemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

	private ImageView mIv;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mIv = (ImageView) findViewById(R.id.iv);
		findViewById(R.id.bt).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
			case R.id.bt:
				Animation animation =  AnimationUtils.loadAnimation(this, R.anim.translate);
				animation.setInterpolator(new HesitateInterpolator());
				mIv.clearAnimation();
				mIv.startAnimation(animation);
				break;
		}
	}
	public static class HesitateInterpolator implements Interpolator {
		public HesitateInterpolator() {}
		public float getInterpolation(float t) {
			float x=2.0f*t-1.0f;
			float value = 0.5f*(x*x*x + 1.0f);

			float v = 0.0f;
			if (t<0.1f) {
				v=3*t;
			} else if (t>0.9f) {
				v = 3*t-2;
			} else {
				v = 0.5f*t+0.25f;
			}
			return v;
		}
	}
}
