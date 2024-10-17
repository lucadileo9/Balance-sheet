package pannelli.altro;

import java.awt.event.ActionEvent;

import javax.swing.JFrame;

import pannelli.BasePanel;
import salvataggi.SalvaOggetti;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * salvare il bilancio
 * @author Luca Di Leo
 */
public class SalvaPanel extends BasePanel{

	private static final long serialVersionUID = 1L;
	
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri
	 * @param bil bilancio
	 */
	public SalvaPanel(Bilancio bil) {
		super(bil,"Inserire il nome del file");	
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(testo.getText().isEmpty())
		{
			testo.setText("è necessario un nome");
			return;
		}
		SalvaOggetti o = new SalvaOggetti (testo.getText(), bil);
		if(o.ilFileEsiste())
		{
			JFrame f = new JFrame("File esistente");
			FilePanel p = new FilePanel(o,f);
			f.add(p);
			f.pack();
			f.setVisible(true);
		}
		else
			o.serializzazione();
	}
} 