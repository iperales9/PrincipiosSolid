/*
 * EJERCICIO:
 * Explora el "Principio SOLID de Inversión de Dependencias (Dependency Inversion
 * Principle, DIP)" y crea un ejemplo simple donde se muestre su funcionamiento
 * de forma correcta e incorrecta.
 *
 * DIFICULTAD EXTRA (opcional):
 * Crea un sistema de notificaciones.
 * Requisitos:
 * 1. El sistema puede enviar Email, PUSH y SMS (implementaciones específicas).
 * 2. El sistema de notificaciones no puede depender de las implementaciones específicas.
 * Instrucciones:
 * 1. Crea la interfaz o clase abstracta.
 * 2. Desarrolla las implementaciones específicas.
 * 3. Crea el sistema de notificaciones usando el DIP.
 * 4. Desarrolla un código que compruebe que se cumple el principio.
 */

public class Ejercicio30 {

    public static void main(String[] args) {
        
        Notifier emailNotifier = new EmailNotifier();
        NotificationService service = new NotificationService(emailNotifier);
        service.send();

        Notifier pushNotifier = new PushNotifier();
        service = new NotificationService(pushNotifier);
        service.send();

        Notifier smsNotifier = new SMSNotifier();
        service = new NotificationService(smsNotifier);
        service.send();

    }

    interface Notifier {
        void send_notification();
    }

    static class EmailNotifier implements Notifier {
        @Override
        public void send_notification() {
            System.out.println(" Email enviado");
        }
    }
    
    static class PushNotifier implements Notifier {

        @Override
        public void send_notification() {
            System.out.println(" Push enviado");
        }
    }

    static class SMSNotifier implements Notifier {
        @Override
        public void send_notification() {
            System.out.println(" SMS enviado");
        }
    }

    static class NotificationService {
        private Notifier notifier;

        public NotificationService(Notifier notifier) {
            this.notifier = notifier; // Dependencia inyectada
        }

        public void send() {
            notifier.send_notification();
        }
    }
}
