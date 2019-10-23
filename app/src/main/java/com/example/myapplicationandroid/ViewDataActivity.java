package com.example.myapplicationandroid;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.myapplicationandroid.Adapter.DataAdapter;
import com.example.myapplicationandroid.Database.DatabaseHelper;
import com.example.myapplicationandroid.Item.DataItem;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewDataActivity extends AppCompatActivity {

    private String TAG = "ViewDataActivity";
    private ArrayList<DataItem> dataItemArrayList;
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    @BindView(R.id.editText_insert)
     EditText editTextInsert;
    @BindView(R.id.button_insert)
     Button buttonInsert;
    @BindView(R.id.editText_remove)
     EditText editTextRemove;
    @BindView(R.id.button_remove)
     Button buttonRemove;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_data);
        ButterKnife.bind(this);

        Log.d(TAG, "Hello");

        createDataList();
        buildRecyclerView();

        buttonInsert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                insertItem(position);
            }
        });

        buttonRemove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = Integer.parseInt(editTextInsert.getText().toString());
                removeItem(position);
            }
        });
    }

    public void insertItem(int position){
        dataItemArrayList.add(position, new DataItem(R.drawable.ic_battery_full, "New Item At Position" + position, "This is line 2" ));
        mAdapter.notifyItemInserted(position);
    }

    public void removeItem(int position){
        dataItemArrayList.remove(position);
        mAdapter.notifyItemRemoved(position);
    }

    private void createDataList() {
        dataItemArrayList = new ArrayList<>();
        dataItemArrayList.add(new DataItem(R.drawable.ic_android, "Line 1", "Line 2"));
        dataItemArrayList.add(new DataItem(R.drawable.ic_audio, "Line 1", "Line 2"));
        dataItemArrayList.add(new DataItem(R.drawable.ic_battery_full, "Line 1", "Line 2"));
    }

    public void buildRecyclerView(){
        mRecyclerView = findViewById(R.id.recyclerview);
        mRecyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        mAdapter = new DataAdapter(dataItemArrayList);

        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }


}
