package service;

import model.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.LinkedList;
import java.util.List;

public class RateCalculationServiceImpl implements RateCalculationService {

    private final TimePointService timePointService;

    private final AmountsCalculationService amountsCalculationService;

    private final OverpaymentCalculationService overpaymentCalculationService;

    private final ResidualCalculationService residualCalculationService;

    private final ReferenceCalculationService referenceCalculationService;

    public RateCalculationServiceImpl(
            TimePointService timePointService,
            AmountsCalculationService amountsCalculationService,
            OverpaymentCalculationService overpaymentCalculationService,
            ResidualCalculationService residualCalculationService,
            ReferenceCalculationService referenceCalculationService)
    {
        this.timePointService = timePointService;
        this.amountsCalculationService = amountsCalculationService;
        this.overpaymentCalculationService = overpaymentCalculationService;
        this.residualCalculationService = residualCalculationService;
        this.referenceCalculationService = referenceCalculationService;
    }

    @Override
    public List<Rate> calculate(final InputData inputData) {
        List<Rate> rateList = new LinkedList<>();

        BigDecimal rateNumber = BigDecimal.ONE;

        Rate fistRate = calculateFirstRate(rateNumber, inputData);

        rateList.add(fistRate);

        Rate previousRate = fistRate;

        for (BigDecimal index = rateNumber.add(BigDecimal.ONE); index.compareTo(inputData.getMonthsDuration()) <= 0;index = index.add(BigDecimal.ONE)){
            Rate nextRate = CalculateNextRate(index, inputData, previousRate);
            rateList.add(nextRate);
            previousRate = nextRate;

            if (mortgageFinished(nextRate)){
                break;
            }

        }

        return rateList;
    }

    private boolean mortgageFinished(Rate nextRate) {
        BigDecimal toCompare = nextRate.getMortgageResidual().getAmount().setScale(0, RoundingMode.HALF_UP);
        return BigDecimal.ZERO.equals(toCompare);
    }

    private Rate calculateFirstRate(BigDecimal rateNumber, InputData inputData) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData);
        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }

    private Rate CalculateNextRate(BigDecimal rateNumber, InputData inputData, Rate previousRate) {
        TimePoint timePoint = timePointService.calculate(rateNumber, inputData);
        Overpayment overpayment = overpaymentCalculationService.calculate(rateNumber, inputData);
        RateAmounts rateAmounts = amountsCalculationService.calculate(inputData, overpayment ,previousRate);
        MortgageResidual mortgageResidual = residualCalculationService.calculate(rateAmounts, inputData, previousRate);
        MortgageReference mortgageReference = referenceCalculationService.calculate(inputData, rateAmounts, previousRate);

        return new Rate(rateNumber, timePoint, rateAmounts, mortgageResidual, mortgageReference);
    }
}
