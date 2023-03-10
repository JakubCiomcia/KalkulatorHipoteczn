package service;

import model.InputData;
import model.MortgageResidual;
import model.Rate;
import model.RateAmounts;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class ResidualCalculationServiceImpl implements ResidualCalculationService {

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData) {
        if (BigDecimal.ZERO.equals(inputData.getAmount())) {
            return new MortgageResidual(BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            BigDecimal residualAmount = calculatedResidualAmount(inputData.getAmount(), rateAmounts);
            BigDecimal residualDuration = inputData.getMonthsDuration().subtract(BigDecimal.ONE);
            return new MortgageResidual(residualAmount, residualDuration);
        }
    }

    @Override
    public MortgageResidual calculate(RateAmounts rateAmounts, final InputData inputData, Rate previousRate) {
        BigDecimal previousResidualAmount = previousRate.getMortgageResidual().getAmount();
        BigDecimal previousResidualDuration = previousRate.getMortgageResidual().getDuration();

        if (BigDecimal.ZERO.equals(previousResidualAmount)) {
            return new MortgageResidual(BigDecimal.ZERO, BigDecimal.ZERO);
        } else {
            BigDecimal residualAmount = calculatedResidualAmount(previousResidualAmount, rateAmounts);
            BigDecimal residualDuration = calculatedResidualDuration(inputData, residualAmount, previousResidualDuration, rateAmounts);
            return new MortgageResidual(residualAmount, residualDuration);
        }


    }

    private BigDecimal calculatedResidualDuration(
            InputData inputData,
            BigDecimal residualAmount,
            BigDecimal previousResidualDuration,
            RateAmounts rateAmounts
    ) {
        if (rateAmounts.getOverpayment().getAmount().compareTo(BigDecimal.ZERO) > 0) {
            switch (inputData.getRateType()) {
                case CONSTANT:
                    return calculateConstantResidualDuration(inputData, residualAmount, rateAmounts);
                case DECREASING:
                    return calculateDecreasingResidualDuration(residualAmount, rateAmounts);
                default:
                    throw new MortgageException();
            }
        } else {
            return previousResidualDuration.subtract(BigDecimal.ONE);
        }
    }


    private BigDecimal calculateDecreasingResidualDuration(BigDecimal residualAmount, RateAmounts rateAmounts) {
        return residualAmount.divide(rateAmounts.getCapitalAmount(), 0, RoundingMode.CEILING);
    }

    private BigDecimal calculateConstantResidualDuration(InputData inputData, BigDecimal residualAmount, RateAmounts rateAmounts) {

        BigDecimal q = AmountsCalculationService.calculateQ(inputData.getInterestPercent());

        BigDecimal xNumerator = rateAmounts.getRateAmount();

        BigDecimal xDenominator = rateAmounts.getRateAmount().subtract(residualAmount.multiply(q.subtract(BigDecimal.ONE)));

        BigDecimal x = xNumerator.divide(xDenominator, 10, RoundingMode.HALF_UP);
        BigDecimal y = q;

        BigDecimal logX = BigDecimal.valueOf(Math.log(x.doubleValue()));

        BigDecimal logY = BigDecimal.valueOf(Math.log(y.doubleValue()));

        return logX.divide(logY, 0, RoundingMode.CEILING);
    }


    private BigDecimal calculatedResidualAmount(final BigDecimal residualAmount, final RateAmounts rateAmounts) {
        return residualAmount
                .subtract(rateAmounts.getCapitalAmount())
                .subtract(rateAmounts.getOverpayment().getAmount())
                .max(BigDecimal.ZERO);
    }
}
