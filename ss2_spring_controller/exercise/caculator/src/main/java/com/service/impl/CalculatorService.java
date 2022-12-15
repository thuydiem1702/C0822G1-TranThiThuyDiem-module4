package com.service.impl;

import com.service.ICalculatorService;
import org.springframework.stereotype.Service;

@Service
public class CalculatorService implements ICalculatorService {

    @Override
    public double calculate(double firstParam, double secondParam, String operator) {

        double result = 0;

        switch (operator) {
            case "Addition(+)":
                result = firstParam + secondParam;
                break;
            case "Subtraction(-)":
                result = firstParam - secondParam;
                break;
            case "Multiplication(*)":
                result = firstParam * secondParam;
                break;
            case "Division(/)":
                result = firstParam / secondParam;
                break;
        }

        return result;
    }
}
