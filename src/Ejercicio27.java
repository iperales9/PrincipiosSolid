
/*
 * EJERCICIO:
 * Explora el "Principio SOLID Abierto-Cerrado (Open-Close Principle, OCP)"
 * y crea un ejemplo simple donde se muestre su funcionamiento
 * de forma correcta e incorrecta.
 *
 * DIFICULTAD EXTRA (opcional):
 * Desarrolla una calculadora que necesita realizar diversas operaciones matemáticas.
 * Requisitos:
 * - Debes diseñar un sistema que permita agregar nuevas operaciones utilizando el OCP.
 * Instrucciones:
 * 1. Implementa las operaciones de suma, resta, multiplicación y división.
 * 2. Comprueba que el sistema funciona.
 * 3. Agrega una quinta operación para calcular potencias.
 * 4. Comprueba que se cumple el OCP.
 */

import javax.swing.*;
import java.util.HashMap;

public class Ejercicio27 {
    public static void main(String[] args) {

        Calculadora calc = new Calculadora();
        calc.operaciones.put("potencia", new Potencia());

        System.out.println(calc.calculate("suma", 5, 3));
        System.out.println(calc.calculate("resta", 5, 3));
        System.out.println(calc.calculate("multiplicacion", 5, 3));
        System.out.println(calc.calculate("division", 5, 3));
        System.out.println(calc.calculate("potencia", 5, 3));

    }

    interface operation {
        public double operate(double a, double b);
    }

    static class Suma implements operation {
        public double operate(double a, double b) {
            return a + b;
        }
    }

    static class Resta implements operation {
        public double operate(double a, double b) {
            return a - b;
        }
    }

    static class Multiplicacion implements operation {
        public double operate(double a, double b) {
            return a * b;
        }
    }

    static class Division implements operation {
        public double operate(double a, double b) {
            return a / b;
        }
    }

    static class Potencia implements operation {
        public double operate(double a, double b) {
            return Math.pow(a, b);
        }
    }

    static class Calculadora {
        HashMap<String, operation> operaciones;

        public Calculadora() {
            this.operaciones = new HashMap<>(); // Inicializa el HashMap
            operaciones.put("suma", new Suma());
            operaciones.put("resta", new Resta());
            operaciones.put("multiplicacion", new Multiplicacion());
            operaciones.put("division", new Division());
        }

        public double calculate(String operacion, double a, double b) {
            if (operaciones.get(operacion) != null)
                return operaciones.get(operacion).operate(a, b);
            else
                throw new RuntimeException("La operación " + operacion + " no existe");
        }
    }

}
