package strutture;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
/** 
 * Classe che implementa una generica voce di un 
 * bilancio, senza specificare se si tratti di
 * un'entrata o di un'uscita
 * @author Luca Di Leo
 * 
 */
public class Voce implements Comparable<Voce>, Serializable{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Descrizione della voce
	 */
	private String descrizione;
	/**
	 * Data in cui la spesa è stata fatta
	 */
	private Date data; 
	/**
	 * Ammontare della spesa, può essere negativo o positivo 
	 */
	private int ammontare;
	
	/**
	 * Costeuttore che inizializza una voce con la descrizione, l'ammontare
	 * e una data di default, ossia la data di oggi
	 * @param descrizione descrizione della voce
	 * @param a ammontare
	 */
	public Voce(String descrizione, int a) {
		setDescrizione(descrizione);
		setData(0, 0, 0);
		setAmmontare(a);
	}
	/**
	 * Costeuttore che inizializza una voce con la descrizione, l'ammontare
	 * e la data voluta dall'utente
	 * @param descrizione descrizione della voce
	 * @param anno anno della voce
	 * @param mese mese della voce
	 * @param giorno giorno della voce
	 * @param a	ammontare
	 */
	public Voce(String descrizione, int anno, int mese, int giorno, int a) {
		setDescrizione(descrizione);
		setData(anno, mese, giorno);
		setAmmontare(a);
	}

	/**
	 * getter
	 * @return la descrizione
	 */
	public String getDescrizione() {
		return descrizione;
	}

	/**
	 * setter
	 * @param descrizione the descrizione to set
	 */
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}
	/**
	 * getter
	 * @return la data
	 */
	public Date getData() {	
		return data; 
	}
	/**
	 * setter
	 * @param anno anno da settare
	 * @param mese mese da settare
	 * @param giorno giorno da settare
	 */
	public void setData(int anno, int mese, int giorno) {
		if (anno==0 && mese==0 && giorno==0)
		{ //valori per inserire la data di default, cioè oggi
			this.data = new Date();
			return;
		}
		//codice per immettere una data qualsiasi
		Calendar calendar = Calendar.getInstance(TimeZone.getTimeZone("Europe/Rome"),Locale.ITALY);
		calendar.set(anno, mese-1, giorno);
		this.data = calendar.getTime();
	}
	
	/**
	 * getter
	 * @return the ammontare
	 */
	public int getAmmontare() {
		return ammontare;
	}
	/**
	 * setter
	 * @param a the ammontare to set
	 */
	public void setAmmontare(int a) {
		this.ammontare=a;
	}
	@Override
	/*
	 * Metodo per poter comparare due classi, la 
	 * comparazione tra due classi è banalmente 
	 * la comparazione tra le due date
	 * @return  0 se uguali nell'ordinamento
	 *  <0 se v viene dopo this
	 *  >0 se v viene prima di this
	 */
	public int compareTo(Voce v) {
		return this.data.compareTo(v.data);
	}
	/**
	 * ritorna la data in formato stringa per stamparla
	 * @return the data
	 */
	public String printData() {	
		SimpleDateFormat dateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);			
		dateFormat.applyPattern("dd/MM/yyyy");
		return dateFormat.format(data);
	}
	/**
	 * Metodo toString riscritto per poter
	 * stampare la singola uscita
	 */
	@Override
	public String toString() { 
		return printData()+" "+getDescrizione()+" "+getAmmontare();
	}
}
