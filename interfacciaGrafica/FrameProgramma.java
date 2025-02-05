package interfacciaGrafica;

import java.util.List;

/**
 * La classe {@FrameProgramma} è la classe che implementa la finestra principale del programma. Contiene tutti
 * i pannelli che rendono funzionabile il programma a livello di interfaccia grafica. Estende JFrame e dichiara
 * il tipo di LayoutManager come BoxLayout
 * @version 2.0
 * 
 */



import javax.swing.*;
import javax.swing.table.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import gestionePrenotazioni.*;
import dati.*;
import interfacciaGrafica.*;
import caricamentoSalvataggio.*;


/**
 * Il frame principale del programma che viene visualizzato dall'utente immediatamente all'avvio del programma.
 * Contiene diversi pannelli, compreso l'header, un pannello per la scelta della data, un pannello per la scelta 
 * dell'operazione da effettuare e un pannello per la visualizzazione dei dettagli di un'aula.
 * @author jetad
 *
 */
public class FrameProgramma extends JFrame {
	
	private GestionePrenotazioni gestionePrenotazioni;
	private JTable tabellaPrenotazioni;
	private ModelloTabella modello;
	
	
	private String data;
	private List <Prenotazione> prenotazioni;

	private Aula aula;
	
	public FrameProgramma(GestionePrenotazioni gestionePrenotazioni) {
		
		this.gestionePrenotazioni = gestionePrenotazioni;
		this.modello = new ModelloTabella(gestionePrenotazioni, data);
		
		prenotazioni = gestionePrenotazioni.getPrenotazioni();
		List<Aula> aule = gestionePrenotazioni.getAule();
		aula = gestionePrenotazioni.getAule().get(0);
		data = ScegliData.getData();
		
		setTitle("Prenotazione aule");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        
        setLayout(new BoxLayout(getContentPane(), BoxLayout.PAGE_AXIS));
        
        tabellaPrenotazioni = new JTable(modello);
        tabellaPrenotazioni.setCellSelectionEnabled(true);
        tabellaPrenotazioni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        //tabellaPrenotazioni.addColumnSelectionInterval(0, tabellaPrenotazioni.getColumnCount() -1);
        
        JScrollPane scrollPane = new JScrollPane(tabellaPrenotazioni);
        
        tabellaPrenotazioni.setDefaultRenderer(Object.class, new CelleColorate());
        
        
        //******************header**********************************************************************
        JPanel header = new JPanel();
        //header.setBounds(0, 0, 1300, 60);
        header.setBackground(Color.pink.darker());
        header.setBorder(BorderFactory.createRaisedBevelBorder());
        header.setPreferredSize(new Dimension(800, 65)); 
        JLabel nomeUniversita = new JLabel("Università di Pippo");
        nomeUniversita.setFont(new Font("Serif", Font.BOLD, 24)); // Aumenta la dimensione del font
        nomeUniversita.setAlignmentX(Component.CENTER_ALIGNMENT); // Centra il testo orizzontalmente
        header.add(nomeUniversita);
        
        
        //*******************titolo********************************************************
        
        JPanel header2 = new JPanel();
        //header2.setBounds(0, 60, 1300, 40);
        header2.setBackground(Color.pink.brighter());
        //header2.setBorder(BorderFactory.createRaisedBevelBorder());
        JLabel titolo = new JLabel("Prenotazione aule", JLabel.LEFT);
        header2.add(titolo);
       
        //**********selezione giorno************************************************
        
        JPanel pannelloData = new JPanel();
        pannelloData.setLayout(new FlowLayout());
        
        JTextField showData = new JTextField(data);
        showData.isEditable();
        showData.setEditable(false);
        
        JButton scegliData = new JButton("Scegli la data");
        scegliData.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		ScegliData calendario = new ScegliData(showData, modello);
        		calendario.setVisible(true);
        		
        		
        	}
        });
        
        pannelloData.add(scegliData);
        pannelloData.add(showData);
        
        
        //********************************contenuti programma********************
        JPanel pannelloCentrale = new JPanel(new SpringLayout());
        pannelloCentrale.setLayout(new BoxLayout(pannelloCentrale, BoxLayout.LINE_AXIS));
        
        
        
        //pannello che contiene i bottoni per la gestione delle prenotazioni
        JPanel pannelloForm = new JPanel();
        pannelloForm.setBackground(Color.white);
        
        pannelloForm.setLayout(new GridLayout(0 ,1));
        
        //*********bottone aggiunta prenotazione*******************************
        JButton aggiungiPrenotazione = new JButton("Aggiungi una prenotazione");
        aggiungiPrenotazione.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new PannelloAggiungiPrenotazione(FrameProgramma.this, gestionePrenotazioni, modello).setVisible(true);
        	}
        });
      

        pannelloForm.add(aggiungiPrenotazione);
        
        
        //*******************************************************************
        
        JPanel pannelloTabella = new JPanel(new BorderLayout());
        JButton modificaPrenotazione = new JButton("Modifica una prenotazione esistente");
        
        

        modificaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabellaPrenotazioni.getSelectedRow(); //ora
                int oraSelezionata = selectedRow + 8;
                int selectedColumn = tabellaPrenotazioni.getSelectedColumn(); //indice aula
                
                Prenotazione prenotazioneSelezionata = null;
                for(Prenotazione p : gestionePrenotazioni.getPrenotazioni()) {
                	if ((p.getOraInizio() == oraSelezionata )&& (p.getAula().equals(aule.get(selectedColumn -  1)))){
                		prenotazioneSelezionata = p;
                		break;
                	}
                }
                
                if (prenotazioneSelezionata !=null) {
      
                   
                    new PannelloModificaPrenotazione(FrameProgramma.this, gestionePrenotazioni, modello, prenotazioneSelezionata).setVisible(true);
                    //tabellaPrenotazioni.clearSelection();
                } else {
                    JOptionPane.showMessageDialog(FrameProgramma.this, "Seleziona una prenotazione da modificare.");
                }
            }
        });

        pannelloForm.add(modificaPrenotazione);
        
        
        JButton cancellaPrenotazione = new JButton("Cancella prenotazione");
        cancellaPrenotazione.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = tabellaPrenotazioni.getSelectedRow(); //ora
                int oraSelezionata = selectedRow + 8;
                int selectedColumn = tabellaPrenotazioni.getSelectedColumn(); //indice aula
                
                Prenotazione prenotazioneSelezionata = null;
                for(Prenotazione p : gestionePrenotazioni.getPrenotazioni()) {
                	if ((p.getOraInizio() == oraSelezionata )&& (p.getAula().equals(aule.get(selectedColumn -  1)))){
                		prenotazioneSelezionata = p;
                		break;
                	}
                }
                
                if (prenotazioneSelezionata !=null) {
                	if(gestionePrenotazioni.cancellaPrenotazione(prenotazioneSelezionata)) {
                		modello.fireTableDataChanged();
						JOptionPane.showMessageDialog(null, "Prenotazione cancellata");
						
                	}
                 
                } else {
                    JOptionPane.showMessageDialog(FrameProgramma.this, "Seleziona una prenotazione da cancellare.");
                }
            }
        });
        
        pannelloForm.add(cancellaPrenotazione);
