import com.google.gson.JsonSyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class Principal {
    private static final Scanner lectura = new Scanner(System.in);
    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");

    private static final Map<Integer, String[]> conversionesPredefinidas = Map.of(
            1, new String[]{"MXN", "USD"},
            2, new String[]{"MXN", "EUR"},
            3, new String[]{"MXN", "GBP"},
            4, new String[]{"USD", "MXN"},
            5, new String[]{"EUR", "MXN"},
            6, new String[]{"GBP", "MXN"}
    );

    public static void main(String[] args) {
        Calcular calculos = new Calcular(new Conversor());
        GenerarArchivo generador = new GenerarArchivo();
        List<String> respuestas = new ArrayList<>();

        String menu = """
                \n***************************************************
                *** Sea bienvenido al Conversor de Monedas ***
                
                1) Peso Mexicano ==>> Dólar Estadounidense
                2) Peso Mexicano ==>> Euro
                3) Peso Mexicano ==>> Libra Esterlina
                4) Dólar Estadounidense ==>> Peso Mexicano
                5) Euro ==>> Peso Mexicano
                6) Libra Esterlina ==>> Peso Mexicano
                7) Otra opción de conversión
                8) Salir
                ***************************************************""";

        int opcion = 0;
        do {
            System.out.println(menu);
            try {
                opcion = Integer.parseInt(lectura.nextLine());
                if (opcion == 7) {
                    calculos.almacenarValoresPersonalizados();
                } else if (conversionesPredefinidas.containsKey(opcion)) {
                    String[] monedas = conversionesPredefinidas.get(opcion);
                    calculos.almacenarValores(monedas[0], monedas[1]);
                } else if (opcion != 8) {
                    System.out.println("Ingrese una opción válida");
                    continue;
                } else {
                    break;
                }
                respuestas.add(getMarcaDeTiempo() + " - " + calculos.obtenerMensajeRespuesta());
            } catch (JsonSyntaxException | NullPointerException e) {
                System.out.println("Error. Ingrese solo códigos de moneda válidos.");
            } catch (NumberFormatException | InputMismatchException e) {
                System.out.println("Error. Ingrese un valor numérico válido.");
            }
        } while (opcion != 8);

        generador.guardarJson(respuestas);
        System.out.println("Finalizando programa");
    }

    private static String getMarcaDeTiempo() {
        return LocalDateTime.now().format(formatter);
    }
}
