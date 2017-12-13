package com.example.mj_uc.excursapp.modelo.Adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;


public class ImagenAdapter extends ArrayAdapter<String> {

    String[] nombres;
    int [] fotos;
    Context mContext;

    public ImagenAdapter(@NonNull Context context, String[] nombresImg, int [] idImg) {
        super(context, R.layout.listview_item);
        this.nombres = nombresImg;
        this.fotos = idImg;
        this.mContext = context;
    }

    @Override
    public int getCount() {
        return nombres.length;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ViewHolder mViewHolder = new ViewHolder();
        if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater) mContext.
                    getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.listview_item, parent, false);
            mViewHolder.photo = (ImageView) convertView.findViewById(R.id.imageView);
            mViewHolder.nomPhoto = (TextView) convertView.findViewById(R.id.tituloAct);
            convertView.setTag(mViewHolder);
        } else {
            mViewHolder = (ViewHolder) convertView.getTag();
        }
            mViewHolder.photo.setImageResource(fotos[position]);
            mViewHolder.nomPhoto.setText(nombres[position]);

        return convertView;
    }

    static class ViewHolder{
        ImageView photo;
        TextView nomPhoto;
    }
}
