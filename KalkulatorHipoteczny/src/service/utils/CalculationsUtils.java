package service.utils;

import service.AmountsCalculationService;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class CalculationsUtils {

    public static final BigDecimal YEAR = BigDecimal.valueOf(12);

    public static BigDecimal calculatedInterestAmount(BigDecimal residualAmount, BigDecimal interestPercent) {
        return residualAmount.multiply(interestPercent).divide(YEAR, 2, RoundingMode.HALF_UP);
    }

    public static BigDecimal calculateQ(final BigDecimal interestPercent) {
        return interestPercent.divide(AmountsCalculationService.YEAR, 10, RoundingMode.HALF_UP).add(BigDecimal.ONE);
    }

    public static BigDecimal calculatedDecreasingCapitalAmount(
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = amount.divide(monthsDuration, 10, RoundingMode.HALF_UP);

        if (capitalAmount.compareTo(residualAmount) >= 0) {
            return residualAmount;
        }
        return capitalAmount;
    }

}