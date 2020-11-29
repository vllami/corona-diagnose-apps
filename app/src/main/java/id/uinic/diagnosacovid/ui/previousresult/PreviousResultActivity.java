package id.uinic.diagnosacovid.ui.previousresult;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.database.DatabaseHelper;
import id.uinic.diagnosacovid.databinding.ActivityPreviousResultBinding;
import id.uinic.diagnosacovid.ui.result.ResultActivity;

import static id.uinic.diagnosacovid.util.Const.JAWABAN_KEY;
import static id.uinic.diagnosacovid.util.Const.RESULT_KEY;

public class PreviousResultActivity extends AppCompatActivity {
    ActivityPreviousResultBinding bind;

    String[] daftarDiagnosa;
    Integer[] jawabanDiagnosa, resultDiagnosa;

    DatabaseHelper dbCenter;
    protected Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_previous_result);

        dbCenter = new DatabaseHelper(this);
        RefreshList();
    }

    private void RefreshList() {
        SQLiteDatabase db = dbCenter.getReadableDatabase();
        cursor = db.rawQuery("SELECT * FROM diagnosa ORDER BY tanggal_diagnosa DESC", null);
        daftarDiagnosa = new String[cursor.getCount()];
        jawabanDiagnosa = new Integer[cursor.getCount()];
        resultDiagnosa = new Integer[cursor.getCount()];

        for (int cc = 0; cc < cursor.getCount(); cc++) {
            cursor.moveToPosition(cc);
            daftarDiagnosa[cc] = cursor.getString(1);
            jawabanDiagnosa[cc] = cursor.getInt(2);
            resultDiagnosa[cc] = cursor.getInt(3);
        }

        bind.lvDiagnosa.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, daftarDiagnosa));
        bind.lvDiagnosa.setSelected(true);
        bind.lvDiagnosa.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView arg0, View arg1, int arg2, long arg3) {
                final Integer selectionJawaban = jawabanDiagnosa[arg2];
                final Integer selectionResult = resultDiagnosa[arg2];
                Intent i = new Intent(PreviousResultActivity.this, ResultActivity.class);
                i.putExtra(JAWABAN_KEY, selectionJawaban);
                i.putExtra(RESULT_KEY, selectionResult);
                startActivity(i);
            }
        });
        ((ArrayAdapter) bind.lvDiagnosa.getAdapter()).notifyDataSetInvalidated();
    }

    public void btnBeranda(View view) {
        onBackPressed();
    }
}