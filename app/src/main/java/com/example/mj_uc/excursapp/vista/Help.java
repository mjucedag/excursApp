package com.example.mj_uc.excursapp.vista;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;

public class Help extends AppCompatActivity {

    private TextView tvCreate;
    private TextView tvSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);

        tvCreate= (TextView) findViewById(R.id.tvCreate);
        tvSearch= (TextView) findViewById(R.id.tvSearch);

        tvCreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHelpCreate = new Intent(Help.this, HelpCreate.class);
                startActivity(goToHelpCreate);
            }
        });

        tvSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goToHelpSearch = new Intent(Help.this, HelpSearch.class);
                startActivity(goToHelpSearch);
            }
        });

    }
}
