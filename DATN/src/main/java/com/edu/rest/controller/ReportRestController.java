package com.edu.rest.controller;

import java.util.Calendar;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.edu.reponsitory.ReportReponsitory;
import com.edu.report.hangnam;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/reports")
public class ReportRestController {
    @Autowired
    ReportReponsitory reponsitory;
    
    @GetMapping()
    public List<hangnam> getAll(Model model, @RequestParam(value = "month", defaultValue = "10") int month) {
        Calendar cal = Calendar.getInstance();
        Map<Integer, Double> surveyMap = new LinkedHashMap<>();
      
        List<hangnam> list = reponsitory.reportthang(month);
        for (int i = 0; i < list.size(); i++) {
            surveyMap.put(list.get(i).getDate(), list.get(i).getSum());
           
        }
        model.addAttribute("month", month);
        model.addAttribute("sum", reponsitory.reporttongthang(month));
        
        return reponsitory.reportthang(month);
    }
}
