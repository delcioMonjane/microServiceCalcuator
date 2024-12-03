package rest.calculator.services;

import org.springframework.stereotype.Service;


public interface CalculatorService {

        double sum(double a, double b);

        double sub(double a, double b);

        double mul(double a, double b);

        double div(double a, double b);
}
