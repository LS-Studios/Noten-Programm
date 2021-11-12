package lsstudios.database;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import lsstudios.gui.PopUps;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;

public class Database {
    //Static Variables
    public static String DBPath;
    public static int benutzerId;
    public static int fachIdToEdit;

    //Connect Methods
    public static Connection ConnectToDB(String DBPath) {
        Connection conn = null;
        try {
            String url = "jdbc:sqlite:" + DBPath;

            conn = DriverManager.getConnection(url);

        } catch (Exception e) {
            conn = null;
        }
        return  conn;
    }
    public static void CloseConnectionToDB(String DBPath) {
        Connection conn = ConnectToDB(DBPath);
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    //SetUp Datatables
    public static void SetUp() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE SetUP(g text);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);

            System.out.println("SetedDatabaseUp");
        } catch (Exception e) {
            System.out.println("AlreadySetedUp");

            return;
        }

        //Create Tables
        CreateBenutzerTable();
        CreateFachTable();
        CreateNoteTable();
        CreateFachBelegungTable();
        CreateFachInfosTable();

        //Add new Fächer to Fach
        Database.AddDataToFach("Deutsch");
        Database.AddDataToFach("Englisch");
        Database.AddDataToFach("Latein");
        Database.AddDataToFach("Schwedisch");
        Database.AddDataToFach("Spanisch");
        Database.AddDataToFach("Kunst");
        Database.AddDataToFach("Musik");
        Database.AddDataToFach("Religion");
        Database.AddDataToFach("Geografie");
        Database.AddDataToFach("Geschichte");
        Database.AddDataToFach("Sozialkunde");
        Database.AddDataToFach("Wirtschaft");
        Database.AddDataToFach("Biologie");
        Database.AddDataToFach("Chemie");
        Database.AddDataToFach("Informatik");
        Database.AddDataToFach("Mathematik");
        Database.AddDataToFach("Physik");
        Database.AddDataToFach("Sport");

    }

    public static void CreateBenutzerTable() {
        System.out.println(DBPath);
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Benutzer(id INTEGER PRIMARY KEY AUTOINCREMENT, Vorname TEXT, Nachname TEXT, Passwort TEXT, NotenSystem TEXT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateFachTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Fach(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateNoteTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Note(id INTEGER PRIMARY KEY AUTOINCREMENT, BenutzerId INT, FachId INT, Note INT, NotenWert TEXT, Semester INT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateFachBelegungTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE FachBelegung(BenutzerId INT, FachId INT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateFachInfosTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE FachInfos(id INTEGER PRIMARY KEY AUTOINCREMENT, BenutzerId INT, FachId INT, Thema TEXT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    //Add Data To Datatables
    public static void AddDataToBenutzer(String Vorname, String Nachname, String Passwort, String Notensystem) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO Benutzer (Vorname, Nachname, Passwort, NotenSystem) VALUES (?, ?, ?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, Vorname);
            prst.setString(2, Nachname);
            prst.setString(3, Passwort);
            prst.setString(4, Notensystem);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void AddDataToFach(String name) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO Fach (Name) VALUES (?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, name);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void AddDataToNote(int benutzerId, int fachId, int note, String notenWert, int Semester) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO Note (BenutzerId, FachId, Note, NotenWert, Semester) VALUES (?, ?, ?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.setInt(3, note);
            prst.setString(4, notenWert);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void AddDataToFachBelegung(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO FachBelegung (BenutzerId, FachId) VALUES (?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void AddDataOfNewBenutzerToFachBelegung(int newBenutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO FachBelegung (BenutzerId, FachId) VALUES (?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void AddDataToFachInfos(int schülerId, int fachId, String thema) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO FachInfos (BenutzerId, FachId, Thema) VALUES (?, ?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, schülerId);
            prst.setInt(1, fachId);
            prst.setString(1, thema);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", "" + e);
        }
    }

    //Remove Data Of Datatables
    public static void RemoveDataOfBenutzer(int benutzerId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "DELETE FROM Benutzer WHERE id LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void RemoveDataOfFach(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "DELETE FROM Fach WHERE BenutzerId LIKE ? AND FachId LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void RemoveDataOfNote(int benutzerId, int fachId, int note, String notenWert) {
        Connection conn = ConnectToDB(DBPath);

        String code = "DELETE FROM Note WHERE BenutzerId LIKE ? AND FachId LIKE ? AND Note LIKE ? AND Notenwert LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.setInt(3, note);
            prst.setString(4, notenWert);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void RemoveDataOfFachBelegung(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "DELETE FROM FachBelegung WHERE BenutzerId LIKE ? AND FachId LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void RemoveDataOfFachinfos(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "DELETE FROM FachInfos WHERE BenutzerId LIKE ? AND FachId LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.executeUpdate();
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    //Get Data Of Datatable
    public static int GetBenutzerId(String Vorname, String Nachname, String Passwort) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT id FROM Benutzer WHERE Vorname LIKE ? AND Nachname lIKE ? AND Passwort LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, Vorname);
            prst.setString(2, Nachname);
            prst.setString(3, Passwort);

            ResultSet rs = prst.executeQuery();

            return rs.getInt("id");


        } catch (Exception e) {
            System.out.println(e);

            return -1;
        }
    }

    public static Benutzer GetDataOfBenutzer() {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT * FROM Benutzer WHERE id LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            ResultSet rs = prst.executeQuery();

            return new Benutzer(rs.getString("Vorname"), rs.getString("Nachname"), rs.getString("Passwort"), rs.getString("NotenSystem"));
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return null;
    }

    public static int GetFachId(String name) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT id FROM Fach WHERE Name LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, name);

            ResultSet rs = prst.executeQuery();

            return rs.getInt("id");

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return 0;
    }

    public static String GetFachName(int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT Name FROM Fach WHERE id LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, fachId);

            ResultSet rs = prst.executeQuery();

            return rs.getString("Name");

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return null;
    }

    public static int GetDataOfNote(int benutzerId, int fachId, int note, String notenWert) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT id FROM Fach WHERE BenutzerId LIKE ? AND FachId LIKE ? AND Note LIKE ?, AND NotenWert LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);
            prst.setInt(3, note);
            prst.setString(4, notenWert);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return 0;
    }

    public static ArrayList<Integer> GetDataOfFachBelegung(int benutzerId) {
        Connection conn = ConnectToDB(DBPath);
        String code = "SELECT FachId FROM FachBelegung WHERE BenutzerId LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);

            ResultSet rs = prst.executeQuery();

            ArrayList<Integer> fächer = new ArrayList<>();
            while (rs.next()) {
                fächer.add(rs.getInt("FachId"));

            }

            return fächer;

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return null;
    }

    public static ArrayList<Integer> GetKleineNoten(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT Note FROM Note WHERE BenutzerId LIKE ? AND FachId LIKE ? AND NotenWert LIKE 'KleineNote';";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);

            ResultSet rs = prst.executeQuery();

            ArrayList<Integer> noten = new ArrayList<>();
            while (rs.next()) {
                noten.add(rs.getInt("Note"));
            }

            return noten;

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return null;
    }

    public static int GetKlausurNote(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT Note FROM Note WHERE FachId LIKE ? AND NotenWert LIKE 'KlausurNote';";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);

            ResultSet rs = prst.executeQuery();

            return rs.getInt("Note");

        } catch (Exception e) {
            //PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return -1;
    }

    public static int GetBenutzerLength() {
        int length = 0;

        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT * FROM Benutzer;";

        try (Statement st = conn.createStatement()) {

            ResultSet rs = st.executeQuery(code);

            while (rs.next()) {
                length++;
            }

            return length;
        } catch (Exception e) {
            System.out.println(e);
        }

        return length;
    }

    public static void ChangeScreen(String filepath, Pane pane) throws IOException {
        Parent root = FXMLLoader.load(Database.class.getClassLoader().getResource(filepath));
        Stage window = (Stage) pane.getScene().getWindow();

        Scene newScene = new Scene(root);
        newScene.setFill(Color.TRANSPARENT);
        window.setScene(newScene);
    }
}
