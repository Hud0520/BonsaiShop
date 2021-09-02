package com.t9.bsshop.controller.adv;

import com.t9.bsshop.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;

@Controller("OrderAdmin")
@RequestMapping(value = {"/adv/order"})
public class OrderController {
    @Autowired
    private OrderService os;
    @RequestMapping(value={""})
    public ModelAndView showOrderPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/order/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        model.addAttribute("orders",os.getAllOrder());
        mv.addObject("menuactive",activemenu);
        return mv;
    }

    @RequestMapping(value={""},method = RequestMethod.POST)
    public ModelAndView showOrderPageSearch(Model model,
                                      @RequestParam(value = "txtDateS") String st ,
                                      @RequestParam(value = "txtDateE") String en ,
                                      @RequestParam(value = "txtCatName") String stt )
    {
        ModelAndView mv = new ModelAndView("adv/order/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(!st.equalsIgnoreCase("")|| !en.equalsIgnoreCase("")|| !"".equalsIgnoreCase(stt)) {
            ArrayList srearh = new ArrayList();
            srearh.add(st);srearh.add(en);srearh.add(stt);
            model.addAttribute("search",srearh);
            model.addAttribute("orders", os.getAllBy( st, en, stt));
        }else{
            model.addAttribute("orders",os.getAllOrder());
        }
        return mv;
    }

    @RequestMapping(value = "/view")
    public  ModelAndView showOrderView(Model model, @RequestParam(value = "id" ,required = false) String id ){
        ModelAndView mv = new ModelAndView("adv/order/view");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(id!=null) {
            model.addAttribute("order", os.getById(Long.parseLong(id)));
        }else{
            model.addAttribute("notfound",true);
        }
        return mv;
    }
    @RequestMapping(value = "/view",method = RequestMethod.POST)
    public  ModelAndView showOrderViewEd(Model model,
                                         @RequestParam(value = "id" ) String id,
                                         @RequestParam(value = "txtEx" ) String txtEx){
        ModelAndView mv;
        if(id!=null && !"".equals(txtEx)) {
             mv= new ModelAndView("adv/order/view");
            os.editStatus(Long.parseLong(id),txtEx);
            model.addAttribute("order", os.getById(Long.parseLong(id)));
        }else{
            mv= new ModelAndView("adv/order/view?err=true");
        }
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        return mv;
    }

    @RequestMapping ("/view/del")
    public String delPage(Model model, @RequestParam(value = "conf") String conf,
                               @RequestParam(value = "id") String id)
    {
        if (conf.equalsIgnoreCase("true")){
            if(!os.del(Integer.parseInt(id))){
                return "redirect:/adv/order?deler=true";
            }
        }
        return "redirect:/adv/order";
    }
}
