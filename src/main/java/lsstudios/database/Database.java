package lsstudios.database;

import lsstudios.gui.PopUps;

import java.sql.*;

public class Database {
    //Static Variables
    public static String DBPath;
    public static int benutzerId;

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
    public static void CreateBenutzerTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Benutzer(id INTEGER PRIMARY KEY AUTOINCREMENT, Vorname TEXT, Nachname TEXT, Passwort TEXT, Notensysten INT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateFachTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Schüler(id INTEGER PRIMARY KEY AUTOINCREMENT, Name TEXT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateNoteTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE Note(id INTEGER PRIMARY KEY AUTOINCREMENT, SchülerId INT, FachId INT, Note INT, NotenWert TEXT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    public static void CreateFachBelegungTable() {
        Connection conn = ConnectToDB(DBPath);

        String code = "CREATE TABLE FachBelegung(id INTEGER PRIMARY KEY AUTOINCREMENT, BenutzerId INT, FachId INT);";

        try {
            Statement stm = conn.createStatement();
            stm.execute(code);
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }
    }

    //Add Data To Datatables
    public static void AddDataToBenutzer(String Vorname, String Nachname, String Passwort, int Notensystem) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO Benutzer (Vorname, Nachname, Passwort, Notensystem) VALUES (?, ?, ?, ?);";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, Vorname);
            prst.setString(2, Nachname);
            prst.setString(3, Passwort);
            prst.setInt(4, Notensystem);
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

    public static void AddDataToNote(int benutzerId, int fachId, int note, String notenWert) {
        Connection conn = ConnectToDB(DBPath);

        String code = "INSERT INTO Note (BenutzerId, FachId, Note, NotenWert) VALUES (?, ?, ?, ?);";

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

    //Get Data Of Datatable
    public static Benutzer GetDataOfBenutzer() {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT * FROM Benutzer WHERE id LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return new Benutzer(rs.getString("Vorname"), rs.getString("Nachname"), rs.getString("Passwort"), rs.getInt("Notensystem"));
            }
        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return null;
    }

    public static int GetDataOfFach(String name) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT id FROM Fach WHERE Name LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setString(1, name);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return 0;
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

    public static int GetDataOfFachBelegung(int benutzerId, int fachId) {
        Connection conn = ConnectToDB(DBPath);

        String code = "SELECT id FROM FachBelegung WHERE BenutzerId LIKE ? AND FachId LIKE ?;";

        try (PreparedStatement prst = conn.prepareStatement(code)) {
            prst.setInt(1, benutzerId);
            prst.setInt(2, fachId);

            ResultSet rs = prst.executeQuery();

            while (rs.next()) {
                return rs.getInt("id");
            }

        } catch (Exception e) {
            PopUps.ErrorPopUp("Error", "An Error has happen!", ""+ e);
        }

        return 0;
    }
}
