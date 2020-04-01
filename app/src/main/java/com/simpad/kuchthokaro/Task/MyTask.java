package com.simpad.kuchthokaro.Task;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.solver.widgets.Snapshot;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.simpad.kuchthokaro.R;
import com.simpad.kuchthokaro.Utils.TaskRecyclerViewAdapter;

import java.util.ArrayList;

public class MyTask extends Fragment {

    private static final String TAG = "MyTask";
    private Context mcontext;
    private TextView listNameText;
    private RecyclerView recyclerView;
    private DatabaseReference mDatabase;
    private String categoryName;
    private ImageView add;
    private EditText newTask;
    private TextView dateTimeDisplay;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        mcontext=context;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.my_tasks,container,false);

        return view;
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mDatabase= FirebaseDatabase.getInstance().getReference().child("category");
        final ArrayList<String> task = new ArrayList<>();
        add = view.findViewById(R.id.add);
        newTask = view.findViewById(R.id.newListName);

//        task.add("Go to home");
//        task.add("To go out to play");
//        task.add("Add relative layout");
//        task.add("Read a book");
//        task.add("Go to home");
//        task.add("To go out to play");
//        task.add("Add relative layout");
//        task.add("Read a book");
//        task.add("Go to home");
//        task.add("To go out to play");
//        task.add("Add relative layout");
//        task.add("Read a book");
//        task.add("Go to home");
//        task.add("To go out to play");
//        task.add("Add relative layout");
//        task.add("Read a book");

        listNameText=view.findViewById(R.id.listName);
        Bundle bundle = getArguments();
        if(bundle!=null) {
            listNameText.setText(bundle.getString("listName"));
            categoryName = bundle.getString("listName");
        }



        ImageView backToList = view.findViewById(R.id.goToList);
        backToList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((AppCompatActivity) mcontext).getSupportFragmentManager().beginTransaction().setCustomAnimations(android.R.anim.slide_in_left,android.R.anim.slide_out_right).replace(R.id.container,new MyList()).commit();
            }
        });

        final TaskRecyclerViewAdapter taskRecyclerViewAdapter = new TaskRecyclerViewAdapter(mcontext,task);
        recyclerView = view.findViewById(R.id.taskRecyclerView);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mcontext);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(taskRecyclerViewAdapter);

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");//yyyy-MM-dd HH:mm:ss
                date = dateFormat.format(calendar.getTime());
                mDatabase.child(categoryName).child(newTask.getText().toString()).child("Created on").setValue(date);
                Toast.makeText(mcontext, date, Toast.LENGTH_SHORT).show();
                newTask.setText("");
                taskRecyclerViewAdapter.notifyDataSetChanged();
            }
        });


        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                task.clear();
                for (DataSnapshot snap : dataSnapshot.child(categoryName).getChildren()){
                    if(!snap.getKey().equals("Date")){
                        task.add(snap.getKey());
                    }

                }
                taskRecyclerViewAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


    }





}
