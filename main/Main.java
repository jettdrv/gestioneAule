/**
 * Lo scopo di questo progetto e' di creare un modo per gestire diverse aule di un edificio. Le aule
 * sono di due tipi: aula didattica e laboratorio. I dati di queste aule vengono memorizzati in un file di testo.
 * L'utente puo' prenotare una data aula se vengono rispettati certi vincoli.
 *
 * @author Jeta Derveni
 * @version 1.0
 * 
 */

package main;

import java.util.*;

import dati.*;
import gestionePrenotazioni.*;

/**
 * La classe {@code main} contiene il main del programma
 * @author jetad
 *
 */

public class Main {

	public static void main(String[] args) {
		List <Aula> aule = new ArrayList<Aula>();
		Aula a1 = new AulaDidattica("a67", 50, "Didattica", true, true);
		
		aule.add(a1);
		
		Prenotazione p1 = new Prenotazione("20.12.2025", 15, 17, "jeta", "esame", a1);
		//p1.toString();
		Prenotazione p2 = new Prenotazione("20.12.2025", 9, 12, "jeta", "esame", a1);
		
		GestionePrenotazioni g = new GestionePrenotazioni(aule);

		if(!g.aggiungiPrenotazione(p1, a1)) {
			System.out.println("Prenotazione non valida");
		}else {
			System.out.println("Prenotazione aggiunta");
			
		}
		
		if(!g.aggiungiPrenotazione(p2, a1)) {
			System.out.println("Prenotazione non valida");
		}else {
			System.out.println("Prenotazione aggiunta");
			
		}
		g.stampaPrenotazioni();
		
		g.cancellaPrenotazione(p2);
		
		g.stampaPrenotazioni();

	}

}
