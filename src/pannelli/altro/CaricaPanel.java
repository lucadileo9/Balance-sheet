package pannelli.altro;

import java.awt.event.ActionEvent;

import grafica.MyPanel;
import pannelli.BasePanel;
import salvataggi.SalvaOggetti;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * caricare un bilancio da file
 * @author Luca Di Leo
 */
public class CaricaPanel extends BasePanel {

	private static final long serialVersionUID = 1L;
	
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param bil bilancio
	 * @param p pannello da modificaree
	 */
	public CaricaPanel(Bilancio bil, MyPanel p) {
		super(bil, "Inserire il nome del file", p);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(testo.getText().isEmpty())
		{
			testo.setText("è necessario un nome");
			return;
		}
		//creo l'oggeto per salvare gli oggetti
		SalvaOggetti s = new SalvaOggetti (testo.getText(), bil);
		//deserializzo ottenendo il bilancio usanod un bilancio di appoggio
		Bilancio app=s.deserializzazione();
		//copio il bilancio ottenuto nel bilancio di partenza
		bil.setTutto(app.getTutto());
		//e resetto le date per essere sicuro di visualizzare l'intero bilancio
		bil.setDataInizio(1970, 1, 1);
		bil.setDataFine(0, 0, 0);
		
		p.aggiorna();
	}
}