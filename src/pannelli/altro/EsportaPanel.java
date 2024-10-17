package pannelli.altro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

import javax.swing.JButton;
import javax.swing.JPanel;

import grafica.MyPanel;
import pannelli.BasePanel;
import salvataggi.EsportaCSV;
import salvataggi.EsportaTesto;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * esportare il bilancio
 * @author Luca Di Leo
 */
public class EsportaPanel extends BasePanel{

	private static final long serialVersionUID = 1L;
	/**
	 * Bottoni del pannello per esportare il
	 * bilancio in formato csv o testo
	 */
	private JButton CSVBott, testoBott; 
	
	/**
	 * Costruttore che chiama il padre e rimuove tutti i componenti 
	 * poichè il pannello sarà molto diverso rispetto al padre
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public EsportaPanel(Bilancio bil, MyPanel p) {
		super(bil,"Inserire il nome del file", p);

		removeAll(); //rimuovo i precedenti componenti in quanto il panello sarà divesrso
		CSVBott= new JButton("Formato CSV");
		testoBott= new JButton("Formato testo");
		
		CSVBott.addActionListener(this);
		testoBott.addActionListener(this);
		
		JPanel nord= new JPanel();
		nord.setLayout(new BorderLayout());
		nord.add(testo, BorderLayout.NORTH);
		
		JPanel centro= new JPanel();
		centro.add(CSVBott);
		centro.add(testoBott);

		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		
		// TODO Auto-generated constructor stub
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		EsportaTesto app;
		if(e.getSource()== CSVBott)
			app=new EsportaCSV (testo.getText(), bil);
		else
			app= new EsportaTesto (testo.getText(), bil);
		
		app.esporta();
	}
}
