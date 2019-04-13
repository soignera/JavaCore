package lesson22.touragency.storage.initor.datasourcereader.sax;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.domain.CityDiscriminator;
import lesson17.touragency.city.domain.ColdCity;
import lesson17.touragency.city.domain.HotCity;
import lesson17.touragency.country.domain.Country;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.*;

import static lesson11.touragency.common.solution.utils.CollectionUtils.getLast;


public class CountriesWithCitiesSaxHandler extends DefaultHandler {
    private static final String INIT_DATA_PATH = "init-data";
    private static final String COUNTRIES_PATH = INIT_DATA_PATH + "/countries";
    private static final String COUNTRY_PATH = COUNTRIES_PATH + "/country";
    private static final String COUNTRY_LANGUAG_PATH = COUNTRY_PATH + "/languag";
    private static final String COUNTRY_NAME_PATH = COUNTRY_PATH + "/name";
    private static final String CITIES_PATH = COUNTRY_PATH + "/cities";
    private static final String CITY_PATH = CITIES_PATH + "/city";
    private static final String CITY_NAME_PATH = CITY_PATH + "/name";
    private static final String CITY_CLIMATE_PATH = CITY_PATH + "/climate";
    private static final String CITY_POPULATION = CITY_PATH + "/population";


    private static final String CITY_HOT_HOTTEST_MONTH = CITY_PATH + "/hottestMonth";
    private static final String CITY_HOT_HOTTEST_TEMP = CITY_PATH + "/hottestTemp";


    private static final String CITY_COLD_COLDEST_MONTH = CITY_PATH + "/coldestMonth";
    private static final String CITY_COLD_COLDEST_TEMP = CITY_PATH + "/coldestTemp";


    private StringBuilder content = new StringBuilder();
    private List<Country> countries = Collections.emptyList();
    private List<City> cities = Collections.emptyList();

    private Deque<String> tagStack = new ArrayDeque<>();

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
        content.setLength(0);
        tagStack.add(qName);

        switch (stackAsStringPath()) {
            case COUNTRIES_PATH: {
                countries = new ArrayList<>();
                break;
            }

            case COUNTRY_PATH: {
                countries.add(new Country());
                break;
            }

            case CITIES_PATH: {
                cities = new ArrayList<>();
                getLast(countries).setCities(cities);
                break;
            }

            case CITY_PATH: {
                if (isHot(attributes)) {
                    cities.add(new HotCity());
                } else {
                    cities.add(new ColdCity());
                }
                break;
            }
        }
    }

    private boolean isHot(Attributes attributes) {
        return CityDiscriminator.HOT.equals(CityDiscriminator.valueOf(attributes.getValue("type")));
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        String dataAsStr = content.toString();

        switch (stackAsStringPath()) {
            case COUNTRY_NAME_PATH: {
                getLast(countries).setName(dataAsStr);
                break;
            }

            case COUNTRY_LANGUAG_PATH: {
                getLast(countries).setLanguag(dataAsStr);
                break;
            }

            case CITY_NAME_PATH: {
                getLast(cities).setName(dataAsStr);
                break;
            }

//   ??         case CITY_CLIMATE_PATH: {
//                getLast(cities).setClimate(dataAsStr);
//                break;
//            }

            case CITY_POPULATION: {
                getLast(cities).setPopulation(Integer.parseInt(dataAsStr));
                break;
            }



            case CITY_COLD_COLDEST_MONTH: {
                getColdCity().setColdestMonth(dataAsStr);
                break;
            }

            case CITY_COLD_COLDEST_TEMP: {
                getColdCity().setColdestTemp(Integer.valueOf(dataAsStr));
                break;
            }


            case CITY_HOT_HOTTEST_MONTH: {
                getHotCity().setHottestMonth(dataAsStr);
                break;
            }

            case CITY_HOT_HOTTEST_TEMP: {
                getHotCity().setHottestTemp(Integer.valueOf(dataAsStr));
                break;
            }

        }
        tagStack.removeLast();
    }

    private ColdCity getColdCity() {
        return (ColdCity) getLast(cities);
    }

    private HotCity getHotCity() {
        return (HotCity) getLast(cities);
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        String value = new String(ch, start, length);
        content.append(value.replaceAll("\\n",""));
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        super.ignorableWhitespace(ch, start, length);
    }

    public List<Country> getCountries() {
        return countries;
    }


    private String stackAsStringPath() {
        StringBuilder fullPath = new StringBuilder();

        Iterator<String> iter = tagStack.iterator();
        while (iter.hasNext()) {
            String tag = iter.next();
            fullPath.append(tag);

            if (iter.hasNext()) {
                fullPath.append("/");
            }
        }

        return fullPath.toString();
    }}
