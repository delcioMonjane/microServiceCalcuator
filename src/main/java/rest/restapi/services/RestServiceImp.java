package rest.restapi.services;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import rest.restapi.model.CalculatorObject;
import rest.restapi.publishers.RestPublisher;

@Service
@AllArgsConstructor
public class RestServiceImp implements RestService {
    RestPublisher restPublisher;

    @Override
    public double sub(double a, double b) {
        CalculatorObject calculatorObject = new CalculatorObject(a, b, "sub");
        return restPublisher.sendRestSubEvent(calculatorObject);
    }

    @Override
    public double mul(double a, double b) {
        CalculatorObject calculatorObject = new CalculatorObject(a, b, "mul");
        return restPublisher.sendRestMulEvent(calculatorObject);
    }

    @Override
    public double div(double a, double b) {
        if (b == 0) {
            throw new IllegalArgumentException("Cannot divide by zero");
        }
        CalculatorObject calculatorObject = new CalculatorObject(a, b, "div");
        return restPublisher.sendRestDivEvent(calculatorObject);
    }

    @Override
    public double sum(double a, double b) {
        CalculatorObject calculatorObject = new CalculatorObject(a, b, "sum");
        return restPublisher.sendRestSumEvent(calculatorObject);
    }
}
