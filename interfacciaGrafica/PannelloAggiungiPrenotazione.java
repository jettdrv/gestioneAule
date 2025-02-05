package interfacciaGrafica;


import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import dati.*;
import gestionePrenotazioni.*;

/**
 * Classe che estende un JFrame. Si occupa dell'aggiunta di una prenotazione e contiene aree di testo e bottoni 
 * opportuni per gestire l'input dell'utente. 
 */

public class PannelloAggiungiPrenotazione extends JFrame{
	
	protected GestionePrenotazioni gestionePrenotazioni;
	protected ModelloTabella modello;
	
	protected int oraInizio;
	protected int oraFine;
	protected String nomePrenotatore;
	protected String motivazione;
	protected Aula aula;
	protected String data;
	
	protected JLabel l2;
	protected JTextField t1;
	protected JLabel l3;
	protected JTextField t2 ;
	protected JTextField testoData;
	protected JLabel d;
	protected JLabel l4;
	protected JLabel l5;
	protected JLabel l6;
	protected JComboBox<Integer> c2;
	protected JComboBox<Integer> c3;
	protected JRadioButton ad;
	protected JRadioButton lab;
	protected JButton conferma;
	protected JPanel panel;
	protected JComboBox <String>comboAule;
	
	protected JButton b2;
	
	/**
	 * Costruttore della classe PannelloAggiungiPrenotazione che inizializza gli attributi delle istanze alle variabili
	 * passate come parametro. Aula viene inizializzata a {@code null}, mentre la data sara' predefinita al momento
	 * dell'apertura della finestra. Questa classe non modifica la data, pero' utilizza quello calcolato
	 * da {@code ScegliData}. Contiene un pannello che include tutti i bottoni, le textField e le comboBox
	 * per l'inserimento dei dati della prenotazione da aggiungere. Per l'aggiunta dell'aula, l'utente deve 
	 * scegliere il tipo di aula tra Aula Didattica e Laboratorio. In base a questa scelta visualizzo le aule. Al 
	 * momento della conferma, il programma crea un'istanza di prenotazione, chiama {@code aggiungiPrenotazione()} 
	 * di gestionePrenotazioni e informa l'utente sullo stato della nuova prenotazione. (se e valida o no).
	 * @param frameProgramma
	 * @param gestionePrenotazioni
	 * @param modello
	 */
	public PannelloAggiungiPrenotazione(JFrame frameProgramma, GestionePrenotazioni gestionePrenotazioni, ModelloTabella modello) {
		super("Aggiungi prenotazione");
		this.gestionePrenotazioni = gestionePrenotazioni;
		this.modello = modello;
		aula = null;
		this.data = ScegliData.getData();
		
		setSize(400, 300);
		setLocationRelativeTo(frameProgramma);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		

/******************************Inserimento dati della prenotazione*****************************************/
		panel = new JPanel(new GridLayout(0, 1));
		    l2 = new JLabel("Nome: ");
		  
		  
	        t1 = new JTextField("");
	        
	        l3 = new JLabel("Motivazione: ");
	        t2 = new JTextField(""); 
	        
	        d = new JLabel("Data: ");
	        testoData = new JTextField(data);
	        testoData.setEditable(false);
	        
	       
	        l4 = new JLabel("Ora inizio: ");
	       
	        c2= new JComboBox<Integer>();
	       
	        l5 = new JLabel("Ora fine: ");
	       
	        c3= new JComboBox<Integer>();
	        
	        
	        for(int i = 8; i<= 18; i++) {
	        	c2.addItem(i);
	        	c3.addItem(i);
	        }
	       
/******************************Selezione aula*************************************************/
	        l6 = new JLabel("Scegli il tipo di aula: ");
	        
	        ButtonGroup g = new ButtonGroup();
	        ad= new JRadioButton("Aula didattica");
	        lab = new JRadioButton("Laboratorio");
	        conferma = new JButton("Conferma");
	        
	        
	        g.add(ad);
	        g.add(lab);
	        
	        comboAule = new JComboBox<String>();
	        conferma.addActionListener(new ActionListener() {
	        	/**
	        	 * Funzione dell'interfaccia {@code ActionListener} che determina il comportamento del programma
	        	 * al momento della premuta del bottone conferma. Se viene selezionato il primo bottone radio, 
	        	 * allora il comboBox delle aule mostrera' all'utente solo i numeri delle aule che sono didattiche.
	        	 * Altrimenti visualizza i numeri dei laboratori.
	        	 * @param e
	        	 */
	        	@Override
	        	public void actionPerformed(ActionEvent e) {
	        		if(ad.isSelected()) {
	        	        for(Aula a : gestionePrenotazioni.getAule()) {
	        	        	if(a.getTipoAula().equals("Didattica")) {
	        	        		comboAule.addItem(a.getNumeroAula());
	        	        	}
	        	        }
	        	        
	        			
	        		}else if(lab.isSelected()) {
	        	        for(Aula a : gestionePrenotazioni.getAule()) {
	        	        	if(a.getTipoAula().equals("Laboratorio"))
	        	        	comboAule.addItem(a.getNumeroAula());
	        	        }
	        		}
	        	}
	        });
	        
	       
	      
	        
/****************Conferma prenotazione***********************************************/
	        b2 = new JButton("Aggiungi");
	        
	        b2.addActionListener(new ActionListener() {
	        	/**
	        	 * Funzione dell'interfaccia {@code ActionListener} che determina il comportamento del programma
	        	 * al momento della premuta del bottone aggiungi. Crea una nuova istanza di {@code Prenotazione}
	        	 * con i dati ottenuti in precedenza e assegna alla variabile {@code aula} l'aula selezionata.
	        	 * In questo modo possiamo chiamare il metodo {@code aggiungiPrenotazione()} di {@code gestionePrenotazioni}
	        	 * ed effettivamente inserire la nuova prenotazione solo se l'aula e' diversa da null. 
	        	 * Se i dati sono validi e la prenotazione e' stata aggiunta con successo, il frame mostrera' JOptionPane
	        	 * con il messaggio  "Prenotazione avvenuta con successo" e chiude la finestra di PannelloAggiungiPrenotazione,
	        	 * altrimenti mostra il messaggio "Prenotazione non riuscita".
	        	 * @param e
	        	 */
				@Override
				public void actionPerformed(ActionEvent e) {
					
					nomePrenotatore = t1.getText();
			        motivazione = t2.getText();
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
				        if(gestionePrenotazioni.aggiungiPrenotazione(p, aula)) {
				        	modello.fireTableDataChanged();
							JOptionPane.showMessageDialog(null, "Prenotazione avvenuta con successo");
							
							dispose();
					    }else {
						JOptionPane.showMessageDialog(null, "Prenotazione non riuscita");
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



	        
	        panel.add(b2);
	       add(panel);
	        
	}
	
}
