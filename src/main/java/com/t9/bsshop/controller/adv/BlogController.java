package com.t9.bsshop.controller.adv;

import com.t9.bsshop.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

@Controller("BlogAdmin")
@RequestMapping("/adv/blog")
public class BlogController {
    @Autowired
    private BlogService bs;
    private Path uploadPath= Paths.get(System.getProperty("user.dir")+"/src/main/webapp/assets/img");
    @RequestMapping(value={""}, method = RequestMethod.POST)
    public ModelAndView showblogPage(Model model, @RequestParam(value = "keyword", required = false) String keyword)
    {
        ModelAndView mv = new ModelAndView("adv/blog/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed"};
        if(keyword != null){
            model.addAttribute("search",keyword);
            model.addAttribute("blogs",bs.getAllBy(keyword));
        }else{
            model.addAttribute("blogs",bs.getAll());
        }
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value={""})
    public ModelAndView showblogPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/blog/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","collapsed","collapsed","","collapsed"};
        model.addAttribute("blogs",bs.getAll());
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
                           @RequestParam(value = "txtSlug") MultipartFile slug,
                           @RequestParam(value = "typ") int typ)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && slug!=null && !slug.isEmpty()){
            String fileName=String.valueOf(System.currentTimeMillis());
            try {
                slug.transferTo(uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            bs.addBlog(name,dep,fileName);
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
                           @RequestParam(value = "txtSlug") MultipartFile slug,
                           @RequestParam(value = "txtId") long id)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(dep) && slug != null){
            String fileName=bs.getById(id).getThumbnail();
            if(!slug.isEmpty()){
                try {
                    Files.deleteIfExists(uploadPath.resolve(fileName));
                    fileName=String.valueOf(System.currentTimeMillis());
                    slug.transferTo(uploadPath.resolve(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            bs.editBlog(id,name,dep,fileName);
            return "redirect:/adv/blog";
        }else{
            return "redirect:/adv/blog/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/ae/del"})
    public ModelAndView delblogPage(Model model, @RequestParam(value = "conf") String conf,
                                 @RequestParam(value = "id") long id)
    {
        if (conf.equalsIgnoreCase("true")){
            String fileName=bs.getById(id).getThumbnail();
            bs.delBlog(id);
            try {
                Files.deleteIfExists(uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            model.addAttribute("getconf", true);
        }
        return showblogPage(model);
    }
}
