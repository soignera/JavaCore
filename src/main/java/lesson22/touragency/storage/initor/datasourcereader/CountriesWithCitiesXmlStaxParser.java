package lesson22.touragency.storage.initor.datasourcereader;

import lesson17.touragency.city.domain.City;
import lesson17.touragency.city.domain.CityDiscriminator;
import lesson17.touragency.city.domain.ColdCity;
import lesson17.touragency.city.domain.HotCity;
import lesson17.touragency.common.solution.parser.FileParser;
import lesson17.touragency.common.solution.xml.stax.parse.CustomStaxReader;
import lesson17.touragency.country.domain.Country;

import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

import static lesson11.touragency.city.domain.CityDiscriminator.COLD;
import static lesson11.touragency.common.solution.xml.stax.XmlStaxUtils.readContent;

public class CountriesWithCitiesXmlStaxParser implements FileParser<List<Country>> {

    private final RuntimeException NO_END_TAG_FOUND_EXCEPTION = new RuntimeException("Suitable end tag NOT found");

    @Override
    public List<Country> parseFile(String file) throws Exception {

        List<Country> result = new ArrayList<>();

        try (CustomStaxReader staxReader = CustomStaxReader.newInstance(file)) {

            XMLStreamReader reader = staxReader.getReader();

            while (reader.hasNext()) {
                int eventType = reader.next();

                switch (eventType) {
                    case XMLStreamReader.START_ELEMENT: {
                        String tagName = reader.getLocalName();
                        if ("countries".equals(tagName)) {
                            result = new ArrayList<>(readDocument(reader));
                        }
                        break;
                    }

                    case XMLStreamConstants.END_ELEMENT: {
                        return result;
                    }
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<Country> readDocument(XMLStreamReader reader) throws XMLStreamException {

        List<Country> countries = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("country".equals(tagName)) {
                        countries.add(readCountry(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return countries;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private Country readCountry(XMLStreamReader reader) throws XMLStreamException {
        Country country = new Country();
        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("name".equals(tagName)) {
                        country.setName(readContent(reader));
                    } else if ("languag".equals(tagName)) {
                        country.setLanguag(readContent(reader));
                    } else if ("cities".equals(tagName)) {
                        country.setCities(readCities(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return country;
                }
            }
        }

        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private List<City> readCities(XMLStreamReader reader) throws XMLStreamException {
        List<City> cities = new ArrayList<>();

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {
                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    if ("city".equals(tagName)) {
                        cities.add(readCity(reader));
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return cities;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private City readCity(XMLStreamReader reader) throws XMLStreamException {
        String type = reader.getAttributeValue(null, "type");
        City city = createCity(type);

        while (reader.hasNext()) {
            int eventType = reader.next();

            switch (eventType) {

                case XMLStreamReader.START_ELEMENT: {
                    String tagName = reader.getLocalName();
                    appendCommonCityData(city, tagName, reader);
                    if (city instanceof ColdCity) {
                        appendPassengerAttributes((ColdCity) city, tagName, reader);
                    } else {
                        appendTruckAttributes((HotCity) city, tagName, reader);
                    }
                    break;
                }

                case XMLStreamConstants.END_ELEMENT: {
                    return city;
                }
            }
        }
        throw NO_END_TAG_FOUND_EXCEPTION;
    }

    private City createCity(String type) {
        CityDiscriminator cityDiscriminator = CityDiscriminator.valueOf(type);

        if (COLD.equals(cityDiscriminator)) {
            return new ColdCity();
        } else {
            return new HotCity();
        }
    }

    private void appendCommonCityData(City city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("name".equals(tagName)) {
            city.setName(readContent(reader));
//    ????    } else if ("climate".equals(tagName)) {
//            city.setClimate(readContent(reader));
        } else if ("population".equals(tagName)) {
            city.setPopulation(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendPassengerAttributes(ColdCity city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("coldestMonth".equals(tagName)) {
            city.setColdestMonth(readContent(reader));
        } else if ("coldestTemp".equals(tagName)) {
            city.setColdestTemp(Integer.parseInt(readContent(reader)));
        }
    }

    private void appendTruckAttributes(HotCity city, String tagName, XMLStreamReader reader) throws XMLStreamException {
        if ("hottestMonth".equals(tagName)) {
            city.setHottestMonth(readContent(reader));
        } else if ("hottestTemp".equals(tagName)) {
            city.setHottestTemp(Integer.parseInt(readContent(reader)));
        }
    }
}
