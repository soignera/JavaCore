package lesson8.MonthEnumMap;

import java.util.HashMap;
import java.util.Map;

public enum MonthEnumMap {
    JAN("This is cold month"),
    FEB("Blizzards"),
    NOV("Froze"),
    JULY("Warm"),
    AUGUST;
    private static Map<String, MonthEnumMap> strNameEnumItemMap;

    static {
        strNameEnumItemMap = new HashMap<>();
        for (MonthEnumMap enumItem : MonthEnumMap.values()) {
            strNameEnumItemMap.put(enumItem.name(), enumItem);
        }
    }
    private String description;

    MonthEnumMap(String description) {
        this.description = description;
    }

    MonthEnumMap() {
        this.description = "Other";
    }

    public String getDescription() {
        return description;
    }

    public static boolean isStrBelongsToEnumValues(String enumItemAsStr){
        return strNameEnumItemMap.containsKey(enumItemAsStr);
        }


}
