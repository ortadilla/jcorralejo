package activities;

import es.jcorralejo.android.R;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

public class SplashActivity extends Activity {

	AnimationSet animationSet;
	ImageView imagen;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.splash);
		imagen = (ImageView) findViewById(R.id.logo);
		configurarAnimacion();
		imagen.startAnimation(animationSet);
	}

	public void configurarAnimacion(){
		animationSet = new AnimationSet(true);
		
		Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fadein);
		fadein.setDuration(1000);
		Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fadeout);
		fadeout.setDuration(1000);
		fadeout.setStartOffset(2500);

		animationSet.addAnimation(fadein);
		animationSet.addAnimation(fadeout);
		animationSet.setStartOffset(1000);

		animationSet.setAnimationListener(new AnimationListener(){
			public void onAnimationStart(Animation animation) {
				imagen.setImageResource(R.drawable.wikicode);
			}
			public void onAnimationEnd(Animation arg0) {}
			public void onAnimationRepeat(Animation arg0) {}
		});

	}
}