/***********************Salva Prenotazioni**********************************/        
        JPanel salvataggio = new JPanel();
        
        JButton s1 = new JButton("Salva");
        
       
        s1.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {

        		String nomeFile = JOptionPane.showInputDialog(null, "Inserisci il nome del file:", "Salvataggio", JOptionPane.PLAIN_MESSAGE);
        	
        		Salva salvaPrenotazioni = new Salva(gestionePrenotazioni, "src/" + nomeFile.trim() + ".txt");
        	
        	}
        });
        
        salvataggio.add(s1);
/***************Carica Prenotazioni****************************************/
        JButton s2 = new JButton("Carica");
        s2.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Carica caricaPrenotazioni = new Carica(gestionePrenotazioni);
        		prenotazioni = caricaPrenotazioni.getPrenotazioni();
        	}
        });
        
        salvataggio.add(s2);
        
/**********************************************************************************/
        JPanel dettagliAule= new JPanel();
        dettagliAule.setLayout(new GridLayout(1, 0));
        JButton vediDettagli = new JButton("Vedi dettagli");
        
        JTextArea a = new JTextArea(" ");
        
        vediDettagli.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		int selectedColumn = tabellaPrenotazioni.getSelectedColumn();
        		Aula aulaSelezionata = aule.get(selectedColumn +1);
        		a.setText(aulaSelezionata.toString()); 
        	}
        });
        
    
        dettagliAule.add(vediDettagli);
        dettagliAule.add(a);
        
        pannelloTabella.add(scrollPane, BorderLayout.NORTH);
        pannelloTabella.add(salvataggio, BorderLayout.CENTER);
        pannelloTabella.add(dettagliAule, BorderLayout.SOUTH);
        
        
        pannelloCentrale.add(pannelloForm);
        pannelloCentrale.add(pannelloTabella);
    
        
        
        add(header);
        add(header2);
        add(pannelloData);
        
        add(pannelloCentrale);
   
	}
	
	
}


