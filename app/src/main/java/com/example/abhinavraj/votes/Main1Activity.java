package com.example.abhinavraj.votes;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Main1Activity extends AppCompatActivity {
    EditText ed,txt;
    String s="RENDER";
    String str = "";
    Button proceed;
    TextView b1,b2;
    private int counter = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main1);
        b1 = (TextView) findViewById(R.id.toVote);
        b2 = (TextView) findViewById(R.id.HostVote);
        b1.setText(Html.fromHtml("<u>VOTER LOGIN</u>"));
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter%2 == 0)
                counter++;
                if(counter%2 != 0)
                {
                    b2.setText(Html.fromHtml("<u>HOST LOGIN</u>"));
                    b1.setText("VOTER LOGIN");
                }
            }
        });

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(counter%2 != 0)
                    counter--;
                if(counter%2 == 0)
                {
                    b1.setText(Html.fromHtml("<u>VOTER LOGIN</u>"));
                    b2.setText("HOST LOGIN");
                }
            }
        });

        proceed= (Button) findViewById(R.id.button);
        proceed.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                     ed = (EditText) findViewById(R.id.editText3);
                     txt = (EditText) findViewById(R.id.editText6);
                     if(txt.getText().toString().equals(str)){
                         Toast.makeText(Main1Activity.this, "Enter Username", Toast.LENGTH_SHORT).show();
                     }
                     else{

                         if(ed.getText().toString().equals(s)){
                             if(counter%2 == 0)
                             {
                                 Intent i = new Intent(Main1Activity.this, UserActivity.class);
                                 startActivity(i);
                             }
                             else
                             {
                                 Intent i = new Intent(Main1Activity.this, MainActivity.class);
                                 startActivity(i);
                             }
                         }
                         else
                         {
                             Toast.makeText(Main1Activity.this, "Incorrect password", Toast.LENGTH_SHORT).show();
                         }
                         ed.getText().clear();
                     }
            }
        });

    }
}
