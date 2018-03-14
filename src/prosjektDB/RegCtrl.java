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
	
	public void regovelse() {
		
	}
	
	@SuppressWarnings("resource")
	public void regTreningsokt(int pnrFK) {
		String dateTime; // format: YYYY-MM-DD HH:MM:SS
		int varighet;
		int form; // format: talle mellom 1 og 10
		int prestasjon; // format: tall mellom 1 og 10
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Dato og tid (format: YYYY-MM-DD HH:MM:SS): ");
		dateTime = sc.nextLine();
		if (!(dateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))) {
			throw new IllegalArgumentException("Formatet er ikke korrekt, format: YYYY-MM-DD HH:MM:SS");
		}
		
		System.out.println("Varighet (i minutter): ");
		varighet = sc.nextInt();
		
		System.out.println("Form: (tall mellom 1 og 10)");
		form = sc.nextInt();
		if (form <= 0 || form > 10) {
			throw new IllegalArgumentException("Formatet er ikke korrekt, format: tall mellom 1-10 ");
		}
		
		System.out.println("Prestasjon: (tall mellom 1 og 10)");
		prestasjon = sc.nextInt();
		if (prestasjon <= 0 || prestasjon > 10) {
			throw new IllegalArgumentException("Formatet er ikke korrekt, format: tall mellom 1-10 ");
		}
		
		
		sc.close();
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Apparat VALUES(" + "dateTime" + "," + Integer.toString(varighet) + "," + Integer.toString(form) + "," + Integer.toString(prestasjon) + "," + Integer.toString(pnrFK) + ")");
		}catch(Exception e) {
			System.out.println("db error during insert of apparat");
		}
	}
	
	public void regPerson(int Pnr) {
		//TODO
	}
	
	
	public static void main(String[] args) {
		RegCtrl r = new RegCtrl();
		r.regApparat();
		r.regTreningsokt(1);
	}
	
	
}
