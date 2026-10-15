import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;
import vistas.LogisticaVista;

public class App {
    public static void main(String[] args) throws Exception {
        var vista = new LogisticaVista();
        vista.mostrarEnvios(
                new String[][] { { "TERRESTRE", "10001", "Polimeros Colombia", "1200.0", "400.0", "3000000.0" } },
                new String[] { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" });
        vista.setVisible(true);
    }
}