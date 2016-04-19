package vasquezh.rviewfirebase.Recycler;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import vasquezh.rviewfirebase.R;

/**
 * Created by Laboratorio on 19/04/2016.
 */
public class MyViewHolder extends RecyclerView.ViewHolder {

    TextView nameTxt;


    public MyViewHolder(View itemView) {
        super(itemView);

        nameTxt= (TextView) itemView.findViewById(R.id.nameTxt);
    }
}
