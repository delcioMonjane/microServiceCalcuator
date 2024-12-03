package rest.restapi.infrastracture.publishers;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;
import rest.restapi.api.CalculatorAMQP;
import rest.restapi.api.CalculatorAMQPMapper;
import rest.restapi.model.CalculatorEvent;
import rest.restapi.model.CalculatorObject;
import rest.restapi.publishers.RestPublisher;

@Service
@AllArgsConstructor
public class RestPublisherImpl implements RestPublisher {

    private final RabbitTemplate rabbitTemplate;
    private DirectExchange direct;


    @Override
    public double sendRestSumEvent(CalculatorObject calculatorObject) {
        return sendRestEvent(calculatorObject, CalculatorEvent.SUM);
    }

    @Override
    public double sendRestSubEvent(CalculatorObject calculatorObject) {
        return sendRestEvent(calculatorObject, CalculatorEvent.SUB);
    }

    @Override
    public double sendRestMulEvent(CalculatorObject calculatorObject) {
        return sendRestEvent(calculatorObject, CalculatorEvent.MUL);
    }

    @Override
    public double sendRestDivEvent(CalculatorObject calculatorObject) {
        return sendRestEvent(calculatorObject, CalculatorEvent.DIV);
    }

    public double sendRestEvent(CalculatorObject calculatorObject, String calculatorEvent) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            CalculatorAMQP calculatorAMQP = CalculatorAMQPMapper.mapToCalculatorAMQP(calculatorObject);
            String calculatorJson = objectMapper.writeValueAsString(calculatorAMQP);

            System.out.println(" [x] Sent '" + calculatorEvent + "'");
            return (double) rabbitTemplate.convertSendAndReceive(direct.getName(), calculatorEvent, calculatorJson);

        } catch (Exception ex) {
            System.out.println(" [x] Exception sending rest event: '" + ex.getMessage() + "'");
            throw new RuntimeException("Exception sending rest event: " + ex.getMessage(), ex);
        }

    }
}
