package estructuras;

import java.io.Serializable;

/**
 * Contrato que deben cumplir todos los elementos �nicamente identificados
 */
public interface IdentificadoUnicamente extends Serializable
{

    /**
     * Devuelve el identificador del elemento
     * @return identificador
     */
    public String darIdentificador();
}
