package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.Maritimo;

public class MaritimoFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Maritimo(datos.codigo(), datos.cliente(), datos.peso(), datos.distancia());
    }
}
