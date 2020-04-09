package com.letscard.struts2.validator;

import com.opensymphony.xwork2.validator.ValidationException;
import com.opensymphony.xwork2.validator.validators.FieldValidatorSupport;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class LocalDateValidator extends FieldValidatorSupport {

    String min;
    String max;
    LocalDate now = LocalDate.now();

    public String getMin() {
        return min;
    }

    public void setMin(String min) {
        this.min = min;
    }

    public String getMax() {
        return max;
    }

    public void setMax(String max) {
        this.max = max;
    }

    public LocalDate getNow() {
        return now;
    }

    public void setNow(LocalDate now) {
        this.now = now;
    }

    @Override
    public void validate(Object o) throws ValidationException {
        String fieldName = getFieldName();
        LocalDate val = (LocalDate) getFieldValue(fieldName, o);
        if (val == null)
            return;
        LocalDate minDate, maxDate;
        if (getMin() == null) {
            minDate = LocalDate.of(1900, 1, 1);
            setMin(minDate.toString());
        } else {
            try {
                minDate = LocalDate.parse(getMin());
            } catch (DateTimeParseException d) {
                minDate = LocalDate.of(1900, 1, 1);
            }
        }
        if (getMax() == null) {
            maxDate = getNow();
            setMax(maxDate.toString());
        } else {
            try {
                maxDate = LocalDate.parse(getMax());
            } catch (DateTimeParseException d) {
                maxDate = getNow();
            }
        }
        if (val.isBefore(minDate) || val.isAfter(maxDate)) {
            addFieldError(fieldName, o);
            return;
        }
    }
}
