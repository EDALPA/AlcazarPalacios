package calculadora;

/**
 * 
 * Este programa, RPN.java es una calculadora de notación inversa polaca que utiliza una pila.
 * Reverse Polish notation (RPN) Notación Polaca inversa, por ejemplo la expresión: 4 * 5 - 7 / 2 % 3 nos da 1,5 respetando la prioridad de los operadores 
 * en notación RPN seria: 4 5 * 7 2 / - 3 % (pues no podemos poner los paréntesis para alterar la prioridad)
 */

/**
 * <h2>Clase para generar nodos de pila.</h2>
 * @author Eduardo
 *	
 */
class NodoPila {
	
/**
 * <h3>Constructor</h3>
 * @param dato El número que se va a almacenar.
 * @param abajo Otro objeto del mismo tipo que quedará anidado debajo del actual en la pila.
 */
	public NodoPila(double dato, NodoPila abajo) {
		this.dato = dato;
		this.abajo = abajo;
	}

	public NodoPila abajo;
	public double dato;
}

/**
 * <h2>Clase para la lógica de la aplicación.</h2>
 * <h3>gestiona el apilamiento y desapilamiento y hace los calculos hasta obtener el resultado.</h3>
 * @author Eduardo
 * 
 */
public class RPN {
	
/**
 * 
 * <h3>Método para apilar.</h3>
 * el nuevo nodo empuja al anterior hacia abajo y ocupa su puesto comoprimero de la pila.
 * @param nuevo_dato El dato numérico que se va a almacenar en la pila.
 */
	public void pushPila(double nuevo_dato) {
		NodoPila nuevo_nodo = new NodoPila(nuevo_dato, arriba);
		arriba = nuevo_nodo;
	}
	
/**
 * <h3>Método para desapilar los datos.</h3>. 
 * devuelve el valor almacenado en el nodo superior y despues lo elimina quedando el siguiente en su lugar.
 * @return (double) El dato numérico almacenado en el nodo superior de la pila.
 */
	public double popPila() {
		double dato_arriba = arriba.dato;
		arriba = arriba.abajo;
		return dato_arriba;
	}
	
/**
 * <h3>Constructor de la clase.</h3>
 * @param commando (String) la entrada introducida por teclado.
 */
	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}

	/**
	 * <h3>Método para calcular el resultado.</h3>
	 * este método extrae primero los dígitos y operadores de la entrada
	 * y en funcíon de su valor realiza las operaciones de apilado y desapilado así como las aritméticas 
	 * haciendo uso de la estrategia adecuada.
	 * @return (double) el resultado final de la operación.
	 */
	public double resultado() {
		String [] tokens = commando.split(" ");
		Operacion operacion = new Operacion();
		operacion.setEstrategias(commando);
		for (int i = 0; i < tokens.length; i++) {
			if(operacion.estrategias.containsKey(tokens[i])) {
				setValores();
				operacion.setEstrategia(operacion.estrategias.get(tokens[i]));
				pushPila(operacion.opera(a, b));
			} else {
				try {
					Double numero = Double.parseDouble(tokens[i]);
					pushPila(numero);
				}catch (Exception e) {
					throw new IllegalArgumentException();
				}
			}
		}
		double val = popPila();
		if (arriba != null) {
			throw new IllegalArgumentException();
		}
		
		return val;
	}

	/**
	 * <h3>Método para recoger valores de la pila y asignarlos a "a" y "b".</h3>
	 */
	public void setValores() {
		b = popPila();
		a = popPila();
	}
		

	/**
	 * <h3>getter para testing.</h3>
	 * se usa para comprobar en las pruebas unitarias si el objeto se crea correctamente.
	 * {@inheritDoc} PilaTestCase
	 * @return (NodoPila) el atributo "arriba"
	 */
	public NodoPila getArriba() {
		return arriba;
	}

	/**
	 * <h3>getter para testing.</h3>
	 * se usa para comprobar en las pruebas unitarias si el valor se asigna correctamente .
	 * {@inheritDoc} SetValoresTestCase
	 * @return (double) el atributo a
	 */
	public double getA() {
		return a;
	}

	/**
	 * <h3>getter para testing.</h3>
	 * se usa para comprobar en las pruebas unitarias si el valor se asigna correctamente.
	 * {@inheritDoc} SetValoresTestCase
	 * @return el atributo b
	 */
	public double getB() {
		return b;
	}


	private String commando;
	private NodoPila arriba;
	private double a;
	private double b;

}