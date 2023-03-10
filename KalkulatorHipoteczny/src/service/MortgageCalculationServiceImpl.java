package service;

import model.InputData;
import model.Rate;
import model.Summary;
import java.util.List;

public class MortgageCalculationServiceImpl implements MortgageCalculationService {
    private final PrintingService printingService;
    private final RateCalculationService rateCalculationService;
    private final SummaryService summaryService;

    public MortgageCalculationServiceImpl(PrintingService printingService, RateCalculationService rateCalculationService, SummaryService summaryService) {
        this.printingService = printingService;
        this.rateCalculationService = rateCalculationService;
        this.summaryService = summaryService;
    }

    public void calculate(InputData inputData) {
        this.printingService.printInputDataInfo(inputData);
        List<Rate> rates = this.rateCalculationService.calculate(inputData);
        Summary summary = this.summaryService.calculate(rates);
        this.printingService.printSummary(summary);
        this.printingService.printScheduleRates(rates, inputData);
    }
}
