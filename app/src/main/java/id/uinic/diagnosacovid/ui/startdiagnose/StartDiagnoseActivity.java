package id.uinic.diagnosacovid.ui.startdiagnose;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.database.DatabaseHelper;
import id.uinic.diagnosacovid.ui.result.ResultActivity;

import static id.uinic.diagnosacovid.util.Const.JAWABAN_KEY;
import static id.uinic.diagnosacovid.util.Const.RESULT_KEY;

public class StartDiagnoseActivity extends AppCompatActivity {

    protected Cursor cursor;
    DatabaseHelper dbHelper;
    Dialog dialog;
    Button btnSelanjutnya;

    TextView tvPertanyaan;
    RadioGroup rgPilihan;
    RadioButton rbYa, rbTidak;

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
            "indera penciuman atau perasa menghilang?",
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
        setContentView(R.layout.activity_start_diagnose);

        // Call ID
        tvPertanyaan = findViewById(R.id.tv_pertanyaan);
        rgPilihan = findViewById(R.id.rg_pilihan);
        rbYa = findViewById(R.id.rb_ya);
        rbTidak = findViewById(R.id.rb_tidak);

        tvPertanyaan.setText(pertanyaan_diagnosa[nomor]);
        rbYa.setText(jawaban[0]);
        rbTidak.setText(jawaban[1]);

        dbHelper = new DatabaseHelper(this);

        selesai = false;

        rgPilihan.check(0);
        jawabanYa = 0;
        jawabanTidak = 0;
    }

    public void btnPertanyaanSelanjutnya(View view) {
        if (rbYa.isChecked() || rbTidak.isChecked()) {

            RadioButton jawaban_user = findViewById(rgPilihan.getCheckedRadioButtonId());
            String ambil_jawaban_user = jawaban_user.getText().toString();
            rgPilihan.check(0);
            if (ambil_jawaban_user.equalsIgnoreCase(jawaban_ya[nomor])) jawabanYa++;
            else jawabanTidak++;
            nomor++;
            if (nomor < pertanyaan_diagnosa.length) {
                tvPertanyaan.setText(pertanyaan_diagnosa[nomor]);
                rbYa.setText(jawaban[(nomor * 2)]);
                rbTidak.setText(jawaban[(nomor * 2) + 1]);

                if (nomor == ( pertanyaan_diagnosa.length - 1)) {
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
        Toast.makeText(getApplicationContext(), "Berhasil",
                Toast.LENGTH_LONG).show();
        finish();
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    @Override
    public void onBackPressed() {
        dialog.show();

        if (selesai) {
            super.onBackPressed();
        } else {
            // Pada onBackPressed method
            dialog = new Dialog(StartDiagnoseActivity.this);
            dialog.setContentView(R.layout.alert_dialog);
            dialog.getWindow().setBackgroundDrawable(getDrawable(R.drawable.radius));
            // ViewGroup pertama adalah Width, kedua adalah Height
            dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            dialog.setCancelable(false);
            dialog.getWindow().getAttributes().windowAnimations = R.style.animation;

            Button btnTidak = dialog.findViewById(R.id.btn_tidak);
            Button btnYa = dialog.findViewById(R.id.btn_ya);

            btnTidak.setOnClickListener(v -> dialog.dismiss());
            btnYa.setOnClickListener(v -> finish());

//            new AlertDialog.Builder(new ContextThemeWrapper(StartDiagnoseActivity.this, R.style.CustomAlertDialog))
//                    .setIcon(R.drawable.ic_alert)
//                    .setTitle("Diagnosa sedang berlangsung")
//                    .setMessage("Anda yakin ingin keluar?")
//                    .setPositiveButton("Ya", (dialog, which) -> finish())
//                    .setNegativeButton("Tidak", (dialog, which) -> {
//                    })
//                    .show();
        }
    }
}