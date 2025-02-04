package interfacciaGrafica;

import java.util.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.format.DateTimeFormatter;
import java.time.*;

/**
 * La classe {@code ScegliData} permette all'utente di selezionare una data che verra' usata dalle altre classi
 * FrameProgramma, PannelloModificaPrenotazione, PannelloAggiungiPrenotazione e modelloTabella. In questo modo
 * l'utente puo' visualizzare solo le prenotazioni relative a questa data. Puo' aggiungere una prenotazione o 
 * modificare una prenotazione esistente in questa data. ScegliData apre una nuova finestra di dialogo
 * che contiene tutti i componenti che ottengono i dati del giorno da selezionare. All'avvio del programma
 * la variabile data (che e' statica), viene inizializzata alla data di oggi ottenuta LocalDateTime.now().
 * Contiene un costruttore che prende in ingresso un JTextField (che visualizza la data nel frame principale. 
 * lo passo come parametro in modo che il cambiamento della data venga sincronizzato subito)e il modello della 
 * tabella(stesso motivo, aggiorno immediatamente la data che viene passata a ModelloTabella).
 * Per ogni ComboBox mostra le alternative giuste in modo che l'utente non aggiunga dati scorretti. Altri metodi
 * implementati sono {@code actionPerformed()}, {@code aggiornaGiorni()} e {@code getData()}.
 */

public class ScegliData extends JDialog implements ActionListener {
	private JLabel anno;
	private JLabel mese;
	private JLabel giorno;
	private JButton seleziona ;
	private int numGiorni;
	
	private JComboBox<String> a;
	private JComboBox<String> m;
	private JComboBox<Integer> g;
	
	private JTextField s;
	private ModelloTabella mo;

	private String[] nomiMesi = {"Gennaio", "Febbraio", "Marzo", "Aprile", "Maggio", 
			"Giugno", "Luglio", "Agosto", "Settembre", "Ottobre", "Novembre", "Dicembre"};
	
	private static String oggi = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	private static String data = oggi;
	
	
	public ScegliData( JTextField s, ModelloTabella mo) {
		
		setSize(400, 400);
		setLayout(new GridLayout(0 ,1));
		this.s = s;
		this.mo = mo;
		
		anno = new JLabel("Anno: ");
		a = new JComboBox<String>();
		for(int i = 25; i <= 50; i++) {
			a.addItem("20"+i);
		}
		
		mese = new JLabel("Mese: ");
		m = new JComboBox<String>();
		for(int i = 0; i<nomiMesi.length; i++) {
			m.addItem(nomiMesi[i]);
		}
		m.addActionListener(this);
		
		giorno = new JLabel("Giorno ");
		g= new JComboBox<Integer> ();
		g.addActionListener(this);
		
		seleziona = new JButton("Seleziona");
		seleziona.addActionListener(new ActionListener() {
			
			/**
			 * Procedura dell' interfaccia ActionListener che definisce il codice da eseguire quando 
			 * si clicca il bottone "Seleziona". In questo caso si definiscono 3 stringhe, gio, me e an
			 * che corrispondono al giorno, mese e anno. Queste tre stringhe verranno concatenate (separate
			 * da un "/" ) per formare la data. Allo stesso tempo si mostra una finestra di messaggio
			 * che visualizza la data inserita confermando l'inserimento corretto. Si invoca setText a s 
			 * immettendo la nuova data e si invoca setDataSelezionata di mo. Alla fine si chiama dispose()
			 * per chiudere la finestra di ScegliData automaticamente e ritornare al frame principale
			 * @param e
			 * @return 
			 */
			@Override
			public void actionPerformed(ActionEvent e) {
				String gio =g.getSelectedItem().toString();
				
				int temp = m.getSelectedIndex() +1;
				String me =String.valueOf(temp);
				
				String an = (String) a.getSelectedItem();
				data = gio+"/"+me+"/"+an;
				
				JOptionPane.showMessageDialog(ScegliData.this, "Data selezionata: " + data, "Conferma", JOptionPane.INFORMATION_MESSAGE);
				//SwingUtilities.invokeLater(() -> {
					s.setText(data);
	        		mo.setDataSelezionata(data);
	        		
				//});
				dispose();
			}
			
		});
		
		add(anno);
		add(a);
		add(mese);
		add(m);
		add(giorno);
		add(g);
		add(seleziona);
	
		}
	
	/**
	 * Procedura dell' interfaccia ActionListener che definisce il codice da eseguire quando 
	 * si si selezionano l'anno e il mese nelle opportune ComboBox in modo da assegnare correttamente il numero
	 * di giorno a numGiorni (che verra' utilizzata dal ciclo for che aggiunge gli elementi della ComboBox 
	 * dei giorni). Si ricava l'indice del mese selezionato e l'anno selezionato (che viene convertito in un intero)
	 * Inoltre si controlla l'indice del mese selezionato e si assegna 31 o 30 al mese opportuno. Viene aggiunto
	 * anche un controllo sull'anno : se l'anno e' bisestile, allora il numero di giorni sara' 29 (febbraio). Poi 
	 * chiama aggiornaGiorni che aggiunge i giorni alla ComboBox g; 
	 * @param e
	 * @return 
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
			int idx = m.getSelectedIndex();
			int annoSelezionato = Integer.valueOf((String)a.getSelectedItem());
		    int giornoSelezionato = (g.getSelectedItem() != null) ? (int) g.getSelectedItem() : 1; // Memorizza il giorno selezionato
			if(idx==0 || idx==2 || idx == 4 || 
					idx==6 || idx == 7 || idx==9 || 
					idx == 11) {
				numGiorni = 31;
			}else if(idx == 1) {
				if(((annoSelezionato%4 == 0 )&& (annoSelezionato %100!=0)) || (annoSelezionato % 400==0))
				{
					numGiorni = 29;
				}
				else {
					numGiorni = 28;
				}
			}else {
				numGiorni = 30;
			}
			
			aggiornaGiorni(numGiorni, giornoSelezionato);
			}		


	/**
	 * Funzione di servizio che in base al numero di giorni passato come parametro, aggiunge i diversi field
	 * della ComboBox. Ad esempio, se il mese selezionato e' marzo, il numero massimo di giorni e' 30 e 
	 * quindi l'utente potra' scegliere la data solo nell'intervallo [1, 30]. Questo per evitare di memorizzare
	 * valori logicamente sbagliati. Se dovessimo scegliere un altro mese, cio' provoca un nuovo ActionEvent,
	 * e quindi il numero massimo di giorni cambiera'. Se la data del giorno selezionato non fa parte dell'intervallo
	 * [1, numGiorni], allora seleziono 1 di default. In caso contrario, mantengo la stessa data.
	 * @param n -> numero massimo di giorni da far visualizzare
	 * @param giornoSelezionato
	 */
	private void aggiornaGiorni(int n, int giornoSelezionato) {
	    g.removeAllItems();
	    for (int i = 1; i <= n; i++) {
	        g.addItem(i);
	    }
	
	    if (giornoSelezionato <= n) {
	        g.setSelectedItem(giornoSelezionato);
	    } else {
	        g.setSelectedItem(1); // 
	    }
	}

/**
 * Funzione static che puo' essere invocata dall'esterno anche se non si ha nessuna istanza della classe ScegliData.
 * Ritorna la data selezionata.	
 */
	public static String getData() {
		return data;
	}
}

	
		
		
