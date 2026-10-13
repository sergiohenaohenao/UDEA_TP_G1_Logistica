import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public class App {
    public static void main(String[] args) throws Exception {
        DatosEnvio datos = new DatosEnvio("10001", "Cliente de prueba", 1000, 100);

        for (TipoEnvio tipo : TipoEnvio.values()) {
            Envio envio = EnvioFactory.getFactory(tipo).crearEnvio(datos);
            System.out.println(envio.getTipo() + ": " + envio.calcularTarifa());
        }
    }
}