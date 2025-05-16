package com.example.task91p;

import android.graphics.Typeface;
import android.os.Bundle;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.StyleSpan;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class DetailActivity extends AppCompatActivity {

    TextView textViewDetailType, textViewDetailName, textViewDetailPhone,
        textViewDetailDescription, textViewDetailDate, textViewDetailLocation;
    Button buttonDelete;

    DatabaseHelper dbHelper;
    int advertId;
    boolean wasDeleted = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle("Item Details");

        textViewDetailType = findViewById(R.id.textViewDetailType);
        textViewDetailName = findViewById(R.id.textViewDetailName);
        textViewDetailPhone = findViewById(R.id.textViewDetailPhone);
        textViewDetailDescription = findViewById(R.id.textViewDetailDescription);
        textViewDetailDate = findViewById(R.id.textViewDetailDate);
        textViewDetailLocation = findViewById(R.id.textViewDetailLocation);
        buttonDelete = findViewById(R.id.buttonDelete);

        dbHelper = new DatabaseHelper(this);
        advertId = getIntent().getIntExtra("id", -1);

        if (advertId != -1) {
            for (Advert ad : dbHelper.getAllAdverts()) {
                if (ad.id == advertId) {

                    // Type line stays simple
                    textViewDetailType.setText(ad.type + " item");

                    // Apply bold label to each detail
                    textViewDetailName.setText(makeBoldLabel("Name: ", ad.name));
                    textViewDetailPhone.setText(makeBoldLabel("Phone: ", ad.phone));
                    textViewDetailDescription.setText(makeBoldLabel("Description: ", ad.description));
                    textViewDetailDate.setText(makeBoldLabel("Date: ", ad.date));
                    textViewDetailLocation.setText(makeBoldLabel("Location: ", ad.location));
                    break;
                }
            }
        }

        buttonDelete.setOnClickListener(v -> {
            boolean deleted = dbHelper.deleteAdvert(advertId);
            if (deleted) {
                Toast.makeText(DetailActivity.this, "Advert removed!", Toast.LENGTH_SHORT).show();
                wasDeleted = true;
                setResult(RESULT_OK);
                finish();
            } else {
                Toast.makeText(DetailActivity.this, "Failed to delete advert", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        if (wasDeleted) {
            setResult(RESULT_OK);
        }
        finish();
        return true;
    }

    private SpannableString makeBoldLabel(String label, String value) {
        SpannableString spannable = new SpannableString(label + value);
        spannable.setSpan(new StyleSpan(Typeface.BOLD), 0, label.length(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        return spannable;
    }
}
