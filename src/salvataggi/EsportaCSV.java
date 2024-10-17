package salvataggi;

import java.io.IOException;
import java.util.ArrayList;

import strutture.Bilancio;
import strutture.Voce;

/**
 * Classe che permette di esportare il bilancio in 
 * formato CSV
 * @author Luca Di Leo
 * 
 */

public class EsportaCSV extends EsportaTesto {
	
	/**
	 * Costruttore che non fa altro che richiamare il
	 * costruttore padre passando i vari valori
	 * @param nomeFile nome del file
	 * @param bil bilancio da esportare
	 */
	public EsportaCSV(String nomeFile, Bilancio bil) {
		super(nomeFile, bil);
	}
	
	@Override
	/**
	 * Metodo che permette di esportare il testo
	 * trovandoci nella classe esportaCSV il bilancio verrà
	 * esportato utilizzando come separatore la virgola
	 */
	public void esporta() {
		String buffer= null;
	//Ottengo le voci che effettivamente voglio esportare
		ArrayList<Voce> app=bil.getVisibili();
	//Ottengo la stringa contenente l'intero bilancio
		for(int i=0; i<bil.size(); i++) {
			buffer= app.get(i).printData()+ ",";
			buffer=buffer.concat(app.get(i).getDescrizione()+ ",");
			buffer=buffer.concat(app.get(i).getAmmontare()+ ",");
			buffer=buffer.concat("\n");
			try {out.write(buffer);		} 
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