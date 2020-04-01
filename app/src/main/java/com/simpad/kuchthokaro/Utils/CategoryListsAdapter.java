package com.simpad.kuchthokaro.Utils;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.cardview.widget.CardView;

import com.simpad.kuchthokaro.Model.data;
import com.simpad.kuchthokaro.R;

import java.util.ArrayList;
import java.util.Random;

public class CategoryListsAdapter extends BaseAdapter {

    private final Context mcontext;
    private ArrayList<String > catList;
    private ArrayList<Integer> numList;
    private LayoutInflater mlayoutInflater;


    public CategoryListsAdapter(Context mcontext, ArrayList<String> catList, ArrayList<Integer> numList) {
        this.mcontext = mcontext;
        this.catList = catList;
        this.numList = numList;
        mlayoutInflater = (LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return catList.size();
    }

    @Override
    public Object getItem(int position) {
        data Data = new data(catList.get(position),"2782001");
        return Data;


    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    ///////////////////////////////////////// View Holder Class /////////////////////////////////////

    public static class ViewHolder{
        TextView catName;
        TextView numItem;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder viewHolder;
        ArrayList<String> colors = new ArrayList<>();

        colors.add("#ffd31d");
        colors.add("#fe346e");
        colors.add("#f1e7b6");
        colors.add("#ffb6b6");
        colors.add("#fde2e2");
        colors.add("#0c7b93");
        colors.add("#ffd5e5");
        colors.add("#f1c6de");
        colors.add("#be8abf");
        colors.add("#42e6a4");
        colors.add("#51eaea");
        colors.add("#d597ce");
        colors.add("#ff8080");
        colors.add("#b6ffea");

        Random random = new Random();
        Integer index = random.nextInt(colors.size());




        String categoryName = catList.get(position);
        String numOfItems = numList.get(position).toString()+" Task";
        CardView category ;

        if(convertView==null){


            convertView = mlayoutInflater.inflate(R.layout.my_list_grid_view,parent,false);
            viewHolder = new ViewHolder();
            viewHolder.catName=convertView.findViewById(R.id.listName);
            viewHolder.numItem=convertView.findViewById(R.id.taskNumber);
            category = convertView.findViewById(R.id.list);
            category.setCardBackgroundColor(Color.parseColor(colors.get(index)));
            convertView.setTag(viewHolder);
        }
        else
            viewHolder = (ViewHolder) convertView.getTag();

//        viewHolder.catName=convertView.findViewById(R.id.listName);
//        viewHolder.numItem=convertView.findViewById(R.id.taskNumber);
        viewHolder.catName.setText(categoryName);
        viewHolder.numItem.setText(numOfItems);


        return convertView;
    }
}
