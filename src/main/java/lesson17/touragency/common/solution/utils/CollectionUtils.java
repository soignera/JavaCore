package lesson17.touragency.common.solution.utils;

import java.util.List;

public final class CollectionUtils {

    public static<T>  T getLast(List<T> list) {
        if (list.isEmpty()) {
            return null;
        } else {
            return list.get(list.size() - 1);
        }
    }
}
