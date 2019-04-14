package lesson24.touragency.city.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public enum CityDiscriminator {
    COLD, HOT;
    static Map<String, CityDiscriminator> stringCityDiscriminatorMap = new HashMap<>();

    static {
        for (CityDiscriminator discriminator : CityDiscriminator.values()) {
            stringCityDiscriminatorMap.put(discriminator.name(), discriminator);
        }
    }

    public static Optional<CityDiscriminator> getDiscriminatorByName(String discriminatorName) {
        return Optional.ofNullable(stringCityDiscriminatorMap.get(discriminatorName));
    }

//    public static boolean isDiscriminatorExists(String discriminator) {
//        return getDiscriminatorByName(discriminator) != null;
//    }
//
//    public static boolean isDiscriminatorNotExists(String discriminator) {
//        return !isDiscriminatorExists(discriminator);
//    }
}
