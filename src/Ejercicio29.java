/*
 * EJERCICIO:
 * Explora el "Principio SOLID de Segregación de Interfaces (Interface Segregation Principle, ISP)"
 * y crea un ejemplo simple donde se muestre su funcionamiento de forma correcta e incorrecta.
 *
 * DIFICULTAD EXTRA (opcional):
 * Crea un gestor de impresoras.
 * Requisitos:
 * 1. Algunas impresoras sólo imprimen en blanco y negro.
 * 2. Otras sólo a color.
 * 3. Otras son multifunción, pueden imprimir, escanear y enviar fax.
 * Instrucciones:
 * 1. Implementa el sistema, con los diferentes tipos de impresoras y funciones.
 * 2. Aplica el ISP a la implementación.
 * 3. Desarrolla un código que compruebe que se cumple el principio.
 */


import java.awt.*;

public class Ejercicio29 {

    public static void main(String[] args) {

        Printer printer = new Printer();
        PrinterColor printerColor = new PrinterColor();
        MultifunctionPrinter multifunctionPrinter = new MultifunctionPrinter();

        printer.print("doc");
        printerColor.printColor("doc");
        multifunctionPrinter.print("doc");
        multifunctionPrinter.printColor("doc");
        multifunctionPrinter.send_fax("doc");
        multifunctionPrinter.Scanner("doc");

    }

    interface PrinterInterface {
        void print(String document);
    }

    interface ColorPrinterInterface {
        void printColor(String document);
    }

    interface ScannerInterface {
        void Scanner(String document);
    }

    interface FaxInterface {
        void send_fax(String document);
    }

    static public class Printer implements PrinterInterface {

        @Override
        public void print(String document) {
            System.out.println("Imprimiendo en blanco y negro el documento " + document);
        }

    }

    static public class PrinterColor implements ColorPrinterInterface {

        @Override
        public void printColor(String document) {
            System.out.println("Imprimiendo en color el documento" + document);
        }
    }

    static public class MultifunctionPrinter implements PrinterInterface, ColorPrinterInterface, ScannerInterface, FaxInterface {

        @Override
        public void print(String document) {
            System.out.println("Imprimiendo en blanco y negro el documento " + document);
        }

        @Override
        public void printColor(String document) {
            System.out.println("Imprimiendo en color el documento" + document);
        }

        @Override
        public void send_fax(String document) {
            System.out.println("Enviando el fax " + document);
        }

        @Override
        public void Scanner(String document) {
            System.out.println("Escaneando el documento " + document);
        }
    }

}