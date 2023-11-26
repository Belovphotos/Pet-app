package ru.common.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import ru.common.utils.EnumUtils;

public enum SearchType {
    EQUAL("Равно"),
    NOT_EQUAL("Не равно"),
    START_WITH("Начинается с"),
    CONTAIN("Содержит"),
    END_WITH("Оканчивается на"),
    EMPTY("Пустой"),
    NOT_EMPTY("Не пустой"),
    NOT_START_WITH("Не начинается с"),
    NOT_CONTAIN("Не содержит"),
    NOT_END_WITH("Не оканчивается на"),
    GREATER("Больше (только для чисел и дат)"),
    GREATER_OR_EQUAL("Больше или равно (только для чисел и дат)"),
    LESS("Меньше (только для чисел и дат)"),
    LESS_OR_EQUAL("Меньше или равно (только для чисел и дат)"),
    BETWEEN("Интервал (только для чисел и дат)"),
    EXISTS("Содержит ли элемент"),
    ALL("Содержит ли все элемент"),
    GT("Больше"),
    GTE("Больше или равно"),
    LT("Меньше"),
    LTE("Меньше или равно"),
    REGEX("Регулярные выражения"),
    SIZE("Равно на колличество элементов");

    private final String string;

    SearchType(String string) {
        this.string = string;
    }

    @Override
    public String toString() {
        return string;
    }

    @JsonCreator
    public static SearchType getEnum(String strValue) {
        return EnumUtils.getEnums(strValue, values());
    }
}
