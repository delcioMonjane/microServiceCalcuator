package rest.restapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class CalculatorObject {
    private double a;
    private double b;
    private String operation;
}
