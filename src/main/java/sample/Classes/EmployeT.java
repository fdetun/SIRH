package sample.Classes;

import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created bu foued lamine 17/06/2022.
 */
public class EmployeT extends RecursiveTreeObject<EmployeT> {
    public StringProperty Matricule;
     public StringProperty Nom;
     public StringProperty Prenom;

    public EmployeT(String matricule, String nom, String prenom) {
       this.Matricule= new SimpleStringProperty(matricule);
       this.Nom= new SimpleStringProperty(nom);
       this.Prenom= new SimpleStringProperty(prenom);

    }


}
