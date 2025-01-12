package com.example.gsb_medicine;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.List;

public class MainActivity extends AppCompatActivity { //le terme extends permet de faire heriter mainactivty de AppCompaActivity
    //les attributs sont déclarés ici en privé
    private static final String PREF_NAME= "UserPref";
    private static final String KEY_USER_STATUS= "UserStatus";
    private EditText EditTextDenomination, EditTextForme_pharmaceutique,EditTextDenominationSubstances,EditTextTitulaires;
    private Button ButtonDeconnexion, ButtonRechercher, ButtonQuitter;
    private Spinner SpinnerVoieAdmin;
    private ListView Listview;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        //Initialise des composants UI (interface utilisateur )
        EditTextDenomination = findViewById(R.id.Denomination_du_medicament);
        EditTextForme_pharmaceutique = findViewById(R.id.forme_pharmaceutique);
        EditTextTitulaires = findViewById(R.id.titulaires);
        EditTextDenominationSubstances = findViewById(R.id.Denomination_substances);
        ButtonDeconnexion = findViewById(R.id.btn_Deconnexion);
        ButtonRechercher = findViewById(R.id.btn_Rechercher);
        ButtonQuitter = findViewById(R.id.btn_quitter);
        SpinnerVoieAdmin = findViewById(R.id.planets_spinner);
        Listview = findViewById(R.id.select_dialog_listview);

        // Initialize the database helper
        dbHelper = new DatabaseHelper(this);

// Set up the spinner with Voies_dadministration data
        //spinner : liste deroulante
        //appel de la fonction permettant de remplir les infos dans la liste deroulante avec les donne de voies d'administration
        setupVoiesAdminSpinner();
        cacherClavier();

// Set up the click listener for the search button
        // Parametre l'ecouteur du clik qd on va rechercher
        ButtonRechercher.setOnClickListener(new View.OnClickListener()  { // fonction qui va s'executer sur l'attribut bouton rechercher
            @Override
            public void onClick(View view) {
                // Perform the search and update the ListView
                performSearch();
                cacherClavier();
            }
        });
        Listview.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                // Get the selected item
                Medicament selectedMedicament = (Medicament) adapterView.getItemAtPosition(position);
                // Show composition of the selected medicament
                //afficherCompositionMedicament(selectedMedicament);
            }
        });
    }
    // Fermeture constructeur on declare les methodes

    private void setupVoiesAdminSpinner() {
        // Fetch distinct Voies_dadministration data from the database and populate the spinner
        List<String> voiesAdminList = dbHelper.getDistinctVoiesAdmin();
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, voiesAdminList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinnerVoieAdmin.setAdapter(spinnerAdapter);
    }
    private void cacherClavier() {
        // Obtenez le gestionnaire de fenetre
        InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

        // Obtenez la vue actuellement focalisÃ©e, qui devrait Ãªtre la vue avec le clavier
        View vueCourante = getCurrentFocus();

        // VÃ©rifiez si la vue est non nulle pour Ã©viter les erreurs
        if (vueCourante != null) {
            // Masquez le clavier
            imm.hideSoftInputFromWindow(vueCourante.getWindowToken(), 0);
        }
    }
    private boolean isUserAuthenticated(){
        SharedPreferences preferences = getSharedPreferences(PREF_NAME, MODE_PRIVATE);
        // SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(this);
        String userStatus = preferences.getString(KEY_USER_STATUS, "");

        // VÃ©rifiez si la chaÃ®ne d'Ã©tat de l'utilisateur est "authentification=OK"
        return "authentification=OK".equals(userStatus);

    }
  private void performSearch (){
     String denomination = EditTextDenomination.getText().toString().trim();
     String FormePharmaceutique = EditTextForme_pharmaceutique.getText().toString().trim();
     String Titulaires = EditTextTitulaires.getText().toString().trim();
     String DenominationSubstance = EditTextDenominationSubstances.getText().toString().trim();
     String VoieAdmin = SpinnerVoieAdmin.getSelectedItem().toString();
     // Get recupère, toString Transforme en chaine de caracteres, trim supprime les espaces

      List <Medicament> searchResults = dbHelper.searchMedicament(denomination,FormePharmaceutique,Titulaires,DenominationSubstance,VoieAdmin);
      MedicamentAdapter adapter = new MedicamentAdapter(this, searchResults);
      Listview.setAdapter(adapter);
      //Medicadapter : classe

    }
}