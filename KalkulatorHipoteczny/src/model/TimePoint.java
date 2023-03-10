package model;

import java.math.BigDecimal;
import java.time.LocalDate;

public class TimePoint {
    private final LocalDate date;
    private final BigDecimal month;
    private final BigDecimal year;

    public TimePoint(LocalDate date, BigDecimal year, BigDecimal month) {
        this.date = date;
        this.year = year;
        this.month = month;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public BigDecimal getMonth() {
        return this.month;
    }

    public BigDecimal getYear() {
        return this.year;
    }
}
