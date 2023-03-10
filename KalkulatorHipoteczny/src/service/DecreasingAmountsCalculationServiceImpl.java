package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.RateAmounts;
import service.utils.CalculationsUtils;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DecreasingAmountsCalculationServiceImpl implements DecreasingAmountsCalculationService {

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = inputData.getAmount();
        BigDecimal referenceAmount = inputData.getAmount();
        BigDecimal referenceDuration = inputData.getMonthsDuration();


        BigDecimal interestAmount = CalculationsUtils.calcualtedInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calcualtedCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);

        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    @Override
    public RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate) {
        BigDecimal interestPercent = inputData.getInterestPercent();

        BigDecimal residualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal referenceAmount = previousRate.getMortgageReference().getReferenceAmount();
        BigDecimal referenceDuration = previousRate.getMortgageReference().getReferenceDuration();

        BigDecimal interestAmount = CalculationsUtils.calcualtedInterestAmount(residualAmount, interestPercent);
        BigDecimal capitalAmount = calcualtedCapitalAmount(referenceAmount, referenceDuration, residualAmount);
        BigDecimal rateAmount = capitalAmount.add(interestAmount);


        return new RateAmounts(rateAmount, interestAmount, capitalAmount, overpayment);
    }

    private BigDecimal calcualtedCapitalAmount(
            BigDecimal amount,
            BigDecimal monthsDuration,
            BigDecimal residualAmount
    ) {
        BigDecimal capitalAmount = amount.divide(monthsDuration, 50, RoundingMode.HALF_UP);

        if(capitalAmount.compareTo(residualAmount) >= 0){
            return residualAmount;
        }
        return capitalAmount;
    }
}
