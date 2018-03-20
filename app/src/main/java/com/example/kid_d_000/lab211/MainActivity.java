package com.example.kid_d_000.lab211;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Spinner zodiaco;
    String items[];
    EditText email;
    //RadioGroup radioGroup;
    RadioButton male,female;
    CheckBox h1,h2,h3;
    Button saveme,getme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        zodiaco=findViewById(R.id.zodiac);
        email=findViewById(R.id.email);
        male=findViewById(R.id.male);
        female=findViewById(R.id.female);
        //radioGroup=findViewById(R.id.radioGroup);
        h1=findViewById(R.id.hobbie1);
        h2=findViewById(R.id.hobbie2);
        h3=findViewById(R.id.hobbie3);
        saveme=findViewById(R.id.saveme);
        getme=findViewById(R.id.getme);
        items=getResources().getStringArray(R.array.signos_zodiaco);
        ArrayAdapter<String> adapter=new ArrayAdapter<String>(getBaseContext(),android.R.layout.simple_spinner_item,items);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        zodiaco.setAdapter(adapter);

        saveme.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                guardar();
                Toast.makeText(MainActivity.this,"Guardado",Toast.LENGTH_SHORT).show();
            }
        });

        getme.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cargar();
            }
        });
    }

    private void cargar() {
        SharedPreferences preferences=getSharedPreferences("Valores", Context.MODE_PRIVATE);

        String email=preferences.getString("email","Sin información");
        boolean male=preferences.getBoolean("male",false);
        boolean female=preferences.getBoolean("female",false);
        //int radioGroup=preferences.getInt("radioGroup",0);
        boolean  h1=preferences.getBoolean("h1",false);
        boolean  h2=preferences.getBoolean("h2",false);
        boolean  h3=preferences.getBoolean("h3",false);
        int zodiaco=preferences.getInt("zodiaco",0);

        this.email.setText(email);
        this.male.setChecked(male);
        this.female.setChecked(female);
        //this.radioGroup.setId(radioGroup);
        this.h1.setChecked(h1);
        this.h2.setChecked(h2);
        this.h3.setChecked(h3);
        this.zodiaco.setSelection(zodiaco);
    }

    private void guardar() {
        SharedPreferences preferences=getSharedPreferences("Valores", Context.MODE_PRIVATE);

        String email=this.email.getText().toString();
        boolean male=this.male.isChecked();
        boolean female=this.female.isChecked();
        //int radioGroup=this.radioGroup.getCheckedRadioButtonId();
        boolean h1=this.h1.isChecked();
        boolean h2=this.h2.isChecked();
        boolean h3=this.h3.isChecked();
        int zodiaco=this.zodiaco.getSelectedItemPosition();

        SharedPreferences.Editor editor=preferences.edit();

        editor.putString("email",email);
        editor.putBoolean("male",male);
        editor.putBoolean("female",female);
        //editor.putInt("radioGroup",radioGroup);
        editor.putBoolean("h1",h1);
        editor.putBoolean("h2",h2);
        editor.putBoolean("h3",h3);
        editor.putInt("zodiaco",zodiaco);

        editor.commit();
    }
}
