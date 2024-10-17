package pannelli.visualizza;

import java.awt.event.ActionEvent;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.Bilancio;

/**
 * 
 * Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il bilancio di un giorno
 * @author Luca Di Leo
 *
 */
public class GiornoPanel extends BasePanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public GiornoPanel(Bilancio bil, MyPanel p) {
		super(bil, "Inserire giorno(formato gg/mm/yyyy)", p);
		// TODO Auto-generated constructor stub
	}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del bilancio
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo la data che sarà settata come data di 
	 * inizio che di fine per evidenziare il giorno voluto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		String s =testo.getText();
		if(!controlloData(3,s)) {
			errore(0);
			return;
		}
		String [] d=s.split("/");
		int g= Integer.parseInt(d[0]);
		int m= Integer.parseInt(d[1]);
		int a= Integer.parseInt(d[2]);

		bil.setDataInizio(a, m, g);
		bil.setDataFine(a, m, g);
		p.aggiorna();
		}

}
