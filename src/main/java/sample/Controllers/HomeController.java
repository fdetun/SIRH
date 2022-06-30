package sample.Controllers;

import javafx.scene.layout.*;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import com.jfoenix.controls.*;
import com.jfoenix.controls.JFXTreeTableView;
import com.jfoenix.controls.datamodels.treetable.RecursiveTreeObject;
import com.jfoenix.transitions.hamburger.HamburgerBackArrowBasicTransition;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Bounds;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.Window;
import javafx.util.Callback;
import javafx.util.StringConverter;
import sample.Classes.Employe;


import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.ResourceBundle;
import java.util.Scanner;

import static javax.swing.UIManager.getInt;


/**
 * Created by Foued Lamine on 18/06/2022.
 */
public class HomeController implements Initializable {

    public static Stage HomeStage;
    @FXML
    private Label test;

    @FXML
    private JFXTextField searchFeild;

    @FXML
    private Label matriculeF;

    @FXML
    private Label creditTotalLabel;

    @FXML
    private Label nomF;

    @FXML
    private Label prenomF;

    @FXML
    private Label posteF;

    @FXML
    private Label serviceF;


    @FXML
    private Label regionF;

    @FXML
    private Label dateEntreeF;

    @FXML
    private Label structure;

    @FXML
    private JFXHamburger hamburger;

    @FXML
    private JFXDrawer drawer;

    public static ObservableList<Employe> employes = FXCollections.observableArrayList();

    @FXML
    private JFXTreeTableView<Employe> employesTable;

    ///////////////////////////////  TAB PANE ELEMENTS  ///////////////////////////////////////////////////

    @FXML
    private  Button plusButton;

    @FXML
    private JFXCheckBox supDayCheck;

    @FXML
    private DatePicker supDate;

    @FXML
    private JFXTextField hsup50;

    @FXML
    private JFXTextField hsup75;

    @FXML
    private JFXTextField hsup100;

    @FXML
    private JFXTextField hsuprepos;

    @FXML
    private JFXButton InsererButtonSup;

    @FXML
    private JFXButton etatButton;

    @FXML
    private VBox recuperationVBox;
    
    @FXML
    private JFXCheckBox recupDayCheck;
    @FXML
    private JFXDatePicker recupDate;

    @FXML
    private JFXTextField recDu;

    @FXML
    private JFXTextField recAu;

    @FXML
    private  JFXCheckBox recup816check;

    @FXML
    private JFXButton InsererButtonRec;

    @FXML
    private JFXButton etatButton1;

    @FXML
    private JFXCheckBox payMonthCheck;

    @FXML
    private JFXDatePicker payDate;

    @FXML
    private JFXTextField hpay50;

    @FXML
    private JFXTextField hpay75;

    @FXML
    private JFXTextField hpay100;

    @FXML
    private JFXButton InsererButtonPay;

    @FXML
    private JFXButton etatButton2;

    @FXML
    private AnchorPane dateRangeVbox;




    public static Employe employeCourant;
    public static Parent rt;

    private String                    pattern       = "yyyy-MM-dd";
    private DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(pattern);

    public ObservableList<LocalDate> selectedDates = FXCollections.observableArrayList();
    private    static boolean isAdmin = false;
    private static String userName = "Karim";
    private static String name = "Karim";
    private static String surname = "Karim";
    private static int idUser = 1 ;
    @FXML
    void drawerInOut(MouseEvent event) {
        System.out.println("-------------Hello From Drawer!!");
/*
        HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
        burgerTask2.setRate(-1);
        burgerTask2.setRate(burgerTask2.getRate() * -1);
        burgerTask2.play();
*/
    }
    public static void SetUser(String _name, boolean _isAdmin) throws SQLException, ClassNotFoundException {
        userName = _name;
        isAdmin = _isAdmin;

        DB_CNX.getConnection();
        String sql = "Select id,username  from user WHERE  user.username = '"+_name+"'";
        System.out.println(sql);
        ResultSet res = DB_CNX.statement.executeQuery(sql);
        while (res.next()){
            idUser= res.getInt("id");
            name= res.getString("username");
            surname= res.getString("username");
        }
        System.out.println(idUser+name+surname);
        DB_CNX.closeConnection();

    }

