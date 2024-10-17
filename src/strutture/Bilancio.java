package strutture;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;

/**
 * Classe che rappresenta un bilancio vero e proprio
 * avrà quindi un insieme di entrate e di uscite
 * @author Luca Di Leo
 * @version 2.0
 * 
 */
public class Bilancio implements Serializable {
	
	private static final long serialVersionUID = 1L;
	/**
	 * array contenente l'insieme di TUTTE le voci
	 */
	private ArrayList<Voce> tutto;
	/**
	 * Data d'inizio da cui iniziare a vedere le operazioni
	 */
	private Date dataInizio ;
	/**
	 * Data finale fino a cui vedere le operazioni
	 */
	private Date dataFine;
	
	/**
	 * Costruttore che stanzia l'arrayList e inizializza 
	 * la data di inzio alla prima disponibile nel calendario
	 * mentre la data di fine alla data di oggi, così da andare 
	 * a vedere tutti le possibili voci presenti nel bilancio
	 */
	public Bilancio() {
		tutto = new ArrayList<Voce>();
		this.setDataInizio(1970, 1, 1);
		this.setDataFine(0, 0, 0);
	}
	/**
	 * Costruttore a cui viene passato già un arrayList
	 * e inizializza la data di inzio alla prima disponibile 
	 * nel calendario mentre la data di fine alla data di oggi, 
	 * così da andare a vedere tutti le possibili voci presenti nel bilancio
	 * @param v arrayList delle voci
	 */
	public Bilancio(ArrayList<Voce> v) {
		setTutto(v);
		this.setDataInizio(1970, 1, 1);
		this.setDataFine(0, 0, 0);
	}
	/**
	 * getter
	 * @return the entrate
	 */
	public ArrayList<Voce> getTutto() {
		return tutto;
	}
	/**
	 * setter
	 * @param tutto the tutto to set
	 */
	public void setTutto(ArrayList<Voce> tutto) {
		this.tutto = tutto;
	}
	/**
	 * getter
	 * @return the dataInizio
	 */
	public Date getDataInizio() {
		return dataInizio;
	}
	
	/** setter 
	 * @param a anno 
	 * @param m mese 
	 * @param g giorno
	 */
	public void setDataInizio(int a, int m, int g) {
		Calendar calendar = Calendar.getInstance();
		calendar.set(a,m-1,g,0,0,0);
	//setto anche ore, minuti, e secondi, così da ottenere praticamente
	// l'inizio del giorno e visualizzare TUTTE le voci cronoligicamente successive
		this.dataInizio = calendar.getTime();
	}
	/**
	 * getter
	 * @return the dataFine
	 */
	public Date getDataFine() {
		return dataFine;
	}
	/** setter 
	 * @param a anno 
	 * @param m mese 
	 * @param g giorno
	 */
	public void setDataFine(int a, int m, int g) {
		Calendar calendar = Calendar.getInstance();
		if(a==0 && m==0 && g==0) {
			calendar.roll(Calendar.DAY_OF_MONTH, true);
			this.dataFine= calendar.getTime();
			return;
		}
		calendar.set(a,m-1,g,23,59,59);
		//setto anche ore, minuti, e secondi, così da ottenere praticamente
		// la fine del giorno e visualizzare TUTTE le voci cronoligicamente precedenti
		this.dataFine= calendar.getTime();
	}
	

