package id.sch.smktelkom_mlg.learn.signature.adapter;

import android.content.Context;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import id.sch.smktelkom_mlg.learn.signature.R;
import id.sch.smktelkom_mlg.learn.signature.model.Perusahaan;

/**
 * Created by Faiqoh on 07/08/2017.
 */

public class PerusahaanAdapter extends RecyclerView.Adapter<PerusahaanAdapter.ViewHolder> {

    ArrayList<Perusahaan> perusahaanList;
    IPerusahaaAdapter mIPerusahaanAdapter;

    public PerusahaanAdapter(Context context, ArrayList<Perusahaan> perusahaanList) {
        this.perusahaanList = perusahaanList;
        mIPerusahaanAdapter = (IPerusahaaAdapter) context;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_list, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Perusahaan perusahaan = perusahaanList.get(position);
        holder.tvNama.setText(perusahaan.nama);
        holder.tvKeluhan.setText(perusahaan.keluhan);
        //holder.ivFoto.setImageDrawable(perusahaan.foto);
        holder.cItem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mIPerusahaanAdapter.doClick(perusahaan.id);
                }
            });
    }

    @Override
    public int getItemCount() {
        if (perusahaanList != null)
            return perusahaanList.size();
        return 0;
    }

    public interface IPerusahaaAdapter {
        void doClick(String pos);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //ImageView ivFoto;
        TextView tvNama;
        TextView tvKeluhan;
        CardView cItem;

        public ViewHolder(View itemView) {
            super(itemView);
            //ivFoto = (ImageView) itemView.findViewById(R.id.imageView);
            tvNama = (TextView) itemView.findViewById(R.id.textViewNama);
            tvKeluhan = (TextView) itemView.findViewById(R.id.textViewKeluhan);
            cItem = (CardView) itemView.findViewById(R.id.itemCard);
//            itemView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View view) {
//                    mIPerusahaanAdapter.doClick(getAdapterPosition());
//                }
//            });

        }
    }
}
