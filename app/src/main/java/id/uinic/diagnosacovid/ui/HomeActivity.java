package id.uinic.diagnosacovid.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.uinic.diagnosacovid.R;

public class HomeActivity extends AppCompatActivity {

    Animation animLeft, animRight;
    TextView tvHeader, tvPenjelasan;
    ImageView imgDokter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        Button btnMulaiDiagnosa = findViewById(R.id.btn_mulai_diagnosa);
        btnMulaiDiagnosa.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, MainActivity.class);
            startActivity(i);
        });

        ConstraintLayout btnDataCovid = findViewById(R.id.btn_data_covid);
        btnDataCovid.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, DataCovidActivity.class);
            startActivity(i);
        });

        ConstraintLayout btnTentangCovid = findViewById(R.id.btn_tentang_covid);
        btnTentangCovid.setOnClickListener(v -> {
            Intent i = new Intent(HomeActivity.this, AboutCovidActivity.class);
            startActivity(i);
        });

        // Animation
        animLeft = AnimationUtils.loadAnimation(this, R.anim.anim_left);
        animRight = AnimationUtils.loadAnimation(this, R.anim.anim_right);

        // Call
        tvHeader = findViewById(R.id.header);
        tvPenjelasan = findViewById(R.id.penjelasan);
        imgDokter = findViewById(R.id.dokter);

        tvHeader.setAnimation(animLeft);
        tvPenjelasan.setAnimation(animLeft);
        imgDokter.setAnimation(animRight);
    }

    public void btnMulaiDiagnosa(View view) {
    }

    public void btnDataCovid(View view) {
    }

    public void btnTentangCovid(View view) {
    }
}