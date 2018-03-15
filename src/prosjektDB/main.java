package prosjektDB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class main{
	static Scanner scanner = new Scanner(System.in);
	static List<String> commands = Arrays.asList("help", "shutoff", "register", "login", "checklogin");
	static boolean shutoff = false;

	static GetCtrl getController = new GetCtrl();
	static RegCtrl registerController = new RegCtrl();
	
	static String loggedInPnr = "Ingen bruker pålogget enda.";


	public static void printHelp() {
		// Metode for aa printe ut alternativer i programmet
		
		System.out.println("Du skrev hjelp.\nHer er ting du kan gjoere i programmet:\n"+
				"'help' - Viser denne listen over funksjonalitet i programmet.\n"
				+"'shutoff' - avslutter programmet.\n'register' - starter registrering av ny bruker/økt/øvelse/apparat/øvelsesgruppe.\n'login' - logger på ønsket bruker gjennom personnummer.\n'home' - tar deg tilbake til hovedmeny.\n'checklogin' - sjekker hvilken bruker som er pålogget.\n");
	}
	
	public static void registerOkt() {
		//TODO
		try {
		registerController.regTreningsokt(Integer.parseInt(loggedInPnr));
		} catch (Exception e) {
			System.out.println("Logged in pnr er ikke en integer. Har du logget inn?");
		}
	}
	
	public static void registerOvelse() {
		//TODO
		registerController.regovelse();
	}
	
	public static void registerApparat() {
		//TODO
		registerController.regApparat();
	}
	
	public static void registerOvelsesgruppe() {
		//TODO
		registerController.regOvelseGruppe();
	}
	
	public static void register() {
		List<String> localCommands = Arrays.asList("bruker", "treningsøkt", "øvelse", "apparat", "øvelsesgruppe");
		System.out.println("----Hva vil du registerere?----");
		System.out.println("Valg: \n1 - bruker - skriv 'bruker'.\n2 - Treningsøkt - skriv 'treningsøkt'.\n3 - Øvelse - skriv 'øvelse'.\n4 - Apparat - skriv 'apparat'.\n5 - Øvelsesgruppe - skriv 'øvelsesgruppe'.\n");
		System.out.print("Skriv inn valg: ");
		String registerValg = scanner.next();
		if(registerValg.toLowerCase().equals("home")) {
			return;
		}
		if(localCommands.contains(registerValg.toLowerCase())) {
			// Bruker skrev inn gyldig command
			if(registerValg.toLowerCase().equals("bruker")) {
				registerUser();
			}
			
			if(registerValg.toLowerCase().equals("treningsøkt")) {
				registerOkt();
			}

			if(registerValg.toLowerCase().equals("øvelse")) {
				registerOvelse();
			}
			
			if(registerValg.toLowerCase().equals("apparat")) {
				registerApparat();
			}
			
			if(registerValg.toLowerCase().equals("øvelsesgruppe")) {
				registerOvelsesgruppe();
			}
		}
		else {
			System.out.println("Skriv inn en gyldig kommando, eller home for å gå til hovedmenyen.");
			register();
		}
	}
	
	
	public static void registerUser() {
		System.out.println("------Registrering av ny bruker------\nHva er ditt personnummer?");
		System.out.print("Personnummer: ");
		String Pnr = scanner.next();
		if(Pnr.toLowerCase().equals("home")) {
			return;
		}
		try {
			int PnrInt = Integer.parseInt(Pnr);
			if(getController.eksistererPerson(Pnr)) {
				System.out.println("Personen er allerede registrert.");
				getController.printPersonNavn(Pnr);
			} else {
				registerController.regPerson(PnrInt);
			}
		} catch (Exception e) {
			System.out.println("Ugyldig personummer! Skriv inn et tall");
			registerUser();
		}
	}
	
	public static void performAction(String action) {
		// Denne metoden m� holde if-setninger for hver av actions, og deretter kj�re hver action sin metode.
		if(action.equals("help")) {
			printHelp();
		}
		
		if(action.equals("shutoff")) {
			shutOff();
		}
		
		if(action.equals("register")) {
			register();
		}
		
		if(action.equals("login")) {
			login();
		}
		
		if(action.equals("checklogin")) {
			checkLoginPnr();
		}
	}
	
	public static void login() {
		// TODO
		System.out.print("Skriv inn personnr du vil logge inn på:");
		String loginPnr = scanner.next();
		
		if(loginPnr.toLowerCase().equals("home")) {
			return;
		}
		
		//Try catch her sjekker bare om personen skrev inn tall.
		try {
			int parsedIntCheck = Integer.parseInt(loginPnr);
		} catch (Exception e) {
			System.out.println("Personnummer må være tall!");
			login();
		}
		if(getController.eksistererPerson(loginPnr)) {
			// PersonIDen eksisterte, logger inn på denne.
			loggedInPnr = loginPnr;
			System.out.println("Du logget inn på personnummer: " + loginPnr);
			System.out.print("Velkommen ");
			getController.printPersonNavn(loginPnr);
		}
		else {
			System.out.println("Ingen bruker registrert på pnr: " + loginPnr);
			login();
		}
	}
	
	public static void shutOff() {
		shutoff = true;
	}
	
	public static void waitForUserAction() {
		// Ber brukeren skrive en handling, og sjekker om det er gyldig. Hvis gyldig kj�res performaction.
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
	
	public static void checkLoginPnr() {
		System.out.println("Logged in Pnr er: " + loggedInPnr);
		try {
			int parsedInt = Integer.parseInt(loggedInPnr);
			getController.printPersonNavn(loggedInPnr);
		} catch (Exception e) {
			// Ingen bruker pålogget.
			return;
		}
	}
	
	public static void main(String args[]) {
		//Main metoden.

		System.out.println("Velkommen til treningsdagbok\n");
		System.out.println("For hjelp skriv inn 'help'");

		
		
		while(!shutoff) {
			waitForUserAction();
		}
		scanner.close();
		System.out.println("Hade bra!");
	}
	
}