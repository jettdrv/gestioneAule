package interfacciaGrafica;


import javax.swing.table.*;
import dati.*;
import gestionePrenotazioni.*;
/**
 * La classe {@code ModelloTabella} è la classe che implementa il modello dei dati della tabella principale.
 * Ha tre attributi: un oggetto del tipo gestionePrenotazione, un vettore di stringhe che contiene il nome dei mesi
 * e una stringa che rappresenta la data che viene selezionata dall'utente. Questa variabile viene definita nella
 * classe ScegliData ed e' di tipo static. 
 * @version 2.0
 * 
 */



public class ModelloTabella extends AbstractTableModel{
	
	private final GestionePrenotazioni gestionePrenotazioni;
	private final String[] colNome;
	private String data;
	
	/**
	 * Costruttore della classe che prende in ingresso l'oggetto gestionePrenotazioni e la data calcolata 
	 * dalla classe {@code ScegliData}. Questi parametri vengono assegnati alle variabili della classe. 
	 * Inoltre, il costruttore assegna agli nomi delle colonne il numero delle aule ottenuto dalla lista
	 * di aule che ha gestionePrenotazioni
	 * @param gestionePrenotazioni
	 * @param data
	 */
	public ModelloTabella(GestionePrenotazioni gestionePrenotazioni, String data) {
		this.gestionePrenotazioni = gestionePrenotazioni;
		
		this.colNome = new String[gestionePrenotazioni.getAule().size() + 1]; //aggiungo 1 perche' una colonna contiene i nomi delle righe
        this.colNome[0] = "Ora";
        for (int i = 0; i < gestionePrenotazioni.getAule().size(); i++) {
            this.colNome[i + 1] = gestionePrenotazioni.getAule().get(i).getNumeroAula();
        }
        this.data = data;
	}
	/**
	 * Funzione della classe astratta AbstractTableModel che restituisce il numero di colonne della tabella
	 * @return colNme.length
	 */
	public int getColumnCount(){return colNome.length;}
	
	/**
	 * Funzione della classe astratta AbstractTableModel che restituisce il numero di righe della tabella. 
	 * Siccome gli unici valori ammissibili per l'ora sono nell'intervallo 8-18, e' giusto che si mostrino solo
	 * questi orari
	 * @return 10
	 */
	public int getRowCount() {return 10;}
	
	/**
	 * Funzione della classe astratta AbstractTableModel che restituisce il valore che si trova nella cella con 
	 * indice di riga row ed indice di colonna col. Nel nostro caso, nella prima colonna dobbiamo mostrare
	 * solo gli intervalli di tempo, mentre nelle altre celle mostriamo i dettagli delle prenotazioni possibili.
	 * Se non esiste nessuna prenotazione nella data scelta per una certa aula, la cella della tabella sara' vuota.
	 * @param row  indice della riga
     * @param col indice della colonna
     * @return "Prenotato" se esiste una prenotazione per l'aula selezionata nella ora selezionata, null altrimenti
	 */
	@Override
	public Object getValueAt(int row, int col) {
		
		if (col == 0) {
            return ((row + 8) + ":00-" + (row + 9) + ":00");
        } else {
        	Aula a = gestionePrenotazioni.getAule().get(col-1);
        	int ora = row + 8;
        	
        	 for (Prenotazione p : gestionePrenotazioni.getPrenotazioni()) {
                 if ((p.getDataPrenotazione().equals(data))&&(p.getAula().equals(a)) && (p.getOraInizio() <= ora) && (p.getOraFine() > ora)) {
                	 return ("Prenotato");
                 }
        	 }
        
        }
		return null;
	}
	
	/**
	 * Procedura che aggiorna il valore della data a quello piu' recente in modo che si possa sincronizzare il cambiamento
	 * e il modello possa cambiare e mostrare i dati per il giorno selezionato
	 * @param data
	 */
	
	public void setDataSelezionata(String data) {
        this.data = data;
        fireTableDataChanged(); // 
    }
	
	/**
	 * Funzione che restituisce il nome della colonna con indice col
	 * @param col indice della colonna
	 * @return colNome[col] il nome della colonna selezionata
	 * 
	 */
    public String getColumnName(int col) {
        return colNome[col];
    }

    /**
     * Imposta le celle a celle non modificabili dall'utente
     * @param row  indice della riga
     * @param col indice della colonna
     * @return false
     */
	public boolean isCellEditable(int row, int col) {
		return false;
	}
	

}
