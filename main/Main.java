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

import javax.swing.JOptionPane;

import dati.*;
import gestionePrenotazioni.*;
import interfacciaGrafica.*;

/**
 * La classe {@code main} contiene il main del programma
 *
 */

public class Main {

	public static void main(String[] args) {
		
		
		
		CaricaAule c = new CaricaAule("src/aule.txt");
		c.stampaLista();
		List <Aula> aule = c.getListaAule();
		
		
		//aule.add(a1);
		
		Aula a1 = aule.get(0);
		Aula a2 = aule.get(1);
		Aula l1 = aule.get(aule.size() -1);
		Prenotazione p1 = new Prenotazione("20/12/2025", 15, 17, "jeta", "esame", a1);
		
	
		Prenotazione p3 = new Prenotazione("24/12/2025", 12, 16, "jeta", "esame", l1);
		
		GestionePrenotazioni g = new GestionePrenotazioni(aule);
		FrameProgramma f = new FrameProgramma(g);
		
		f.setVisible(true);
		
		
		
		if(!g.aggiungiPrenotazione(p1, a1)) {
			System.out.println("Prenotazione non valida");
		}else {
			System.out.println("Prenotazione aggiunta");
			
		}
		
		
		if(!g.aggiungiPrenotazione(p3, l1)) {
			System.out.println("Prenotazione non valida");
		}else {
			System.out.println("Prenotazione aggiunta");
			
		}

		
		g.stampaPrenotazioni();

	}

}
