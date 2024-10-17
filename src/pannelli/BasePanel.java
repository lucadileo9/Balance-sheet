package pannelli;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextField;

import grafica.MyPanel;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello di base, il quale verrà
 * esteso da altri pannelli che verranno visualizzati a seconda
 * della scelta dell'utente nel menu
 * @author Luca Di Leo
 */
public abstract class BasePanel extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;
	/**
	 * Bilancio che verrà manipolato
	 */
	protected Bilancio bil; 
	/**
	 * Bottone per inviare
	 */
	protected JButton invio;
	/**
	 * Testo in cui inserire le varie info
	 */
	protected JTextField testo;
	/**
	 * Pannello da modificare
	 */
	protected MyPanel p;
	/**
	 * Il costruttore inizializza le varie componenti e le aggiunge
	 * al pannello
	 * @param bil bilancio
	 * @param s stringa con cui inizializzare il testo
	 * @param p pannello da modificare
	 */
	public BasePanel(Bilancio bil, String s, MyPanel p) {
		super();
		this.bil = bil;
		this.p=p;

		invio= new JButton("Invio");
		testo= new JTextField(s, 20);
		testo.selectAll();
		invio.addActionListener(this);
		add(testo);
		add(invio);
	}
	/**
	 * Il costruttore secondario per il pannello di salvataggio
	 * che non avrà bisogno di modificare nessuna tabella/totale
	 * @param bil bilancio
	 * @param s stringa con cui inizializzare il testo 
	 */
	public BasePanel(Bilancio bil, String s){
		super();
		this.bil = bil;
		
		//inizializzazione componenenti
		invio= new JButton("Invio");
		testo= new JTextField(s, 20);
		testo.selectAll(); //così da eviodenziare il testo e renderlo più facile da cancellare 
		invio.addActionListener(this);
		add(testo);
		add(invio);
	}

	/**
	 * Metodo per controllare il formato della data
	 * @param quale quale formato controllare
	 * @param dataDaControllare data da controllare
	 * @return vero se il formato è giusto, falso altrimenti
	 */
	public boolean controlloData(int quale, String dataDaControllare) {
		switch (quale) {
		case 0://controllo dell'anno: yyyy
		{
			if ((dataDaControllare.length() != 4))
				return false;
		//se la stringa non ha la lunghezza corretta è inutile continuare
			if (!dataDaControllare.chars().allMatch(Character::isDigit))
				return false;
		//controllo che tutti i caratteri siano numer
		}
			break;
		case 1://controllo del mese mm/yyyy
		{
			if ((dataDaControllare.length() != 7)) 
				return false;
			//se la stringa non ha la lunghezza corretta è inutile continuare
				
			for(int i=0; i< dataDaControllare.length();i++) 
			{
				if(i==0 || i==1 || i==3 || i==4 || i==5 ||i==6) // 06/2002
																// 01 3456
				{
					if (dataDaControllare.charAt(i)< '0' || dataDaControllare.charAt(i) > '9') 
					 return false;
					// se il carattere è minore di 0 o maggiore di 9, non è un numero
					
				//else è un numero, quindi continuo i controlli
				}
				else //controllo per i caratteri 2 e 5, i quali devono essere barre 
				{
					if(dataDaControllare.charAt(i)!= '/' ) 
					return false;
				}
			}
		}			
			break;
		case 2: //controllo della settimana dd/mm/yyyy, uguale al controllo del giorno
		case 3://controllo del giorno dd/mm/yyyy
		{
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
		}
			break;
		default:
			return false;		
		}
		return true;
//se tutti i controlli sono andati bene significa che il controllo
//è andato bene e quindi posso ritornare true
	}
	/**
	 * Metodo che scrive nel testo un messaggio di errore
	 * @param err paramentro utile solo per i pannelli di entrata e uscita
	 */
	public void errore(int err) {
		testo.setText("Errore nel formato della data");
		}	
	/**
	 * Ascoltatore che poi andrà implementato dai vari pannelli
	 */
	@Override
	public abstract void actionPerformed(ActionEvent e);
}
