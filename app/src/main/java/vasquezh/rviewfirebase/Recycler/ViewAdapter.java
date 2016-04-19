package vasquezh.rviewfirebase.Recycler;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import vasquezh.rviewfirebase.Data.Information;
import vasquezh.rviewfirebase.R;

/**
 * Created by Laboratorio on 19/04/2016.
 */
public class ViewAdapter extends RecyclerView.Adapter<MyViewHolder> {

    Context c;
    ArrayList<Information> informations;

    public ViewAdapter(Context c, ArrayList<Information> informations) {
        this.c = c;
        this.informations = informations;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View v= LayoutInflater.from(parent.getContext()).inflate(R.layout.model,parent,false);
        MyViewHolder holder=new MyViewHolder(v);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {

        String name=informations.get(position).getName();
        holder.nameTxt.setText(name);

    }

    @Override
    public int getItemCount() {
        return informations.size();
    }
}
