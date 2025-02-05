package caricamentoSalvataggio;

import java.io.*;
import java.util.*;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import dati.*;
import gestionePrenotazioni.*;
/**
 * Questa classe si occupa del caricamento delle prenotazioni da file. Il nome del file viene scelto mediante
 * un JFileChooser, che permette al utente di visualizzare tutti i file disponibili nel suo dispositivo e di 
 * scegliere il file che contiene delle prenotazioni gia' salvate. Utilizza un {@code BufferedReader} per leggere le
 * righe dal file e lo assegna ad una stringa che poi viene tagliata per formare un array temporaneo con i dati 
 * della prenotazione (senza gli spazi bianchi che servono da separatore). La cosa piu' importante e' il modo
 * in cui sono strutturati i file. Si deve avere una visione chiara del modo in cui si vengono memorizzati nel file
 * per poter caricarli correttamente.
 * @author jetad
 *
 */

public class Carica {
	
	private GestionePrenotazioni p;
	private List<Prenotazione> prenotazioni;
	private List<Aula> aule;
	private String nomePrenotatore;
	private String motivazione;
	private String data;
	private int oraInizio;
	private int oraFine;
	private String nrAula;
	private Aula a = null;
	
	private JFileChooser fc;
	
	private FileReader fin = null;
	
	/**
	 * Costruttore della classe {@code Carica} che prende in ingresso una GestionePrenotazioni p. Definisce un {@code JFileChooser}
	 * che permettera' all'utente di scegliere il file da caricare dal file system. Una volta scelto il file
	 * si crea un nuovo oggetto di tipo File. Se la lista di prenotazioni di p non e' vuota, bisogna svuotarlo
	 * per salvare le nuove prenotazioni. Il BufferedReader legge una riga intera del file e lo assegna ad una 
	 * variabile di tipo String. Questa variabile poi verra' "splittata" formando un array che contiene solo
	 * le sottostringhe senza includere la virgola. Ogni elemento dell'array viene assegnato al campo opportuno
	 * di una prenotazione. Si crea un nuovo oggetto di tipo Prenotazione e lo si appende alla lista di prenotazioni
	 * mediante il metodo {@code add()}
	 * 
	 * @param p
	 */
	public Carica(GestionePrenotazioni p) {
			this.p = p;
			prenotazioni = p.getPrenotazioni();
			aule = p.getAule();
			
			fc = new JFileChooser();
			fc.setDialogTitle("Seleziona il file da caricare");
			int sel = fc.showOpenDialog(null);
			
			if(sel == JFileChooser.APPROVE_OPTION) {
				File file = fc.getSelectedFile();
			
				try {
					
					if(p != null) {
						prenotazioni.clear();
					}
					fin = new FileReader(file);
				}catch(FileNotFoundException e ) {
					JOptionPane.showMessageDialog(null, "File non trovato");
					System.exit(3);
				}
				
				BufferedReader b = new BufferedReader(fin);
				String linea = null;
				
				try {
					while((linea = b.readLine())!= null) {
						String [] temp = linea.split(",");
						
						//assegnamenti delle variabili
						data = temp[0];
						oraInizio = Integer.parseInt(temp[1]);
						oraFine = Integer.parseInt(temp[2]);
						nomePrenotatore = temp[3];
						motivazione = temp[4];
						nrAula = temp[5];
						for(Aula t : aule) {
							if(t.getNumeroAula().equals(nrAula)) {
								a = t;
								System.out.println(a.toString());
								break;
							}
						}
						Prenotazione temp2 = new Prenotazione(data, oraInizio, oraFine, nomePrenotatore, motivazione, a);
						prenotazioni.add(temp2);
		
						
					}
					fin.close();
				}catch(IOException e) {
					System.err.println("Errore di input");
					System.exit(4);
				}
			}
	}
	/**
	 * Funzione che restituisce la lista di prenotazioni aggiornata
	 * @return prenotazioni
	 */
	
	public List<Prenotazione> getPrenotazioni(){
		return prenotazioni;
	}

}
