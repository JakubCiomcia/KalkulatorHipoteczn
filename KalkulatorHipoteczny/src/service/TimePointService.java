package service;

import model.InputData;
import model.Rate;
import model.TimePoint;

import java.math.BigDecimal;

public interface TimePointService {

    TimePoint calculate(final BigDecimal rateNumber, final InputData inputData);

    TimePoint calculate(BigDecimal rateNumber, Rate previousRate);
}
