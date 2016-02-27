package pedidofacil.com.br.pedidofacil;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Collections;
import java.util.List;

/**
 * Created by diego on 2/25/16.
 */
public class NavigationDrawerAdapter extends RecyclerView.Adapter<NavigationDrawerAdapter.MyviewHolder> {
    private LayoutInflater inflater;
    private List<NavigationDrawerInfo> data = Collections.emptyList();
    private Context context;
    public NavigationDrawerAdapter(Context context, List<NavigationDrawerInfo> data){
        this.context = context;
        inflater = LayoutInflater.from(context);
        this.data = data;
    }

    @Override
    //ViewHolder describes a item view on recycleView
    public MyviewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.navigation_drawer_row, parent,false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyviewHolder holder, int position) {
        NavigationDrawerInfo info = data.get(position);
        holder.icon.setImageResource(info.getIconId());
        holder.text.setText(info.getTitle());
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    //method to delete item row of recycleView
    /*public void delete(int position){
        data.remove(position);
        notifyItemRemoved(position);
    }*/

    class MyviewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        ImageView icon;
        TextView text;
        public MyviewHolder(View itemView) {
            super(itemView);
            icon = (ImageView) itemView.findViewById(R.id.image_nd_row);
            text = (TextView) itemView.findViewById(R.id.text_nd_row);
            //Select the entire itemView. It also can be used for select icon or text only
            itemView.setOnClickListener(this);
        }

        public void onClick(View v){
            Toast.makeText(context,"Item Clicked at" + getAdapterPosition(),Toast.LENGTH_SHORT).show();
            // set Background Color when item clicked
            //v.setBackgroundColor(ContextCompat.getColor(context,R.color.color_highlight));
            //delete(getAdapterPosition());
            if (getAdapterPosition() == 0) {
                context.startActivity(new Intent(context, ContentActivity.class));
            }
            else if (getAdapterPosition() == 1){
                context.startActivity(new Intent(context,SubActivity.class));
            }
        }
    }

}
