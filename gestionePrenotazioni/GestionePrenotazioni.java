package gestionePrenotazioni;
import java.util.*;

import dati.*;

/**
 * La classe {@code GestionePrenotazioni} gestisce la prenotazione delle aule seguendo i vincoli predefiniti.
 * Contiene un ArrayList per le aule che vengono caricate da file e un Arraylist con tutte le prenotazioni.
 * Ho scelto di utilizzare un ArrayList per memorizzare le aule e le prenotazioni perche' posso aggiungere e rimuovere
 * elementi senza problemi siccome la struttura dati non ha una dimensione fissa. Il tempo di accesso agli elementi
 * e' lineare siccome si accedono direttamente utilizzando gli indici. L'unico contro e' la ricerca degli elementi
 * che richiede il confronto di ciascun elemento dell'array.
 * La classe aggiunge, modifica o cancella una data prenotazione. 
 * @author jetad
 * @version 1.0
 */

public class GestionePrenotazioni {
	//attributi della classe
	private List <Aula> aule;
	private List <Prenotazione> prenotazioni;
	
	/**
	 * Costruttore della classe che prende in ingresso una lista di aule e la assegna alla variabile {@code aule}
	 * Inoltre, crea l'ArrayList vuoto delle prenotazioni
	 * @param aule
	 */
	
	public GestionePrenotazioni(List <Aula> aule) {
		this.aule = aule;
		prenotazioni = new ArrayList<Prenotazione>();
	}
	
	/**
	 * Metodo che aggiunge la prenotazione passata come parametro solo se rispetta i vincoli. Questi vincoli 
	 * vengono controllati mediante le funzioni di servizio {@code controlloVincoliGenerali} e 
	 * {@code controlloVincoliAula}. Se il valore di ritorno di queste funzioni e' {@code true} allora 
	 * proseguo con l'inserimento della prenotazione dell'ArrayList {@code prenotazioni}. In caso contrario
	 * ritorno false e non effettuo nessuna modifica.
	 * @param prenotazione
	 * @param aula
	 * @return {@code true} se l'aggiunta della prenotazione ha avuto successo, {@code false} altrimenti
	 */
	
	public boolean aggiungiPrenotazione(Prenotazione prenotazione, Aula aula) {
		
		//controllo se vengono rispettati gli orari di apertura dell'edificio
		
		if(!controlloVincoliGenerali(prenotazione)) {
			return false;
		}
		
		//controllo se l'aula è disponibile 
		if(!isAulaDisponibile(aula, prenotazione.getDataPrenotazione(), prenotazione.getOraInizio(), prenotazione.getOraFine())) {
			System.out.println("Aula non disponibile per l'orario indicato");
			return false;
		}
		
		//controllo dei vincoli per ogni tipo di aula
		if(!controlloVincoliAula(prenotazione, aula)) {
			return false;
		}
		
		prenotazioni.add(prenotazione);
		prenotazione.setAula(aula);
		return true;
	}
	
	/**
	 * Metodo che modifica la prenotazione passata come parametro {@code vecchiaPrenotazione} in una nuova
	 * prenotazione {@code nuovaPrenotazione} solo se rispetta i vincoli. Questi vincoli 
	 * vengono controllati mediante le funzioni di servizio {@code controlloVincoliGenerali} e 
	 * {@code controlloVincoliAula}. Cancello la vecchia prenotazione tramite il metodo {@code .remove()}
	 * dell'ArrayList. Se il valore di ritorno di queste funzioni di servizio e' {@code true} allora 
	 * proseguo con l'inserimento della nuova prenotazione dell'ArrayList {@code prenotazioni}. In caso contrario
	 * ritorno false e aggiungo di nuovo la vecchia prenotazione.
	 * @param vecchiaPrenotazione
	 * @param nuovaPrenotazione
	 * @param aula
	 * @return {@code true} se la modifica della prenotazione ha avuto successo, {@code false} altrimenti
	 */
	
	
	public boolean modificaPrenotazione(Prenotazione vecchiaPrenotazione, Prenotazione nuovaPrenotazione, Aula nuovaAula) {
		if(!prenotazioni.contains(vecchiaPrenotazione)) {
			return false; //la prenotazione inserita non esiste
		}
		
		prenotazioni.remove(vecchiaPrenotazione);
		
		if(!isAulaDisponibile(nuovaAula, nuovaPrenotazione.getDataPrenotazione(), nuovaPrenotazione.getOraInizio(), nuovaPrenotazione.getOraFine())) {
			prenotazioni.add(vecchiaPrenotazione);
			return false;
		}else {
			if(controlloVincoliGenerali(nuovaPrenotazione) && controlloVincoliAula(nuovaPrenotazione, nuovaAula)) {
				prenotazioni.add(nuovaPrenotazione);
				nuovaPrenotazione.setAula(nuovaAula);
				return true;
			}
			else {
				return false;
			}
		}
		
		
	}
	
