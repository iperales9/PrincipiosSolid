/*
 * EJERCICIO:
 * Explora el "Principio SOLID de Sustitución de Liskov (Liskov Substitution Principle, LSP)"
 * y crea un ejemplo simple donde se muestre su funcionamiento
 * de forma correcta e incorrecta.
 *
 * DIFICULTAD EXTRA (opcional):
 * Crea una jerarquía de vehículos. Todos ellos deben poder acelerar y frenar, así como
 * cumplir el LSP.
 * Instrucciones:
 * 1. Crea la clase Vehículo.
 * 2. Añade tres subclases de Vehículo.
 * 3. Implementa las operaciones "acelerar" y "frenar" como corresponda.
 * 4. Desarrolla un código que compruebe que se cumple el LSP.
 */

public class Ejercicio28 {

    public static void main(String[] args) {

        Car car = new Car();
        Motorcycle motorcycle = new Motorcycle();
        Bicycle bicycle = new Bicycle();

        test_vehicle(car);
        test_vehicle(motorcycle);
        test_vehicle(bicycle);
    }


    static public class vehicle {

        int velocidad = 0;

        public void accelerate(int increment) {
            System.out.println("Acelerando...");
            velocidad += increment;
        }

        public void brake(int increment) {
            System.out.println("Frenando...");
            velocidad -= increment;
        }
    }

    static public class Car extends vehicle {

        public void accelerate(int increment) {
            super.accelerate(increment);
            System.out.println("Velocidad actual del coche" + velocidad + " km/h");
        }

        public void brake(int increment) {
            super.brake(increment);
            System.out.println("Velocidad actual del coche " + velocidad + " km/h");
        }
    }

    static public class Motorcycle extends vehicle {

        public void accelerate(int increment) {
            super.accelerate(increment);
            System.out.println("Velocidad actual de la moto " + velocidad + " km/h");

        }

        public void brake(int increment) {
            super.brake(increment);
            System.out.println("Velocidad actual de la moto " + velocidad + " km/h");

        }
    }

    static public class Bicycle extends vehicle {
        public void accelerate(int increment) {
            super.accelerate(increment);
            System.out.println("Velocidad actual de la bici " + velocidad + " km/h");
        }

        public void brake(int increment) {
            super.brake(increment);
            System.out.println("Velocidad actual de la bici " + velocidad + " km/h");
        }
    }

    static public void test_vehicle(vehicle vehicle) {
        vehicle.accelerate(10);
        vehicle.brake(10);
    }
    
}
