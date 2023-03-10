package service;

import model.InputData;
import model.TimePoint;
import service.TimePointService;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class TimePointServiceFactory {
    private static final BigDecimal YEAR = BigDecimal.valueOf(12L);


    public static TimePointService create() {
        return (rateNumber, inputData) -> {
            return new TimePoint(calculateDate(rateNumber, inputData), calculateMonth(rateNumber), rateNumber);
        };
    }

    private static LocalDate calculateDate(BigDecimal rateNumber, InputData inputData) {
        return inputData.getRepaymentStartDate().plus((long)rateNumber.subtract(BigDecimal.ONE).intValue(), ChronoUnit.MONTHS);
    }

    private static BigDecimal calculateYear(BigDecimal rateNumber) {
        return rateNumber.divide(YEAR, RoundingMode.UP).max(BigDecimal.ONE);
    }

    private static BigDecimal calculateMonth(BigDecimal rateNumber) {
        return BigDecimal.ZERO.equals(rateNumber.remainder(YEAR)) ? YEAR : rateNumber.remainder(YEAR);
    }
}
