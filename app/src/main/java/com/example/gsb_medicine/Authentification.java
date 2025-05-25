package com.example.gsb_medicine;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.security.SecureRandom;

public class Authentification extends AppCompatActivity {
    private EditText editTextCv, cleSaisie;
    private Button btn_valider_code_visiteur, btn_validé_cle_secrète;
    LinearLayout layoutkey;
    String myRandomKey;
    private static final String SECURETOKEN ="BethSepher9";
    private static final String KEY_USER_STATUS = "userStatuts";
    private static final String PREF_NAME = "userPref";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_authentification);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        editTextCv = findViewById(R.id.edit_text_code_visiteur);
        cleSaisie = findViewById(R.id.edit_text_valide_cle_secrete);
        btn_valider_code_visiteur = findViewById(R.id.btn_valider_code_visiteur);
        layoutkey = findViewById(R.id.key);
        layoutkey.setVisibility(View.INVISIBLE);
        }

        //ici les methodes
    public void afficherLayout(View v){
        layoutkey.setVisibility(View.VISIBLE);
        myRandomKey = genererChaineAleatoire(5);
        String codeVisiteur = editTextCv.getText().toString();
        SendKeyTask sendKeyTask = new SendKeyTask(getApplicationContext());
        sendKeyTask.execute(codeVisiteur,myRandomKey,SECURETOKEN);

    }

    public void validerConnexion(View V){
        String keySaisie = cleSaisie.getText().toString();
        if (keySaisie.equals(myRandomKey)) {
            setUserStatus("authentification.OK");
            Intent intent = new Intent(Authentification.this, MainActivity.class); // à adapter
            startActivity(intent);
        } else {
            setUserStatus("authentification.KO");
        }
    }


    private String genererChaineAleatoire(int longueur) {
        String caracteresPermis = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        StringBuilder chaineAleatoire = new StringBuilder();
        SecureRandom random = new SecureRandom();

        for (int i = 0; i < longueur; i++) {
            int index = random.nextInt(caracteresPermis.length());
            char caractereAleatoire = caracteresPermis.charAt(index);
            chaineAleatoire.append(caractereAleatoire);
        }

        return chaineAleatoire.toString();
    }

    private void setUserStatus(String status) {
        SharedPreferences sharedPreferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_USER_STATUS, status);
        editor.apply();
    }


}