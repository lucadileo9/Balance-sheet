package strutture;

import java.awt.Frame;

import grafica.MyFrame;
import grafica.MyPanel;
/**
 * Main Ufficiale e finale
 * @author Luca Di Leo
 *
 */
public class Main {
	/**
	 * Commenti per togliere il warning
	 * @param args argomenti vuoti
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String a="Stipendio",b="Spesa",c="Regalo",d="Soldi persi",e="Soldi trovati";
		Bilancio bil= new Bilancio();

		//2006
		bil.aggiungiVoce("Regalo da peppino", 2006,6,2, 800, true);
		bil.aggiungiVoce("Regalo da peppone", 2006,6,20, 1200, true);
		bil.aggiungiVoce("Regalo da peppino", 2006,6,22, 80, true);
		bil.aggiungiVoce("Pensione", 2006,6,23, 1800, true);
		bil.aggiungiVoce("Regalo a peppino", 2006,11,5, 800, false);
		bil.aggiungiVoce("Colazione", 2006,2,15, 80, false);

		//2007
		bil.aggiungiVoce("Pensione", 2007,6,23, 1800, true);
		bil.aggiungiVoce("Bonifico", 2007,9,12, 2800, true);
		bil.aggiungiVoce(a, 2007,9,15, 8, true);
		bil.aggiungiVoce(b, 2007,7,5, 120, false);
		bil.aggiungiVoce("Colazione", 2007,2,15, 8, false);
		
		//settimana
		bil.aggiungiVoce("Bonifico", 2007,9,23, 2800, true);
		bil.aggiungiVoce("Pensione", 2007,9,29, 100, true);
		bil.aggiungiVoce("Pensione", 2007,9,27, 1000, true);
		bil.aggiungiVoce(d, 2007,9,30, 1000, false);
		bil.aggiungiVoce("Colazione", 2007,9,25, 100, false);
		
		//2009
		bil.aggiungiVoce(e, 2009,10,10, 2800, true);
		bil.aggiungiVoce(a, 2009,10,10, 2800, true);
		bil.aggiungiVoce(b, 2009,7,5, 120, false);
		bil.aggiungiVoce(b, 2009,1,13, 110, false);
		bil.aggiungiVoce(d, 2009,1,30, 12, false);

		//2010
		bil.aggiungiVoce("Pensione", 2010,6,23, 1800, true);
		bil.aggiungiVoce("Bonifico", 2010,9,12, 2800, true);
		bil.aggiungiVoce(a, 2010,9,15, 8, true);
		bil.aggiungiVoce(b, 2010,7,5, 120, false);
		bil.aggiungiVoce(d, 2010,1,30, 12, false);
		bil.aggiungiVoce("Colazione", 2010,2,15, 8, false);
		
		//2012
		bil.aggiungiVoce(a, 2012,9,15, 8, true);
		bil.aggiungiVoce(b, 2012,7,5, 120, false);
		bil.aggiungiVoce(b, 2012,1,13, 112, false);
		
		//giorno
		bil.aggiungiVoce(c, 2012, 12, 25, 20, true);
		bil.aggiungiVoce(c, 2012, 12, 25, 30, true);
		bil.aggiungiVoce(c, 2012, 12, 25, 50, true);
		bil.aggiungiVoce(c, 2012, 12, 25, 50, false);
		bil.aggiungiVoce(c, 2012, 12, 25, 50, false);

		MyFrame f = new MyFrame("Bilancio");
		MyPanel p= new MyPanel(bil);
		f.add(p);
		f.setVisible(true);
        f.setExtendedState(Frame.MAXIMIZED_BOTH);//per rendelo a schermo intero


	}
}