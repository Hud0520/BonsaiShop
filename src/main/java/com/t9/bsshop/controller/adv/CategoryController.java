package com.t9.bsshop.controller.adv;

import com.t9.bsshop.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller("AdminCategory")
@RequestMapping("/adv/category")
public class CategoryController {
    @Autowired
    private CategoryService cs;
    @RequestMapping(value={""}, method = RequestMethod.GET)
    public ModelAndView showCatPage(Model model,@RequestParam(value = "keyword", required = false) String keyword)
    {
        ModelAndView mv = new ModelAndView("adv/category/index");
        String[] activemenu={"collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed","collapsed"};
        if(keyword != null){
            model.addAttribute("search",keyword);
            model.addAttribute("cats",cs.getAllBy(keyword));
        }else{
            model.addAttribute("cats",cs.getAll());
        }
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value={""}, method = RequestMethod.POST)
    public ModelAndView showCatPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/category/index");
        String[] activemenu={"collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed","collapsed"};
        model.addAttribute("cats",cs.getAll());
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value = {"/ae"})
    public ModelAndView cataePage(Model model, @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "err", required = false) String err){

        ModelAndView mv = new ModelAndView("adv/category/ae");
        String[] activemenu={"collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(id!=null) {
            model.addAttribute("cat", cs.getById(Integer.parseInt(id)));
            model.addAttribute("ed", "edit");
        }else{
            model.addAttribute("cat", null);
            model.addAttribute("ed", "add");
        }
        if("invalid".equalsIgnoreCase(err)){
            model.addAttribute("err","true");
        }
        return  mv;
    }

    @RequestMapping(value = {"/ae/add"})
    public String cataPage(Model model, @RequestParam(value = "txtName") String name,
                                        @RequestParam(value = "txtNotes") String dep,
                                        @RequestParam(value = "txtSlug") String slug,
                                        @RequestParam(value = "typ") int typ)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && !"".equalsIgnoreCase(slug)){
            cs.addCat(name,dep,slug);
            switch (typ){
                case 1: return "redirect:/adv/category";
                case 2: return "/adv/category/ae";
                default: return "redirect:/adv/";
            }
        }else{
            return "redirect:/adv/category/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/ae/edit"})
    public String catePage(Model model, @RequestParam(value = "txtName") String name,
                           @RequestParam(value = "txtNotes") String dep,
                           @RequestParam(value = "txtSlug") String slug,
                           @RequestParam(value = "txtId") long id)
    {   if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && !"".equalsIgnoreCase(slug)){
            cs.editCat(id,name,dep,slug);
            return "redirect:/adv/category";
        }else{
            return "redirect:/adv/category/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/del"})
    public String catePage(Model model, @RequestParam(value = "conf") String conf,
                           @RequestParam(value = "id") String id)
    {
        if (conf.equalsIgnoreCase("true")){
            if(!cs.delCat(Integer.parseInt(id))){
                return "redirect:/adv/category?deler=true";
            }
        }
        return "redirect:/adv/category";
    }

}
