package id.uinic.diagnosacovid.ui.datacovid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

import id.uinic.diagnosacovid.R;
import id.uinic.diagnosacovid.api.Api;
import id.uinic.diagnosacovid.api.ApiInterface;
import id.uinic.diagnosacovid.databinding.ActivityDataCovidBinding;
import id.uinic.diagnosacovid.model.DataCovid;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DataCovidActivity extends AppCompatActivity {
    ActivityDataCovidBinding bind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_data_covid);
        getDataCovid();
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