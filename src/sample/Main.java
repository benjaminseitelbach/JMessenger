package sample;

import java.sql.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends Application {

    static int numeroLigneSalon = 4;

    public static void debug () {
        String test1 = "Oui";
        String test2 = "Oui";

        boolean egalite = test1.equals(test2);

        System.out.println(egalite);

    }


    public static void setSql (String sPseudo, String sMdp) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Jmessenger?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","root");

            Statement stmt = con.createStatement();

            stmt.executeUpdate("INSERT INTO utilisateurs (pseudo, mdp) VALUES ('"+sPseudo+"','"+sMdp+"')");

            con.close();
        }catch(Exception e){ System.out.println(e);}

    }

    public static boolean rechercheUtilisateur (String pseudoSaisi, String mdpSaisi) {
        try{

            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con= DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/Jmessenger?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC","root","root");

            Statement stmt = con.createStatement();

            ResultSet rs=stmt.executeQuery("select * from utilisateurs");

            boolean utilisateurExistant = false;

            while(rs.next()) {

                if (pseudoSaisi.equals(rs.getString(1))) {
                    if (mdpSaisi.equals(rs.getString(2))) {
                        utilisateurExistant = true;
                    }

                }

            }

            con.close();

            return utilisateurExistant;


        }catch(Exception e){ System.out.println(e); return false;}

    }


    @Override
    public void start(Stage pageConnexion) {



        //Initialisation du titre de la page connexion
        pageConnexion.setTitle("JMessenger");

        //Initialisation de la grille pour la page connexion
        GridPane grilleConnexion = new GridPane();
        grilleConnexion.setAlignment(Pos.CENTER);
        grilleConnexion.setHgap(10);
        grilleConnexion.setVgap(10);
        grilleConnexion.setPadding(new Insets(25, 25, 25, 25));

        /* INITIALISATION DES DIFFERENTS ELEMENTS DE LA PAGE DE CONNEXION */

        //Message de bienvenue
        Text titreSceneConnexion = new Text("Bienvenue");
        titreSceneConnexion.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        grilleConnexion.add(titreSceneConnexion, 0, 0, 2, 1);

        //Pseudo
        Label textePseudo = new Label("Pseudo:");
        grilleConnexion.add(textePseudo, 0, 1);

        //Saisie du pseudo
        TextField saisiePseudo = new TextField();
        grilleConnexion.add(saisiePseudo, 1, 1);

        //Mot de passe
        Label texteMdp = new Label("Mot de passe:");
        grilleConnexion.add(texteMdp, 0, 2);

        //Saisie du mot de passe
        PasswordField saisieMdp = new PasswordField();
        grilleConnexion.add(saisieMdp, 1, 2);

        Button connexion = new Button("Connexion");
        grilleConnexion.add(connexion, 0, 4);

        Button inscription = new Button("Inscription");

        grilleConnexion.add(inscription, 2, 4);

        //Initialisation de la scene de la page connexion
        Scene sceneConnexion = new Scene(grilleConnexion, 500, 300);

        //Initialisation de la page principale
        Stage pagePrincipale = new Stage();
        pagePrincipale.setTitle("JMessenger");
        GridPane grillePrincipale = new GridPane();
        grillePrincipale.setAlignment(Pos.TOP_LEFT);
        grillePrincipale.setHgap(10);
        grillePrincipale.setVgap(10);
        grillePrincipale.setPadding(new Insets(25, 25, 25, 25));

        Button boutonCreerSalon = new Button("Créer un salon");
        grillePrincipale.add(boutonCreerSalon, 0, 2);

        //Salons disponibles
        Label texteSalons = new Label("Salons: ");
        grillePrincipale.add(texteSalons,0,3);

        /* CREATION D'UN SALON */

        //Nom du salon
        Label texteNomSalonCreation = new Label("Nom du salon:");
        grillePrincipale.add(texteNomSalonCreation,2,1);

        //Saisie du nom du salon
        TextField saisieNomSalonCreation = new TextField();
        grillePrincipale.add(saisieNomSalonCreation, 2, 2);

        Button boutonCreer = new Button("Créer");
        grillePrincipale.add(boutonCreer, 2, 3);

        saisieNomSalonCreation.setVisible(false);
        texteNomSalonCreation.setVisible(false);
        boutonCreer.setVisible(false);



        Scene scenePrincipale = new Scene(grillePrincipale, 800, 500);

        connexion.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String pseudo = saisiePseudo.getText();
                String mdp = saisieMdp.getText();
                Utilisateur utilisateur = new Utilisateur(pseudo, mdp);
                if(rechercheUtilisateur(pseudo, mdp)) {

                    Text scenetitle = new Text("Bonjour "+pseudo);
                    scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                    grillePrincipale.add(scenetitle, 0, 0, 2, 1);
                    pagePrincipale.setScene(scenePrincipale);
                    pagePrincipale.show();
                    pageConnexion.close();

                }
                else {

                    final Text actiontarget = new Text();
                    actiontarget.setFill(Color.FIREBRICK);
                    actiontarget.setText("Pseudo ou mot de passe incorrect");
                    grilleConnexion.add(actiontarget, 0, 6);


                }


            }
        });


        inscription.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                String pseudo = saisiePseudo.getText();
                String mdp = saisieMdp.getText();
                Utilisateur utilisateur = new Utilisateur(pseudo, mdp);
                System.out.println(pseudo);
                System.out.println(mdp);
                setSql(pseudo, mdp);

                Text scenetitle = new Text("Bienvenue "+pseudo);
                scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
                grillePrincipale.add(scenetitle, 0, 0, 2, 1);
                pagePrincipale.setScene(scenePrincipale);
                pagePrincipale.show();
                pageConnexion.close();

            }
        });

        boutonCreer.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {

                String nomSalonCreation = saisieNomSalonCreation.getText();
                //Initialisation de la page du salon
                Stage pageSalon = new Stage();
                pageSalon.setTitle(nomSalonCreation);

                GridPane grilleSalon = new GridPane();
                grilleSalon.setAlignment(Pos.TOP_LEFT);
                grilleSalon.setHgap(10);
                grilleSalon.setVgap(10);
                grilleSalon.setPadding(new Insets(25, 25, 25, 25));

                Text texteNomSalon = new Text(nomSalonCreation);
                grilleSalon.add(texteNomSalon, 5, 0);

                Button boutonAjouterMembre = new Button("Ajouter un membre");
                grilleSalon.add(boutonAjouterMembre, 1, 1);

                Scene sceneSalon= new Scene(grilleSalon, 500, 500);
                pageSalon.setScene(sceneSalon);


                pageSalon.show();

                saisieNomSalonCreation.setVisible(false);
                texteNomSalonCreation.setVisible(false);
                boutonCreer.setVisible(false);

                Button boutonSalon = new Button(nomSalonCreation);
                grillePrincipale.add(boutonSalon, 0, numeroLigneSalon);

                numeroLigneSalon ++;


            }
        });

        boutonCreerSalon.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent e) {
                saisieNomSalonCreation.setVisible(true);
                texteNomSalonCreation.setVisible(true);
                boutonCreer.setVisible(true);
            }
        });
        pageConnexion.setScene(sceneConnexion);
        pageConnexion.show();
    }


    public static void main(String[] args) {
        //debug();
        launch(args);

    }
}
