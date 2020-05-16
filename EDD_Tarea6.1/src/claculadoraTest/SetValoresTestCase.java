package claculadoraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import calculadora.RPN;

/**
 * 
 * @author Eduardo
 * Clase par aprobar el método "setValores()" @see RPN 
 */
class SetValoresTestCase {
	RPN rpn;
	
	/**
	 * Se crea un objeto RPN y se inicializan 2 nodos de prueba
	 */
	private void init() {
		rpn = new RPN("");
		rpn.pushPila(2.3);
		rpn.pushPila(5.0);
	}
	
	/**
	 * Se ejecuta el método y se comprueba que los valores coinciden con los introducidos.
	 */
	@Test
	void testSetValores() {
		init();
		rpn.setValores();
		assertEquals(2.3, rpn.getA());
		assertEquals(5.0, rpn.getB());
	}

}
