package com.simpad.kuchthokaro.Task;

import android.content.Context;
import android.icu.text.SimpleDateFormat;
import android.icu.util.Calendar;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.PopupMenu;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.snapshot.IndexedNode;
import com.simpad.kuchthokaro.Model.data;
import com.simpad.kuchthokaro.R;
import com.simpad.kuchthokaro.Utils.CategoryListsAdapter;

import java.util.ArrayList;

public class MyList extends Fragment {


    private static final String TAG = "MyList";
    private Context mcontext;
    private FragmentManager fragmentManager;
    private DatabaseReference mDatabase;
    private ArrayList<String> listName;
    private ArrayList<Integer> taskNum;
    private CategoryListsAdapter categoryListsAdapter;
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
        View view = inflater.inflate(R.layout.tasks_my_list_fragment,container,false);
        listName = new ArrayList<>();
        taskNum = new ArrayList<>();
        return view;

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        mDatabase = FirebaseDatabase.getInstance().getReference();
        GridView gridView = view.findViewById(R.id.myListGridView);
        int width = getResources().getDisplayMetrics().widthPixels;
        Log.d(TAG, "onCreateView: "+width);
        int gridWidth = width/2;
        gridView.setColumnWidth(gridWidth);
        categoryListsAdapter = new CategoryListsAdapter(mcontext,listName,taskNum);
        gridView.setAdapter(categoryListsAdapter);
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                MyTask taskMy = new MyTask();
                Log.d(TAG, "onItemClick: "+position);
                Log.d(TAG, "To Parse "+parent.getItemAtPosition(position));
                data Data = new data();
                Data = (data)parent.getItemAtPosition(position);
                Bundle bundle = new Bundle();
                bundle.putString("listName",Data.getCategoryName());
                taskMy.setArguments(bundle);
                Log.d(TAG, "To Parse "+Data.getCategoryName());
                fragmentManager=((AppCompatActivity)mcontext).getSupportFragmentManager();
                fragmentManager.beginTransaction().setCustomAnimations(android.R.anim.fade_in,android.R.anim.fade_out)
                        .replace(R.id.container, taskMy).addToBackStack(null).commit();

            }
        });
        ImageView add = view.findViewById(R.id.add);
        final EditText categoryName = view.findViewById(R.id.newListName);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calendar = Calendar.getInstance();
                dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");//yyyy-MM-dd HH:mm:ss
                date = dateFormat.format(calendar.getTime());
                String category = categoryName.getText().toString();
                data newCategory = new data(category,date);
                mDatabase.child("category").child(newCategory.getCategoryName()).child("Date").setValue(newCategory.getDate());
                Toast.makeText(mcontext, "Done", Toast.LENGTH_SHORT).show();
                categoryName.setText("");
                categoryListsAdapter.notifyDataSetChanged();


            }
        });









        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                listName.clear();
                taskNum.clear();
                for (DataSnapshot snapshot : dataSnapshot.child("category").getChildren()){
                    listName.add(snapshot.getKey());
                    taskNum.add((int) snapshot.getChildrenCount()-1);
                    Log.d(TAG, "onDataChange: "+snapshot+"1");
                }

                categoryListsAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        Toolbar toolbar = view.findViewById(R.id.my_list_tool_bar);
        toolbar.setCollapseIcon(R.drawable.ic_action_name);
        final ImageView menuImage = view.findViewById(R.id.toolbarMenu);
        menuImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(getContext(),menuImage);
                popupMenu.inflate(R.menu.app_bar_menu);
                popupMenu.show();
            }
        });


    }


}
