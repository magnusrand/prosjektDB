package prosjektDB;

import java.util.Scanner;

public class main{
	
	
	public static void main(String args[]) {
		//TODO
		GetCtrl getController = new GetCtrl();
		RegCtrl registerController = new RegCtrl();
		Scanner scanner = new Scanner(System.in);
		boolean shutoff = false;
		int pnr = -1;
		String name = "placeholder";
		while(!shutoff) {
			pnr = -1;
			
			// Programmet åpner og ber om pnr
			System.out.println("Velkommen til treningsdagbok.\n");
			System.out.println("For hjelp skriv inn 'hjelp'.");
			System.out.println("Skriv inn ditt pnr: ");
			String inputFromUser = scanner.next();
			try {
				pnr = Integer.parseInt(inputFromUser);
				System.out.println("Hva heter du?"); // VET IKKE OM VI SKAL LAGRE NAVN?
				name = scanner.next();
			} catch(IllegalArgumentException e) {
				if(inputFromUser.toLowerCase().equals("hjelp")) {
					System.out.println("Du skrev hjelp.\nHer er følgende kommandoer du kan skrive:\n");
				}
				else {
				System.err.println("Du må skrive et tall");
				}
				}
			System.out.println(pnr);
			if(!(pnr == -1)) {
				System.out.println("Du har logget inn på bruker: " + name);
			}
			
			
			
			//
			
		}
	}
	
}