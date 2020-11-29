package id.uinic.diagnosacovid.ui.quisioner;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.ContextThemeWrapper;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.ui.result.ResultActivity;
import id.uinic.diagnosacovid.database.DatabaseHelper;

import static id.uinic.diagnosacovid.util.Const.JAWABAN_KEY;
import static id.uinic.diagnosacovid.util.Const.RESULT_KEY;

public class MainActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;

    TextView pertanyaan;
    RadioGroup radio;
    RadioButton ya, tidak;
    Button btnSelanjutnya;
    int nomor = 0;
    Boolean selesai;
    public static int result, jawabanYa, jawabanTidak;



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

        pertanyaan = findViewById(R.id.pertanyaan);
        radio = findViewById(R.id.pilihan);
        ya = findViewById(R.id.ya);
        tidak = findViewById(R.id.tidak);
        btnSelanjutnya = findViewById(R.id.btn_selanjutnya);

        pertanyaan.setText(pertanyaan_diagnosa[nomor]);
        ya.setText(jawaban[0]);
        tidak.setText(jawaban[1]);

        dbHelper = new DatabaseHelper(this);

        selesai = false;

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

                if (nomor < ( pertanyaan_diagnosa.length - 1)) {
                    btnSelanjutnya.setText(R.string.lihat_hasil_text);
                }

            } else {
                result = jawabanYa * 10;
                selesai = true;
                Intent selesai = new Intent(getApplicationContext(), ResultActivity.class);
                selesai.putExtra(JAWABAN_KEY, jawaban_ya);
                selesai.putExtra(RESULT_KEY, result);
                startActivity(selesai);

                simpanDataDiagnosa();
            }
        } else {
            Toast.makeText(this, "Anda belum memilih", Toast.LENGTH_LONG).show();
        }
    }

    private void simpanDataDiagnosa() {
        @SuppressLint("SimpleDateFormat") DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String dateTime = df.format(Calendar.getInstance().getTime());

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        db.execSQL("insert into diagnosa(tanggal_diagnosa, jawabanYa, result) values('" +
                dateTime + "','" +
                jawabanYa + "','" +
                result + "')");
        Toast.makeText(getApplicationContext(), "Berhasil" ,
                Toast.LENGTH_LONG).show();
        finish();
    }

    @Override
    public void onBackPressed() {
        if (selesai){
            super.onBackPressed();
        } else {
            new AlertDialog.Builder(new ContextThemeWrapper(MainActivity.this, R.style.CustomAlertDialog))
                    .setIcon(R.drawable.ic_alert)
                    .setTitle("Pertanyaan Belum Selesai")
                    .setMessage("Yakin ingin keluar ?")
                    .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                    .show();
        }
    }
}