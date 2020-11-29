package id.uinic.diagnosacovid.ui.home;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.ui.datacovid.DataCovidActivity;
import id.uinic.diagnosacovid.ui.previousresult.PreviousResultActivity;
import id.uinic.diagnosacovid.ui.startdiagnose.StartDiagnoseActivity;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        // Call ID
        TextView tvHeader = findViewById(R.id.tv_header);
        TextView tvPenjelasan = findViewById(R.id.tv_penjelasan);
        ImageView imgDokter = findViewById(R.id.img_dokter);

        // Animation
        Animation animLeft = AnimationUtils.loadAnimation(this, R.anim.anim_left);
        Animation animRight = AnimationUtils.loadAnimation(this, R.anim.anim_right);

        // Set Animation
        tvHeader.setAnimation(animLeft);
        tvPenjelasan.setAnimation(animLeft);
        imgDokter.setAnimation(animRight);
    }

    public void btnMulaiDiagnosa(View view) {
        Intent i = new Intent(HomeActivity.this, StartDiagnoseActivity.class);
        startActivity(i);
    }

    public void btnDataCovid(View view) {
        Intent i = new Intent(HomeActivity.this, DataCovidActivity.class);
        startActivity(i);
    }

    public void btnHasilSebelumnya(View view) {
        Intent i = new Intent(HomeActivity.this, PreviousResultActivity.class);
        startActivity(i);
    }
}