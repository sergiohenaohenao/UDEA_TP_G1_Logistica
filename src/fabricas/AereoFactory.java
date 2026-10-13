package fabricas;

import modelos.Aereo;
import modelos.DatosEnvio;
import modelos.Envio;

public class AereoFactory extends EnvioFactory {

    @Override
    public Envio crearEnvio(DatosEnvio datos) {
        return new Aereo(datos.codigo(), datos.cliente(), datos.peso(), datos.distancia());
    }
}
