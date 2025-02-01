package dati;
/**
 * La classe {@code Laboratorio} è una classe che eredita gli attributi e i metodi della classe astratta {@code Aula}.
 * Oltre agli attributi ereditati, contiene anche informazioni aggiuntive specifiche per questo tipo di aula come
 * la presenza di PC e la presenza delle prese elettriche.
 * @version 1.0
 * 
 */

public class Laboratorio extends Aula {
	//attributi aggiuntivi
	private boolean haPc;
	private boolean haPreseElettriche;
	
	/**
	 * Costruttore della classe {@code Laboratorio} che prende in ingresso il numero dell'aula, la capienza e il tipo 
	 * di aula e li passa al costruttore della classe padre. Inoltre prende in ingresso anche due valori aggiuntivi
	 * boolean che rappresentano la variabile {@code haPc} e {@code haPreseElettriche}.
	 * @param numeroAula
	 * @param capienza
	 * @param tipoAula
	 * @param haPc
	 * @param haPreseElettriche
	 */
	
	public Laboratorio(String numeroAula, int capienza, boolean haPc, boolean haPreseElettriche){
		super(numeroAula, capienza, "Laboratorio");
		
		this.haPc = haPc;
		this.haPreseElettriche = haPreseElettriche;
	}
	
	/**
	 * Metodo ereditato dalla classe Aula, che viene sovrascritto per 
	 * restituire una rappresentazione di un oggetto AulaDidattica con tutti i contenuti degli attributi.
	 */
	@Override
	public String toString() {
		return ("Dettagli aula: \n \t numero aula -> " + getNumeroAula() + 
				" \n \t capienza -> "+ getCapienza() + "\n\t tipo aula -> " + getTipoAula() + "\n\t ha degli PC: " + haPc +
				"\n\t ha delle prese elettriche: " + haPreseElettriche);
	}

}
