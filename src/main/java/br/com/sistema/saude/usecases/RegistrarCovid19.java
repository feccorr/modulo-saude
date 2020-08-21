package br.com.sistema.saude.usecases;

import br.com.sistema.saude.domains.Covid19;
import br.com.sistema.saude.gateways.Covid19AsyncGateway;
import br.com.sistema.saude.gateways.Covid19DatabaseGateway;
import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class RegistrarCovid19 {

  private final Covid19DatabaseGateway covid19DatabaseGateway;

  private final Covid19AsyncGateway covid19AsyncGateway;

  public void execute(final Covid19 covid19) throws JsonProcessingException {
    covid19DatabaseGateway.save(covid19);
    covid19AsyncGateway.enviar(covid19);
  }
}