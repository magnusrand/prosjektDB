package prosjektDB;

// bare hentet denne fra samme sted vi fikk DBConn og RegCTRL. Her kan vi skrive selection/query SQL metoder som main kan kalle på for å hente info fra databasen.

import java.sql.*;


public class GetCtrl extends DBConn {
    public void printKlasseResultat (String klasseNavn) {
       try {
           Statement stmt = conn.createStatement();
           String query = "select navn, klubb, lopstid from Loper where klasse='"+klasseNavn+"' and status='ok' order by lopstid";
           ResultSet rs = stmt.executeQuery(query);
           int nr = 1;
           System.out.println("Resultatliste for klasse "+klasseNavn);
           while (rs.next()) {
             System.out.println(" " + nr++ + " "+ rs.getString("navn") + " "
                + rs.getString("klubb") + " " + rs.getInt("lopstid"));
}
} catch (Exception e) {
          System.out.println("db error during select of loper = "+e);
} }
}