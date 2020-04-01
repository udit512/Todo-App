package com.simpad.kuchthokaro.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.simpad.kuchthokaro.Model.data;
import com.simpad.kuchthokaro.R;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> tasks;
    private LayoutInflater mlayoutInflater;
    private Context mcontext;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView taskName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            taskName = itemView.findViewById(R.id.taskText);
        }
    }

    public TaskRecyclerViewAdapter(Context context,ArrayList<String> tasks) {
        this.tasks = tasks;
        this.mcontext=context;
        mlayoutInflater = (LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    public TaskRecyclerViewAdapter() {
    }

    @NonNull
    @Override
    public TaskRecyclerViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View v =  mlayoutInflater.inflate(R.layout.my_task_recycler_view,parent,false);

        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskRecyclerViewAdapter.ViewHolder holder, int position) {

        String task =tasks.get(position);
        holder.taskName.setText(task);

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
