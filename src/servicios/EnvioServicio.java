package servicios;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

import fabricas.EnvioFactory;
import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public class EnvioServicio {

    private static final String[] encabezados = { "Tipo", "Código", "Cliente", "Peso", "Distancia", "Costo" };
    private static List<Envio> envios = new ArrayList<>();

    public static String[] getEncabezados() {
        return encabezados;
    }

    public static Envio agregar(TipoEnvio tipo, DatosEnvio datos) {
        if (buscar(datos.codigo()) != null) {
            return null;
        }
        Envio envio = EnvioFactory.getFactory(tipo).crearEnvio(datos);
        envios.add(envio);
        return envio;
    }

    public static boolean retirar(String codigo) {
        Envio envio = buscar(codigo);
        if (envio == null) {
            return false;
        }
        envios.remove(envio);
        return true;
    }

    private static Envio buscar(String codigo) {
        for (Envio envio : envios) {
            if (envio.getCodigo().equals(codigo)) {
                return envio;
            }
        }
        return null;
    }

    public static String[][] getDatos() {
        DecimalFormat df = new DecimalFormat("#,##0.00");
        String[][] datos = new String[envios.size()][encabezados.length];
        int fila = 0;
        for (Envio envio : envios) {
            datos[fila][0] = envio.getTipo().toString();
            datos[fila][1] = envio.getCodigo();
            datos[fila][2] = envio.getCliente();
            datos[fila][3] = df.format(envio.getPeso());
            datos[fila][4] = df.format(envio.getDistancia());
            datos[fila][5] = "$ " + df.format(envio.calcularTarifa());
            fila++;
        }
        return datos;
    }
}
