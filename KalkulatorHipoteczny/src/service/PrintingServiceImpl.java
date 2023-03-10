package service;

import model.InputData;
import model.Overpayment;
import model.Rate;
import model.Summary;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class PrintingServiceImpl implements PrintingService {

    public void printInputDataInfo(InputData inputData) {
        StringBuilder msg = new StringBuilder(NEW_LINE);
        msg.append(MORTGAGE_AMOUNT).append(inputData.getAmount()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(MORTGAGE_PERIOD).append(inputData.getMonthsDuration()).append(MONTHS);
        msg.append(NEW_LINE);
        msg.append(INTEREST).append(inputData.getInterestDisplay()).append(PERCENT);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_START_MONTH);
        msg.append(inputData.getOverpaymentStartMonth());
        msg.append(NEW_LINE);
        Optional.ofNullable(inputData.getOverPaymentSchema()).filter((schema) -> {
            return schema.size() > 0;
        }).ifPresent((schema) -> {
            this.logOverpayment(msg, inputData.getOverPaymentSchema(), inputData.getOverpaymentReduceWay());
        });
        this.printMessage(msg);
    }

    private void logOverpayment(StringBuilder msg, Map<Integer, BigDecimal> schema, String reduceWay) {
        switch (reduceWay) {
            case Overpayment.REDUCE_PERIOD:
                msg.append(OVERPAYMENT_REDUCE_PERIOD);
                break;
            case Overpayment.REDUCE_RATE:
                msg.append(OVERPAYMENT_REDUCE_RATE);
                break;
            default:
                throw new MortgageException();
        }

        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_FREQUENCY).append(NEW_LINE).append(prettyPrintOverpaymentSchema(schema));
    }

    private String prettyPrintOverpaymentSchema(Map<Integer, BigDecimal> schema) {
        StringBuilder schemaMsg = new StringBuilder();
        for (Map.Entry<Integer, BigDecimal> entry : schema.entrySet()) {
            schemaMsg.append(MONTH)
                    .append(entry.getKey())
                    .append(COMMA)
                    .append(AMOUNT)
                    .append(entry.getValue())
                    .append(CURRENCY)
                    .append(NEW_LINE);
        }
        return schemaMsg.toString();
    }


    public void printScheduleRates(List<Rate> rates, InputData inputData) {
        {
            String format = "%4s %3s" +
                    "%4s %4s" +
                    "%3s %2s" +
                    "%4s %2s" +
                    "%4s %8s" +
                    "%7s %8s" +
                    "%7s %10s" +
                    "%4s %8s" +
                    "%4s %9s" +
                    "%4s %4s";

            int index = 1;
            for (Rate rate : rates) {
                String message = String.format(format,
                        RAT_NUMBER, rate.getRateNumber(),
                        DATE, rate.getTimePoint().getDate(),
                        YEAR, rate.getTimePoint().getYear(),
                        MONTH, rate.getTimePoint().getMonth(),
                        RATE, rate.getRateAmounts().getRateAmount(),
                        INTEREST, rate.getRateAmounts().getInterestAmount(),
                        CAPITAL, rate.getRateAmounts().getCapitalAmount(),
                        OVERPAYMENT, rate.getRateAmounts().getOverpayment().getAmount(),
                        LEFT_AMOUND, rate.getMortgageResidual().getAmount(),
                        LEFT_MONTHS, rate.getMortgageResidual().getDuration()
                );
                printMessage(message);
                if (index % 12 == 0) {
                    printSeparator(SEPARATOR);
                }
                index++;
            }
        }
    }

    @Override
    public void printSummary(final Summary summary) {
        StringBuilder msg = new StringBuilder();
        msg.append(INTEREST_SUM).append(summary.getInterestSum()).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(OVERPAYMENT_PROVISION).append(summary.getOverpaymentProvisionSum().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(LOST_SUM).append(summary.getTotalLostSum().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);
        msg.append(CAPITAL_SUM).append(summary.getTotalCapital().setScale(2, RoundingMode.HALF_UP)).append(CURRENCY);
        msg.append(NEW_LINE);

        printMessage(msg);
    }

    private void printSeparator(StringBuilder sep) {
        System.out.println(sep);
    }

    private void printMessage(StringBuilder message) {
        System.out.println(message);
    }

    private void printMessage(String message) {
        System.out.println(message);
    }

}
