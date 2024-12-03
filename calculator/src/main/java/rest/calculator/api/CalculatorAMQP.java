package rest.calculator.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class CalculatorAMQP {
    private double a;
    private double b;
    private String operation;
}
