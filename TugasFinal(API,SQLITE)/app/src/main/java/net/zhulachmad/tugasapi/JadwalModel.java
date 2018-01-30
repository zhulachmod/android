package net.zhulachmad.tugasapi;

/**
 * Created by root on 25/01/18.
 */

public class JadwalModel {
    private String tanggal;
    private String imsyak;
    private String shubuh;
    private String terbit;
    private String dhuha;

    private String dzuhur;
    private String ashr;
    private String maghrib;
    private String isya;

    public JadwalModel(String tanggal, String imsyak, String shubuh, String terbit, String dhuha, String dzuhur, String ashr, String maghrib, String isya) {
        this.tanggal = tanggal;
        this.imsyak = imsyak;
        this.shubuh = shubuh;
        this.terbit = terbit;
        this.dhuha = dhuha;

        this.dzuhur = dzuhur;
        this.ashr = ashr;
        this.maghrib = maghrib;
        this.isya = isya;
    }

    public JadwalModel() {
    }

    public String getTanggal() {
        return tanggal;
    }

    public void setTanggal(String tanggal) {
        this.tanggal = tanggal;
    }

    public String getImsyak() {
        return imsyak;
    }

    public void setImsyak(String imsyak) {
        this.imsyak = imsyak;
    }

    public String getShubuh() {
        return shubuh;
    }

    public void setShubuh(String shubuh) {
        this.shubuh = shubuh;
    }

    public String getTerbit() {
        return terbit;
    }

    public void setTerbit(String terbit) {
        this.terbit = terbit;
    }

    public String getDhuha() {
        return dhuha;
    }

    public void setDhuha(String dhuha) {
        this.dhuha = dhuha;
    }




    public String getDzuhur() {
        return dzuhur;
    }

    public void setDzuhur(String dzuhur) {
        this.dzuhur = dzuhur;
    }





    public String getAshr() {
        return ashr;
    }

    public void setAshr(String ashr) {
        this.ashr = ashr;
    }

    public String getMaghrib() {
        return maghrib;
    }

    public void setMaghrib(String maghrib) {
        this.maghrib = maghrib;
    }

    public String getIsya() {
        return isya;
    }

    public void setIsya(String isya) {
        this.isya = isya;
    }
}
