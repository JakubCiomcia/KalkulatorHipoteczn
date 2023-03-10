package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class RateAmounts {
    private final BigDecimal rateAmount;
    private final BigDecimal interestAmount;
    private final BigDecimal capitalAmount;
    private final Overpayment overpayment;

    public RateAmounts(BigDecimal rateAmount, BigDecimal interestAmount, BigDecimal capitalAmount, Overpayment overpayment) {
        this.rateAmount = rateAmount;
        this.interestAmount = interestAmount;
        this.capitalAmount = capitalAmount;
        this.overpayment = overpayment;
    }

    public BigDecimal getRateAmount() {
        return this.rateAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getInterestAmount() {
        return this.interestAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getCapitalAmount() {
        return this.capitalAmount.setScale(2, RoundingMode.HALF_UP);
    }

    public Overpayment getOverpayment() {
        return this.overpayment;
    }
}