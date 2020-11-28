package id.uinic.diagnosacovid.ui.dataterbaru;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.widget.TextView;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.databinding.ActivityDataCovidBinding;

public class DataCovidActivity extends AppCompatActivity {
    ActivityDataCovidBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_data_covid);
    }
}