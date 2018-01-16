package com.example.abhinavraj.votes;

import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class UserActivity extends AppCompatActivity {

    Button comp1,comp2,comp3,comp4;
    int a1=-1,a2=-1,a3=-1,a4=-1;
    boolean r1,r2,r3,r4;
    DatabaseHelper myDb;
    public static String globalPreferenceName="Votes";
    private int counter = 0;
    TextView txt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);
        txt = (TextView) findViewById(R.id.greeting);
        txt.setText("");
        myDb = new DatabaseHelper(this);
        comp1 = (Button) findViewById(R.id.button1);
        comp2 = (Button) findViewById(R.id.button2);
        comp3 = (Button) findViewById(R.id.button3);
        comp4 = (Button) findViewById(R.id.button4);
        SharedPreferences settings1 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
        SharedPreferences settings2 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
        SharedPreferences settings3 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
        SharedPreferences settings4 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
        a1= settings1.getInt("votes1",a1);
        a2= settings2.getInt("votes2",a2);
        a3= settings3.getInt("votes3",a3);
        a4= settings4.getInt("votes4",a4);

        if(a1==-1 && a2==-1 && a3==-1 && a4==-1){
            a1++;
            saveMyData("votes1",a1,settings1);
            a2++;
            saveMyData("votes2",a2,settings2);
            a3++;
            saveMyData("votes3",a3,settings3);
            a4++;
            saveMyData("votes4",a4,settings4);

            r1 = myDb.insertData("Captain America", Integer.toString(a1));
            r2 = myDb.insertData("Iron Man",String.valueOf(a2));
            r3 = myDb.insertData("Thor",String.valueOf(a3));
            r4 = myDb.insertData("Hulk",String.valueOf(a4));
            if(!r1 || !r2 || !r3 || !r4){
                Toast.makeText(UserActivity.this, "Error in the app", Toast.LENGTH_LONG).show();
            }

        }



            comp1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(counter == 0) {
                        a1++;
                        SharedPreferences settings1 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                        saveMyData("votes1",a1,settings1);
                        updateData("1",a1);
                        txt.setText("Thank You for support");
                        counter++;
                    }
                    else
                    {
                        Toast.makeText(UserActivity.this,"Only one vote per user",Toast.LENGTH_SHORT).show();
                    }
                }


            });
            comp2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(counter == 0)
                    {
                        a2++;
                        SharedPreferences settings2 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                        saveMyData("votes2",a2,settings2);
                        updateData("2",a2);
                        txt.setText("Thank You for support");
                        counter++;
                    }
                    else
                    {
                        Toast.makeText(UserActivity.this,"Only one vote per user",Toast.LENGTH_SHORT).show();
                    }
                }


            });
            comp3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(counter == 0)
                    {
                        a3++;
                        SharedPreferences settings3 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                        saveMyData("votes3",a3,settings3);
                        updateData("3",a3);
                        txt.setText("Thank You for support");
                        counter++;
                    }
                   else
                    {
                        Toast.makeText(UserActivity.this,"Only one vote per user",Toast.LENGTH_SHORT).show();
                    }
                }

            });
            comp4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    if(counter == 0)
                    {
                        a4++;
                        SharedPreferences settings4 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                        saveMyData("votes4",a4,settings4);
                        updateData("4",a4);
                        txt.setText("Thank You for support");
                        counter++;
                    }
                    else
                    {
                        Toast.makeText(UserActivity.this,"Only one vote per user",Toast.LENGTH_SHORT).show();
                    }

                }


            });


    }

    public void updateData(String s,int votes){

        boolean isUpdate = myDb.updateData(s,String.valueOf(votes));
        if (isUpdate == true)
            Toast.makeText(UserActivity.this, "Voting successful", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(UserActivity.this, "Voting unsuccessful", Toast.LENGTH_SHORT).show();

    }

    public void saveMyData(String s,int n,SharedPreferences k){
        SharedPreferences.Editor editor = k.edit();
        editor.putInt(s,n);
        editor.commit();
    }
}
