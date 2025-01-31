package dati;
/**
 * La classe {@code AulaDidattica} è una classe che eredita gli attributi e i metodi della classe astratta {@code Aula}.
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
	
	public AulaDidattica(String numeroAula, int capienza, String tipoAula, boolean haLavagna, boolean haVideoproiettore) {
		super(numeroAula, capienza,"Didattica");
		this.haLavagna = haLavagna;
		this.haVideoproiettore = haVideoproiettore;
		
	}

}
