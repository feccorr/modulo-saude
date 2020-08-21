package br.com.sistema.saude.gateways.kafka;

import br.com.sistema.saude.domains.Covid19;
import br.com.sistema.saude.gateways.Covid19AsyncGateway;
import br.com.sistema.saude.gateways.kafka.resource.Covid19Resource;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;


@Component
@RequiredArgsConstructor
public class Covid19SyncGatewayGatewayImpl implements Covid19AsyncGateway {

    private final KafkaTemplate<String, String> kafkaTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void enviar(Covid19 covid19) {

        final String mensagem = converterParaJson(covid19);

        kafkaTemplate.send("saude-covid-input", mensagem);

    }

    private String converterParaJson(Covid19 covid19) {
        try {
            return objectMapper.writeValueAsString(new Covid19Resource(covid19));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
            throw new RuntimeException(e.getMessage());
        }
    }
}
