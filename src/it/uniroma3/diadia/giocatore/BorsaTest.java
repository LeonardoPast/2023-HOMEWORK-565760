package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class BorsaTest {
	private Borsa borsa;

	@Before
	public void setup(){
		this.borsa = new Borsa();
		
	}
	
	@Test
	public void testAttrezzo() {
		Attrezzo attrezzo = new Attrezzo("pala", 1);
		this.borsa.addAttrezzo(attrezzo);
		assertEquals(this.borsa.getAttrezzo("pala"), attrezzo);
	}
	
	@Test
	public void testPesoMax() {
		assertEquals(this.borsa.getPesoMax(), 10);
	}
	
	@Test
	public void testVuota() {
		assertTrue(this.borsa.isEmpty());
	}

}
