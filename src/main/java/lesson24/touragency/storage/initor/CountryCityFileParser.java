package lesson24.touragency.storage.initor;

import lesson24.touragency.country.domain.Country;
import lesson24.touragency.storage.initor.StorageInitializer;
import lesson24.touragency.storage.initor.datasourcereader.CountriesWithCitiesTxtFileParser;
import lesson24.touragency.storage.initor.datasourcereader.CountriesWithCitiesXmlStaxParser;
import lesson24.touragency.common.solution.parser.FileParser;

import java.io.File;
import java.util.List;

public class CountryCityFileParser implements Runnable {
    private StorageInitializer.DataSourceType dataSourceType;
    private List<Country> countries;
    private Thread thread;
    private File fileToParse;
    private volatile Exception parseException;

    public CountryCityFileParser(StorageInitializer.DataSourceType dataSourceType, File file) {
        this.dataSourceType = dataSourceType;
        thread = new Thread(this);
        fileToParse = file;
    }

    @Override
    public void run() {
        try {
            countries = getCountriesFromStorage(fileToParse.getAbsolutePath(), dataSourceType);
        } catch (Exception e) {
            System.out.println("Error while parse file with countries");
            parseException = e;
        }
    }

    public synchronized List<Country> getCountries() {
        return countries;
    }

    public void asyncParseCountries() {
        thread.start();
    }

    public void blockUntilJobIsFinished() throws InterruptedException {
        thread.join();
    }

    private List<Country> getCountriesFromStorage(String filePath, StorageInitializer.DataSourceType dataSourceType) throws Exception {

        FileParser<List<Country>> dataSourceReader = null;

        switch (dataSourceType) {

            case TXT_FILE: {
                dataSourceReader = new CountriesWithCitiesTxtFileParser();
                break;
            }

            case XML_FILE: {
                //dataSourceReader = new MarksWithModelsXmlDomParser();
                dataSourceReader = new CountriesWithCitiesXmlStaxParser();
                //    dataSourceReader = new MarksWithModelsXmlSaxParser();
                break;
            }
        }

        return dataSourceReader.parseFile(filePath);
    }

    public Exception getParseException() {
        return parseException;
    }
}
