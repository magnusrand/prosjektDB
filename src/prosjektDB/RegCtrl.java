package prosjektDB;

import java.sql.Statement;
import java.util.Scanner;

public class RegCtrl extends DBConn {
	
	public RegCtrl() {
		connect();
	}
	
	public void regApparat() {
		String navn;
		String beskrivelse;
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Apparatnavn: ");
		navn = "'"+sc.nextLine()+"'";
		
		System.out.println("Beskrivelse: ");
		beskrivelse = "'"+sc.nextLine()+"'";
		if(beskrivelse == "") {
			beskrivelse = "'null'";
		}
		
		sc.close();
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Apparat VALUES("+navn+","+beskrivelse+")");
		}catch(Exception e) {
			System.out.println("db error during insert of apparat: "+e);
		}
		
	}
	
	public void regØvelse() {
		
	}
	
	public void regTreningsøkt() {
		
	}
	
	public static void main(String[] args) {
		RegCtrl r = new RegCtrl();
		r.regApparat();
	}
	
}
