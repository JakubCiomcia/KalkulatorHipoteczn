import model.InputData;
import model.Overpayment;
import model.RateType;
import service.*;

import java.math.BigDecimal;
import java.util.TreeMap;

public class Main {

    public static void main(String[] args) {


        TreeMap<Integer, BigDecimal> overpaymentSchema = new TreeMap<>();
        overpaymentSchema.put(3, BigDecimal.valueOf(10000));
        overpaymentSchema.put(5, BigDecimal.valueOf(5000));
        overpaymentSchema.put(6, BigDecimal.valueOf(7000));
        overpaymentSchema.put(7, BigDecimal.valueOf(1500));
        overpaymentSchema.put(8, BigDecimal.valueOf(30000));
        overpaymentSchema.put(11, BigDecimal.valueOf(10000));


        InputData inputData = new InputData()
                .withAmount(new BigDecimal("498000"))
                .withMonthsDuration(BigDecimal.valueOf(160))
                .withRateType(RateType.DECREASING)
                .withOverpaymentReduceWay(Overpayment.REDUCE_PERIOD)
                .withOverpaymentSchema(overpaymentSchema)
                .withOverpaymentProvisionPercent(BigDecimal.valueOf(10));

        PrintingService printingService = new PrintingServiceImpl();
        RateCalculationService rateCalculationService = new RateCalculationServiceImpl(
                new TimePointServiceImpl(),
                new AmountsCalculationServiceImpl(
                        new ConstantAmountsCalculationServiceImpl(),
                        new DecreasingAmountsCalculationServiceImpl()
                ),
                new OverpaymentCalculationServiceImpl(),
                new ResidualCalculationServiceImpl(),
                new ReferenceCalculationServiceImpl()
        );
        MortgageCalculationService mortgageCalculationService = new MortgageCalculationServiceImpl(
                printingService,
                rateCalculationService,
                SummaryServiceFactory.create()
        );

        mortgageCalculationService.calculate(inputData);

    }
}
