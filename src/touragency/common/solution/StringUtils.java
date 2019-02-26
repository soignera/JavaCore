package touragency.common.solution;

public class StringUtils {
    public static boolean isBlank(String s) {
        return s == null || s.isEmpty();
    }

    public static boolean isNotBlank(String s) {
        return !isBlank(s);
    }
}
