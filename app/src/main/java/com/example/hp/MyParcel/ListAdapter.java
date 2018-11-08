package com.example.hp.MyParcel;

/**
 * Created by HP on 5/21/2018.
 */

import java.util.ArrayList;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> Resepi;
    ArrayList<String> Kategori;


    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> resepi,
            ArrayList<String> kategori
    )
    {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.Resepi = resepi;
        this.Kategori = kategori;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewNAME);
            holder.ResepiTextView = (TextView) child.findViewById(R.id.textViewRESEPI);
            holder.KategoriTextView = (TextView) child.findViewById(R.id.textViewKATEGORI);
            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Name_TextView.setText(Name.get(position));
        holder.ResepiTextView.setText(Resepi.get(position));
        holder.KategoriTextView.setText(Kategori.get(position));
        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView ResepiTextView;
        TextView KategoriTextView;
    }

}