	/**
	 * Aggiunge una voce al bilancio, l'utente deve passare solamente un numero
	 * pari a quello per l'ammontare, mentre il controllo del segno/coerenza del
	 * numero verrà fatto dal programma
	 * L'elemento viene creato nell'add stesso.
	 * @param descrizione descrizione della voce
	 * @param anno 	anno della voce
	 * @param mese mese della voce
	 * @param giorno giorno della voce
	 * @param a somma della voce
	 * @param isEntrata per capire se è entrata o no
	 */
	public void aggiungiVoce(String descrizione, int anno, int mese, int giorno, int a, boolean isEntrata) {
		if(isEntrata) //se si tratta di un'entrata mi assicuro di inserire un numero positivo
		{
			if(a >= 0) // se l'ammontare è positivo lo inserisco e basta
				tutto.add(new Voce(descrizione, anno, mese, giorno, a));
			else //altrimenti inserisco la voce cambiandone il segno
				tutto.add(new Voce(descrizione, anno, mese, giorno, -a));
		}
		else //altrimenti mi assicuro di aggiungere un numero negativo
		{
			if(a <= 0) // se l'ammontare è negativo lo inserisco e basta
				tutto.add(new Voce(descrizione, anno, mese, giorno, a));
			else //altrimenti inserisco la voce cambiandone il segno
				tutto.add(new Voce(descrizione, anno, mese, giorno, -a));
		}
	}
	/**
	 * Necessario per rimuovere la voce v
	 * @param v voce da rimuovere
	 */
	public void rimuoviVoce(Voce v) {
		tutto.remove(v);
	}
	@Override
	public String toString() {
		//innanzitutto se il bilancio è vuoto mi fermo e ritorno un messaggio
		if(tutto.isEmpty() )
			return "Bilancio vuoto";
		
		Collections.sort(tutto);
		//meccanismo che dovrebbe permettermi di vedere solo le op. relative alla data
		StringBuffer stringa = new StringBuffer() ;
		ArrayList<Voce> app=this.getVisibili();
		for(Voce v:app) {
			stringa.append(v+ " ");
			}
		return stringa.toString();
	}
	/**
	 * Funzione che cerca una stringa nell'array, cercando una stringa
	 * qualsiasi la cercherà sia nella descrizione che nelle date
	 * che nelle uscite/entrate
	 * @param s stringa da cercara
	 * @param i indice da cui iniziare la ricerca
	 * @return indice della voce contenente la stringa
	 * 
	 */
	public int ricerca(String s, int i) {
		if (s.isEmpty())
			return -1;
		ArrayList<Voce> app=this.getVisibili();			
		for(;i<app.size(); i++) 
		{ //app.get(i).getDescrizione().contains(s) qualora si volesse fare la ricerca solo sulla descrizione
			if (	(app.get(i).toString()).contains(s) ) // controllo se contiene la stringa cercata
				{ return i;}
		}
			return -1;
	}
	/**
	 * Metodo che calcola il totale del bilancio
	 * @return il totale del bilancio
	 */
	public int totale() {
		if (tutto.isEmpty())
			return -1;
		ArrayList<Voce> app=this.getVisibili();
		int tot=0;
		for(Voce v:app) {
			tot+=v.getAmmontare();
		}
		return tot;
	}
	
	/**
	 * Metodo per ottenre 
	 * @param index indice dell'elemento cercato
	 * @return l'elemento cercato
	 */
	public Voce elementAt(int index) {
		// TODO Auto-generated method stub
		return tutto.get(index);
	}
	/**
	 * Metodo che ritorna l'arrayList delle voci "visibili",
	 * ossia quelle cronologicamente comprese tra la data
	 * di inizio e la data di fine
	 * @return l'arrayList
	 */
	public ArrayList<Voce> getVisibili(){
		if(this.getTutto().isEmpty())
			return new ArrayList<Voce>();
		
		ArrayList<Voce> app=new ArrayList<Voce>();
		for(Voce v: this.getTutto())
		{
			if ((v.getData().compareTo(this.getDataInizio()) >= 0 ) &&
				(v.getData().compareTo(this.getDataFine()) <= 0))
			{
				app.add(v);	
			}
		}
		return app;
	}
	
	/**
	 * Metodo che ritorna la dimensione del bilancio, ossia
	 * la dimensione dell'arrayList contenente le voci visibili	
	 * @return la dimensione
	 */
	public int size() {
		ArrayList<Voce> app=this.getVisibili();
		if(app == null)
			return 0;
		return app.size();
	}
}