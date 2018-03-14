package prosjektDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main{
	static Scanner scanner = new Scanner(System.in);
	static List<String> commands = Arrays.asList("help", "shutoff", "register");
	static boolean shutoff = false;

	static GetCtrl getController = new GetCtrl();
	static RegCtrl registerController = new RegCtrl();


	public static void printHelp() {
		// Metode for aa printe ut alternativer i programmet
		
		System.out.println("Du skrev hjelp.\nHer er ting du kan gjoere i programmet:\n"+
				"'hjelp' - Viser denne listen over funksjonalitet i programmet.\n"
				+"'shutoff' - avslutter programmet.\n'register' - starter registrering av ny bruker.\n");
	}
	
	
	public static void registerUser() {
		System.out.println("------Registrering av ny bruker------\nHva er ditt personnummer?");
		System.out.print("Personnummer: ");
		String Pnr = scanner.next();
		try {
			int PnrInt = Integer.parseInt(Pnr);
			if(getController.eksistererPerson(Pnr)) {
				System.out.println("Personen er allerede registrert.");
			} else {
				registerController.regPerson(PnrInt);
			}
		} catch (Exception e) {
			System.out.println("Ugyldig personummer! Skriv inn et tall");
			registerUser();
		}
	}
	
	public static void performAction(String action) {
		// Denne metoden må holde if-setninger for hver av actions, og deretter kjøre hver action sin metode.
		if(action.equals("help")) {
			printHelp();
		}
		
		if(action.equals("shutoff")) {
			shutOff();
		}
		
		if(action.equals("register")) {
			registerUser();
		}
	}
	
	public static void shutOff() {
		shutoff = true;
	}
	
	public static void waitForUserAction() {
		// Ber brukeren skrive en handling, og sjekker om det er gyldig. Hvis gyldig kjøres performaction.
		System.out.print("Skriv en handling:");
		String action = scanner.next();
		action = action.toLowerCase();
		if(commands.contains(action)) {
			performAction(action);
		}
		else {
			System.out.println("Ugyldig action. Skriv 'hjelp' om du trenger hjelp.");
		}	
	}
	
	public static void main(String args[]) {
		//Main metoden.

		System.out.println("Velkommen til treningsdagbok\n");
		System.out.println("For hjelp skriv inn 'hjelp'");

		
		
		while(!shutoff) {
			waitForUserAction();
		}
		
		System.out.println("Hade bra!");
	}
	
}