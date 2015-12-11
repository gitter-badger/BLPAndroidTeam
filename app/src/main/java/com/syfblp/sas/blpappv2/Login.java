package com.syfblp.sas.blpappv2;

/**
 * Created by 212464350 on 11/23/2015.
 */
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Login extends AppCompatActivity {
    EditText editText;
    Button button;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        editText=(EditText)findViewById(R.id.editText2);
        button=(Button) findViewById(R.id.button2);
        password="AndroidTeam";

        //Easter Egg
        Button button1;
        button1 = (Button) findViewById(R.id.button45);
        button1.setVisibility(View.VISIBLE);
        button1.setBackgroundColor(Color.TRANSPARENT);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "Crispy!", Toast.LENGTH_SHORT).show();
            }
        });

    }

    public void Success(View view) {
        String entered =editText.getText().toString();
        if (entered.equals(password)){
            Intent intent= new Intent(this,MainActivity.class);
            startActivity(intent);
        }
        else{
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("Please enter the correct password");

            alertDialogBuilder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface arg0, int arg1) {

                }
            });

            alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
            editText.setText("");
        }
    }
}