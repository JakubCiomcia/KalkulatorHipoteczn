package service;

import model.InputData;
import model.MortgageReference;
import model.Rate;
import model.RateAmounts;

public interface ReferenceCalculationService {

    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
