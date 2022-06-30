package sample.Classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by FouedLamine on 06/15/2022.
 */
public class Employe extends RecursiveTreeObject
        <Employe> {
    private StringProperty matricule;
    private StringProperty nom;
    private StringProperty Prenom;
    private StringProperty date;
    private StringProperty poste;
    private StringProperty Service;
    private StringProperty structure;
    private StringProperty region;


    public Employe(String matricule, String nom, String prenom, String date, String poste, String Service, String structure, String region) {
        this.matricule = new SimpleStringProperty(matricule);
        this.nom = new SimpleStringProperty(nom);
        Prenom = new SimpleStringProperty(prenom);
        this.date = new SimpleStringProperty(date);
        this.poste = new SimpleStringProperty(poste);
        this.Service = new SimpleStringProperty(Service);
        this.structure = new SimpleStringProperty(structure);
        this.region = new SimpleStringProperty(region);
    }

    public String getMatricule() {
        return matricule.get();
    }

    public StringProperty matriculeProperty() {
        return matricule;
    }

    public void setMatricule(String matricule) {
        this.matricule.set(matricule);
    }

    public String getNom() {
        return nom.get();
    }

    public StringProperty nomProperty() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom.set(nom);
    }

    public String getStructure() {
        return structure.get();
    }

    public StringProperty structureProperty() {
        return structure;
    }

    public String getPrenom() {
        return Prenom.get();
    }

    public StringProperty prenomProperty() {
        return Prenom;
    }

    public void setPrenom(String prenom) {
        this.Prenom.set(prenom);
    }

    public String getDate() {
        return date.get();
    }

    public StringProperty dateProperty() {
        return date;
    }

    public void setDate(String date) {
        this.date.set(date);
    }

    public String getPoste() {
        return poste.get();
    }

    public StringProperty posteProperty() {
        return poste;
    }

    public void setPoste(String poste) {
        this.poste.set(poste);
    }

    public String getService() {
        return Service.get();
    }

    public StringProperty serviceProperty() {
        return Service;
    }

    public void setService(String service) {
        this.Service.set(service);
    }

    public String getRegion() {
        return region.get();
    }

    public StringProperty regionProperty() {
        return region;
    }

    public void setRegion(String region) {
        this.region.set(region);
    }

}
