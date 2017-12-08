package com.example.mj_uc.excursapp.vista.Help;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.TextView;

import com.example.mj_uc.excursapp.R;

public class HelpCreate extends AppCompatActivity {

    private TextView tvc5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.help_create);

        tvc5 = (TextView)findViewById(R.id.tvc5);
        tvc5.setGravity(Gravity.CENTER);
    }
}
