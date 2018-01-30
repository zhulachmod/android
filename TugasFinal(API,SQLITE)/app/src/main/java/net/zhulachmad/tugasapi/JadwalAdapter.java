package net.zhulachmad.tugasapi;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by root on 25/01/18.
 */

public class JadwalAdapter extends RecyclerView.Adapter<JadwalAdapter.Holder>{

    private List<JadwalModel> mListData;
    private Context mContext;

    public JadwalAdapter(List<JadwalModel> mListData, Context mContext) {
        this.mListData = mListData;
        this.mContext = mContext;
    }

    @Override
    public Holder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.adapter_item_data, null);
        return new Holder(view);
    }

    @Override
    public void onBindViewHolder(Holder holder, int position) {
        JadwalModel model = mListData.get(position);

        //set data makanan
        holder.tvTanggal.setText("Tanggal :"+model.getTanggal());
        holder.tvImsyak.setText("Imsyak :"+model.getImsyak());
        holder.tvShubuh.setText("Shubuh :"+model.getShubuh());
        holder.tvTerbit.setText("Terbit :"+model.getTerbit());
        holder.tvDhuha.setText("Dhuha :"+model.getDhuha());
        holder.tvDzuhur.setText("Dzuhur :"+model.getDzuhur());
        holder.tvAshr.setText("Ashr :"+model.getAshr());
        holder.tvMaghrib.setText("Maghrib :"+model.getMaghrib());
        holder.tvIsya.setText("isya :"+model.getIsya());
    }

    @Override
    public int getItemCount() {
        return mListData.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {

        public TextView tvTanggal;
        public TextView tvImsyak;
        public TextView tvShubuh;
        public TextView tvTerbit;
        public TextView tvDhuha;

        public TextView tvDzuhur;
        public TextView tvAshr;
        public TextView tvMaghrib;
        public TextView tvIsya;


        public Holder(View itemView) {
            super(itemView);

            tvTanggal = (TextView) itemView.findViewById(R.id.textview_tanggal);
            tvImsyak = (TextView) itemView.findViewById(R.id.textview_imsyak);
            tvShubuh = (TextView) itemView.findViewById(R.id.textview_shubuh);
            tvTerbit = (TextView) itemView.findViewById(R.id.textview_terbit);
            tvDhuha = (TextView) itemView.findViewById(R.id.textview_dhuha);

            tvDzuhur = (TextView) itemView.findViewById(R.id.textview_dzuhur);
            tvAshr = (TextView) itemView.findViewById(R.id.textview_ashr);
            tvMaghrib = (TextView) itemView.findViewById(R.id.textview_maghrib);
            tvIsya = (TextView) itemView.findViewById(R.id.textview_isya);

        }
    }
}