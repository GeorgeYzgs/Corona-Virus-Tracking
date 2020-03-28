package com.spring.coronavirus.dao;

import com.spring.coronavirus.models.LocationStats;
import org.springframework.stereotype.Component;
import java.util.ArrayList;
import java.util.List;

/**
 * @author George.Giazitzis
 */
@Component
public class LocationDao {

    private List<LocationStats> list = new ArrayList();

    public List<LocationStats> getList() {
        return list;
    }

    public void setList(List<LocationStats> list) {
        this.list = list;
    }

    public int[] totalData(){
        int sumOfAllCases = 0;
        int totalNewCases = 0;
        int sumOfAllDeaths = 0;
        int totalNewDeaths = 0;
        int sumOfAllRecovered = 0;
        int totalNewRecovered = 0;
        for (int i = 0; i <list.size() ; i++) {
            sumOfAllCases+=list.get(i).getLatestTotalCases();
            totalNewCases+=list.get(i).getDiffFromPreviousDayCases();
            sumOfAllDeaths+=list.get(i).getLatestTotalDeaths();
            totalNewDeaths+=list.get(i).getDiffFromPreviousDayDeaths();
            sumOfAllRecovered+=list.get(i).getLatestTotalRecovered();
            totalNewRecovered+=list.get(i).getDiffFromPreviousDayRecovered();
        }
        return new int[]{sumOfAllCases, totalNewCases, sumOfAllDeaths, totalNewDeaths, sumOfAllRecovered, totalNewRecovered};
    }
}
