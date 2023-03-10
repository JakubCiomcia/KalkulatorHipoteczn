package service;


import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public interface AmountsCalculationService {

    BigDecimal YEAR = BigDecimal.valueOf(12);

    RateAmounts calculate(final InputData inputData, final Overpayment overpayment);

    RateAmounts calculate(final InputData inputData, final Overpayment overpayment, final Rate previousRate);

    static BigDecimal calculatedCapitalAmount(
            final BigDecimal rateAmount,
            final BigDecimal interestAmount,
            final BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = rateAmount.subtract(interestAmount);

        if (capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }
}
