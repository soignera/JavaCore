package lesson22.touragency.storage.initor.datasourcereader.sax;

import lesson17.touragency.common.solution.xml.sax.XmlSaxUtils;
import lesson17.touragency.country.domain.Country;
import lesson17.touragency.common.solution.parser.FileParser;
import lesson17.touragency.storage.initor.datasourcereader.sax.CountriesWithCitiesSaxHandler;

import javax.xml.parsers.SAXParser;
import java.io.File;
import java.util.List;

public class CountriesWithCitiesXmlSaxParser implements FileParser<List<Country>> {
    @Override
    public List<Country> parseFile(String file) throws Exception {
        SAXParser saxParser = XmlSaxUtils.getParser();

        lesson17.touragency.storage.initor.datasourcereader.sax.CountriesWithCitiesSaxHandler saxHandler = new CountriesWithCitiesSaxHandler();
        saxParser.parse(new File(file), saxHandler);
        return saxHandler.getCountries();
    }
}
