package pannelli.visualizza;

import java.awt.event.ActionEvent;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.Bilancio;

/**
 *	Classe che implementa il pannello che si presenterà cercando di 
 * visualizzare il bilancio di un anno
 * @author Luca Di Leo
 *
 */
public class AnnoPanel extends BasePanel{
	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public AnnoPanel(Bilancio bil, MyPanel p) {
		super(bil, "Inserire anno(formato yyyy)", p);	
			}
	/**
	 * L'ascoltatore cambia la data di inizio e di fine del bilancio
	 * così da visualizzare solo quel periodo
	 * L'utente inserisce solo l'anno e poi setto la data di fine all'anno
	 * successivo per visulizzare il periodo voluto
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		String s=testo.getText();
		
		if(!controlloData(0,s)) {
		//se la data non è corretta segnalo l'errore e fermo tutto
			errore(0);
			return;
		}
		//altrimenti estraggo la data e modifico
		int i =Integer.parseInt(s);
		bil.setDataInizio(i, 1, 1);
		bil.setDataFine(i+1, 1, 1);
		//aggiorno il pannello
		p.aggiorna();
	}	
}
