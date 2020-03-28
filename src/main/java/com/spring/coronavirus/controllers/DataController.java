package com.spring.coronavirus.controllers;

import com.spring.coronavirus.dao.LocationDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * @author George.Giazitzis
 */
@Controller
public class DataController {

    @Autowired
    LocationDao locationDao;

    @GetMapping("/")
    private String home(ModelMap mm) {
        int[] arr = locationDao.totalData();
        mm.addAttribute("totalCases", arr[0]);
        mm.addAttribute("totalNewCases", arr[1]);
        mm.addAttribute("totalDeaths", arr[2]);
        mm.addAttribute("totalNewDeaths", arr[3]);
        mm.addAttribute("totalRecovered", arr[4]);
        mm.addAttribute("totalNewRecovered", arr[5]);
        mm.addAttribute("locationList", locationDao.getList());
        return "home";
    }
}
