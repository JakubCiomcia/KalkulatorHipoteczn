package service.utils;

import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationsUtils {

    private CalculationsUtils() {
    }

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    public static BigDecimal calcualtedInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 50, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculatedResidualAmount(RateAmounts rateAmounts, BigDecimal amount) {
        return amount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }

}