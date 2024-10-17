package pannelli.aggiungi;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.swing.JPanel;
import javax.swing.JTextField;

import grafica.MyPanel;
import pannelli.BasePanel;
import strutture.Bilancio;

/**
 * Classe che implementa il pannello che si presenterà cercando di 
 * inserire un'uscita
 * @author Luca Di Leo
 */
public class UscitaPanel extends BasePanel{
	
	private static final long serialVersionUID = 1L;
	/**
	 * Porzioni in cui inserire i dati
	 */
	protected JTextField data, descrizione, ammontare;
	/**
	 * Dati dell'uscita da inserire
	 */
	protected int g,m,a,amm;
	
	/**
	 * Il costruttore non fa altro che chiamare il costruttore 
	 * del padre inserendoci dentro la frase adatta, e passandoci
	 * i vari parametri.
	 * @param bil bilancio
	 * @param p pannello da modificare
	 */
	public UscitaPanel(Bilancio bil, MyPanel p) {
		super(bil, "Inserire anno(formato yyyy)", p);	
		super.removeAll(); //rimuovo tutti i componenti, poichè il pannello sarà 
		// molto diverso rispetto a quello base
		
		//codice per ottenere la data di oggi nel formato voluto
		SimpleDateFormat dateFormat = (SimpleDateFormat)SimpleDateFormat.getDateInstance(DateFormat.SHORT, Locale.ITALY);			
		dateFormat.applyPattern("dd/MM/yyyy");
		data= new JTextField(dateFormat.format(new Date()));
		data.selectAll();
		
		descrizione= new JTextField("Inserire descrizione"); 
		ammontare= new JTextField("Inserire ammontare", 20);
		
		//nord
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(data, BorderLayout.NORTH);
		nord.add(descrizione, BorderLayout.CENTER);
		//centro		
		JPanel centro = new JPanel ();
		centro.add(ammontare, BorderLayout.NORTH);
		centro.add(invio, BorderLayout.SOUTH);

		//finale
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
	}
	@Override
	/**
	 * Metodo che sovrascrive il metodo errore, andando ad inserire vari messaggi di
	 * errore a seconda dell'input(err), 
	 * @param err numero dell'errore
	 */
	public void errore(int err) {
		data.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		ammontare.setText("XXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXXX");
		if(err==0) {
			descrizione.setText("Errore nel formato");
		}
		if(err==1) {
			descrizione.setText("Ammontare non ammissibile");
		}	
	}
	/**
	 * Metodo che controlla che la stringa immessa come ammontare sia
	 * formata solo da numeri e che quindi sia converitbile
	 * @param p stringa da controllare
	 * @return vero se è formata solo da numeri, falso altrimenti
	 */
	public boolean controlloAmmontare(String p) {
		if(p.isEmpty())
			return false;
		if(p.charAt(0) == '-'){// se l'utente ha immesso un numero negativo
	// eliminiamolo così da non sfalsare il controllo
			p= p.substring(1);			
		}
		return p.chars().allMatch(Character::isDigit);
	}
	/**
	 * Metodo che permette di estrarre i dati dal pannello
	 * @return true se l'estrazione è andata a buon fine, false altrimenti
	 */
	public boolean estraiDati() {
		String s =data.getText();
		String p =ammontare.getText();
		
		if(!controlloData(3,s)) {
			errore(0);
			return false; 
		}
		if(!controlloAmmontare(p)) {
			errore(1);
			return false;
		}
		String [] d=s.split("/");
		
		g= Integer.parseInt(d[0]);
		m= Integer.parseInt(d[1]);
		a= Integer.parseInt(d[2]);
		amm=Integer.parseInt(p);
		
		return true;
	}
	 
	@Override
	/**
	 * Metodo che non fa altro che estrarre i dati, aggiungere l'uscita
	 * e aggiornare il panellp
	 */
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(estraiDati())
			bil.aggiungiVoce(descrizione.getText(), a, m, g, amm, false);
		p.aggiorna();
	}
}
