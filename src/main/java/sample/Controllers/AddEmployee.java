package sample.Controllers;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.fxml.FXML;
import javafx.stage.Stage;
import sample.Classes.Employe;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;


public class AddEmployee {
    @FXML
    private JFXButton ajoutEmployee;
    @FXML
    private JFXTextField serviceE;
    @FXML
    private JFXTextField nomE;
    @FXML
    private JFXTextField prenomE;
    @FXML
    private JFXTextField posteE;
    @FXML
    private JFXTextField StructureE;
    @FXML
    private JFXTextField regionE;
    @FXML
    private JFXTextField dateEntreE;
    @FXML
    private JFXTextField matriculeE;



    static String rndmStr() {

        int leftLimit = 97; // letter 'a'
        int rightLimit = 122; // letter 'z'
        int targetStringLength = 10;
        Random random = new Random();
        StringBuilder buffer = new StringBuilder(targetStringLength);
        for (int i = 0; i < targetStringLength; i++) {
            int randomLimitedInt = leftLimit + (int)
                    (random.nextFloat() * (rightLimit - leftLimit + 1));
            buffer.append((char) randomLimitedInt);
        }
        String generatedString = buffer.toString();

        System.out.println(generatedString);
        return generatedString;
    }

    public void addEmpl() throws IOException {
        Stage stage = (Stage) ajoutEmployee.getScene().getWindow();

        String tmp="";

        String region = regionE.getText();
        String structure = StructureE.getText();
        String service = serviceE.getText();
        String poste = posteE.getText();
        String nom = nomE.getText();
        String prenom = prenomE.getText();
        String mat = matriculeE.getText();
        String date = dateEntreE.getText();
        String sql;

        ArrayList<String> el = new ArrayList<>(Arrays.asList(region,structure,service,poste,mat,nom,prenom,date));
        ArrayList<String> elQuotized = new ArrayList<>();
        el.forEach((e)->{
            elQuotized.add("\'"+e+"\'");
        });
        ArrayList<String> tabes = new ArrayList<>(Arrays.asList("region","structure","Service","PositionDeTravail","Employe"));

        System.out.println(region);
        System.out.println(rndmStr());

        try {
            Connection conn = null;
            Statement stmt = null;
            System.out.println("Connecting to database...");
            conn = DriverManager.getConnection(DB_CNX.getDbUrl(), DB_CNX.getUSER(), DB_CNX.getPASS());
            stmt = conn.createStatement();
            String ids;
            String result;
            final String end = ");";
            for (int i = 0; i < tabes.size(); i++) {
                ids = "\'"+ rndmStr() +"\'";

                sql= "insert into " + tabes.get(i) + " values (";
                switch(i) {
                    case 0:
                        result = String.join(",", Arrays.asList(ids,elQuotized.get(i)));
                        sql+= result + end;
                        break;
                    case 1:
                        //insert into structure values('STR001','structure1', 'R001');
                        result = String.join(",", Arrays.asList(ids,elQuotized.get(1),tmp));
                        sql+= result + end;
                        break;
                    case 2:
                        //insert into Service values('SER01','Finance', 'STR001');
                        result = String.join(",", Arrays.asList(ids,elQuotized.get(2),tmp));
                        sql+= result + end;
                        break;
                    case 3:
                        //insert into PositionDeTravail values('P001','Directeur', 'SER01');
                        result = String.join(",", Arrays.asList(ids,elQuotized.get(3),tmp));
                        sql+= result + end;
                        break;
                    case 4:
                        result = String.join(",", Arrays.asList(elQuotized.get(4),elQuotized.get(5),elQuotized.get(6),elQuotized.get(7),tmp));
                        HomeController.employes.add(new Employe(el.get(4),el.get(5),el.get(6),el.get(7),el.get(3), el.get(2),el.get(1),el.get(0)));
                        sql+= result + end;
                        break;

                }
                System.out.println(sql);
                tmp =ids;
                stmt.executeUpdate(sql);
            }


            stmt.close();
            conn.close();


        }  catch (SQLException e) {
            e.printStackTrace();
        }
        stage.close();
    }
}
