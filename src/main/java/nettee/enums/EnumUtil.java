package nettee.enums;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

public final class EnumUtil {
    private EnumUtil(){}
    public static <E extends Enum<E>, T> boolean isUniqueAllOf(Class<E> enumClass, Function<E, T> getter) {
        Set<T> set = new HashSet<>();
        for (var item: enumClass.getEnumConstants()) {
            set.add(getter.apply(item)); //static int getCode(SampleStatus item) {return item.code}
        }
//        return set.size() == enumClass.getEnumConstants().length;
        return EnumSet.allOf(enumClass).stream()
                .map(getter)
                .collect(Collectors.toSet())
                .size() == enumClass.getEnumConstants().length;
    }

    public static <E extends Enum<E>, T> boolean isUniqueOf(E item1, E item2, Function<E, T> getter) {
        return java.util.Objects.equals(getter.apply(item1), getter.apply(item2));
    }

    public static <E extends Enum<E>, T> boolean isUniqueOf(E[] items, Function<E, T> getter) {
        Set<T> set = new HashSet<>();
        for (var item : items) {
            set.add(getter.apply(item));
        }

        return items.length == set.size();
    }


}
