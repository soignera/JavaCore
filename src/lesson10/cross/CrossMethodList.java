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
        Set<String> setStr = new HashSet<>();
        for (String s1 : list) {
            for (String s2 : list2) {
                if (s1==s2){
                    setStr.add(s1);

                }
            }
        }
        System.out.println(setStr);
//        for (String s : setStr) {
//            System.out.println(s);
//        }
    }
}
