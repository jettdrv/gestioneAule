package dati;

/**
 * La classe {@code Aula} rappresenta in modo astratto e generale il concetto di aula. 
 * Contiene informazioni come il numero dell'aula (come stringa), la capienza e il tipo di aula.
 * @author jetad
 * @version 1.0
 * 
 */

public abstract class Aula {
	//attributi della classe
	private String numeroAula;
	private int capienza;
	private String tipoAula; 
	
	/**
	 * Costruttore della classe che prende in ingresso una stringa che rappresenta il numero dell'aula,
	 * la capienza dell'aula e il tipo 
	 * @param numeroAula
	 * @param capienza
	 * @param tipoAula
	 */
	
	public Aula(String numeroAula, int capienza, String tipoAula){
		this.numeroAula = numeroAula;
		this.capienza = capienza;
		this.tipoAula = tipoAula;
	}
	
	//getter e setter
	public String getNumeroAula() {
		return numeroAula;
	}
	
	public int getCapienza() {
		return capienza;
	}
	
	public String getTipoAula() {
		return tipoAula;
	}
	
	public void setNumeroAula(String numeroAula) {
		this.numeroAula = numeroAula;
	}
	
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	
	public void setTipoAula(String tipoAula) {
		this.tipoAula = tipoAula;
	}
	
}
