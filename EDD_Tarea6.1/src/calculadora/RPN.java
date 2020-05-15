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
	 * y en funcíon de su valor realiza las operaciones de apilado y desapilado así como las aritméticas.
	 * @return (double) el resultado final de la operación
	 */
	public double resultado() {
		double a, b;
		int j;
		for (int i = 0; i < commando.length(); i++) {
			// si es un digito
			if (Character.isDigit(commando.charAt(i))) {
				double numero;
				// obtener un string a partir del numero
				String temp = "";
				for (j = 0; (j < 100)
						&& (Character.isDigit(commando.charAt(i)) || (commando.charAt(i) == '.')); i++, j++) {
					temp = temp + String.valueOf(commando.charAt(i));
				}
				// convertir a double y añadir a la pila
				numero = Double.parseDouble(temp);
				pushPila(numero);
			// Si es un operador
			} else if (commando.charAt(i) == '+') {
				b = popPila();
				a = popPila();
				pushPila(a + b);
			} else if (commando.charAt(i) == '-') {
				b = popPila();
				a = popPila();
				pushPila(a - b);
			} else if (commando.charAt(i) == '*') {
				b = popPila();
				a = popPila();
				pushPila(a * b);
			} else if (commando.charAt(i) == '/') {
				b = popPila();
				a = popPila();
				pushPila(a / b);
			} else if (commando.charAt(i) == '^') {
				b = popPila();
				a = popPila();
				pushPila(Math.pow(a, b));
			} else if (commando.charAt(i) == '%') {
				b = popPila();
				a = popPila();
				pushPila(a % b);
			// Si no es nada de lo anterior ni tampoco espacio en blanco 
			} else if (commando.charAt(i) != ' ') {
				throw new IllegalArgumentException();
			}
		}
		// Obtener resultado final
		double val = popPila();
		// Comprobar pila vacía
		if (arriba != null) {
			throw new IllegalArgumentException();
		}

		return val;

	}

	/**
	 * getter para testing, se usa para comprobar en las pruebas unitarias si el objeto se crea correctamente @see PilaTestCase
	 * @return (NodoPila) el atributo "arriba"
	 */
	public NodoPila getArriba() {
		return arriba;
	}

	private String commando;
	private NodoPila arriba;

}