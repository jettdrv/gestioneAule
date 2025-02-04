package interfacciaGrafica;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

import dati.*;
import gestionePrenotazioni.*;

/**
 * Classe che estende PannelloAggiungiPrenotazioni ereditando tutti i suoi campi e i suoi metodi.(ho aggiunto il 
 * modificatore di accesso protected in modo che anche questa classe possa manipolare gli stessi campi) 
 * Ho scelto questa implementazione per evitare di riscrivere lo stesso codice due volte. L'unica cosa 
 * che cambia nel costruttore di questa classe e' l'inizializzazione di alcune componenti del pannello con i contenuti
 * gia' scelti nella prenotazione da modificare. Cambia anche il bottone b2 che lo nascondiamo e aggiungiamo un 
 * nuovo bottone conferma che chiama il metodo modificaPrenotazione(). 
 */


public class PannelloModificaPrenotazione extends PannelloAggiungiPrenotazione{
	

	private Prenotazione prenotazione;
	
	/**
	 * Costruttore della classe che a differenza del costruttore di PannelloAggiungiPrenotazione, prende in ingresso
	 * un argomento in piu', ossia prenotazione (cioe' la prenotazione che stiamo modificando). Infine aggiunge
	 * al pannello {@code panel} tutte le componenti in ordine(tranne b2 dell'aggiunta che lo rendiamo non visibile) -
	 * @param frameProgramma
	 * @param gestionePrenotazioni
	 * @param modello
	 * @param prenotazione
	 */
	public PannelloModificaPrenotazione(JFrame frameProgramma, GestionePrenotazioni gestionePrenotazioni,
			ModelloTabella modello, Prenotazione prenotazione ) {
		super(frameProgramma, gestionePrenotazioni, modello);
		this.prenotazione= prenotazione;

/******************************Aggiornamento dati della prenotazione*****************************************/
	
		  	t1.setText(prenotazione.getNomePrenotatore());
	        t2.setText(prenotazione.getMotivazione()); 
	        
	        testoData.setText(prenotazione.getDataPrenotazione());
	        testoData.setEditable(false);
	     
	        c2.setSelectedItem(prenotazione.getOraInizio());
	        c3.setSelectedItem(prenotazione.getOraFine());

/****************Conferma Modifica******************************************/
		b2.setVisible(false);
	       JButton b3 = new JButton("Conferma modifica");
	        b3.addActionListener(new ActionListener() {
	        	/**
	        	 * Funzione dell'interfaccia {@code ActionListener} che determina il comportamento del programma
	        	 * al momento della premuta del bottone "conferma modifica". Crea una nuova istanza di {@code Prenotazione}
	        	 * con i nuovi dati inseriti e assegna alla variabile {@code aula} la nuova aula selezionata.
	        	 * In questo modo possiamo chiamare il metodo {@code modificaPrenotazione()} di {@code gestionePrenotazioni}
	        	 * e sostituire la vecchia prenotazione con la nuova. I controlli vengono effettuati da questo metodo.
	        	 * Se la prenotazione e' stata modificata con successo, il frame mostrera' JOptionPane
	        	 * con il messaggio  "Prenotazione modificata" e chiude la finestra di PannelloAggiungiPrenotazione,
	        	 * altrimenti mostra il messaggio "Dati non validi".
	        	 * @param e
	        	 */
				@Override
				public void actionPerformed(ActionEvent e) {
					
					nomePrenotatore = t1.getText().trim();
			        motivazione = t2.getText().trim();
			        oraInizio = (int)c2.getSelectedItem();
			        oraFine = (int)c3.getSelectedItem();
			        String nrAulaSelezionata = (String)comboAule.getSelectedItem();
	
			        
			        for(Aula a: gestionePrenotazioni.getAule()) {
			        	if(a.getNumeroAula().equals(nrAulaSelezionata)) {
			        		aula = a;
			        		break;
			        	}
			        }
			        
			        if(aula!=null) {
						Prenotazione p = new Prenotazione(data, oraInizio, oraFine, nomePrenotatore, motivazione, aula);
				        if(gestionePrenotazioni.modificaPrenotazione(prenotazione, p, aula)) {
				        	modello.fireTableDataChanged();
							JOptionPane.showMessageDialog(null, "Prenotazione modificata");
							
							dispose();
					    }else {
						JOptionPane.showMessageDialog(null, "Dati non validi");
					}
			        
			        }
					
				}
	        });
	        panel.add(l2);
	        panel.add(t1);
	        panel.add(l3);
	        panel.add(t2);
	        panel.add(d);
	        panel.add(testoData);
	        panel.add(l4);
	        panel.add(c2);
	        panel.add(l5);
	        panel.add(c3);
	        panel.add(l6);
	        panel.add(ad);
	        panel.add(lab);
	        panel.add(conferma);
	        panel.add(comboAule);
	        
	        panel.add(b3);
	       
	       add(panel);
	       
	}
	
}
