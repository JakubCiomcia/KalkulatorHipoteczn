package model;

import java.math.BigDecimal;

public class MortgageResidual {
    private final BigDecimal residualAmount;
    private final BigDecimal residualDuration;

    public MortgageResidual(BigDecimal residualAmount, BigDecimal residualDuration) {
        this.residualAmount = residualAmount;
        this.residualDuration = residualDuration;
    }

    public BigDecimal getResidualAmount() {
        return this.residualAmount;
    }

    public BigDecimal getResidualDuration() {
        return this.residualDuration;
    }
}
