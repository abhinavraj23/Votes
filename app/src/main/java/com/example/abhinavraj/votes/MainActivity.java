package com.example.abhinavraj.votes;

import android.content.SharedPreferences;
import android.database.Cursor;
import android.support.v4.content.SharedPreferencesCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Button comp1,comp2,comp3,comp4,result,delete;
    int a1=-1,a2=-1,a3=-1,a4=-1;
    boolean r1,r2,r3,r4;
    DatabaseHelper myDb;
    public static String globalPreferenceName="Votes";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);
        comp1 = (Button) findViewById(R.id.button1);
        comp2 = (Button) findViewById(R.id.button2);
        comp3 = (Button) findViewById(R.id.button3);
        comp4 = (Button) findViewById(R.id.button4);
        result= (Button) findViewById(R.id.button5);
        delete= (Button) findViewById(R.id.button6);
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
                Toast.makeText(MainActivity.this, "Error in the app", Toast.LENGTH_LONG).show();
            }

        }
            comp1.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    a1++;
                    SharedPreferences settings1 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                      saveMyData("votes1",a1,settings1);
                      updateData("1",a1);

                    }


            });
            comp2.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    a2++;
                    SharedPreferences settings2 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                     saveMyData("votes2",a2,settings2);
                     updateData("2",a2);

                    }


            });
            comp3.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    a3++;
                    SharedPreferences settings3 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes3",a3,settings3);
                    updateData("3",a3);

                    }

            });
            comp4.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    a4++;
                    SharedPreferences settings4 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes4",a4,settings4);
                    updateData("4",a4);

                    }


            });
            result.setOnClickListener(
                    new View.OnClickListener() {

                        @Override
                        public void onClick(View v) {
                            Cursor res = myDb.getAllData();
                            if(res.getCount() == 0) {
                                // show message
                                showMessage("Error","Nothing found");
                                return;
                            }

                            StringBuffer buffer = new StringBuffer();
                            while (res.moveToNext()) {
                                buffer.append("Id :"+ res.getString(0)+"\n");
                                buffer.append("Name :"+ res.getString(1)+"\n");
                                buffer.append("Votes :"+ res.getString(2)+"\n\n");
                            }

                            // Show all data
                            showMessage("Data",buffer.toString());
                        }
                    }
            );
            delete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                     a1=0;
                    SharedPreferences settings1 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes1",a1,settings1);
                    VupdateData("1",a1);
                    a2=0;
                    SharedPreferences settings2 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes2",a2,settings2);
                    VupdateData("2",a2);
                    a3=0;
                    SharedPreferences settings3 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes3",a3,settings3);
                    VupdateData("3",a3);
                    a4=0;
                    SharedPreferences settings4 = getSharedPreferences(globalPreferenceName,MODE_PRIVATE);
                    saveMyData("votes4",a4,settings4);
                    VupdateData("4",a4);
                    if(a1==0 && a2==0 && a3==0 && a4==0){
                        Toast.makeText(MainActivity.this, "Votes cleared", Toast.LENGTH_LONG).show();
                    }
                    else
                        Toast.makeText(MainActivity.this, "Unsuccessful", Toast.LENGTH_LONG).show();
                }
            });


    }

    public void showMessage(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
    public void updateData(String s,int votes){

        boolean isUpdate = myDb.updateData(s,String.valueOf(votes));
        if (isUpdate == true)
            Toast.makeText(MainActivity.this, "Voting successful", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(MainActivity.this, "Voting unsuccessful", Toast.LENGTH_LONG).show();

    }
    public void VupdateData(String s,int votes){
        myDb.updateData(s,String.valueOf(votes));
    }
    public void saveMyData(String s,int n,SharedPreferences k){
        SharedPreferences.Editor editor = k.edit();
        editor.putInt(s,n);
        editor.commit();
    }


}
