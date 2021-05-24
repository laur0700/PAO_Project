import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Driver
{
    public void WriteToAudit(String Content)
    {
        try
        {
            LocalDateTime myDateObj = LocalDateTime.now();
            DateTimeFormatter myFormatObj = DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss");
            String formattedDate = myDateObj.format(myFormatObj);

            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect",
                    "root", "naruto02");

            Statement myStmt = myConn.createStatement();
            myStmt.executeUpdate("INSERT INTO AUDIT(action, timestamp) " +
                    "VALUES (" + '"' + Content + '"' + ", " + '"' + formattedDate + '"' + ")");

        }catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public ResultSet ReadFromDB(String table)
    {
        ResultSet rs = null;
        try
        {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect",
                    "root", "naruto02");

            Statement myStmt = myConn.createStatement();

            rs = myStmt.executeQuery("SELECT * FROM " + table + ";");

        }catch(Exception e)
        {
            e.printStackTrace();
        }

        return rs;
    }

    public void AddProdus(Produse P)
    {
        try
        {
            Connection myConn = DriverManager.getConnection("jdbc:mysql://localhost:3306/proiect",
                    "root", "naruto02");

            Statement myStmt = myConn.createStatement();

            myStmt.executeUpdate("INSERT INTO PRODUSE(NUME, PRET, NUME_CATEGORIE, NUME_PRODUCATOR, " +
                    "NUME_DISTRIBUITOR, ID_MAGAZIN, STOC_MAGAZIN, ID_MAGAZIN_ONLINE, STOC_MAGAZIN_ONLINE, " +
                    "ID_DEPOZIT, STOC_DEPOZIT, DESCRIERE) VALUES" +
                    "(" + '"' + P.getNume() + '"' + ", " + P.getPret() + ", " + '"' + P.getNumeCategorie() + '"' + ", " +
                    '"' + P.getNumeProducator() + '"' + ", " + '"' + P.getNumeDistribuitor() + '"' + ", " +
                    P.getId_magazin() + ", " + P.getStoc_magazin() + ", " + P.getId_magazin_online() + ", " +
                    P.getStoc_magazin_online() + ", " + P.getId_depozit() + ", " +  P.getStoc_depozit() + ", " +
                    '"' + P.getDescriere() + '"' + ");");

        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }
}
