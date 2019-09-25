package com.issa.animeshow;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.ViewHolder> {
   // private static final String TAG = "RecyclerViewAdapter";
    private List<List<String>> animeList;
    private Context context;
    public MainRecyclerViewAdapter(List<List<String>> animeList, Context context) {
        this.animeList = animeList;
        this.context = context;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.mainrecyclerview_list_item,parent,false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        viewholder.animeTitle.setText(animeList.get(position).get(0));
        Picasso.get().load(animeList.get(position)
                .get(2)).centerCrop()
                .resize(260,380)
                .into(viewholder.animeImage);

        final int tmp_i = position;
        /*viewholder.parent_layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnimeDetails.class);
                intent.putExtra("url", animeList.get(tmp_i).get(1));
                context.startActivity(intent);
            }
        });
*/
    }


    @Override
    public int getItemCount() {
        return animeList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView animeTitle;
        ImageView animeImage;
        CardView parent_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTitle = itemView.findViewById(R.id.animeTitle);
            animeImage=itemView.findViewById(R.id.animeImage);
            parent_layout=itemView.findViewById(R.id.parent_layout);
        }
    }
}
