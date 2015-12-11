package com.syfblp.sas.blpappv2.directory;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.syfblp.sas.blpappv2.R;

import java.util.ArrayList;

import de.hdodenhof.circleimageview.CircleImageView;


public class Profile extends AppCompatActivity {

    ArrayList<Person> tobedispayedList = new ArrayList<>();
    ArrayList<Person> input = new ArrayList<>();
    Person person;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);



        Intent incomingIntent = this.getIntent();
        person = (Person) incomingIntent.getSerializableExtra("snails");
        input = (ArrayList<Person>) incomingIntent.getSerializableExtra("json");

        getProfImg();

        TextView firstLastNameTxt = (TextView) findViewById(R.id.txtFirstLastName);
        firstLastNameTxt.setText(person.getFirstName() + " " + person.getLastName());

        TextView FunctionLocationTxt = (TextView) findViewById(R.id.txtFunctionLocation);
        FunctionLocationTxt.setText(person.getFunction() + "  |  " + person.getLocation());

        TextView roleTxt = (TextView) findViewById(R.id.txtRole);
        roleTxt.setText(person.getRole());

        TextView alTxt = (TextView) findViewById(R.id.txtAL);
        alTxt.setText(person.getAl());

        TextView phoneTxt = (TextView) findViewById(R.id.txtPhone);
        phoneTxt.setText(person.getPhone());

        TextView emailTxt = (TextView) findViewById(R.id.txtEmail);
        emailTxt.setText(person.getEmail());

        TextView uniTxt = (TextView) findViewById(R.id.txtUniv);
        uniTxt.setText(person.getUniversity());

        View buttonView = findViewById(R.id.btnCall);
        buttonView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + person.getPhone()));
                startActivity(intent);
            }
        });
    }

    private Person getPeople() {
        return person;
    }

    private void getProfImg() {

        if(null == person) {
            return;
        }

        CircleImageView profImage = (CircleImageView) findViewById(R.id.profile_image);
        String lastNm = person.getLastName();

        int id = 0;
        id = getResources().getIdentifier(lastNm.toLowerCase(), "drawable", Profile.this.getPackageName());

       /* if(id == 0) {
            profImage.setVisibility(View.GONE);
            return;
        } */

        try {
            profImage.setImageDrawable(getResources().getDrawable(id));
        } catch(Exception ex) {
            Log.e("", "Resource not found!");
        }
    }
}