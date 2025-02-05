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
import caricamentoSalvataggio.*;

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
		
		
		GestionePrenotazioni g = new GestionePrenotazioni(aule);
		FrameProgramma f = new FrameProgramma(g);
		
		f.setVisible(true);
		

		
		g.stampaPrenotazioni();
		
		

	}

}
