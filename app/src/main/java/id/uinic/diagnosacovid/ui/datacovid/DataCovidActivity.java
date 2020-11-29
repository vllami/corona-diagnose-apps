package id.uinic.diagnosacovid.ui.datacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.databinding.DataBindingUtil;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.api.Api;
import id.uinic.diagnosacovid.api.ApiInterface;
import id.uinic.diagnosacovid.databinding.ActivityDataCovidBinding;
import id.uinic.diagnosacovid.model.DataCovid;
import id.uinic.diagnosacovid.ui.home.HomeActivity;
import id.uinic.diagnosacovid.ui.startdiagnose.StartDiagnoseActivity;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCovidActivity extends AppCompatActivity {
    ActivityDataCovidBinding bind;

    TextView tvDate;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        bind = DataBindingUtil.setContentView(this, R.layout.activity_data_covid);
        date();
        getDataCovid();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(v -> finish());
    }

    private void date() {
        tvDate = findViewById(R.id.tv_date);

        SimpleDateFormat sdf = new SimpleDateFormat("dd MMM yyyy", Locale.getDefault());
        String currentDate = sdf.format(new Date());
        tvDate.setText(currentDate);
    }

    private void getDataCovid() {
        ApiInterface Service;
        Call<List<DataCovid>> Call;
        try {
            Service = Api.getApi().create(ApiInterface.class);
            Call = Service.getDataCovid();
            Call.enqueue(new Callback<List<DataCovid>>() {
                @Override
                public void onResponse(retrofit2.Call<List<DataCovid>> call, Response<List<DataCovid>> response) {
                    DataCovid dataCovid = response.body().get(0);
                    bind.setDataCovid(dataCovid);
                }

                @Override
                public void onFailure(retrofit2.Call<List<DataCovid>> call, Throwable t) {
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_SHORT).show();
                    Log.d("ERROR 1", t.getMessage());
                }
            });
        } catch (Exception e) {
            e.printStackTrace();
            Log.d("ERROR EUY", e.getMessage());
        }
    }
}