package prosjektDB;

import java.sql.Statement;
import java.util.ArrayList;
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
		navn = "'" + sc.nextLine() + "'";
		if (navn.equals("'null'")) {
			navn = "null";
		}
		
		System.out.println("Beskrivelse: ");
		beskrivelse = "'" + sc.nextLine() + "'";
		if (beskrivelse.equals("'null'")) {
			beskrivelse = "null";
		}

		
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
		String varighet;
		String form; // format: talle mellom 1 og 10
		String prestasjon; // format: tall mellom 1 og 10
		String pnrFKStr = "'" + Integer.toString(pnrFK) + "'";
		
		ArrayList<String> ovelser = new ArrayList<String>();
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Dato og tid (format: YYYY-MM-DD HH:MM:SS): ");
		dateTime = sc.nextLine();
		if (!(dateTime.equals("null"))) {
			if (!(dateTime.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}:\\d{2}"))) {
				throw new IllegalArgumentException("Formatet er ikke korrekt, format: YYYY-MM-DD HH:MM:SS");
			}
			dateTime = "'" + dateTime + "'";
		}
		
		System.out.println("Varighet (i minutter): ");
		varighet ="'" + sc.nextLine() + "'";
		if (varighet.equals("'null'")) {
			varighet = "null";
		}
		
		System.out.println("Form (tall mellom 1 og 10): ");
		form = sc.nextLine();
		if (!(form.equals("null"))) {
			if (Integer.parseInt(form) <= 0 || Integer.parseInt(form) > 10) {
				throw new IllegalArgumentException("Formatet er ikke korrekt, format: tall mellom 1-10 ");
			}
			form = "'" + form + "'";
		}
		
		System.out.println("Prestasjon (tall mellom 1 og 10): ");
		prestasjon = sc.nextLine();
		if (!(form.equals("null"))) {
			if (Integer.parseInt(prestasjon) <= 0 || Integer.parseInt(prestasjon) > 10) {
				throw new IllegalArgumentException("Formatet er ikke korrekt, format: tall mellom 1-10 ");
			}
			prestasjon = "'" + prestasjon + "'";
		}
		
		while (true) {
			System.out.println("�velse ID (Skriv end n�r ferdig):");
			String ovelseID = sc.next(); 
			if (ovelseID.toLowerCase().equals("end")) {
				break;
			}
			ovelser.add(ovelseID);
		}
		
		
		try {
			Statement st = conn.createStatement();
			st.executeUpdate("INSERT INTO Trenings�kt (Dato, Varighet, Form, Prestasjon, Pnr) VALUES(" + dateTime + "," + varighet + "," + form + "," + prestasjon + "," + pnrFKStr + ")");
		}catch(Exception e) {
			System.out.println("db error during insert of trenings�kt " + e);
		}
		
	}
	
	public void regPerson(int Pnr) {
		//TODO
	}
	
	public void regOvelseGruppe() {
		//TODO
	}
	
	
	public static void main(String[] args) {
		RegCtrl r = new RegCtrl();
		//r.regApparat();
		r.regTreningsokt(1);
	}
	
	
}
