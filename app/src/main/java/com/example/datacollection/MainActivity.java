package com.example.datacollection;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    MyDbHandler db;
    EditText Name;
    EditText Age;
    EditText Phone;
    EditText BaseCity;
    Spinner country_obj;
    Spinner Qualification;

    Spinner CurrLoc;
    private RadioGroup radioSexGroup;
    private RadioButton radioSexButton;
    private RadioGroup radioWhandednessGroup;
    private RadioButton radioWhandednessButton;
    private RadioGroup radioDhandednessGroup;
    private RadioButton radioDhandednessButton;
    int selectedId;
    long userid;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //int width  = Resources.getSystem().getDisplayMetrics().widthPixels;
        //int height = Resources.getSystem().getDisplayMetrics().heightPixels;

        Locale[] locale = Locale.getAvailableLocales();
        ArrayList<String> countries = new ArrayList<String>();
        String country;
        for( Locale loc : locale ){
            country = loc.getDisplayCountry();
            if( country.length() > 0 && !countries.contains(country) ){
                countries.add( country );
            }
        }
        Collections.sort(countries, String.CASE_INSENSITIVE_ORDER);

         country_obj = (Spinner)findViewById(R.id.country_list_spinner_ID);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, countries);
        country_obj.setAdapter(adapter);
//      country_obj.setSelection(adapter.getPosition("Pakistan"));



        db = new MyDbHandler(this, "HandwritingData.db", null, 1);

        Name = (EditText)findViewById(R.id.Nametxt);
        Age = (EditText)findViewById(R.id.Agetxt);
        Phone = (EditText)findViewById(R.id.Phonetxt);
      //  BaseCity = (EditText)findViewById(R.id.Citytxt);
        CurrLoc = (Spinner)findViewById(R.id.CurrLoc_spinner);
        radioSexGroup = (RadioGroup) findViewById(R.id.radioSex);
        radioWhandednessGroup = (RadioGroup)findViewById(R.id.Whandedness);
        radioDhandednessGroup = (RadioGroup)findViewById(R.id.Dhandedness);
        Qualification = (Spinner)findViewById(R.id.Qualificationtxt);


        Bundle bundle = getIntent().getExtras();
        //stuff = bundle.getString("stuff");


        List<String> objects = new ArrayList<>();
        objects.add(0, "--Qualification level--");
        objects.add("Under Matric");
        objects.add("Matric");
        objects.add("Intermediate");
        objects.add("Bachelors");
        objects.add("Masters");
        objects.add("Doctoral");

        ArrayAdapter<String> adapter2 = new ArrayAdapter<String>(this,
                R.layout.spinner_item, objects);
        adapter2.setDropDownViewResource(R.layout.spinner_item);

        Qualification.setAdapter(adapter2);

        List<String> objects2 = new ArrayList<>();

        objects2.add(0, "--Current Location--");
        objects2.add("Islamabad");
        objects2.add("Lahore");
        objects2.add("Karachi");
        objects2.add("Peshawar");
        objects2.add("Quetta");
        objects2.add("Multan");
        objects2.add("Skardu");
        objects2.add("Gilgit");
        objects2.add("Muzaffarabad");
        ArrayAdapter<String> adapter3 = new ArrayAdapter<String>(this,
                R.layout.spinner_item, objects2);
        adapter3.setDropDownViewResource(R.layout.spinner_item);
     // Qualification.setAdapter(adapter);
       CurrLoc.setAdapter(adapter3);
    }

    public void Next(View view)
    {
        String text = Qualification.getSelectedItem().toString();
        String cLoc=CurrLoc.getSelectedItem().toString();
        String country =country_obj.getSelectedItem().toString();
        if (Name.getText().toString().isEmpty())
        {
            Intent intent = new Intent(MainActivity.this, BaselineWritingActivity.class);
            Bundle bundle = new Bundle();
            bundle.putString("stuff", Long.toString(0));
            intent.putExtras(bundle);
            startActivity(intent);
        }
        if (Age.getText().toString().isEmpty() ||country.isEmpty() ||/*BaseCity.getText().toString().isEmpty() ||*/ Phone.getText().toString().isEmpty() ||
        text.isEmpty() || cLoc.isEmpty())
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Age & Phone & Country of Origin # are required fields!", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            toast.show();
        }

        else if (text == "--Qualification level--")
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Please select your Qualification level!", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            toast.show();
        }

        else if (cLoc == "--Current Location--")
        {
            Toast toast = Toast.makeText(getApplicationContext(), "Please select your Qualification level!", Toast.LENGTH_SHORT);
            ViewGroup group = (ViewGroup) toast.getView();
            TextView messageTextView = (TextView) group.getChildAt(0);
            messageTextView.setTextSize(20);
            toast.show();
        }
        else {
            //save data in database
            selectedId = radioSexGroup.getCheckedRadioButtonId();
            radioSexButton = (RadioButton) findViewById(selectedId);

            selectedId = radioWhandednessGroup.getCheckedRadioButtonId();
            radioWhandednessButton = (RadioButton) findViewById(selectedId);

            selectedId = radioDhandednessGroup.getCheckedRadioButtonId();
            radioDhandednessButton = (RadioButton) findViewById(selectedId);


            userid = db.addUserData(Name.getText().toString(), Age.getText().toString(), radioSexButton.getText().toString(),
                        country, Phone.getText().toString(), radioWhandednessButton.getText().toString(),
                        radioDhandednessButton.getText().toString(), Qualification.getSelectedItem().toString(), CurrLoc.getSelectedItem().toString());
           if(userid==-1)
           {
                Toast toast = Toast.makeText(getApplicationContext(), "User not created"+userid, Toast.LENGTH_SHORT);
                ViewGroup group = (ViewGroup) toast.getView();
                TextView messageTextView = (TextView) group.getChildAt(0);
                messageTextView.setTextSize(20);
                toast.show();
            }
else {
               //go to next activity
               Intent intent = new Intent(MainActivity.this, BaselineWritingActivity.class);
               Bundle bundle = new Bundle();
               bundle.putString("stuff", Long.toString(userid));
               intent.putExtras(bundle);
               startActivity(intent);
           }
        }
    }

}
