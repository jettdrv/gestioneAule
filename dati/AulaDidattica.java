package dati;
/**
 * La classe {@code AulaDidattica} e' una classe che eredita gli attributi e i metodi della classe astratta {@code Aula}.
 * Oltre agli attributi ereditati, contiene anche informazioni aggiuntive specifiche per questo tipo di aula come
 * la presenza di una lavagna e la presenza di un videoproiettore.
 * @version 1.0
 * 
 */

public class AulaDidattica extends Aula {
	//attributi aggiuntivi
	private boolean haLavagna;
	private boolean haVideoproiettore;
	
	/**
	 * Costruttore della classe {@code AulaDidattica} che prende in ingresso il numero dell'aula, la capienza e il tipo di aula
	 * (che saranno passati al costruttore della classe padre) e inoltre anche due variabili boolean.
	 * @param numeroAula
	 * @param capienza
	 * @param tipoAula
	 * @param haLavagna
	 * @param haVideoproiettore
	 */
	
	public AulaDidattica(String numeroAula, int capienza, boolean haLavagna, boolean haVideoproiettore) {
		super(numeroAula, capienza,"Didattica");
		this.haLavagna = haLavagna;
		this.haVideoproiettore = haVideoproiettore;
		
	}
	
	/**
	 * Metodo ereditato dalla classe Aula, che viene sovrascritto per 
	 * restituire una rappresentazione di un oggetto AulaDidattica con tutti i contenuti degli attributi.
	 */
	@Override
	public String toString() {
		return ("Dettagli aula: \n \t numero aula -> " + getNumeroAula() + 
				" \n \t capienza -> "+ getCapienza() + "\n\t tipo aula -> " + getTipoAula() + "\n\t ha una lavagna: " + haLavagna +
				"\n\t ha un videoproiettore: " + haVideoproiettore);
	}

}
