package claculadoraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import calculadora.RPN;

/**
 * 
 * @author Eduardo
 *Clase para probar los métodos "pushPila()" y "popPila()" @see RPN
 */
class PilaTestCase {
	static RPN rpn;
	/**
	 * Se crea un objeto RPN, se comprueba que "arriba" es null y se insertan 3 números de prueba comprobando después que "arriba" no es null 
	 */
	@Test
	void testApilar() {
		rpn = new RPN("");
		assertNull(rpn.getArriba()); // El atributo "arriba" se ha inicializado como null
		//Se insertan 3 números de prueba
		rpn.pushPila(2.0); 
		assertNotNull(rpn.getArriba()); // El atributo "arriba" es ahora un objeto no null
		rpn.pushPila(4.0);
		rpn.pushPila(3.5);
	}
	/**
	 * Se comprueba que el objeto RPN existe y se desapilan los 3 objetos apilados anteriormente comprobando sus valores y que están en el orden esperado
	 * despues se comprueba si la pila queda vacía ya que de no ser así se produciría una excepción
	 */
	@Test
	void testDesapilar() {
		assertNotNull(rpn.getArriba()); // La pila contiene objetos
		assertEquals(3.5, rpn.popPila()); // El primer objeto de la pila es el esperado
		assertEquals(4.0, rpn.popPila()); // El segundo objeto de la pila es el esperado
		assertEquals(2.0, rpn.popPila()); // El tercer objeto de la pila es el esperado
		assertNull(rpn.getArriba()); // La pila está vacía
	}
}


