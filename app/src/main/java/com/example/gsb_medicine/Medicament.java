package com.example.gsb_medicine;

public class Medicament {
        private int codeCIS;
        private String denomination;
        private String FormePharmaceutique;
        private String VoieAdmin;
        private String Titulaires;
        private String statutAdmin;
        private int nbMolecules;

        // Constructeur

        // Getter et Setter pour codeCIS
        public int getCodeCIS() {
            return codeCIS;
        }

        public void setCodeCIS(int codeCIS) {
            this.codeCIS = codeCIS;
        }

        // Getter et Setter pour denomination
        public String getDenomination() {
            return denomination;
        }

        public void setDenomination(String denomination) {
            this.denomination = denomination;
        }

        // Getter et Setter pour formePharmaceutique
        public String getFormePharmaceutique() {
            return FormePharmaceutique;
        }

        public void setFormePharmaceutique(String formePharmaceutique) {
            this.FormePharmaceutique = FormePharmaceutique;
        }

        // Getter et Setter pour voiesAdmin
        public String getVoiesAdmin() {
            return VoieAdmin;
        }

        public void setVoiesAdmin(String voiesAdmin) {
            this.VoieAdmin = voiesAdmin;
        }

        // Getter et Setter pour titulaires
        public String getTitulaires() {
            return Titulaires;
        }

        public void setTitulaires(String titulaires) {
            this.Titulaires = titulaires;
        }

        // Getter et Setter pour statut administratif
        public String getStatutAdministratif() {return statutAdmin;}

        public void setStatutAdministratif(String statutAdmin) {
            this.statutAdmin = statutAdmin;
        }

        public String getnbMolecules() {return String.valueOf(nbMolecules);}

        public void setNbMolecule(int nbMolecules) {
            this.nbMolecules = nbMolecules;
        }
    }



