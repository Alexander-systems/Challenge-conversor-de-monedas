import java.util.Scanner;

public class Calcular {
    private String monedaBase;
    private String monedaObjetivo;
    private double cantidad;

    private final Scanner lectura = new Scanner(System.in);
    private final Conversor conversion;

    public Calcular(Conversor conversion) {
        this.conversion = conversion;
    }

    public void almacenarValores(String monedaBase, String monedaObjetivo) {
        this.monedaBase = monedaBase.toUpperCase();
        this.monedaObjetivo = monedaObjetivo.toUpperCase();
        this.cantidad = solicitarCantidad("Ingrese el valor que deseas convertir:");
    }

    public void almacenarValoresPersonalizados() {
        mostrarCodigosMoneda(); // Extraído para mantener el método limpio
        System.out.print("Ingrese la moneda base (3 letras): ");
        this.monedaBase = lectura.next().toUpperCase();

        System.out.print("Ingrese la moneda objetivo (3 letras): ");
        this.monedaObjetivo = lectura.next().toUpperCase();

        this.cantidad = solicitarCantidad("Ingrese el valor que deseas convertir:");
    }

    private double solicitarCantidad(String mensaje) {
        double valor = 0;
        boolean valido = false;

        while (!valido) {
            System.out.print(mensaje + " ");
            if (lectura.hasNextDouble()) {
                valor = lectura.nextDouble();
                valido = true;
            } else {
                System.out.println("Error. Ingrese un número válido.");
                lectura.next();
            }
        }
        return valor;
    }

    public String obtenerMensajeRespuesta() {
        String resultado = conversion.buscaConversion(monedaBase, monedaObjetivo, cantidad);
        String mensaje = String.format("%s %.2f equivale a %s %s", monedaBase, cantidad, monedaObjetivo, resultado);
        System.out.println(mensaje);
        return mensaje;
    }

    private void mostrarCodigosMoneda() {
        System.out.println("""
            Ejemplos de códigos de moneda válidos:
            USD - Dólar estadounidense
            EUR - Euro
            MXN - Peso mexicano
            JPY - Yen japonés
            GBP - Libra esterlina
            ... (otros disponibles en la documentación oficial)
        """);
    }
}
