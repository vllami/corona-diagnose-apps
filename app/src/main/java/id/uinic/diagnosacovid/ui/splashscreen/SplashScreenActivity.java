package id.uinic.diagnosacovid.ui.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.ui.home.HomeActivity;

public class SplashScreenActivity extends AppCompatActivity {

    private static final int SPLASH_SCREEN = 3500;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);

        // Animation
        Animation animFade = AnimationUtils.loadAnimation(this, R.anim.anim_fade);
        Animation animBottom = AnimationUtils.loadAnimation(this, R.anim.anim_bottom);

        // Call ID
        ImageView imgSplashScreen = findViewById(R.id.img_splash_screen);
        TextView tvCopyright = findViewById(R.id.tv_copyright);

        imgSplashScreen.setAnimation(animFade);
        tvCopyright.setAnimation(animBottom);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashScreenActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_SCREEN);
    }
}