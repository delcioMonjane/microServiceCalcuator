package rest.restapi.publishers;

import rest.restapi.model.CalculatorObject;

public interface RestPublisher {

    double sendRestSumEvent(CalculatorObject calculatorObject);

    double sendRestSubEvent(CalculatorObject calculatorObject);

    double sendRestMulEvent(CalculatorObject calculatorObject);

    double sendRestDivEvent(CalculatorObject calculatorObject);
}
