package service;

import model.InputData;
import model.Overpayment;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.Map;
import java.util.Optional;

public class OverpaymentCalculationServiceImpl implements OverpaymentCalculationService {
    public OverpaymentCalculationServiceImpl() {
    }

    public Overpayment calculate(BigDecimal rateNumber, InputData inputData) {
        BigDecimal overpaymentAmount = (BigDecimal)this.calculateAmount(rateNumber, inputData.getOverPaymentSchema()).orElse(BigDecimal.ZERO);
        BigDecimal overpaymentProvision = this.calculateProvision(rateNumber, overpaymentAmount, inputData);
        return new Overpayment(overpaymentAmount, overpaymentProvision);
    }

    private Optional<BigDecimal> calculateAmount(BigDecimal rateNumber, Map<Integer, BigDecimal> overPaymentSchema) {
        Iterator var3 = overPaymentSchema.entrySet().iterator();

        Map.Entry entry;
        do {
            if (!var3.hasNext()) {
                return Optional.empty();
            }

            entry = (Map.Entry)var3.next();
        } while(!rateNumber.equals(BigDecimal.valueOf((long)(Integer)entry.getKey())));

        return Optional.of((BigDecimal)entry.getValue());
    }

    private BigDecimal calculateProvision(BigDecimal rateNumber, BigDecimal overpaymentAmount, InputData inputData) {
        if (BigDecimal.ZERO.equals(overpaymentAmount)) {
            return BigDecimal.ZERO;
        } else {
            return rateNumber.compareTo(inputData.getOverpaymentProvisionMoths()) > 0 ? BigDecimal.ZERO : overpaymentAmount.multiply(inputData.getOverpaymentProvisionPercent());
        }
    }
}