	/**
	 * Metodo che cancella una data prenotazione dalla lista.
	 * @param prenotazione
	 */
	
	public boolean cancellaPrenotazione(Prenotazione prenotazione) {
		
		if(!prenotazioni.contains(prenotazione)) {
			return false; //la prenotazione inserita non esiste
		}
		
		if(prenotazioni.remove(prenotazione))
			{
				System.out.println("Prenotazione cancellata con successo ");
				return true;
			}
		else {
			System.out.println("Cancellazione della prenotazione non effettuata ");
			return false;
		}
	}
	
	/**
	 * Metodo che stampa tutte le prenotazioni effettuate seguendo l'ordine di inserimento
	 */
	public void stampaPrenotazioni() {
	
		int i=1;
			for(Prenotazione p : prenotazioni) {
				
				System.out.print(i + ") ");
				System.out.println(p.toString());
				i++;
			}
	}
	
	//getter
	
	public List<Prenotazione> getPrenotazioni(){
		return prenotazioni;
	}
	
	public List<Aula> getAule(){
		return aule;
	}
	
	
	
/***********************************************************************************
	                              Metodi privati
***********************************************************************************/
	
	
	/**funzione di servizio che controlla se una data aula è disponibile. Funzione 
	 * utilizzata dal metodo aggiungiPrenotazione e modificaPrenotazione
	 * @param aula
	 * @param data
	 * @param oraInizio
	 * @param oraFine
	 * @return {@code true} se l'aula e' disponibile, altrimenti {@code false}
	 */
	private boolean isAulaDisponibile(Aula aula, String data, int oraInizio, int oraFine ) {
		for(Prenotazione p : prenotazioni) {
			if(p.getAula().equals(aula)) {
				if(p.getDataPrenotazione().equals(data)) {
					if(p.getOraFine()>oraInizio && p.getOraInizio()<oraFine) {
						return false;
					}
				}
			}
		}
		return true;
	}
	
	/**funzione di servizio che controlla se l'orario della prenotazione inserita rispetta 
	 * i vincoli generali (cioe l'ora di inizio deve essere minore dell'ora di fine e entrambe 
	 * devono essere dentro i limiti dell'orario di apertura)
	 * @param prenotazione
	 * @return {@code true} se vengono rispettati tutti i vincoli generali, {@code false} altrimenti
	 */
	private boolean controlloVincoliGenerali(Prenotazione prenotazione) {
		if(!(prenotazione.getOraInizio() < prenotazione.getOraFine())) {
			return false;
		}
		
		if(prenotazione.getOraInizio()< 8 || prenotazione.getOraFine()> 18) {
			return false;
		}
		
		return true;
	}
	/**funzione di servizio che controlla i vincoli per ogni tipo di aula. Prima calcola la durata della 
	 * prenotazione, poi verifica se l'aula inserita e' un istanza della classe {@code AulaDidattica} o {@code Laboratorio}.
	 * Nel primo caso si deve controllare se la durata della prenotazione e' minore di 1 ora o maggiore di 8.
	 * Nel secondo caso si verifica se la durata della prenotazione e' di 2 o 4 ore. Se questi vincoli non 
	 * vengono rispettati, ritorna false.
	 * @param prenotazione
	 * @param aula
	 * @return {@code true} se vengono rispettati tutti i vincoli specifici per ogni aula, {@code false} altrimenti
	 */
	
	private boolean controlloVincoliAula(Prenotazione prenotazione, Aula aula) {
		int durataPrenotazione = prenotazione.getOraFine() - prenotazione.getOraInizio();
		if(aula instanceof AulaDidattica) {
			
			if(durataPrenotazione < 1 || durataPrenotazione > 8)
				return false;
		}
		
		if(aula instanceof Laboratorio) {
			
			if(durataPrenotazione!=2 && durataPrenotazione != 4)
				return false;
		}
		return true;
	}
	
	
	
}
