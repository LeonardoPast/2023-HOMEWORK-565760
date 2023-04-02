package it.uniroma3.diadia.giocatore;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {

	private Giocatore giocatore;
	
	@Before
	public void setUp() {
		this.giocatore = new Giocatore();
	}
	
	@Test
	public void testCFU() {
		assertEquals(this.giocatore.getCfu(), 20);
	}
	
	@Test
	public void testCFUSet() {
		this.giocatore.setCfu(0);
		assertEquals(this.giocatore.getCfu(), 0);
	}
	
	@Test
	public void testBorsa() {
		assertEquals(this.giocatore.getBorsa().getClass(), Borsa.class);
	}
	
	
}
