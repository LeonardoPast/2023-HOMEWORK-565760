package it.uniroma3.diadia.ambienti;
import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza stanzaAtrio;
	private Stanza StanzaAdiacente;
	@Before
	public void setup(){

		this.stanzaAtrio = new Stanza("atrio");
		this.StanzaAdiacente = new Stanza("adiacente");
	}
	
	@Test
	public void testStanzaAdiacente() {
		this.stanzaAtrio.impostaStanzaAdiacente("nord", StanzaAdiacente);
		assertEquals(this.stanzaAtrio.getStanzaAdiacente("nord"), this.StanzaAdiacente);
	}
	
	@Test
	public void testContoAttrezziStanza() {
		this.stanzaAtrio.addAttrezzo(new Attrezzo("pala", 1));
		this.stanzaAtrio.addAttrezzo(new Attrezzo("soldo", 1));
		int count=0;
		for(Attrezzo attrezzo: this.stanzaAtrio.getAttrezzi()) {
			if(attrezzo != null) {count++;}
			
		}
		assertEquals(count, 2);
	}
	
	@Test
	public void testAtrezzoPresente() {
		Stanza stanza = new Stanza("prova");
		Attrezzo attrezzo = new Attrezzo("pala",1);
		stanza.addAttrezzo(attrezzo);
		assertEquals(stanza.getAttrezzo("pala").getNome(), attrezzo.getNome());

		
	}
	
	
}
