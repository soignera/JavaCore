package lesson24.touragency.common.business.repo;

import java.util.Comparator;

public final class CommonComparatorHolder {
//    private static Comparator<Climate> comparatorForNullableClimates = new Comparator<Climate>() {
//        @Override
//        public int compare(Climate s1, Climate s2) {
//
//            if (s1.toString() == null && s2.toString() == null) {
//                return 0;
//            } else if (s1.toString() != null) {
//                return s1.toString().compareTo(s2.toString());
//            } else {
//                return -1;
//            }
//
//        }
//    };
    private static Comparator<String> comparatorForNullableStrings = new Comparator<String>() {
        @Override
        public int compare(String s1, String s2) {

            if (s1 == null && s2 == null) {
                return 0;
            } else if (s1 != null) {
                return s1.compareTo(s2);
            } else {
                return -1;
            }

        }
    };



    private CommonComparatorHolder() {

    }
//    public static Comparator<Climate> getComparatorForNullableClimates() {
//        return comparatorForNullableClimates();
//    }



    public static Comparator<String> getComparatorForNullableStrings() {
        return comparatorForNullableStrings;
    }
//    public static Comparator<Integer> getComparatorForNullableIntegers() {
//        return comparatorForNullableIntegers();
//    }



}
