package it.uniroma3.diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

public class PartitaTest {

	private Partita partita;

	@Before
	public void setup(){
		this.partita = new Partita();
		
	}
	
	@Test
	public void testControlloFinita() {
		assertFalse("partita non finita", this.partita.isFinita());
	}
	
	@Test
	public void testControlloFinitaCfu() {
		this.partita.getGiocatore().setCfu(0);
		assertTrue("partitafinitaCfu", this.partita.isFinita());
	}
	
	@Test
	public void testStanzaVincente() {
		this.partita.getLabirinto().setStanzaCorrente(this.partita.getLabirinto().getStanzaVicente());
		assertTrue("partitaVinta", this.partita.isFinita());
	}
}
