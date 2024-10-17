package grafica;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import pannelli.*;
import pannelli.aggiungi.*;
import pannelli.altro.*;
import pannelli.visualizza.*;

import strutture.Bilancio;
import strutture.Voce;

/**
 * Classe che implementa il panello contenente la tabella, il totale,
 * il menu e i vari bottoni
 * @author Luca Di Leo
 *
 */
public class MyPanel extends JPanel implements ActionListener{
	
	private static final long serialVersionUID = 1L;
	/**
	 * bilancio da attaccare alla tabella
	 */
	private Bilancio bil; 
	/**
	 * Bottoni
	 */
	private JButton rimuovi, ricercaBott, prossimo; 
	/**
	 * Testo
	 */
	private JTextField ricercaTesto,totale;
	/**
	 * Tabella dei dati
	 */
	private MyTableModel dataModel; 
	/**
	 * Tabella grafica
	 */
	private JTable t;
	/**
	 * ScrollPane per poter scorrere la tabella
	 * Ancora non riesco ad inserirlo
	 */
	private JScrollPane scrollPane;
	/**
	 * Primo Menu
	 */
	private JMenuItem anno, mese, settimana, giorno, periodo, visualizzaTutto;
	/**
	 * Secondo Menu
	 */
	private JMenuItem entrata,uscita; 
	/**
	 * Primo Menu
	 */
	private JMenuItem salva,carica, esporta;
	/**
	 * Variabile per la ricerca degli elementi
	 */
	private int i=0; 
	 /**
	  * Costruttore che copia il bilancio, inizalizza tutte le componenti, aggiungendo i vari
	  * ascoltatori, per poi aggiungerli al pannello 
	  * @param bil bilancio
	  */
	public MyPanel(Bilancio bil) {
		super();
		this.bil = bil;

		totale= new JTextField( " Il totale è: "+ (bil.totale())  ,5);
		//inizializzo la tabella
		dataModel=new MyTableModel(bil,totale, this);
		t=new JTable(dataModel);
	    scrollPane= new JScrollPane(t);
		
		//inizializzo i bottoni, i testi e aggiungo gli ascoltatori
		rimuovi= new JButton("Rimuovi");
		ricercaTesto= new JTextField("", 15); ricercaTesto.setEditable(true);
		ricercaBott= new JButton("Ricerca");
		prossimo= new JButton("Prossimo"); prossimo.setEnabled(false);
		//per allinearlo a destra in quanto mi sembra più gradevole
		totale.setHorizontalAlignment(JTextField.RIGHT); 
		//per rendelo non modificabile e dargli lo stesso colore di sfondo della tabella
		totale.setEditable(false);
		totale.setBackground(t.getBackground());
		
		rimuovi.addActionListener(this);
		ricercaBott.addActionListener(this);
		prossimo.addActionListener(this);

//////Menu per visulizzare///////////////////////////////////////////////////
		anno= new JMenuItem("Anno");
		mese= new JMenuItem("Mese"); 
		giorno= new JMenuItem("Giorno"); 
		settimana= new JMenuItem("Settimana"); 
		periodo= new JMenuItem ("Periodo");
		visualizzaTutto= new JMenuItem ("Visualizza tutto");

		anno.addActionListener(this);
		mese.addActionListener(this);
		settimana.addActionListener(this);
		giorno.addActionListener(this);
		periodo.addActionListener(this);
		visualizzaTutto.addActionListener(this);

		JMenu visualizza= new JMenu ("Visulizza");
			
		visualizza.add(anno);
		visualizza.add(mese);
		visualizza.add(settimana);
		visualizza.add(giorno);
		visualizza.add(periodo);
		visualizza.add(visualizzaTutto);

/////////////////////////////////////////////////////////////////////////////		
		
//////////Menu per aggiungere elementi////////////////////////////////////////
		entrata= new JMenuItem ("Entrata");
		uscita= new JMenuItem ("Uscita");
		
		entrata.addActionListener(this);
		uscita.addActionListener(this);
		
		JMenu aggiungi= new JMenu ("Aggiungi");

		aggiungi.add(entrata);
		aggiungi.add(uscita);
/////////////////////////////////////////////////////////////////////////////
		
//////////Menu Altro/////////////////////////////////////////////////
		
		esporta= new JMenuItem ("Esporta");
		salva= new JMenuItem ("Salva");
		carica= new JMenuItem ("Carica");

		esporta.addActionListener(this);
		salva.addActionListener(this);
		carica.addActionListener(this);


		JMenu altro= new JMenu("Altro");
		altro.add(esporta);
		altro.add(salva);
		altro.add(carica);

/////////////////////////////////////////////////////////////////////////////

////////Menu finale//////////////////////////////////////////////////////////
		
		JMenuBar fine= new JMenuBar();
		fine.add(visualizza);
		fine.add(aggiungi);
		fine.add(altro);
				
////////Costruzione pannello finale//////////////////////////////////////////
		
		//nord		
		JPanel nord = new JPanel ();
		nord.setLayout(new BorderLayout());
		nord.add(t.getTableHeader(), BorderLayout.NORTH);
		nord.add(scrollPane,BorderLayout.CENTER);
		nord.add(totale, BorderLayout.SOUTH);
			
		//centro		
		JPanel centroNord = new JPanel ();
		centroNord.add(rimuovi);
				
		JPanel centroCentro= new JPanel();
		centroCentro.add(ricercaTesto);
		centroCentro.add(ricercaBott);
		centroCentro.add(prossimo);
						
		JPanel centro = new JPanel ();
		centro.setLayout(new BorderLayout());
		centro.add(centroNord,BorderLayout.NORTH);
		centro.add(centroCentro,BorderLayout.CENTER);
			
		//finale
		this.setLayout(new BorderLayout());
		this.add(nord,BorderLayout.NORTH);
		this.add(centro,BorderLayout.CENTER);
		this.add(fine,BorderLayout.SOUTH);	
	}	
	

	
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		Object scelta= e.getSource();
		
