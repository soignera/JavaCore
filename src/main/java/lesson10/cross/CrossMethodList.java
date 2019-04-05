package lesson10.cross;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CrossMethodList {
    public static void main(String[] args) {
    makeItUniqueDemo();
    }
    private static void makeItUniqueDemo() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        System.out.println(list);
        List<String> list2 = new ArrayList<>();
        list2.add("c");
        list2.add("d");
        list2.add("e");
        list2.add("f");
        System.out.println(list2);
        Set<String> setStrList1 = new HashSet<>(list);
        Set<String> setStrList2 = new HashSet<>(list2);
        Set<String> setStr = new HashSet<>();
        //for (String s1 : setStrList1) {
            for (String s2 : setStrList2) {
                if (setStrList1.add(s2)==false) {
                    setStr.add(s2);
                }
            }
        //}
        System.out.println(setStr);
    }
}
