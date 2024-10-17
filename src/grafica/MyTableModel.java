package grafica;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.JTextField;
import javax.swing.table.AbstractTableModel;

import strutture.Bilancio;
import strutture.Voce;

/**
 * Classe che implementa la tabella a cui è associato un bilancio
 * @author Luca Di Leo
 * 
 */
public class MyTableModel extends AbstractTableModel{
	 private static final long serialVersionUID = 1L;
	 /**
	  * Bilancio
	  */
	 private Bilancio bil;
	 /**
	  * Pannello da modificare
	  */
	 private MyPanel p;
	 /**
	  * Totale del bilancio
	  */
	 private JTextField totale;
	 /**
	  * Intestazione
	  */
	 private final String[] intestazione = {"Data", "Descrizione", "Importo"}; 
	/**
	 * Costruttore che copia il bilancio e il totale
	 * @param bil bilancio
	 * @param totale totale
	 * @param p pannello
	 * 
	 */
	 public MyTableModel (Bilancio bil, JTextField totale, MyPanel p) {
		 this.p=p;
		 this.bil=bil;
		 this.totale=totale;
	 }
	 
	 @Override
	public boolean isCellEditable (int row, int column) { return true; }
	 
	 @Override
	public void setValueAt(Object value, int row, int col) {
		 if(value==null) {//usato per un semplice aggiornamento della tabella
			 totale.setText(" Il totale è: "+ (bil.totale()) );
			 fireTableDataChanged();
			 p.revalidate();
			 p.repaint();
			 return;			 
		 }
		ArrayList<Voce> app=bil.getVisibili();
		Voce v = (Voce)app.get(row);
		
		 if (col == 0) {
		 // modifica la data
			 String s = value.toString();
			 if(this.controlloData(s)) {
				 String [] d=s.split("/");
				 int g= Integer.parseInt(d[0]);
				 int m= Integer.parseInt(d[1]);
				 int a= Integer.parseInt(d[2]);
				 v.setData(a,m,g);
			 }
		 }
		 if (col == 1) // modifica la descrizione 
		 v.setDescrizione((value).toString());
		 if(col == 2 )//controllo anche che il valore immesso sia effettivamente composto da numeri  
		{
			if (value.toString().charAt(0) == '-')//se è negativo
			{
				if(value.toString().substring(1)/*controllo che siano numeri senza
				considerare il meno*/.chars().allMatch(Character::isDigit))
					v.setAmmontare(Integer.parseInt(value.toString()));
			}
			//altrimenti controllo l'intera stringa
			else if(value.toString().chars().allMatch(Character::isDigit))
				v.setAmmontare(Integer.parseInt(value.toString()));
		}
		 // notifica il cambiamento
		 fireTableDataChanged();
		 totale.setText(" Il totale è: "+ (bil.totale()));
		 p.revalidate();
		 p.repaint();
	}
	 
	private boolean controlloData(String dataDaControllare) {
		if ((dataDaControllare.length() != 10)) 
		{
			//se la stringa non ha la lunghezza corretta
			// è inutile continuare
			return false;
		}
				
		for(int i=0; i< dataDaControllare.length();i++) 
		{
			if(i==0 || i==1 || i==3 || i==4 ||
					i==6 ||i==7 ||i==8 ||i==9)
			// 20/06/2002
			// 01 34 6789
			{
				if (dataDaControllare.charAt(i)< '0' || dataDaControllare.charAt(i) > '9') 
				{
					// se il carattere è minore di 0 o maggiore di 9, non è un numero
					return false;
				}
				//else è un numero, quindi continuo i controlli
			}
			else //controllo per i caratteri 2 e 5 che devono essere barre 
			{
				if(dataDaControllare.charAt(i)!= '/' ) 
				{
					return false;
				}
			}
		}
		return true;
	}
	 @Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		 return bil.size();
	 }
	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return intestazione.length;
	}
	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		Collections.sort(bil.getTutto());

		/* 
		 	creo un array di appoggio, all'interno del quale verrano copiati
			tutti gli elementi che dovranno essere inseriti nella tabella, ossia
			tutti gli elementi che si trovano nel range di data visualizzabile		
		 */
		ArrayList<Voce> copia = bil.getVisibili(); 
		
		//controllo che effettivamente ci sia qualche elemento disponibile
		if(copia.isEmpty()) {
			return null;
		}
		
	//costruisco la mia tabella usando l'array contenente solo le voci valide	
		Voce appoggio = (Voce) copia.get(rowIndex);
		switch (columnIndex)
		{
			case 0: return appoggio.printData();
			case 1: return appoggio.getDescrizione();
			case 2: return appoggio.getAmmontare();
			default: return null;
		}
	} 
	/**
	 * Per ottenere il l'intestazione della tabella
	 */
	public String getColumnName(int col) {
		return intestazione[col];
	}
}
