package fabricas;

import modelos.DatosEnvio;
import modelos.Envio;
import modelos.TipoEnvio;

public abstract class EnvioFactory {

    public abstract Envio crearEnvio(DatosEnvio datos);

    public static EnvioFactory getFactory(TipoEnvio tipo) {
        return switch (tipo) {
            case TERRESTRE -> new TerrestreFactory();
            case AEREO -> new AereoFactory();
            case MARITIMO -> new MaritimoFactory();
        };
    }

}