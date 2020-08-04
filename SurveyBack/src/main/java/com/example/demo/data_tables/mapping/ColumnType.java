package com.example.demo.data_tables.mapping;


import com.example.demo.data_tables.repository.DateParser;
import org.springframework.util.StringUtils;

import java.text.ParseException;
import java.util.Date;

public abstract class ColumnType {
    private static final String CODE_STRING = "string";
    private static final String CODE_INTEGER = "integer";
    private static final String CODE_DOUBLE = "double";
    private static final String CODE_FLOAT = "float";
    private static final String CODE_LONG = "long";
    private static final String CODE_DATE = "date";
    private static final String CODE_BOOLEAN = "boolean";
    private static final String CODE_ARRAY = "array";

    public static final ColumnType STRING = new StringColumnType();
    public static final ColumnType INTEGER = new IntegerColumnType();
    public static final ColumnType DOUBLE = new DoubleColumnType();
    public static final ColumnType FLOAT = new FloatColumnType();
    public static final ColumnType LONG = new LongColumnType();
    public static final ColumnType DATE = new DateColumnType();
    public static final ColumnType BOOLEAN = new BooleanColumnType();
    public static final ColumnType ARRAY = new ArrayColumnType();

    private String code;
    private boolean comparable;

    protected ColumnType(String code, boolean isComparable) {
        this.code = code;
        this.comparable = isComparable;
    }

    public static ColumnType parse(String text) {
        // default value is STRING
        ColumnType result = STRING;
        if (StringUtils.hasLength(text)) {
            switch (text.toLowerCase()) {
                case CODE_INTEGER:
                    result = INTEGER;
                    break;
                case CODE_DOUBLE:
                    result = DOUBLE;
                    break;
                case CODE_FLOAT:
                    result = FLOAT;
                    break;
                case CODE_LONG:
                    result = LONG;
                    break;
                case CODE_DATE:
                    result = DATE;
                    break;
                case CODE_BOOLEAN:
                    result = BOOLEAN;
                    break;
                case CODE_ARRAY:
                    result = ARRAY;
                    break;
                default:
                    result = STRING;
                    break;
            }
        }
        return result;
    }

    public abstract Object tryConvert(String text);

    static final class StringColumnType extends ColumnType {
        StringColumnType() {
            super(CODE_STRING, true);
        }

        @Override
        public Object tryConvert(String text) {
            return text;
        }
    }

    static final class DateColumnType extends ColumnType {
        DateColumnType() {
            super(CODE_DATE, true);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            try {
                Date parsedDate = DateParser.parse(text);
                result = parsedDate;
            } catch (ParseException pe) {
                result = text;
            }
            return result;
        }
    }

    static final class IntegerColumnType extends ColumnType {
        IntegerColumnType() {
            super(CODE_INTEGER, true);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            try {
                Integer parsedInteger = Integer.parseInt(text);
                result = parsedInteger;
            } catch (NumberFormatException nfe) {
                result = text;
            }
            return result;
        }
    }

    static final class DoubleColumnType extends ColumnType {
        DoubleColumnType() {
            super(CODE_DOUBLE, true);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            try {
                Double parsedDouble = Double.parseDouble(text);
                result = parsedDouble;
            } catch (NumberFormatException nfe) {
                result = text;
            }
            return result;
        }
    }

    static final class FloatColumnType extends ColumnType {
        FloatColumnType() {
            super(CODE_FLOAT, true);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            try {
                Float parsedFloat = Float.parseFloat(text);
                result = parsedFloat;
            } catch (NumberFormatException nfe) {
                result = text;
            }
            return result;
        }
    }

    static final class LongColumnType extends ColumnType {
        LongColumnType() {
            super(CODE_LONG, true);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            try {
                Long parsedLong = Long.parseLong(text);
                result = parsedLong;
            } catch (NumberFormatException nfe) {
                result = text;
            }
            return result;
        }
    }

    static final class BooleanColumnType extends ColumnType {
        BooleanColumnType() {
            super(CODE_BOOLEAN, false);
        }

        @Override
        public Object tryConvert(String text) {
            Object result = text;
            Boolean parsedBoolean = Boolean.parseBoolean(text);
            result = parsedBoolean;
            return result;
        }
    }

    static final class ArrayColumnType extends ColumnType {
        ArrayColumnType() {
            super(CODE_ARRAY, true);
        }

        @Override
        public Object tryConvert(String text) {
            return text;
        }
    }

    public static String getCodeString() {
        return CODE_STRING;
    }

    public static String getCodeInteger() {
        return CODE_INTEGER;
    }

    public static String getCodeDouble() {
        return CODE_DOUBLE;
    }

    public static String getCodeFloat() {
        return CODE_FLOAT;
    }

    public static String getCodeLong() {
        return CODE_LONG;
    }

    public static String getCodeDate() {
        return CODE_DATE;
    }

    public static String getCodeBoolean() {
        return CODE_BOOLEAN;
    }

    public static String getCodeArray() {
        return CODE_ARRAY;
    }

    public static ColumnType getSTRING() {
        return STRING;
    }

    public static ColumnType getINTEGER() {
        return INTEGER;
    }

    public static ColumnType getDOUBLE() {
        return DOUBLE;
    }

    public static ColumnType getFLOAT() {
        return FLOAT;
    }

    public static ColumnType getLONG() {
        return LONG;
    }

    public static ColumnType getDATE() {
        return DATE;
    }

    public static ColumnType getBOOLEAN() {
        return BOOLEAN;
    }

    public static ColumnType getARRAY() {
        return ARRAY;
    }

    public String getCode() {
        return code;
    }

    public boolean isComparable() {
        return comparable;
    }
}
