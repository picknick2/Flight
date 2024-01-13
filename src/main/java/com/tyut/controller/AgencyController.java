package com.tyut.controller;

import com.tyut.domain.Agency;
import com.tyut.domain.Flight;
import com.tyut.service.AgencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/agency")
public class AgencyController {
    @Autowired
    private AgencyService agencyService;

    @RequestMapping("/isLogin")
    public ModelAndView isLogin(Agency agency){
        boolean flag = agencyService.isLogin(agency);
        System.out.println(flag);
        if (flag){
            return mainJsp();
        }
        else {
            ModelAndView modelAndView = new ModelAndView();
            modelAndView.setViewName("login/agency");
            modelAndView.addObject("errMsg","登录失败");
            return modelAndView;
        }
    }

    @RequestMapping("/main")
    public ModelAndView mainJsp() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("agency/main");
        return modelAndView;
    }

    @RequestMapping("/travellerLogin")
    public ModelAndView toTravellerLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/traveller");
        return modelAndView;
    }

    @RequestMapping("/adminLogin")
    public ModelAndView toAgencyLogin(){
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("login/admin");
        return modelAndView;
    }
    @RequestMapping("/showAllFlight")
    public String showAllFlight(String flightId, Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights=agencyService.findAllFlight();
        model.addAttribute("flights", flights);
        return "agency/airline-list";
    }
    @RequestMapping("/depToFinCityFlight")
    public String depToFinCityFlight(String depCity, Model model, HttpServletRequest httpServletRequest){
        List<Flight> flights=agencyService.findFlightByCity(depCity);
        model.addAttribute("flights", flights);
        return "showFlight";
    }

}
