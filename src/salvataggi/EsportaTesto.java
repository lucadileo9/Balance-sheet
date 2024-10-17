package salvataggi;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import strutture.Bilancio;
import strutture.Voce;

/**
 * Classe che permette di esportare il bilancio in 
 * formato testo
 * @author Luca Di Leo
 * 
 */
public class EsportaTesto {
	/**
	 * File finale su cui scriveremo
	 */
	protected File f; 
	/**
	 * Bilancio su cui serializzeremo/deserializzeremo
	 */
	protected Bilancio bil; 
	/**
	 * File writer che mi permetterà di scrivere su file
	 */
	protected FileWriter out; 

	/**
	 * Costruttore che permette di inizializzare il file, 
	 * allocare il FileWriter col relativo file
	 * @param nomeFile nome del file
	 * @param bil bilancio da esportare
	 *  
	 */
	public EsportaTesto(String nomeFile, Bilancio bil)  {
		this.bil=bil;
		f= new File("");
		f= new File (f.getAbsolutePath()+ File.separator + nomeFile);

		try {  out = new FileWriter (f);		} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella creazione del FileWriter");
			e.printStackTrace();
		} 
	}
	/**
	 * Metodo che permette di esportare il testo
	 * trovandoci nella classe esportaTesto il bilancio verrà
	 * esportato utilizzando come separatore il tab
	 */
	public void esporta() {
		String buffer= null;
	//Ottengo le voci che effettivamente voglio esportare
		ArrayList<Voce> app=bil.getVisibili();	
	//Ottengo la stringa contenente l'intero bilancio
		for(int i=0; i<bil.size(); i++) {
			buffer= app.get(i).printData()+ "\t";
			buffer=buffer.concat(app.get(i).getDescrizione()+ "\t");
			buffer=buffer.concat(app.get(i).getAmmontare()+ "\t");
			buffer=buffer.concat("\n");
			
			try {	out.write(buffer);		} 
			catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println("Errore nella scrittura");
				e.printStackTrace();
			}
		}
		try {	out.close();	} 
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Errore nella chiusura");
			e.printStackTrace();
			}
		}
	}