

package model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Map;

public class InputData {
    private static final BigDecimal PERCENT = BigDecimal.valueOf(100);
    private LocalDate repaymentStartDate = LocalDate.of(2020, 1, 6);
    private BigDecimal wiborPercent = new BigDecimal("1.73");
    private BigDecimal amount = new BigDecimal("300000");
    private BigDecimal monthsDuration = BigDecimal.valueOf(180);
    private RateType rateType;
    private BigDecimal bankMarginPercent;
    private BigDecimal overpaymentStartMonth;
    private Map<Integer, BigDecimal> overPaymentSchema;
    private String overpaymentReduceWay;
    private boolean mortgagePrintPayoffsSchedule;
    private Integer mortgageRateNumberToPrint;
    private BigDecimal overpaymentProvisionPercent;
    private BigDecimal overpaymentProvisionMoths;

    public InputData() {
        this.bankMarginPercent = new BigDecimal("1.9");
        this.overpaymentStartMonth = BigDecimal.valueOf(1);
        this.overpaymentReduceWay = Overpayment.REDUCE_RATE;
        this.mortgagePrintPayoffsSchedule = true;
        this.mortgageRateNumberToPrint = 1;
        this.overpaymentProvisionPercent = BigDecimal.valueOf(3);
        this.overpaymentProvisionMoths = BigDecimal.valueOf(36);
    }

    public InputData withOverpaymentSchema(Map<Integer, BigDecimal> overPaymentSchema) {
        this.overPaymentSchema = overPaymentSchema;
        return this;
    }

    public InputData withOverpaymentReduceWay(String overpaymentReduceWay) {
        this.overpaymentReduceWay = overpaymentReduceWay;
        return this;
    }

    public InputData withOverpaymentProvisionPercent(BigDecimal overpaymentProvisionPercent) {
        this.overpaymentProvisionPercent = overpaymentProvisionPercent;
        return this;
    }

    public InputData withOverpaymentProvisionMoths(BigDecimal overpaymentProvisionMoths) {
        this.overpaymentProvisionMoths = overpaymentProvisionMoths;
        return this;
    }

    public InputData withRepaymentStartDate(LocalDate repaymentStartDate) {
        this.repaymentStartDate = repaymentStartDate;
        return this;
    }

    public InputData withWiborPercent(BigDecimal wiborPercent) {
        this.wiborPercent = wiborPercent;
        return this;
    }

    public InputData withAmount(BigDecimal amount) {
        this.amount = amount;
        return this;
    }

    public BigDecimal getOverpaymentStartMonth() {
        return this.overpaymentStartMonth;
    }

    public boolean isMortgagePrintPayoffsSchedule() {
        return true;
    }

    public Integer getMortgageRateNumberToPrint() {
        return this.mortgageRateNumberToPrint;
    }

    public InputData withMonthsDuration(BigDecimal monthsDuration) {
        this.monthsDuration = monthsDuration;
        return this;
    }

    public InputData withRateType(RateType rateType) {
        this.rateType = rateType;
        return this;
    }

    public InputData withBankMarginPercent(BigDecimal bankMarginPercent) {
        this.bankMarginPercent = bankMarginPercent;
        return this;
    }

    public LocalDate getRepaymentStartDate() {
        return this.repaymentStartDate;
    }

    public BigDecimal getAmount() {
        return this.amount;
    }

    public BigDecimal getMonthsDuration() {
        return this.monthsDuration;
    }

    public RateType getRateType() {
        return this.rateType;
    }

    public BigDecimal getInterestPercent() {
        return this.wiborPercent.add(this.bankMarginPercent).divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestDisplay() {
        return this.wiborPercent.add(this.bankMarginPercent).setScale(2, RoundingMode.HALF_UP);
    }

    public Map<Integer, BigDecimal> getOverpaymentSchema() {
        return this.overPaymentSchema;
    }

    public String getOverpaymentReduceWay() {
        return this.overpaymentReduceWay;
    }

    public BigDecimal getOverpaymentProvisionPercent() {
        return this.overpaymentProvisionPercent.divide(PERCENT, 4, RoundingMode.HALF_UP);
    }

    public BigDecimal getOverpaymentProvisionMoths() {
        return this.overpaymentProvisionMoths;
    }
}