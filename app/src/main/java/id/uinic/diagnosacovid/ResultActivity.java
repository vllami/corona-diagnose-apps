package id.uinic.diagnosacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import id.uinic.diagnosacovid.ui.home.HomeActivity;

import static id.uinic.diagnosacovid.util.Const.JAWABAN_KEY;
import static id.uinic.diagnosacovid.util.Const.RESULT_KEY;

public class ResultActivity extends AppCompatActivity {

    TextView keterangan, saran;
    Integer resultExtra, jawabanExtra;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        keterangan = findViewById(R.id.keterangan);
        saran = findViewById(R.id.saran_kami);

        TextView result = findViewById(R.id.hasil);

        resultExtra = getIntent().getIntExtra(RESULT_KEY, 0);
        jawabanExtra = getIntent().getIntExtra(JAWABAN_KEY, 0);

        result.setText("Mengalami " + jawabanExtra + " dari 10 gejala yang disebutkan.");

        if (resultExtra >= 80) {
            keterangan.setText("Anda terinfeksi COVID-19");
            saran.setText("SEGERA LAKUKAN isolasi mandiri dirumah selama kurang lebih 2 minggu, dan Anda TIDAK DIPERKENANKAN untuk keluar rumah");
        } else if (resultExtra >= 60 && resultExtra < 90) {
            keterangan.setText("Anda memiliki kemungkinan terinfeksi COVID-19");
            saran.setText("LAKUKAN isolasi mandiri, selalu GUNAKAN MASKER saat keluar rumah, dan HINDARI kerumunan");
        } else {
            keterangan.setText("Anda terbebas dari COVID-19");
            saran.setText("TETAP LAKUKAN jaga jarak minimal 1 meter, selalu GUNAKAN MASKER saat keluar rumah, dan HINDARI kerumunan");
        }

        Button btnBeranda = findViewById(R.id.btn_beranda);
        btnBeranda.setOnClickListener(v -> {
            Intent i = new Intent(ResultActivity.this, HomeActivity.class);
            startActivity(i);
        });
    }

    public void btnBeranda(View view) {
    }
}