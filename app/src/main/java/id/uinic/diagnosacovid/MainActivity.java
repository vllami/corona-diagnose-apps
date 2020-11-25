package id.uinic.diagnosacovid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView pertanyaan;
    RadioGroup radio;
    RadioButton ya, tidak;
    int nomor = 0;
    public static int result, jawabanYa, jawabanTidak;

    Button btnSebelumnya;

    // Pertanyaan
    String[] pertanyaan_diagnosa = new String[]{
            "gejala demam?",
            "gejala batuk kering?",
            "nyeri pada tenggorokan?",
            "gejala diare?",
            "konjungtivitis atau mata merah?",
            "sakit kepala?",
            "hilangnya indera penciuman atau perasa?",
            "sesak napas?",
            "rasa tertekan pada dada?",
            "kesulitan berbicara atau bergerak?"
    };

    // Pilihan jawaban Ya atau Tidak
    String[] jawaban = new String[]{
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak",
            "Ya", "Tidak"
    };

    // Jawaban Ya
    String[] jawaban_ya = new String[]{
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya",
            "Ya"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSebelumnya = findViewById(R.id.btn_sebelumnya);
        pertanyaan = findViewById(R.id.pertanyaan);
        radio = findViewById(R.id.pilihan);
        ya = findViewById(R.id.ya);
        tidak = findViewById(R.id.tidak);

        pertanyaan.setText(pertanyaan_diagnosa[nomor]);
        ya.setText(jawaban[0]);
        tidak.setText(jawaban[1]);

        radio.check(0);
        jawabanYa = 0;
        jawabanTidak = 0;
    }

    public void btnPertanyaanSelanjutnya(View view) {
        if (ya.isChecked() || tidak.isChecked()) {

            RadioButton jawaban_user = findViewById(radio.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            radio.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_ya[nomor])) jawabanYa++;
            else jawabanTidak++;
            nomor++;
            if (nomor < pertanyaan_diagnosa.length) {
                pertanyaan.setText(pertanyaan_diagnosa[nomor]);
                ya.setText(jawaban[(nomor * 2)]);
                tidak.setText(jawaban[(nomor * 2) + 1]);

            } else {
                result = jawabanYa * 10;
                Intent selesai = new Intent(getApplicationContext(), ResultActivity.class);
                startActivity(selesai);
            }
        } else {
            Toast.makeText(this, "Anda belum memilih", Toast.LENGTH_LONG).show();
        }
    }

    public void btnPertanyaanSebelumnya(View view) {
        onBackPressed();
    }
}