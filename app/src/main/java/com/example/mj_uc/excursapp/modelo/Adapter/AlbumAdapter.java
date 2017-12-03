package com.example.mj_uc.excursapp.modelo.Adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.mj_uc.excursapp.R;
import com.example.mj_uc.excursapp.modelo.Album;

import java.util.List;

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.MyViewHolder> {

    private Context mc;
    private List<Album> albumList;

    public class MyViewHolder extends RecyclerView.ViewHolder{
        public TextView title, count, date;
        public ImageView thumbnail;

        public MyViewHolder(View view){
            super(view);
            title= (TextView) view.findViewById(R.id.title);
            count = (TextView) view.findViewById(R.id.count);
            date = (TextView) view.findViewById(R.id.date);
            thumbnail = (ImageView) view.findViewById(R.id.thumbnail);
        }
    }

    public AlbumAdapter(Context mc, List<Album> albumList){
        this.mc= mc;
        this.albumList=albumList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.card, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position){
        final Album album = albumList.get(position);
        holder.title.setText(album.getTitulo());
        holder.count.setText(album.getProfesor());
        holder.date.setText(album.getFecha());

        int resourceId = mc.getResources().getIdentifier(removeExtension(album.getImagen()), "drawable", mc.getPackageName());

        Glide.with(mc).load(resourceId).into(holder.thumbnail);

        holder.thumbnail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                /*Intent intent = new Intent(mc, SignoutActivity.class);
                intent.putExtra("ID_PHOTO", album.getId());
                mc.startActivity(intent);*/
            }
        });
    }

    private void showPopupMenu(View view){
        //inflar el menu
        PopupMenu popupMenu = new PopupMenu(mc, view);
        MenuInflater inflater = popupMenu.getMenuInflater();
        inflater.inflate(R.menu.menu_scrolling, popupMenu.getMenu());
       // popupMenu.setOnMenuItemClickListener(new MyMenuItemClickListener());
        popupMenu.show();
    }

    @Override
    public int getItemCount(){ return albumList.size();}

    public String removeExtension(String fileName){
        return fileName.substring(0, fileName.lastIndexOf('.'));
    }
}
