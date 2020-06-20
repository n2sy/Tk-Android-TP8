package com.pref.nidhal.tpsharedpref;

import android.content.SharedPreferences;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private int compteur = 0;
    private int couleur;

    private TextView afficheur;

    private SharedPreferences mPreferences;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        afficheur = (TextView) findViewById(R.id.count_textview);
        couleur = ContextCompat.getColor(this, R.color.default_background);
        mPreferences = getSharedPreferences("prefApplication.dat", MODE_PRIVATE);

        compteur = mPreferences.getInt("count", 0);
        afficheur.setText(String.format("%s", compteur));
        couleur = mPreferences.getInt("color", couleur);
        afficheur.setBackgroundColor(couleur);
    }


    @Override
    protected void onPause() {
        super.onPause();

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.putInt("count", compteur);
        preferencesEditor.putInt("color", couleur);
        preferencesEditor.apply();
    }


    public void changeBackground(View v) {
        int color = ((ColorDrawable) v.getBackground()).getColor();
        afficheur.setBackgroundColor(color);
        couleur = color;
    }

    public void countUp(View v) {
        compteur++;
        afficheur.setText(String.format("%s", compteur));
    }


    public void reset(View v) {
        compteur = 0;
        afficheur.setText(String.format("%s", compteur));

        couleur = ContextCompat.getColor(this, R.color.default_background);
        afficheur.setBackgroundColor(couleur);

        SharedPreferences.Editor preferencesEditor = mPreferences.edit();
        preferencesEditor.clear();
        preferencesEditor.apply();

    }
}