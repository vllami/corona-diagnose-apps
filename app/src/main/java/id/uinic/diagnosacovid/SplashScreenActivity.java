package id.uinic.diagnosacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import id.uinic.diagnosacovid.ui.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 5000;

    // Animation Variable
    Animation animFade, animBottom;
    ImageView image;
    TextView copyright;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Animation
        animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);
        animBottom = AnimationUtils.loadAnimation(this, R.anim.anim_bottom);

        // Call
        image = findViewById(R.id.splashScreen);
        copyright = findViewById(R.id.copyright);

        image.setAnimation(animFade);
        copyright.setAnimation(animBottom);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }
}