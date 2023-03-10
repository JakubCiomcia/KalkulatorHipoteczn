package service;

import model.Rate;
import model.Summary;

import java.math.BigDecimal;
import java.util.List;

public class SummaryServiceFactory {
    public static SummaryService create() {
        return rates -> {
            BigDecimal interestSum = calculate(rates, rate -> rate.getRateAmounts().getInterestAmount());
            BigDecimal overpaymentProvisionSum = calculate(rates, rate -> rate.getRateAmounts().getOverpayment().getProvisionAmount());
            BigDecimal totalLostSum = interestSum.add(overpaymentProvisionSum);
            BigDecimal totalCapital = interestSum.add(totalLostSum);
            return new Summary(interestSum, overpaymentProvisionSum, totalLostSum,  totalCapital);
        };
    }

    private static BigDecimal calculate(List<Rate> rates, Function function) {
        BigDecimal sum = BigDecimal.ZERO;
        for (Rate rate : rates) {
                sum = sum.add(function.calculate(rate));
        }
        return sum;
    }
}