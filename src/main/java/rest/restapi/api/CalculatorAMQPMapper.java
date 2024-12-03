package rest.restapi.api;

import rest.restapi.model.CalculatorObject;

public class CalculatorAMQPMapper {
    public static CalculatorAMQP mapToCalculatorAMQP(CalculatorObject calculatorObject) {
        return new CalculatorAMQP(calculatorObject.getA(), calculatorObject.getB(), calculatorObject.getOperation());
    }
}
