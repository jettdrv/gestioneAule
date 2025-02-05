package dati;

/**
 * Classe che contiene le informazioni relative ad una prenotazione come la data, l'ora di inizio, l'ora di fine,
 * il nome del prenotatore, la motivazione e l'aula associata.
 * @author jetad
 * @version 1.0
 *
 */

public class Prenotazione {
	//attributi della classe
	private String data;
	private int oraInizio;
	private int oraFine;
	private String nomePrenotatore;
	private String motivazione;
	private Aula aula;
	
	/**
	 * Costruttore della classe {@code Prenotazione} che prende in ingresso la data, l'ora di inizio e di fine,
	 * il nome del prenotatore, la motivazione e l'aula.
	 * @param data
	 * @param oraInizio
	 * @param oraFine
	 * @param nomePrenotatore
	 * @param motivazione
	 * @param aula
	 */
	
	public Prenotazione(String data, int oraInizio, int oraFine, String nomePrenotatore, String motivazione, Aula aula) {
		this.data = data;
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;
		this.nomePrenotatore = nomePrenotatore;
		this.motivazione = motivazione;
		this.aula = aula;
	}
	
	//getter e setter
	public String getDataPrenotazione(){
		return data;
	}
	
	public int getOraInizio(){
		return oraInizio;
	}
	
	public int getOraFine(){
		return oraFine;
	}
	
	public String getNomePrenotatore(){
		return nomePrenotatore;
	}
	
	public String getMotivazione(){
		return motivazione;
	}
	 
	public Aula getAula() {
		return aula;
	}
	public void setData(String data) {
		this.data = data;
	}
	
	public void setOraInizio(int oraInizio) {
		this.oraInizio = oraInizio;
	}
	
	public void setOraFine(int oraFine) {
		this.oraFine = oraFine;
	}
	
	public void setNomePrenotatore(String nomePrenotatore) {
		this.nomePrenotatore = nomePrenotatore;
	}
	
	public void setMotivazione(String motivazione) {
		this.motivazione = motivazione;
	}
	
	public void setAula(Aula aula) {
		this.aula = aula;
	}
	
	/**
	 * Metodo della classe Object (da cui derivano tutti gli oggetti in Java), che viene sovrascritto per 
	 * restituire una rappresentazione di un oggetto Prenotazione con tutti i contenuti degli attributi.
	 */
	@Override
	public String toString() {
		return (data + "," + oraInizio + "," +oraFine+ "," + nomePrenotatore+","+ motivazione + ","+ aula.getNumeroAula());
	}
}
