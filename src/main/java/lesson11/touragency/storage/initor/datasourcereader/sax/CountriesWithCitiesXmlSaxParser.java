package lesson11.touragency.storage.initor.datasourcereader.sax;

import lesson11.touragency.common.solution.xml.sax.XmlSaxUtils;
import lesson11.touragency.country.domain.Country;
import lesson11.touragency.storage.initor.datasourcereader.FileParser;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

public class CountriesWithCitiesXmlSaxParser implements FileParser<List<Country>> {
    @Override
    public List<Country> parseFile(String file) throws Exception {
        SAXParser saxParser = XmlSaxUtils.getParser();

        CountriesWithCitiesSaxHandler saxHandler = new CountriesWithCitiesSaxHandler();
        saxParser.parse(new File(file), saxHandler);
        return saxHandler.getCountries();
    }
}
