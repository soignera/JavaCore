package lesson9;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class Collections {

    public static void demoAddToInlineList() {
        List<String> namesInLine = new ArrayList<>(Arrays.asList("Ivan", "petr"));
        namesInLine.add("Dmitry");
        System.out.println(namesInLine);
        namesInLine.sort(Comparator.naturalOrder());
        System.out.println(namesInLine);

    }


    public static void main(String[] args) {

        demoAddToInlineList();
       
    }

}
