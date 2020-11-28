package id.uinic.diagnosacovid.api;

import java.util.List;

import id.uinic.diagnosacovid.model.DataCovid;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {
    @GET("indonesia")
    Call<List<DataCovid>> getDataCovid();
}