		if(scelta==rimuovi) {
			//rimuovo la voce selezionata
			ArrayList<Voce> app= bil.getVisibili();
			int[] indici= t.getSelectedRows();
			for(int i=0; i< indici.length; i++) {
				bil.rimuoviVoce(app.get(indici[i]));
			}
			//notifico il cambiamento della tabella e modifico il totale
			this.aggiorna();

		}
		else if(scelta==ricercaBott) {
			t.clearSelection(); //deseleziono tutto			
			
			i=bil.ricerca(ricercaTesto.getText(), 0);
			//ottengo l'indice della voce trovata
			
			//controllo se la ricerca mi ha resituito un valore non valido
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				//se così non fosse avverto l'utente
				prossimo.setEnabled(false);
				return;
			}
			//se invece l'elemento è presente lo evidenzio
			t.addRowSelectionInterval(i, i);
			//aumento l'indice qualora l'utente volesse cercare le prossime voci
			i++;
			prossimo.setEnabled(true); //rendo cliccabile il tasto prossimo
		}
		
		else if(scelta==prossimo) {
			/*Controllo sia prima che dopo se l'indice è accettabile, perchè l'utente 
			 malizioso potrebbe portare il tutto ad errore
			 */
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				prossimo.setEnabled(false);
				return;
			}
			//ricerco l'espressione immessa continuando da i, per trovare appunto le prossime voci
			i=bil.ricerca(ricercaTesto.getText(), i);
			//controllo se la ricerca mi ha resituito un valore non valido
			if(i==-1) {
				ricercaTesto.setText("Elemento non presente");
				prossimo.setEnabled(false);
				return;
			}
			//se invece l'elemento è presente lo evidenzio
			t.addRowSelectionInterval(i, i);
			//aumento l'indice qualora l'utente vlesse cercare le prossime voci
			i++;
			
		}
		else if(scelta==visualizzaTutto) {
	//per visualizzare l'intero bilancio, settando le date a quelle di default 
			bil.setDataInizio(1970, 1, 1);
			bil.setDataFine(0, 0, 0);
			this.aggiorna();
		}
		else {
		JFrame f = new JFrame(e.getActionCommand());
		BasePanel p = null;
		if(scelta == anno) {
			p= new AnnoPanel (bil, this);
		}
		if(scelta == mese) {
			p= new MesePanel (bil, this);
		}
		if(scelta == settimana) {
			 p= new SettimanaPanel (bil, this);
		}
		if(scelta == giorno) {
			p= new GiornoPanel (bil, this);
		}
		if(scelta == periodo) {
			p= new PeriodoPanel (bil, this);
		}
		if(scelta == entrata) {
			p= new EntrataPanel (bil,this);
		}
		if(scelta == uscita) {
			p = new UscitaPanel (bil, this);
		}
		if(scelta== salva) {
			 p= new SalvaPanel(bil);
		}
		if (scelta== carica) {
			p= new CaricaPanel(bil, this);
		}
		if (scelta==esporta) 
			{p= new EsportaPanel(bil, this);
		}
		f.add(p);
		f.pack();
		f.setVisible(true);
		}
	}
	
	/**
	 * Funzione che aggiorna il pannello 
	 */
	public void aggiorna() {
		//aggiornamento del totale
		totale.setText(" Il totale è: "+ (bil.totale()) );
		//aggiornamento della tabella
		t.setValueAt(null,0,0);
		//aggiornamento della pannello vero e proprio
		this.revalidate();
		this.repaint();
	}
	
}