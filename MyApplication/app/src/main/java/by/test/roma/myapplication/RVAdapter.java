package by.test.roma.myapplication;

import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by Лапа on 8/25/2015.
 */
public class RVAdapter extends RecyclerView.Adapter<RVAdapter.AlarmViewHolder>{
    //view layout enflatimg/creating
    @Override
    public AlarmViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout,parent,false);
        AlarmViewHolder holder = new AlarmViewHolder(v);
        return holder;
    }
    //data bind
    @Override
    public void onBindViewHolder(AlarmViewHolder holder, int position) {
        holder.pic.setImageBitmap(recipes.get(position).img);
        holder.name.setText(recipes.get(position).name);
        holder.shortDescription.setText(recipes.get(position).description);
    }

    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public int getItemCount() {
        return recipes.size();
    }
    List<Recipe> recipes;

    RVAdapter(List<Recipe> recipes){
        this.recipes = recipes;
    }


    //data and dataFields initialize
    public static class AlarmViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        ImageView pic;
        TextView name;
        TextView shortDescription;
        AlarmViewHolder(View itemView) {
            super(itemView);
            cv = (CardView)itemView.findViewById(R.id.cardView);
            pic = (ImageView)itemView.findViewById(R.id.alarmPic);
            name = (TextView)itemView.findViewById(R.id.alarmNameTv);
            shortDescription = (TextView)itemView.findViewById(R.id.alarmDescription);
        }

    }
}
