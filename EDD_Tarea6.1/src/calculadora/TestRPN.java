package calculadora;

import java.util.Scanner;
/**
 * 
 * @author Eduardo
 * Clase de prueba general del programa
 * 
 */
public class TestRPN { 
	/**
	 * Método main, se pide la entrada por teclado y se muestra el retorno del método "resultado"
	 * @param args Argumentos del método (No se usan)
	 */
	public static void main(String args[]) {
		while (true) {
			Scanner in = new Scanner(System.in);
			System.out.println("Introduce expresion Postfija o teclea\"fin\".");
			String linea = in.nextLine();
			if (linea.equals("fin")) {
				System.out.println("Fin de programa");
				break;
			} else {
				RPN calc = new RPN(linea);
				System.out.printf("El resultado es %f\n", calc.resultado());
			}
		}
	}
}