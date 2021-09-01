package com.t9.bsshop.controller.adv;

import com.t9.bsshop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller
public class ReportController {
    @Autowired
    ReportService rs;
    @RequestMapping(value={"/adv/report"})
    public ModelAndView showReportPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/report/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","collapsed",""};
        mv.addObject("menuactive",activemenu);
        model.addAttribute("reports",rs.get("",""));
        return mv;
    }

    @RequestMapping(value={"/adv/report"},method = RequestMethod.POST)
    public ModelAndView showOrderPageSearch(Model model,
                                            @RequestParam(value = "txtDateS") String st ,
                                            @RequestParam(value = "txtDateE") String en)
    {
        ModelAndView mv = new ModelAndView("adv/report/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","collapsed",""};
        mv.addObject("menuactive",activemenu);
        if(!st.equalsIgnoreCase("")|| !en.equalsIgnoreCase("")) {
            ArrayList srearh = new ArrayList();
            srearh.add(st);srearh.add(en);
            model.addAttribute("search",srearh);
        }
        model.addAttribute("reports",rs.get(st,en));
        return mv;
    }
}
