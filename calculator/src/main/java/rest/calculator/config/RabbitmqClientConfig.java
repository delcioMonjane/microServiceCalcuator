package rest.calculator.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import rest.calculator.api.CalculatorEventReceiver;
import rest.calculator.model.CalculatorEvent;
import rest.calculator.services.CalculatorService;

@Configuration
public class RabbitmqClientConfig {

    @Bean
    public DirectExchange direct() {
        return new DirectExchange("Calulator.direct");
    }

    private static class ReceiverConfig {

        @Bean(name = "autoDeleteQueue_Sum")
        public Queue autoDeleteQueue_Sum() {
            System.out.println("autoDeleteQueue_Sum created!");
            return new Queue("autoDeleteQueue_Sum", false, true, true);
        }

        @Bean(name = "autoDeleteQueue_Sub")
        public Queue autoDeleteQueue_Sub() {
            System.out.println("autoDeleteQueue_Sub created!");
            return new Queue("autoDeleteQueue_Sub", false, true, true);
        }

        @Bean(name = "autoDeleteQueue_Mul")
        public Queue autoDeleteQueue_Mul() {
            System.out.println("autoDeleteQueue_Mul created!");
            return new Queue("autoDeleteQueue_Mul", false, true, true);
        }

        @Bean(name = "autoDeleteQueue_Div")
        public Queue autoDeleteQueue_Div() {
            System.out.println("autoDeleteQueue_Div created!");
            return new Queue("autoDeleteQueue_Div", false, true, true);
        }

        @Bean
        public Binding binding(DirectExchange direct,
                              Queue autoDeleteQueue_Sum) {
            return BindingBuilder.bind(autoDeleteQueue_Sum)
                    .to(direct)
                    .with(CalculatorEvent.SUM);

        }

        @Bean
        public Binding binding2(DirectExchange direct,
                               Queue autoDeleteQueue_Sub) {
            return BindingBuilder.bind(autoDeleteQueue_Sub)
                    .to(direct)
                    .with(CalculatorEvent.SUB);

        }

        @Bean
        public Binding binding3(DirectExchange direct,
                               Queue autoDeleteQueue_Mul) {
            return BindingBuilder.bind(autoDeleteQueue_Mul)
                    .to(direct)
                    .with(CalculatorEvent.MUL);

        }

        @Bean
        public Binding binding4(DirectExchange direct,
                               Queue autoDeleteQueue_Div) {
            return BindingBuilder.bind(autoDeleteQueue_Div)
                    .to(direct)
                    .with(CalculatorEvent.DIV);

        }

        @Bean
        public CalculatorEventReceiver receiver(CalculatorService calculatorService, @Qualifier("autoDeleteQueue_Sum") Queue autoDeleteQueue_Sum) {
            return new CalculatorEventReceiver(calculatorService);
        }

    }
}
