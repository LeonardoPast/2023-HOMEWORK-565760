package it.uniroma3.diadia;

import java.util.Scanner;

import it.uniroma3.diadia.ambienti.Stanza;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il letodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author docente di POO (da un'idea di Michael Kolling and David J. Barnes)
 * 
 * @version base
 */

public class DiaDia {

	static final private String MESSAGGIO_BENVENUTO = ""
			+ "Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n"
			+ "Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"
			+ "I locali sono popolati da strani personaggi, " + "alcuni amici, altri... chissa!\n"
			+ "Ci sono attrezzi che potrebbero servirti nell'impresa:\n"
			+ "puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n"
			+ "o regalarli se pensi che possano ingraziarti qualcuno.\n\n"
			+ "Per conoscere le istruzioni usa il comando 'aiuto'.";

	static final private String[] elencoComandi = { "vai", "aiuto", "fine", "prendi", "posa" };

	private Partita partita;
	private Scanner scannerDiLinee;

	private IOConsole ioController;

	public DiaDia(IOConsole console) {
		this.partita = new Partita();
		this.ioController = console;
	}

	public void gioca() {
		String istruzione;
		Scanner scannerDiLinee;

		this.ioController.mostraMessaggio(MESSAGGIO_BENVENUTO);
		// System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);
		do
			// istruzione = scannerDiLinee.nextLine();
			istruzione = this.ioController.leggiRiga();
		while (!processaIstruzione(istruzione));
	}

	/**
	 * Processa una istruzione
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false
	 *         altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		Comando comandoDaEseguire = new Comando(istruzione);

		if (comandoDaEseguire.getNome() == null) {
			this.ioController.mostraMessaggio("Inserire uno dei comandi consentiti");
			return false;
		}
		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		} else if (comandoDaEseguire.getNome().equals("vai"))
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		
		else if (comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			this.ioController.mostraMessaggio("Comando sconosciuto");
		// System.out.println("Comando sconosciuto");
		if (this.partita.vinta()) {
			this.ioController.mostraMessaggio("Hai vinto!");
			// System.out.println("Hai vinto!");

			return true;
		} else
			return false;
	}

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for (int i = 0; i < elencoComandi.length; i++)
			System.out.print(elencoComandi[i] + " ");
		System.out.println();
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra e ne stampa il
	 * nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if (direzione == null)
			this.ioController.mostraMessaggio("Dove vuoi andare ?");
		// System.out.println("Dove vuoi andare ?");
		Stanza prossimaStanza = null;

		prossimaStanza = this.partita.getLabirinto().getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null) {
			// System.out.println("Direzione inesistente");
			this.ioController.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
			this.ioController.mostraMessaggio("Cfu: " + partita.getGiocatore().getCfu());
			this.ioController.mostraMessaggio("Direzione inesistente");
		} else {
			this.partita.getLabirinto().setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
			this.ioController.mostraMessaggio(partita.getLabirinto().getStanzaCorrente().getDescrizione());
			this.ioController.mostraMessaggio("Cfu: " + partita.getGiocatore().getCfu());
		}
		System.out.println(partita.getLabirinto().getStanzaCorrente().getDescrizione());
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		// System.out.println("Grazie di aver giocato!"); // si desidera smettere
		this.ioController.mostraMessaggio("Grazie di aver giocato!");
	}

	private void prendi(String nomeAttrezzo) {

		if (partita.getLabirinto().getStanzaCorrente().getAttrezzi() != null) {
			this.partita.getGiocatore().getBorsa()
					.addAttrezzo(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo));
			if (this.partita.getLabirinto().getStanzaCorrente()
					.removeAttrezzo(this.partita.getLabirinto().getStanzaCorrente().getAttrezzo(nomeAttrezzo))) {
				this.ioController.mostraMessaggio("Item riomsso dalla stanza");
			} else {
				this.ioController.mostraMessaggio("Item non rimosso dalla stanza poiche inesistente!");
			}
		} else {
			this.ioController.mostraMessaggio("Impossibile prendere attrezzo, stanza vuota");
		}
	}

	private void posa(String nomeAttrezzo) {

		if (this.partita.getGiocatore().getBorsa().isEmpty()) {
			this.ioController.mostraMessaggio("Impossibile posare, borsa gia vuota");
		} else {
			if (this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo) == null) {
				this.ioController.mostraMessaggio("Oggetto inesistente nell'inventario");
				return;
			} else {
				if (this.partita.getLabirinto().getStanzaCorrente()
						.addAttrezzo(this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo))) {
					this.ioController.mostraMessaggio(
							this.partita.getGiocatore().getBorsa().getAttrezzo(nomeAttrezzo).toString());
					if (this.partita.getGiocatore().getBorsa().removeAttrezzo(nomeAttrezzo) == null) {
						this.ioController.mostraMessaggio("Impossibile posare");
					} else {
						this.ioController.mostraMessaggio("Attrezzo Posato!");
					}

				}
			}
		}
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia(new IOConsole());
		gioco.gioca();
	}
}