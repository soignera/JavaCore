package lesson9;


import java.util.*;

public class Collectionslesson {

    public static void demoAddToInlineList() {
        List<String> namesInLine = new ArrayList<>(Arrays.asList("Ivan", "petr"));
        namesInLine.add("Dmitry");
        System.out.println(namesInLine);
        Collections.sort(namesInLine, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                    return o1.compareTo(o2);  }
        });
        System.out.println(namesInLine);
//        namesInLine.sort(Comparator.naturalOrder());
//        System.out.println(namesInLine);
//        for (int i = 0; i < (namesInLine.size()-1); i++) {
//
//            if (namesInLine.get(i).compareTo(namesInLine.get(i+1)) >0) {
//                Collections.swap(namesInLine, i, i +1);
//
//            }

  //  }
 //       System.out.println(namesInLine);
    }


    public static void main(String[] args) {
        demoAddToInlineList();
    }
}
