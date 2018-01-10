package net.zhulachmad.mahasiswa;

import android.os.Parcel;
import android.os.Parcelable;


public class ParcelableMahasiswa implements Parcelable {


    private Mahasiswa mahasiswa;

    public Mahasiswa getNamaMhs(){
        return mahasiswa;
    }


    public ParcelableMahasiswa(Mahasiswa mahasiswa){
        super();
        this.mahasiswa=mahasiswa;

    }


    private ParcelableMahasiswa(Parcel in){
        mahasiswa = new Mahasiswa();
        mahasiswa.setNamaMhs(in.readString());
        mahasiswa.setNim(in.readString());


    }
    @Override
    public int describeContents() {
        return 0;
    }


    public void writeToParcel(Parcel parcel, int flags) {
        parcel.writeString(mahasiswa.getNamaMhs());
        parcel.writeString(mahasiswa.getNim());

    }


    public static final Parcelable.Creator<ParcelableMahasiswa> CREATOR =
            new Parcelable.Creator<ParcelableMahasiswa>() {
        @Override
        public ParcelableMahasiswa createFromParcel(Parcel in) {


            return new ParcelableMahasiswa(in);
        }

        @Override
        public ParcelableMahasiswa[] newArray(int size) {
            return new ParcelableMahasiswa[size];
        }
    };

}
