package com.t9.bsshop.controller.adv;

import com.t9.bsshop.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;

@Controller
@RequestMapping("/adv/blog")
public class BlogController {
    @Autowired
    private BlogService bs;
    @RequestMapping(value={""}, method = RequestMethod.POST)
    public ModelAndView showblogPage(Model model, @RequestParam(value = "keyword", required = false) String keyword)
    {
        ModelAndView mv = new ModelAndView("adv/blog/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed"};
        if(keyword != null){
            model.addAttribute("search",keyword);
            model.addAttribute("listblogs",bs.getAllBy(keyword));
        }else{
            model.addAttribute("listblogs",bs.getAll());
        }
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value={""})
    public ModelAndView showblogPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/blog/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed"};
        model.addAttribute("listblogs",bs.getAll());
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value = {"/ae"})
    public ModelAndView aePage(Model model, @RequestParam(value = "id", required = false) String id,
                               @RequestParam(value = "err", required = false) String err){

        ModelAndView mv = new ModelAndView("adv/blog/ae");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(id!=null) {
            model.addAttribute("blog", bs.getById(Integer.parseInt(id)));
            model.addAttribute("ed", "edit");
        }else{
            model.addAttribute("blog", null);
            model.addAttribute("ed", "add");
        }
        if("invalid".equalsIgnoreCase(err)){
            model.addAttribute("err","true");
        }
        return  mv;
    }

    @RequestMapping(value = {"/ae/add"})
    public String addblogPage(Model model, @RequestParam(value = "txtName") String name,
                           @RequestParam(value = "txtNotes") String dep,
                           @RequestParam(value = "txtSlug") File slug,
                           @RequestParam(value = "typ") int typ)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && slug!=null){
            try {
                FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir")+"/src/main/webapp/assets/img/"+slug.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bs.addBlog(name,dep,slug.getName());
            switch (typ){
                case 1: return "redirect:/adv/blog";
                case 2: return "redirect:/adv/blog/ae";
                default: return "redirect:/adv/";
            }
        }else{
            return "redirect:/adv/blog/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/ae/edit"})
    public String editblogPage(Model model, @RequestParam(value = "txtName") String name,
                           @RequestParam(value = "txtNotes") String dep,
                           @RequestParam(value = "txtSlug") File slug,
                           @RequestParam(value = "txtId") long id)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && slug != null){
            try {
                FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir")+"/src/main/webapp/assets/img/"+slug.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            bs.editBlog(id,name,dep,slug.getName());
            return "redirect:/adv/blog";
        }else{
            return "redirect:/adv/blog/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/ae/del"})
    public ModelAndView delblogPage(Model model, @RequestParam(value = "conf") String conf,
                                 @RequestParam(value = "id") String id)
    {
        if (conf.equalsIgnoreCase("true")){
            bs.delBlog(Long.parseLong(id));
        }else{
            model.addAttribute("getconf", true);
        }
        return showblogPage(model);
    }
}
