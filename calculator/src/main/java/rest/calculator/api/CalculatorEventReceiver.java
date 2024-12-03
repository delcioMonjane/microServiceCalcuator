package rest.calculator.api;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import rest.calculator.services.CalculatorService;

import java.nio.charset.StandardCharsets;

@Component
@AllArgsConstructor
public class CalculatorEventReceiver {

    private final CalculatorService calculatorService;

    @RabbitListener(queues = "autoDeleteQueue_Sum")
    public double sum(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);
            System.out.println(" [x] Received Sum by AMQP: " + msg + ".");

            CalculatorAMQP calculatorAMQP = objectMapper.readValue(jsonReceived, CalculatorAMQP.class);

            try {
                return calculatorService.sum(calculatorAMQP.getA(), calculatorAMQP.getB());
            } catch (Exception e) {
                System.out.println(" [x] Exception in sum operation. ");
            }

        } catch (Exception ex) {
            System.out.println(" [x] Exception receiving sum event from AMQP: '" + ex.getMessage() + "'");
            throw new RuntimeException("Exception receiving sum event from AMQP: " + ex.getMessage(), ex);
        }
        return 0;
    }

    @RabbitListener(queues = "autoDeleteQueue_Sub")
    public double sub(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);
            CalculatorAMQP calculatorAMQP = objectMapper.readValue(jsonReceived, CalculatorAMQP.class);

            System.out.println(" [x] Received Sub by AMQP: " + msg + ".");

            try {
                return calculatorService.sub(calculatorAMQP.getA(), calculatorAMQP.getB());
            } catch (Exception e) {
                System.out.println(" [x] Exception in sub operation. ");
            }

        } catch (Exception ex) {
            System.out.println(" [x] Exception receiving sub event from AMQP: '" + ex.getMessage() + "'");
            throw new RuntimeException("Exception receiving sub event from AMQP: " + ex.getMessage(), ex);
        }
        return 0;
    }

    @RabbitListener(queues = "autoDeleteQueue_Mul")
    public double mul(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);
            CalculatorAMQP calculatorAMQP = objectMapper.readValue(jsonReceived, CalculatorAMQP.class);

            System.out.println(" [x] Received Mul by AMQP: " + msg + ".");

            try {
                return calculatorService.mul(calculatorAMQP.getA(), calculatorAMQP.getB());
            } catch (Exception e) {
                System.out.println(" [x] Exception in mul operation. ");
            }

        } catch (Exception ex) {
            System.out.println(" [x] Exception receiving mul event from AMQP: '" + ex.getMessage() + "'");
            throw new RuntimeException("Exception receiving mul event from AMQP: " + ex.getMessage(), ex);
        }
        return 0;
    }

    @RabbitListener(queues = "autoDeleteQueue_Div")
    public double div(Message msg) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            String jsonReceived = new String(msg.getBody(), StandardCharsets.UTF_8);
            CalculatorAMQP calculatorAMQP = objectMapper.readValue(jsonReceived, CalculatorAMQP.class);

            System.out.println(" [x] Received Div by AMQP: " + msg + ".");

            try {
                return calculatorService.div(calculatorAMQP.getA(), calculatorAMQP.getB());
            } catch (Exception e) {
                System.out.println(" [x] Exception in div operation. ");
            }

        } catch (Exception ex) {
            System.out.println(" [x] Exception receiving div event from AMQP: '" + ex.getMessage() + "'");
            throw new RuntimeException("Exception receiving div event from AMQP: " + ex.getMessage(), ex);
        }
        return 0;
    }




}
