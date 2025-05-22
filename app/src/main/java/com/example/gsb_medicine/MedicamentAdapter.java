package com.example.gsb_medicine;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.gsb_medicine.Medicament;
import com.example.gsb_medicine.R;

import java.util.List;

public class MedicamentAdapter extends ArrayAdapter<Medicament> { //va etre en lien avec medoc et va permettre
    public MedicamentAdapter(Context context, List<Medicament> medicaments) {
        super(context, 0, medicaments);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position
        Medicament medicament = getItem(position);

        // Check if an existing view is being reused, otherwise inflate the view
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_medicament, parent, false);
        }
// chaque element est lié par des variables ; à l'interieur des textview ya une valeur
        TextView tvCodeCIS = convertView.findViewById(R.id.text_view_Code_CIS);
        TextView Denomination = convertView.findViewById(R.id.text_view_Denomination_du_medicament);
        TextView Forme_pharmaceutique = convertView.findViewById(R.id.text_view_Forme_pharmaceutique);
        TextView Voies_administration = convertView.findViewById(R.id.text_view_Voies_administration);
        TextView Titulaires = convertView.findViewById(R.id.text_view_Titulaires);
        TextView Statut_administratif = convertView.findViewById(R.id.text_view_Statut_administratif);
        TextView Nombre_Mollecules = convertView.findViewById(R.id.text_view_Nombre_mollecules);

        tvCodeCIS.setText(medicament.getCodeCIS());
        Denomination.setText(medicament.getDenomination());
        Forme_pharmaceutique.setText(medicament.getFormePharmaceutique());
        Voies_administration.setText(medicament.getVoiesAdmin());
        Titulaires.setText(medicament.getTitulaires());
        Statut_administratif.setText(medicament.getStatutAdministratif());
        Nombre_Mollecules.setText(medicament.getnbMolecules());

        return convertView;
        //Affecte les resultatas à la vue
    }
    }

