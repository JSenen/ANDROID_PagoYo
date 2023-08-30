package com.juansenen.pagoyo.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.juansenen.pagoyo.R;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);

        TextView textView = findViewById(R.id.txtview_info);
        TextView textView1 = findViewById(R.id.txtview_contributions);

        textView.setText(R.string.infotxt);
        textView1.setText(R.string.contributions);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.actionbar_menu_back,menu);
        return true;

    }
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
       if (item.getItemId() == R.id.menu_item_back) {
            Intent intent = new Intent(this, MainActivity.class);
            finish();
            startActivity(intent);

        }

        return false;
    }

}