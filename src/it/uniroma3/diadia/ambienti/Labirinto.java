package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	
	private Stanza stanzaFinale;
	private Stanza stanzaIniziale;
	private Stanza stanzaCorrente;
	
	public Labirinto(){

	}
	
	
	public void creaLabirinto() {
		Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		atrio.addAttrezzo(osso);

		// il gioco comincia nell'atrio
        this.stanzaCorrente = atrio;  
		this.stanzaFinale = biblioteca;
	}
	
	public void setStanzaIniziale(Stanza iniziale) {
		this.stanzaIniziale = iniziale;
	}
	
	public Stanza getStanzaVicente() {
		return this.stanzaFinale;
	}
	
	public void setStanzaFinale(Stanza finale) {
		this.stanzaFinale = finale;
	}
	
	public Stanza getStanzaFinale() {
		return this.stanzaFinale;
	}
	
	public void setStanzaCorrente(Stanza corrente) {
		this.stanzaCorrente = corrente;
	}
	
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}

}
