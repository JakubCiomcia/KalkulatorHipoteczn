package model;

import java.math.BigDecimal;

public class Summary {
    private final BigDecimal interestSum;
    private final BigDecimal overpaymentProvisionSum;
    private final BigDecimal totalLostSum;
    private BigDecimal totalCapital;

    public Summary(BigDecimal interestSum, BigDecimal overpaymentProvisionSum, BigDecimal totalLostSum, BigDecimal totalCapital) {
        this.interestSum = interestSum;
        this.overpaymentProvisionSum = overpaymentProvisionSum;
        this.totalLostSum = totalLostSum;
        this.totalCapital = totalCapital;
    }

    public BigDecimal getInterestSum() {
        return this.interestSum;
    }

    public BigDecimal getOverpaymentProvisionSum() {
        return this.overpaymentProvisionSum;
    }

    public BigDecimal getTotalLostSum() {
        return this.totalLostSum;
    }

    public BigDecimal getTotalCapital() {
        return this.totalCapital;
    }
}
