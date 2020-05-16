package calculadora;

import java.util.HashMap;
/**
 * 
 * @author Eduardo
 * Inteface para ser implementado por las clase que realizan las operaciones
 * 
 */
interface Estrategia{
	/**
	 * 
	 * @param a El primer operando
	 * @param b El segundo operando
	 * @return (double) El resultado de la operación
	 */
	public double comoOpera(double a, double b);
}

/**
 * 
 * @author Eduardo
 * Clase para sumar
 */
class Suma implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) La suma de los 2 parámetros
	 */
	public double comoOpera(double a, double b) {
		return a + b;
	}
}

/**
 * 
 * @author Eduardo
 * Clase para restar
 */
class Resta implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) La diferencia de restarle 'b' a 'a'
	 */
	public double comoOpera(double a, double b) {
		return a - b;
	}
}

/**
 * 
 * @author Eduardo
 * Clase para multiplicar
 */
class Multi implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) El producto de los 2 números
	 */
	public double comoOpera(double a, double b) {
		return a * b;
	}
}

/**
 * 
 * @author Eduardo
 * Clase par dividir
 */
class Div implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) El cociente de dividir 'a' entre 'b'
	 */
	public double comoOpera(double a, double b) {
		return a / b;
	}
}

/**
 * 
 * @author Eduardo
 * Clase para potenciar
 */
class Pot implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) El resultado de elevar 'a' a la potencia de 'b'
	 */
	public double comoOpera(double a, double b) {
		return Math.pow(a, b);
	}
}

/**
 * 
 * @author Eduardo
 * Clase para obtener el módulo
 */
class Mod implements Estrategia{
	
	/**
	 * @see Estrategia
	 * @return (double) El resto de de dividir 'a' entre 'b'
	 */
	public double comoOpera(double a, double b) {
		return a % b;
	}
}

/**
 * 
 * @author Eduardo
 * Clase para manejar las estratégias
 */
public class Operacion {
	Estrategia e;
	HashMap<String, Estrategia> estrategias;
	
	/**
	 * Asigna la estrategia
	 * @param e La estrategia a usar
	 */
	public void setEstrategia(Estrategia e) {
		this.e = e;
	}
	
	/**
	 * Este método inicializa solamente las estrategias necesarias para la operación en curso 
	 * y las almacena en un Hashmap para que puedan ser seleccionadas 
	 * dinámicamente desde el método "resultado()" @see RPN
	 * 
	 * @param commando El String de entrada
	 */
	public void setEstrategias(String commando) {
		estrategias = new HashMap<String, Estrategia>();
		for (int i =0;i<commando.length();i++) {
			String car = String.valueOf(commando.charAt(i)); 
			switch (car) {
			case "+":{estrategias.put(car, new Suma());break;}
			case "-":{estrategias.put(car, new Resta());break;}
			case "*":{estrategias.put(car, new Multi());break;}
			case "/":{estrategias.put(car, new Div());break;}
			case "^":{estrategias.put(car, new Pot());break;}
			case "%":{estrategias.put(car, new Mod());break;}
			default: break;
			}
		}
	}
	
	/**
	 * Efectua la operacion con la estrategia seleccionada @see Estrategia
	 * @param a El primer operando
	 * @param b El segundo operando
	 * @return (double) El resultado de la operación
	 */
	public double opera(double a, double b) {
		return e.comoOpera(a, b);
	}
	
}
