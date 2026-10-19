package controladores;

import modelos.DatosEnvio;
import servicios.EnvioServicio;
import vistas.LogisticaVista;

public class LogisticaControlador {

    private LogisticaVista vista;

    public LogisticaControlador(LogisticaVista vista) {
        this.vista = vista;
        this.vista.setGuardarEnvioClick(evento -> guardarEnvio());
        this.vista.setRetirarEnvioClick(evento -> retirarEnvio());
        mostrarEnvios();
    }

    private void mostrarEnvios() {
        vista.mostrarEnvios(EnvioServicio.getDatos(), EnvioServicio.getEncabezados());
    }

    private void guardarEnvio() {
        var codigo = vista.getCodigo().trim();
        var cliente = vista.getCliente().trim();
        var peso = vista.getPeso();
        var distancia = vista.getDistancia();

        if (codigo.isEmpty() || cliente.isEmpty() || peso <= 0 || distancia <= 0) {
            vista.mostrarMensaje("Complete todos los datos. El peso y la distancia deben ser mayores que cero.");
            return;
        }

        var envio = EnvioServicio.agregar(vista.getTipoEnvioSeleccionado(),
                new DatosEnvio(codigo, cliente, peso, distancia));

        if (envio != null) {
            mostrarEnvios();
            vista.ocultarEdicionEnvio();
        } else {
            vista.mostrarMensaje("Ya existe un envío con el código " + codigo);
        }
    }

    private void retirarEnvio() {
        var codigo = vista.pedirCodigoEnvio();
        if (codigo == null) {
            return;
        }
        codigo = codigo.trim();
        if (EnvioServicio.retirar(codigo)) {
            mostrarEnvios();
        } else {
            vista.mostrarMensaje("No existe un envío con el código " + codigo);
        }
    }
}
