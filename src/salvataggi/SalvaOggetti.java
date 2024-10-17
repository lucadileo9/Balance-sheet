/**
 * 
 */
package salvataggi;

import java.io.*;

import strutture.Bilancio;

/**
 * Classe che permette di serializzare 
 * e deserializzare un bilancio
 * @author Luca Di Leo
 *
 */
public class SalvaOggetti {
	private File f; //file finale su cui scrivere
	private Bilancio bil; //bilancio su cui serializzare/deserializzare
	/**
	 * Costruttore della classe che inizializza il bilancio
	 * e crea il file
	 * @param nomeFile stringa che contiene il nome del file
	 * @param bil	bilancio
	 */
	public SalvaOggetti(String nomeFile, Bilancio bil) {
		this.bil=bil;
		f=new File("");		
		f= new File (f.getAbsolutePath()+ File.separator + nomeFile);	
	}
	/**
	 * Metodo che dice se il file esiste o no
	 * @return true se il file esiste, falso altrimenti
	 */
	public boolean ilFileEsiste() {
		return f.exists();
	}
	/**
	 * Metodo per serializzare
	 */
	public void serializzazione() {
		try {
		//Innanzitutto ottengo le voci del bilancio che effettivamente voglio salvare
		Bilancio app= new Bilancio(bil.getVisibili());
		ObjectOutputStream os= new ObjectOutputStream(new FileOutputStream(f));
		os.writeObject(app);
		os.close();
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Ops, qualcosa è andato storto "+e);
			e.printStackTrace();
		}
	}
	/**
	 * Metodo per deserializzare
	 * @return il bilancio ottenuto
	 */
	public Bilancio deserializzazione() {
		if(! ilFileEsiste())
		//se il file non esiste ritorno un bilancio vuoto
			return new Bilancio();
		try {
		ObjectInputStream in= new ObjectInputStream(new FileInputStream(f));
		bil= (Bilancio) in.readObject(); 	
		in.close();
		return bil;
		}
		catch (IOException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("Ops, qualcosa è andato storto "+e);
			e.printStackTrace();
		}
		return bil;
	}
}