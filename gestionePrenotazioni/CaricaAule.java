package gestionePrenotazioni;

import java.io.BufferedReader;
import java.io.*;
import java.util.*;
import dati.*;

/**
 * La classe {@code CaricaAule} si occupa del caricamento delle aule da un file di testo predefinito. L'utente non 
 * puo' modificare tale file. 
 * 
 * 
 *
 */

public class CaricaAule {
	
	private List<Aula> listaAule = new ArrayList<Aula>();
	
	public CaricaAule(String nomeFile){
		
		try {
			BufferedReader reader = new BufferedReader(new FileReader(nomeFile));
			String linea;
			while((linea = reader.readLine())!= null)
			{
				Aula a;
				String [] datiAula = linea.split(" ");
				String numeroAula = datiAula[0];
				int capienza = Integer.parseInt(datiAula[1]);
				String tipoAula = datiAula[2];
				boolean b1= Boolean.valueOf(datiAula[3]);
				boolean b2 = Boolean.valueOf(datiAula[4]);
				
				a = null;
				
				if(tipoAula.equals("Didattica")) {
					a = new AulaDidattica(numeroAula, capienza, b1, b2);
				}else if(tipoAula.equals("Laboratorio")) {
					a = new Laboratorio(numeroAula, capienza, b1, b2);
				}
				
				listaAule.add(a);
				
			}
			reader.close();
		}catch(IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	public List <Aula> getListaAule(){
		return listaAule;
	}
	
	public void stampaLista() {
		for(Aula a : listaAule) {
			System.out.println(a.toString());
		}
	}

}
