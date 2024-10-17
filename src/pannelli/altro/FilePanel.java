package pannelli.altro;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import salvataggi.SalvaOggetti;
/**
 * Classe che implementa il pannello che si presenterÃ  cercando
 *  di salvare il bilancio su di un file già esistente per richiedere 
 *  se sovraccaricare il file o se annullare l'operazione
 * @author Luca Di Leo
 *
 */
public class FilePanel extends JPanel implements ActionListener{
	/**
	 * Bottoni per annullare o sovraccaricare
	 */
	private JButton annulla,sovraccaricare;
	/**
	 * Etichetta per il messaggio all'utente
	 */
	private JLabel etichetta;
	/**
	 * Oggetto per eventualmente salvare il bilancio
	 */
	private SalvaOggetti o;
	/**
	 * Frame in cui ospitare il pannello, viene passato così da poterlo
	 * chiudere finite le operazioni
	 */
	private JFrame f;
	
	private static final long serialVersionUID = 1L;
	/**
	 * Costruttore che inizializza le varie componenti
	 * @param o salvaoggetti
	 * @param f frame ospitante
	 */
	public FilePanel(SalvaOggetti o, JFrame f) {
		this.o=o;
		this.f=f;
		etichetta = new JLabel("File esistente. Sovraccaricare?");
		sovraccaricare= new JButton("Sovraccaricare");
		annulla= new JButton("Annulla");
		
		sovraccaricare.addActionListener(this);
		annulla.addActionListener(this);
		
		//nord		
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(etichetta, BorderLayout.NORTH);
								
		//centro		
		JPanel centro= new JPanel();
		centro.add(sovraccaricare);
		centro.add(annulla);
						
		//finale
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getSource() == sovraccaricare)
		{
			o.serializzazione();
		}
		f.setVisible(false);
	}

}
