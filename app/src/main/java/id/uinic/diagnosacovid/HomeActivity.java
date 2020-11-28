package id.uinic.diagnosacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.uinic.diagnosacovid.ui.dataterbaru.DataCovidActivity;

public class HomeActivity extends AppCompatActivity {

    Animation animLeft, animRight;
    TextView tv_header, tv_penjelasan;
    ImageView img_dokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnMulaiDiagnosa = findViewById(R.id.btn_mulai_diagnosa);
        btnMulaiDiagnosa.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(i);
        });

        // Animation
        animLeft = AnimationUtils.loadAnimation(this, R.anim.anim_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.anim_right);

        // Call
        tv_header = findViewById(R.id.header);
        tv_penjelasan = findViewById(R.id.penjelasan);
        img_dokter = findViewById(R.id.dokter);

        tv_header.setAnimation(animLeft);
        tv_penjelasan.setAnimation(animLeft);
        img_dokter.setAnimation(animRight);
    }

    public void btnMulaiDiagnosa(View view) {
    }

    public void goToDataCovid(View view) {
        Intent i = new Intent(HomeActivity.this, DataCovidActivity.class);
        startActivity(i);
    }
}