package id.uinic.diagnosacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import id.uinic.diagnosacovid.ui.quisioner.MainActivity;

public class ResultActivity extends AppCompatActivity {

    TextView keterangan, saran;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        keterangan = findViewById(R.id.keterangan);
        saran = findViewById(R.id.saran_kami);

        TextView result = findViewById(R.id.hasil);

        result.setText("Mengalami " + MainActivity.jawabanYa + " dari 10 gejala yang disebutkan.");

        if (MainActivity.result >= 80) {
            keterangan.setText("Anda terinfeksi COVID-19");
            saran.setText("SEGERA LAKUKAN isolasi mandiri dirumah selama kurang lebih 2 minggu, dan Anda TIDAK DIPERKENANKAN untuk keluar rumah");
        } else if (MainActivity.result >= 60 && MainActivity.result < 90) {
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