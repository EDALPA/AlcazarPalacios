package claculadoraTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import calculadora.RPN;

/**
 * 
 * @author Eduardo
 *Clase para probar el método "resultado()"
 */
class ResultadoTestCase {

	private static double esperado;
	private static double invalido;
	private static RPN rpn;

	public void init() {
		esperado = (Math.pow(2, 3) + 4.0 * 5.0 - 7.0/2.0) % 3.0;
		invalido = 2.0;
	}
	
	/**
	 * Se realiza la prueba de un caso válido y 4 no válidos
	 */
	@Test
	public void testResultado() {
		init();
		//Caso válido: La entrada tiene un formato válido
	rpn = new RPN("2 3 ^ 4 5 * + 7 2 / - 3 %");
	assertNotNull(rpn);  // el objeto se ha creado
	assertEquals(esperado, rpn.resultado());  // el resultado es el esperado
	assertNotEquals(invalido, rpn.resultado()); // el resultado es distinto del resultado inválido
		//Casos no válidos:
	rpn = new RPN("A"); //La entrada no es un número ni un punto ni un espacio.
	assertThrows(IllegalArgumentException.class, () -> rpn.resultado()); //Se lanza la excepción esperada
	
	rpn = new RPN("2.3 4 7 -"); //La entrada contiene más de 2 números seguidos
	assertThrows(IllegalArgumentException.class, () -> rpn.resultado()); //Se lanza la excepción esperada
	
	rpn = new RPN("- 2"); //La entrada comienza por un signo y la pila está vacía
	assertThrows(NullPointerException.class, () -> rpn.resultado()); //Se lanza la excepción esperada
	
	rpn = new RPN("2 * 4"); // La entrada comienza por un número pero al llegar al signo la pipla solo contiene un número
	assertThrows(NullPointerException.class, () -> rpn.resultado()); //Se lanza la excepción esperada
	}

}
