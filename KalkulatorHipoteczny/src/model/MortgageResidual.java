package model;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MortgageResidual {
    private final BigDecimal amount;
    private final BigDecimal duration;

    public MortgageResidual(BigDecimal residualAmount, BigDecimal residualDuration) {
        this.amount = residualAmount;
        this.duration = residualDuration;
    }

    public BigDecimal getAmount() {
        return this.amount.setScale(2, RoundingMode.HALF_UP);
    }

    public BigDecimal getDuration() {
        return this.duration;
    }
}
