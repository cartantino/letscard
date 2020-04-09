package com.letscard.struts2.converter;

import com.opensymphony.xwork2.conversion.TypeConversionException;
import org.apache.struts2.util.StrutsTypeConverter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Map;

public class LocalDateConverter extends StrutsTypeConverter {

    @Override
    public Object convertFromString(Map context, String[] values, Class toClass) {
        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE;
        if (values == null || values.length == 0 || values[0] == null || values[0].isEmpty())
            return null;
        else {
            try {
                return LocalDate.parse(values[0], f);
            } catch (DateTimeParseException d) {
                throw new TypeConversionException(d);
            }
        }
    }

    @Override
    public String convertToString(Map context, Object o) {
        DateTimeFormatter f = DateTimeFormatter.ISO_LOCAL_DATE;
        return o == null ? null : ((LocalDate) o).format(f);
    }
}
