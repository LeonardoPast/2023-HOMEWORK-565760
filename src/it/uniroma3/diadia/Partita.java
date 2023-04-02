package it.uniroma3.diadia;


import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {


	private boolean finita;
	private int cfu;
	private Labirinto labirinto;
	private Giocatore giocatore;
	
	public Partita(){
		this.labirinto = new Labirinto();
		creaLabirinto();
		this.finita = false;
		this.giocatore = new Giocatore();
	}

    /**
     * Crea tutte le stanze e le porte di collegamento
     */
	public void creaLabirinto() {
		this.labirinto.creaLabirinto();
	}
    
    public Labirinto getLabirinto() {
    	return this.labirinto;
    }
    
    public Giocatore getGiocatore() {
    	return this.giocatore;
    }
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaCorrente()== this.labirinto.getStanzaVicente();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (this.giocatore.getCfu() == 0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
}
