package caricamentoSalvataggio;

import java.io.*;
import java.util.*;
import java.util.List;
import javax.swing.*;

import dati.*;
import gestionePrenotazioni.*;
import java.awt.*;

/**
 * Questa classe si occupa del salvataggio delle prenotazioni in un file selezionato. Il nome del file viene scelto dall'utente
 * quando si clicca il bottone del salvataggio nel frame principale. A questo nome si aggiunge anche l'estensione ".txt" e 
 * viene passato al costruttore. Durante il salvataggio del progresso, e' importante tenere in mente il modo in cui
 * i dati devono essere rappresentati nel file in modo che sia piu' facile caricarli di nuovo. In questo caso
 * ho semplicemente scelto di scorrere la lista di prenotazioni mediante un foreach e chiamare il metodo
 * {@code toString()} della classe Prenotazione. La stringa ritornata contiene ogni campo dell'oggetto Prenotazione 
 * separato da una virgola.
 * @author jetad
 *
 */
public class Salva {
	
	GestionePrenotazioni p;
	List<Prenotazione> prenotazioni;
	
	private FileWriter fout;
	


	/**
	 * Costruttore della classe {@code Salva}. Gestisce anche il caso in cui venga inserito un file gia' esistente
	 * nella directory corrente e informa l'utente di questa esistenza. Inoltre chiede all'utente se vorrebbe
	 * sovrascrivere il file esistente (e percio' si continua con il salvataggio) o no (abortire il salvataggio).
	 * @param p
	 * @param nomeFile
	 */
	public Salva(GestionePrenotazioni p, String nomeFile) {

		File file = new File(nomeFile);
		
		if(file.exists()) {
			int scelta = JOptionPane.showConfirmDialog(null, "Il file esiste già. Desidera sovrascriverlo?" , "Conferma sovrascrittura",
					JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
			if(scelta != JOptionPane.YES_OPTION) {
				return;
			}
		}
		try {
			this.p = p;

			prenotazioni = p.getPrenotazioni();
			fout = new FileWriter(nomeFile);
		}catch(IOException e ) {
			System.err.println("Apertura file fallita");
		}
		String linea = null;
		try {
			for(Prenotazione k : prenotazioni) {
				linea = k.toString() + "\n";
				
				fout.write(linea);
					
			}
			JOptionPane.showMessageDialog(null, "Prenotazioni salvate con successo!");
			fout.close();
		}catch(IOException e) {
			JOptionPane.showMessageDialog(null, "Errore durante il salvataggio" );

		}
		
		
	}
	}
	

