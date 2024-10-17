package pannelli.aggiungi;

import java.awt.event.ActionEvent;

import grafica.MyPanel;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * inserire un'entrata
 * @author Luca Di Leo
 */
public class EntrataPanel extends UscitaPanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public EntrataPanel(Bilancio bil, MyPanel p) {
		
		super(bil, p);
		invio.removeActionListener(this);//rimuovo l'ascoltatore precedente
		invio.addActionListener(this);//inserisco questa stessa classe come ascoltatore
		// TODO Auto-generated constructor stub
	}
	@Override
	/**
	 * Metodo che non fa altro che estrarre i dati, aggiungere l'uscita
	 * e aggiornare il panellp
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(estraiDati())
			bil.aggiungiVoce(descrizione.getText(), a, m, g, amm, true);
		p.aggiorna();
	}
}


