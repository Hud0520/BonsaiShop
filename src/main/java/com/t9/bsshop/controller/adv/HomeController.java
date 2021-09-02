package com.t9.bsshop.controller.adv;


import com.t9.bsshop.model.Order;
import com.t9.bsshop.service.AccService;
import com.t9.bsshop.service.CategoryService;
import com.t9.bsshop.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

@Controller("HomeAdmin")
public class HomeController {
    @Autowired
    private ReportService rs;
    @Autowired
    private AccService as;
    @Autowired
    private CategoryService cs;
    @GetMapping("/adv/")
    public ModelAndView showWelcomePage(Model model)
    {

        ModelAndView mv = new ModelAndView("adv/index");
        String[] activemenu={"","collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","collapsed"};
        ArrayList listDataCard = new ArrayList();
        List<Order> odmth = rs.getOrderMonth();
        listDataCard.add(odmth.size());
        listDataCard.add(rs.getnewOrder().size());
        listDataCard.add(rs.getHotPlant(odmth).size());
        listDataCard.add(rs.getOldPlant().size());
        listDataCard.add(rs.getSumOrder());
        model.addAttribute("card",listDataCard);
        mv.addObject("menuactive",activemenu);
        return mv;
    }

    @GetMapping(value="/adv/product/plb")
    public ModelAndView showProductLowByPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/product/index");
        String[] activemenu={"collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed"};
        model.addAttribute("cats",cs.getAll());
        model.addAttribute("plants",rs.getOldPlant());
        model.addAttribute("title","Sản phẩm ít được mua ");
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value={"/adv/product/phb"})
    public ModelAndView showProductByPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/product/index");
        String[] activemenu={"collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed"};
        model.addAttribute("cats",cs.getAll());
        model.addAttribute("plants",rs.getHotPlant(rs.getOrderMonth()));
        model.addAttribute("title","Sản phẩm đã được mua trong tháng ");
        mv.addObject("menuactive",activemenu);
        return mv;
    }

    @RequestMapping(value={"/adv/order/dhm"})
    public ModelAndView showOrderPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/order/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        model.addAttribute("orders",rs.getOrderMonth());
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value={"/adv/order/new"})
    public ModelAndView showNewOrderPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/order/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        model.addAttribute("orders",rs.getnewOrder());
        mv.addObject("menuactive",activemenu);
        return mv;
    }
}
