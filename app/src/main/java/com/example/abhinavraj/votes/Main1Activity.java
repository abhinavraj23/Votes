package com.example.abhinavraj.votes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main1Activity extends AppCompatActivity {
    EditText ed;
    String s="RENDER";
    Button proceed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        proceed= (Button) findViewById(R.id.button);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     ed = (EditText) findViewById(R.id.editText3);
                    if (ed.getText().toString().equals(s)) {
                        Intent i = new Intent(Main1Activity.this, MainActivity.class);
                        startActivity(i);
                    }
                    else {
                        Toast.makeText(Main1Activity.this, "Incorrect password", Toast.LENGTH_LONG).show();
                    }
                 ed.getText().clear();
            }
        });

    }
}
