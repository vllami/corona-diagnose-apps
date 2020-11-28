package id.uinic.diagnosacovid.model;

import com.google.gson.annotations.SerializedName;

public class DataCovid {

    @SerializedName("name")
    private String name;
    @SerializedName("positif")
    private String positif;
    @SerializedName("sembuh")
    private String sembuh;
    @SerializedName("meninggal")
    private String meninggal;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPositif() {
        return positif;
    }

    public void setPositif(String positif) {
        this.positif = positif;
    }

    public String getSembuh() {
        return sembuh;
    }

    public void setSembuh(String sembuh) {
        this.sembuh = sembuh;
    }

    public String getMeninggal() {
        return meninggal;
    }

    public void setMeninggal(String meninggal) {
        this.meninggal = meninggal;
    }

    @Override
    public String toString() {
        return
                "DataCovid{" +
                        "name = '" + name + '\'' +
                        ",positif = '" + positif + '\'' +
                        ",sembuh = '" + sembuh + '\'' +
                        ",meninggal = '" + meninggal + '\'' +
                        "}";
    }

}