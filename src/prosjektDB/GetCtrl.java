package prosjektDB;

// bare hentet denne fra samme sted vi fikk DBConn og RegCTRL. Her kan vi skrive selection/query SQL metoder som main kan kalle pÃ¥ for Ã¥ hente info fra databasen.

import java.sql.*;


public class GetCtrl extends DBConn {
	
	public GetCtrl() {
		connect();
	}
	
	public boolean eksistererPerson(String Pnr) {
		// IKKE FERDIG. Her skal det sjekkes om et personnummer er registrert i databasen eller ikke.
		try {
			Statement stmt = conn.createStatement();
			//String query = "select * from Person";
	        String query = "select Navn from tobiassk_treningsdagbok.Person where Pnr ='"+Pnr+"'";
	        ResultSet rs = stmt.executeQuery(query);
	        if(rs.next()) {
	        		return true;
	        }
	        else {
	        		return false;
	        }
	         } catch (Exception e) {
	        	 System.out.println("db error during select of navn = "+e);
	         }
		return false;
	}
	
    public void printPersonNavn (String Pnr) {
    		// tar inn personnummer, printer ut navn i konsollen.
       try {
    	   
           Statement stmt = conn.createStatement();
           String query = "select * from Person";
           //String query = "select Navn from tobiassk_treningsdagbok.Person where Pnr ='"+Pnr+"'";
           ResultSet rs = stmt.executeQuery(query);
           rs.next();
           System.out.println("Navnet for pnr: " + Pnr +" er "+ rs.getString("Navn"));
                } catch (Exception e) {
          System.out.println("db error during select of navn = "+e);
		}
    }
  
    public void printTreningsOkt (String OktID) {
		// tar inn OktID, printer ut all info om OKTEN.
	   try {
		   
	       Statement stmt = conn.createStatement();
	       String query = "select * from Treningsøkt where TreningsøktID ='"+OktID+"'";
	       ResultSet rs = stmt.executeQuery(query);
	       System.out.println("Info om TreningsøktID: " + OktID +":"+ "Dato: " + rs.getString("Dato") + " " + "Varighet: " + rs.getString("Varighet") + " " + "Form: " + rs.getString("Form") + " " + "Prestasjon: " + rs.getString("Prestasjon") + " " + "Pnr: " + rs.getString("Pnr"));
	            } catch (Exception e) {
	      System.out.println("db error during select of Treningsøkt = "+e);
		}
	   
	   

	 
	 
}
    
    
    
}