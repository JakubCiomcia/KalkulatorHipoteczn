package service;

import model.InputData;
import model.Rate;
import model.Summary;
import java.util.List;

public interface PrintingService {

    StringBuilder SEPARATOR = createSeparator('-', 180);

    String INTEREST_SUM = " SUMA ODSETEK: ";
    String RAT_NUMBER = "NR: ";
    String OVERPAYMENT_PROVISION = " PROWIZJA ZA NADPLATY: ";
    String LOST_SUM = " SUMA STRAT: ";
    String CAPITAL_SUM = " SUMA KAPITALU: ";
    String OVERPAYMENT_REDUCE_RATE = " NADPLATA, ZMNIEJSZENIE RATY";
    String OVERPAYMENT_REDUCE_PERIOD = " NADPLATA, SKROCENIE OKRESU";
    String OVERPAYMENT_FREQUENCY = " SCHEMAT DOKONYWANIA NADPLAT: ";
    String OVERPAYMENT_START_MONTH = " MIESIAC ROZPOCZECIA NADPLAT: ";
    String YEAR = " ROK: ";
    String MONTH = " MIESIĄC: ";
    String DATE = " DATA";
    String MONTHS = " MIESIĄCY";
    String RATE = " RATA: ";
    String INTEREST = " ODSETKI: ";
    String CAPITAL = " KAPITAL: ";
    String OVERPAYMENT = " NADPLATA: ";
    String LEFT_AMOUND = " POZOSTALO KWOTA: ";
    String LEFT_MONTHS = " POZOSTALO MIESIECY: ";
    String MORTGAGE_AMOUNT = " KWOTA KREDYTU: ";
    String MORTGAGE_PERIOD = " OKRES KREDYTOWANIA: ";
    String AMOUNT = "KWOTA: ";
    String CURRENCY = " ZL ";
    String COMMA = ", ";
    String NEW_LINE = "\n";
    String PERCENT = "% ";

    private static StringBuilder createSeparator(char sign, int length) {
        StringBuilder sep = new StringBuilder();
        sep.append(String.valueOf(sign).repeat(length));
        return sep;
    }

    void printInputDataInfo(InputData inputData);

    void printScheduleRates(List<Rate> rates, final InputData inputData);

    void printSummary(Summary summaryNoOverpayment);
}