    public void constructTable(){


        //matricule,nom,prenom,date,postion,nom_service,nom_structure,nom_region

        JFXTreeTableColumn<Employe,String> matEmployeColumn = new JFXTreeTableColumn<>("Matricule");
        matEmployeColumn.setCellValueFactory(param -> param.getValue().getValue().matriculeProperty());
///////////////////////////////////////////////////////////////////////////////////////////////

        JFXTreeTableColumn<Employe,String> nomColumn = new JFXTreeTableColumn<>("Nom");
        nomColumn.setCellValueFactory(param -> param.getValue().getValue().nomProperty());


        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> prenomColumn = new JFXTreeTableColumn<>("Prénom");
        prenomColumn.setCellValueFactory(param -> param.getValue().getValue().prenomProperty());


        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> dateColumn = new JFXTreeTableColumn<>("Date d'entrée");
        dateColumn.setCellValueFactory(param -> param.getValue().getValue().dateProperty());


        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> posteColumn = new JFXTreeTableColumn<>("Poste");
        posteColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employe, String> param) {
                return param.getValue().getValue().posteProperty();
            }
        });
        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> serviceColumn = new JFXTreeTableColumn<>("Service");
        serviceColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employe, String> param) {
                return param.getValue().getValue().serviceProperty();
            }
        });
        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> structureColumn = new JFXTreeTableColumn<>("Structure");
        structureColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employe, String> param) {
                return param.getValue().getValue().structureProperty();
            }
        });
        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> regionColumn = new JFXTreeTableColumn<>("Region");
        regionColumn.setCellValueFactory(new Callback<TreeTableColumn.CellDataFeatures<Employe, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(TreeTableColumn.CellDataFeatures<Employe, String> param) {
                return param.getValue().getValue().regionProperty();
            }
        });
        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> directionColumn = new JFXTreeTableColumn<>("Direction");
        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////
        JFXTreeTableColumn<Employe,String> departementColumn = new JFXTreeTableColumn<>("Departement");

        ///////////////////////// NEW COLUMN FOR THE TABLE VIEW/////////////////////////////////////////


        final TreeItem<Employe> root = new RecursiveTreeItem<Employe>(employes,RecursiveTreeObject::getChildren);
        employesTable.setRoot(root);
        employesTable.setShowRoot(false);
        employesTable.getColumns().setAll(matEmployeColumn,nomColumn,prenomColumn,dateColumn,posteColumn,serviceColumn,structureColumn,regionColumn);

        employesTable.getSelectionModel().selectedItemProperty().addListener((Observable, oldValue, newValue) -> {

            if(newValue !=null){
                employeCourant = new Employe(newValue.getValue().getMatricule()
                        ,newValue.getValue().getNom()
                        ,newValue.getValue().getPrenom()
                        ,newValue.getValue().getDate()
                        ,newValue.getValue().getPoste()
                        ,newValue.getValue().getService()
                        ,newValue.getValue().getStructure()
                        ,newValue.getValue().getRegion()
                        );
                System.out.println(newValue.getValue().getNom());
                matriculeF.setText(newValue.getValue().getMatricule());
                nomF.setText(newValue.getValue().getNom());
                prenomF.setText(newValue.getValue().getPrenom());
                posteF.setText(newValue.getValue().getPoste());
                serviceF.setText(newValue.getValue().getService());
                structure.setText(newValue.getValue().getStructure().toUpperCase());
                regionF.setText(newValue.getValue().getRegion());
                dateEntreeF.setText(newValue.getValue().getDate());

/*
                typeEmploiF.setText(newValue.getValue().getService());
                dateEntreeF.setText(newValue.getValue().getDate());
*/
            }
        });

    }

    public  void refreshCredits(){

    }
    public void getAllEmployes() throws SQLException, ClassNotFoundException {

        DB_CNX.getConnection();
        String sql = "\n" +
                "select matricule,nom,prenom,date,postion,nom_service,nom_structure,nom_region from employe join positiondetravail p on p.id_PositionDeTravail = employe.id_PositionDeTravail join service s on s.id_service = p.id_service join structure s2 on s2.id_structure = s.id_structure join region r on r.id_region = s2.id_region ";
        ResultSet res=  DB_CNX.statement.executeQuery(sql);
        while(res.next()){
            employes.add(new Employe(res.getString("matricule")
                    ,res.getString("nom"),res.getString("prenom")
                    ,res.getString("date"),res.getString("postion")
                    ,res.getString("nom_service"),res.getString("nom_structure")
                    , res.getString("nom_region")));
        }
        DB_CNX.closeConnection();

    }

    @FXML
    void search(KeyEvent event) {
        employesTable.setPredicate(elm -> {
            Boolean bl=elm.getValue().matriculeProperty().getValue().contains(searchFeild.getText())
                    || elm.getValue().nomProperty().getValue().toUpperCase().contains(searchFeild.getText().toUpperCase())
                    || elm.getValue().prenomProperty().getValue().toUpperCase().contains(searchFeild.getText().toUpperCase())
                    || elm.getValue().structureProperty().getValue().toUpperCase().contains(searchFeild.getText().toUpperCase())
                    || elm.getValue().posteProperty().getValue().toUpperCase().contains(searchFeild.getText().toUpperCase())
                    || elm.getValue().serviceProperty().getValue().toUpperCase().contains(searchFeild.getText().toUpperCase());

            return bl;
        });
    }

    @FXML
    void addEmployee(ActionEvent event) throws  IOException {
        Pane tilePane =        FXMLLoader.load(getClass().getResource("/Views/AddEmployee.fxml"));

        System.out.println("Hello From the dialogue");
        Scene scene = new Scene(tilePane, 600, 500);
        Stage s = new Stage();
        s.setResizable(false);
        s.setTitle("Ajout d'employee ");
        s.setScene(scene);
        s.show();

    }
    @FXML
    void genererEtatPay(ActionEvent event) {

        System.out.println("genarating paied state ! ... ");



    }

    @FXML
    void genererEtatRecu(ActionEvent event) throws SQLException, ClassNotFoundException {
        System.out.println("genarating recup state ! ... ");
        calculer();
    }

    @FXML
    void genererEtatSup(ActionEvent event) throws IOException {




    }

    @FXML
    void ajouterPay(ActionEvent event)  {
        refreshCredits();
         }

    @FXML
    void ajouterRec(ActionEvent event) {
    }

    @FXML
    void ajouterSup(ActionEvent event) {

        ArrayList<String> el = new ArrayList<>(Arrays.asList(employeCourant.getMatricule(),supDate.getValue().toString(),"dimanche"));
        ArrayList<String> elQuotized = new ArrayList<>();
        el.forEach((e)->{
            elQuotized.add("\'"+e+"\'");
        });
        final String end = ");";
        String result = String.join(",", Arrays.asList(elQuotized.get(0),elQuotized.get(1),elQuotized.get(2)));

        String sql;
        try {
            if (supDate.getValue() == null || supDayCheck.isSelected()) {
                sql = "INSERT INTO sirh_laposte.heursupp (employee, date,type)" +
                        " VALUES (" + employeCourant.getMatricule() +", '2000-01-01', " + "dimanche"  + ");";
            } else {
                sql ="INSERT INTO sirh_laposte.heursupp (employee, date,type)" +
                        " VALUES (" + result+end;


            }
        }catch (RuntimeException e ){
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Attention");
            alert.setHeaderText("Acun employé selectionné !");
            alert.setContentText("S'il vous plait vous devez selectionner l'employé d'abbord");
            alert.showAndWait();
            sql="";
        }

        System.out.println(sql);
        try {
            DB_CNX.getConnection();


            DB_CNX.statement.executeUpdate(sql);

            DB_CNX.closeConnection();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }catch (SQLException e) {
            e.printStackTrace();
        }

      //  refreshCredits();
    }



    public double calculer() throws SQLException, ClassNotFoundException {
        double Hrestantes=0;


        return Hrestantes;
    }


    public void refreshRecupDate(){
        //recupDate.hide();
        recupDate.show();
    }

    private static boolean removeSelectedDates(ObservableList<LocalDate> selectedDates, ListView<LocalDate> dateList) {
        return selectedDates.removeAll(dateList.getSelectionModel().getSelectedItems());
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /////////////////////////////////// Initialisation de la date //////////////////////////////////////////////

        recupDate.setShowWeekNumbers(true);

        recupDate.setOnAction(event ->{
            System.out.println("Selected date: " + recupDate.getValue());
                recupDate.show();
            });
        recupDate.setPromptText(pattern);
        recupDate.setConverter(new StringConverter<LocalDate>() {
            @Override
            public String toString(LocalDate date) {
                return (date == null) ? "" : dateFormatter.format(date);
            }

            @Override
            public LocalDate fromString(String string) {
                return ((string == null) || string.isEmpty()) ? null : LocalDate.parse(string, dateFormatter);
            }
        });
        recupDate.setDayCellFactory(new Callback<DatePicker, DateCell>() {
            @Override
            public DateCell call(DatePicker param) {
                return new DateCell() {
                    @Override
                    public void updateItem(LocalDate item, boolean empty) {
                        super.updateItem(item, empty);
                        boolean alreadySelected = selectedDates.contains(item);
                        //setDisable(alreadySelected);
                        setStyle(alreadySelected ? "-fx-background-color: #09a30f;" : "");
                    }
                };
            }
        });

        //recupDate.setOnKeyPressed((javafx.scene.input.KeyEvent ke)->{

       // });
        /////////////////////////////////////////////////////////////////////////////////////////


        try {
            VBox box = FXMLLoader.load(getClass().getResource("/Views/DrawerContent.fxml"));
            drawer.setSidePane(box);
            HamburgerBackArrowBasicTransition burgerTask2 = new HamburgerBackArrowBasicTransition(hamburger);
            burgerTask2.setRate(-1);

            hamburger.addEventHandler(MouseEvent.MOUSE_PRESSED, (EventHandler<MouseEvent>) (event) -> {
                burgerTask2.setRate(burgerTask2.getRate() * -1);
                burgerTask2.play();

               if (drawer.isShown()){
                    drawer.close();
               }else {
                    drawer.open();
                }
            });








            ////////////////////////////////////

            VBox box2= (VBox) box.getChildren().get(1);
            Label label= (Label) box2.getChildren().get(1);
            label.setText(userName);


            System.out.println(label.getText());
            ///////////////////////////////////
            for (Node node : box.getChildren()){
                if (node.getAccessibleText() !=null){
                    node.addEventHandler(MouseEvent.MOUSE_CLICKED, (e) -> {
                        switch ( node.getAccessibleText()){
                            case "deconnect":
                                System.out.println("deconnecting");
                                HomeStage.close();
                                Main.stage.show();
                                break;
                            case "exit":
                                System.out.println("exiting");
                                System.exit(0);
                                break;

                        }



                    });

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }



        ///// Charger touts les employes de la base de donnee
        try {
            getAllEmployes();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        // construire la table des employes --- table view
        constructTable();



//        test.setText( "Welcome Mr " + userName + "\n"+ isAdmin );
    }
    Stage stage;
    public  void Main(Stage stage){
        this.stage=stage;

    }
}
