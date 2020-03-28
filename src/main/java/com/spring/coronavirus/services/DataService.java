package com.spring.coronavirus.services;


import com.spring.coronavirus.dao.LocationDao;
import com.spring.coronavirus.models.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


/**
 * @author George.Giazitzis
 */
@Service
public class DataService {

    @Autowired
    LocationDao locationDao;

    private static String VIRUS_CONFIRMED_CASES = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_global.csv";
    private static String VIRUS_DEATHS = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_deaths_global.csv";
    private static String VIRUS_RECOVERED = "https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";

    @PostConstruct
    @Scheduled(cron = "* 1 * * * ?") // seconds minutes hours days months years <-currently updating every hour
    public void fetchConfirmedCases() throws IOException {
        HttpURLConnection conn = establishConnection(VIRUS_CONFIRMED_CASES);
        List<LocationStats> newList = new ArrayList<>();
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        for (CSVRecord record : records) {
            LocationStats locationStat = new LocationStats();
            locationStat.setState(record.get("Province/State"));
            locationStat.setCountry(record.get("Country/Region"));
            int totalCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
            locationStat.setLatestTotalCases(totalCases);
            locationStat.setDiffFromPreviousDayCases(totalCases - previousDayCases);
            newList.add(locationStat);
        }
        locationDao.setList(newList);
        conn.disconnect();
    }

    @PostConstruct
    @Scheduled(cron = "* 1 * * * ?")
    public void fetchConfirmedDeaths() throws IOException {
        HttpURLConnection conn = establishConnection(VIRUS_DEATHS);
        List<LocationStats> newList = new ArrayList<>(locationDao.getList());
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        int count = 0;
        for (CSVRecord record : records) {
            int totalCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
            newList.get(count).setLatestTotalDeaths(totalCases);
            newList.get(count).setDiffFromPreviousDayDeaths(totalCases - previousDayCases);
            count++;
        }
        locationDao.setList(newList);
        conn.disconnect();
    }

    @PostConstruct
    @Scheduled(cron = "* 1 * * * ?")
    public void fetchConfirmedRecovered() throws IOException {
        HttpURLConnection conn = establishConnection(VIRUS_RECOVERED);
        List<LocationStats> newList = new ArrayList<>(locationDao.getList());
        InputStreamReader in = new InputStreamReader(conn.getInputStream());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(in);
        int count = 0;
        for (CSVRecord record : records) {
            int totalCases = Integer.parseInt(record.get(record.size() - 1));
            int previousDayCases = Integer.parseInt(record.get(record.size() - 2));
            newList.get(count).setLatestTotalRecovered(totalCases);
            newList.get(count).setDiffFromPreviousDayRecovered(totalCases - previousDayCases);
            count++;
        }
        locationDao.setList(newList);
        conn.disconnect();
    }

    public HttpURLConnection establishConnection(String targetUrl) throws IOException {
        URL url = new URL(targetUrl);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "csv");
        if (conn.getResponseCode() != 200) {
            throw new RuntimeException("Failed : HTTP Error code : " + conn.getResponseCode());
        }
        return conn;
    }
}

