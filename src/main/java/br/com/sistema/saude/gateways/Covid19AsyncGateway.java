package br.com.sistema.saude.gateways;

import br.com.sistema.saude.domains.Covid19;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface  Covid19AsyncGateway {
    void enviar(Covid19 covid19) throws JsonProcessingException;
}
