package com.simpad.kuchthokaro.Utils;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.simpad.kuchthokaro.Model.data;
import com.simpad.kuchthokaro.R;

import java.util.ArrayList;

public class TaskRecyclerViewAdapter extends RecyclerView.Adapter<TaskRecyclerViewAdapter.ViewHolder> {

    private ArrayList<String> tasks;
    private LayoutInflater mlayoutInflater;
    private Context mcontext;
    private String ListName;
    private DatabaseReference mdatabase;



    public static class ViewHolder extends RecyclerView.ViewHolder{

        CheckBox checkBox;
        TextView taskName;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox);
            taskName = itemView.findViewById(R.id.taskText);
        }
    }

    public TaskRecyclerViewAdapter(Context context,ArrayList<String> tasks,String ListName) {
        this.tasks = tasks;
        this.mcontext=context;
        mlayoutInflater = (LayoutInflater) this.mcontext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        this.ListName = ListName;
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
    public void onBindViewHolder(@NonNull final TaskRecyclerViewAdapter.ViewHolder holder, int position) {
        mdatabase = FirebaseDatabase.getInstance().getReference();
        final String task =tasks.get(position);
        holder.taskName.setText(task);
        holder.checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(holder.checkBox.isChecked()){
                    Toast.makeText(mcontext, "It has been Checked", Toast.LENGTH_SHORT).show();
                    mdatabase.child("category").child(ListName).child(task).child("done").setValue(1);
                    holder.checkBox.setChecked(false);
                }
            }
        });

    }

    @Override
    public int getItemCount() {
        return tasks.size();
    }
}
