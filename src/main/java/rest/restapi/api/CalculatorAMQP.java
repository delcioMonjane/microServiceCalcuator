package rest.restapi.api;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CalculatorAMQP {
    private double a;
    private double b;
    private String operation;
}
