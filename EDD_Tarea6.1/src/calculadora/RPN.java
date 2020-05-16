package calculadora;

/*
Objetivo: Obtener código refactorizado a partir de otro que no lo está.
Tarea: Para esta tarea, se refactorizará un programa mal escrito, sin cambiar la forma en que funciona el programa. Este programa, RPN.java es una calculadora de notación inversa polaca que utiliza una pila.
Reverse Polish notation (RPN) Notación Polaca inversa, por ejemplo la expresión: 4 * 5 - 7 / 2 % 3 nos da 1,5 respetando la prioridad de los operadores en notación RPN seria: 4 5 * 7 2 / - 3 % (pues no podemos poner los paréntesis para alterar la prioridad)
Se debe reorganizar este código usando al menos tres de las reglas vistas en clase.
 */

/**
 * 
 * @author Eduardo
 *	Clase para generar nodos de pila
 */
class NodoPila {
	
/**
 * Constructor
 * @param dato El número que se va a almacenar
 * @param abajo Otro objeto del mismo tipo que quedará anidado debajo del actual en la pila
 */
	public NodoPila(double dato, NodoPila abajo) {
		this.dato = dato;
		this.abajo = abajo;
	}

	public NodoPila abajo;
	public double dato;
}

/**
 * 
 * @author Eduardo
 * Clase para la lógica de la aplicación
 * gestiona el apilamiento y desapilamiento y hace los calculos hasta obtener el resultado
 */
public class RPN {
	
/**
 * 
 * Método para apilar, el nuevo nodo empuja al anterior hacia abajo y ocupa su puesto comoprimero de la pila
 * @param nuevo_dato El dato numérico que se va a almacenar en la pila
 */
	public void pushPila(double nuevo_dato) {
		NodoPila nuevo_nodo = new NodoPila(nuevo_dato, arriba);
		arriba = nuevo_nodo;
	}
	
/**
 * Método para desapilar los datos, devuelve el valor almacenado en el nodo superior y despues lo elimina quedando el siguiente en su lugar.
 * @return (double) El dato numérico almacenado en el nodo superior de la pila
 */
	public double popPila() {
		double dato_arriba = arriba.dato;
		arriba = arriba.abajo;
		return dato_arriba;
	}
	
/**
 * Constructor de la clase
 * @param commando (String) la entrada introducida por teclado
 */
	public RPN(String commando) {
		arriba = null;
		this.commando = commando;
	}

	/**
	 * Método para calcular el resultado, este método extrae primero los dígitos y operadores de la entrada
	 * y en funcíon de su valor realiza las operaciones de apilado y desapilado así como las aritméticas 
	 * haciendo uso de la estrategia adecuada.
	 * @return (double) el resultado final de la operación
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
	 * Método para recoger valores de la pila y asignarlos a "a" y "b"
	 */
	public void setValores() {
		b = popPila();
		a = popPila();
	}
		

	/**
	 * getter para testing, se usa para comprobar en las pruebas unitarias si el objeto se crea correctamente @see PilaTestCase
	 * @return (NodoPila) el atributo "arriba"
	 */
	public NodoPila getArriba() {
		return arriba;
	}

	/**
	 * getter para testing, se usa para comprobar en las pruebas unitarias si el valor se asigna correctamente @see SetValoresTestCase
	 * @return (double) el atributo a
	 */
	public double getA() {
		return a;
	}

	/**
	 * getter para testing, se usa para comprobar en las pruebas unitarias si el valor se asigna correctamente @see SetValoresTestCase
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