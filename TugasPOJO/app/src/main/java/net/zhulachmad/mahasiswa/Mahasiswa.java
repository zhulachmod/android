package net.zhulachmad.mahasiswa;

/**
 * Created by root on 06/12/17.
 */
import android.os.Parcelable;
import android.os.Parcel;

public class Mahasiswa {

    private String namamhs;
    private String nim;

    public String getNamaMhs() {
        return this.namamhs;
    }

    public String getNim() {
        return this.nim;
    }

    public void setNamaMhs(String namamhs) {
        this.namamhs=namamhs;

    }

    public void setNim(String nim) {
        this.nim=nim;

    }

}

