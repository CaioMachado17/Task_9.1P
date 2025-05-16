package com.example.task91p;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

public class ListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    DatabaseHelper dbHelper;

    AdvertAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Lost and Found Items");

        recyclerView = findViewById(R.id.recyclerViewAdverts);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        dbHelper = new DatabaseHelper(this);
        List<Advert> adverts = dbHelper.getAllAdverts();

        AdvertAdapter adapter = new AdvertAdapter(this, adverts);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish(); // goes back
        return true;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 1) {
            // Refresh the list
            List<Advert> updatedAdverts = dbHelper.getAllAdverts();
            adapter = new AdvertAdapter(this, updatedAdverts);
            recyclerView.setAdapter(adapter);
        }
    }


}
