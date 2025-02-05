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
import java.awt.event.KeyEvent;

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
/*********************Tabella*************************************/
        JPanel pannelloTabella = new JPanel(new BorderLayout());
        tabellaPrenotazioni = new JTable(modello);
        tabellaPrenotazioni.setCellSelectionEnabled(true);
        tabellaPrenotazioni.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        
        JScrollPane scrollPane = new JScrollPane(tabellaPrenotazioni);
        
        tabellaPrenotazioni.setDefaultRenderer(Object.class, new CelleColorate());
        
        
        //******************header**********************************************************************
        JPanel header = new JPanel();
        header.setBackground(Color.pink.darker());
        header.setBorder(BorderFactory.createRaisedBevelBorder());
        header.setPreferredSize(new Dimension(800, 65)); 
        JLabel nomeUniversita = new JLabel("Università di Pippo");
        nomeUniversita.setFont(new Font("Serif", Font.BOLD, 24)); 
        nomeUniversita.setAlignmentX(Component.CENTER_ALIGNMENT); 
        
        
        //*******************titolo********************************************************
        
        JPanel header2 = new JPanel();
        header2.setBackground(Color.pink.brighter());
        JLabel titolo = new JLabel("Prenotazione aule", JLabel.LEFT);
        header2.add(titolo);
       
        //**********selezione giorno************************************************
        
        JPanel pannelloData = new JPanel();
        pannelloData.setLayout(new FlowLayout());
        
        JLabel showData = new JLabel(data);
        
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
/********************************Menu Bar**********************************************/
        JMenuBar menuBar;
        JMenu menu1;
        JMenu menu2;
        
        JMenuItem salva = new JMenuItem("Salva", KeyEvent.VK_T);
        salva.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.ALT_MASK));
        JMenuItem carica = new JMenuItem("Carica", KeyEvent.VK_T);
        carica.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.ALT_MASK));
        
        JMenuItem aggiungiPrenotazione = new JMenuItem("Aggiungi prenotazione", KeyEvent.VK_T);
        aggiungiPrenotazione.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, ActionEvent.CTRL_MASK));
        JMenuItem modificaPrenotazione  = new JMenuItem("Modifica prenotazione ", KeyEvent.VK_T);
        modificaPrenotazione.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, ActionEvent.CTRL_MASK));
        JMenuItem cancellaPrenotazione  = new JMenuItem("Cancella prenotazione", KeyEvent.VK_T);
        cancellaPrenotazione.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, ActionEvent.CTRL_MASK));
        
        menuBar = new JMenuBar();
        menu1 = new JMenu("File");
        menu2 = new JMenu("Edit");
        menuBar.add(menu1);
        menuBar.add(menu2);
      //Primo menu per il caricamento o salvataggio delle prenotazioni  
        salva.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {

        		String nomeFile = JOptionPane.showInputDialog(null, "Inserisci il nome del file:", "Salvataggio", JOptionPane.PLAIN_MESSAGE);
        	
        		Salva salvaPrenotazioni = new Salva(gestionePrenotazioni,  nomeFile.trim() + ".txt");
        	
        	}
        });
        
        carica.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		Carica caricaPrenotazioni = new Carica(gestionePrenotazioni);
        		prenotazioni = caricaPrenotazioni.getPrenotazioni();
        	}
        });
        menu1.add(salva);
        menu1.add(carica);
        
    //secondo menu per l'aggiunta, modifica o cancellamento delle prenotazioni    
        aggiungiPrenotazione.addActionListener(new ActionListener() {
        	@Override
        	public void actionPerformed(ActionEvent e) {
        		new PannelloAggiungiPrenotazione(FrameProgramma.this, gestionePrenotazioni, modello).setVisible(true);
        	}
        });
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
        
        menu2.add(aggiungiPrenotazione);
        menu2.add(modificaPrenotazione);
        menu2.add(cancellaPrenotazione);
        
        
        this.setJMenuBar(menuBar);
/*****************Dettagli Aule***************************************************/
        JPanel dettagliAule= new JPanel();
        dettagliAule.setLayout(new GridLayout(1, 0));
        JButton vediDettagli = new JButton("Vedi dettagli dell'aula");
        
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
        pannelloTabella.add(dettagliAule, BorderLayout.SOUTH);
        
        add(header);
        add(header2);
        add(pannelloData);
        
        add(pannelloTabella);
   
	}
	
	
}


