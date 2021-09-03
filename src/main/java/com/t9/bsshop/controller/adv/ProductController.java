package com.t9.bsshop.controller.adv;


import com.t9.bsshop.service.CategoryService;
import com.t9.bsshop.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Controller
@RequestMapping("/adv/product")
public class ProductController {
    @Autowired
    private ProductService ps;
    @Autowired
    private CategoryService cs;
    private Path uploadPath=Paths.get(System.getProperty("user.dir")+"/src/main/webapp/assets/img");
    @RequestMapping(value={""}, method = RequestMethod.POST)
    public ModelAndView showProductPage(Model model,
                                        @RequestParam(value = "txtName") String name,
                                        @RequestParam(value = "txtCatName") String cat,
                                        @RequestParam(value = "txtPrice") String price
    )
    {
        ModelAndView mv = new ModelAndView("adv/product/index");
        String[] activemenu={"collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed"};
        model.addAttribute("cats",cs.getAll());
        mv.addObject("menuactive",activemenu);
        model.addAttribute("title","Sản phẩm");
        if(!name.equalsIgnoreCase("") || !cat.equalsIgnoreCase("") || !price.equalsIgnoreCase("")) {
            String[] srearh = new String[]{name,cat,price};
            model.addAttribute("search",srearh);
            model.addAttribute("plants", ps.getAllBy(name, cat, price));
        }else{
            model.addAttribute("plants",ps.getAll());
        }
        return mv;
    }
    @RequestMapping(value={""})
    public ModelAndView showProductPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/product/index");
        String[] activemenu={"collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed"};
        model.addAttribute("cats",cs.getAll());
        model.addAttribute("plants",ps.getAll());
        mv.addObject("menuactive",activemenu);
        model.addAttribute("title","Sản phẩm");
        return mv;
    }


    @RequestMapping(value = {"/ae"})
    public ModelAndView prodPage(Model model, @RequestParam(value = "id", required = false) String id,
                                 @RequestParam(value = "err", required = false) String err){

        ModelAndView mv = new ModelAndView("adv/product/ae");
        String[] activemenu={"collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(id!=null) {
            model.addAttribute("plant", ps.getById(Integer.parseInt(id)));
            model.addAttribute("ed", "edit");
        }else{
            model.addAttribute("plant", null);
            model.addAttribute("ed", "add");
        }
        if("invalid".equalsIgnoreCase(err)){
            model.addAttribute("err","true");
        }
        model.addAttribute("cats",cs.getAll());
        return  mv;
    }

    @RequestMapping(value = {"/ae/add"})
    public String aPage(Model model,
                        @RequestParam(value = "txtName") String name,
                        @RequestParam(value = "txtCatName") long id,
                        @RequestParam(value = "txtDetail") String detail,
                        @RequestParam(value = "txtQty") String qty,
                        @RequestParam(value = "txtPrice") String price,
                        @RequestParam(value = "txtNotes") String dep,
                        @RequestParam(value = "txtSlug") String slug,
                        @RequestParam(value = "file") MultipartFile file,
                        @RequestParam(value = "typ") int typ)
    {
        if(!name.equals("") && !dep.equals("") && !detail.equals("") && !qty.equals("") && !price.equals("") && !slug.equals("") && !file.isEmpty()) {
            String fileName=String.valueOf(System.currentTimeMillis());
            try {
                file.transferTo(uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
            ps.addplant(name, detail, cs.getById(id), dep, slug, qty, price, fileName);
            switch (typ){
                case 1: return "redirect:/adv/product";
                case 2: return "redirect:/adv/product/ae";
                default: return "redirect:/adv/";
            }
        }else{
            return "redirect:/adv/product/ae?err=invalid";
        }

    }

    @RequestMapping(value = {"/ae/edit"})
    public String ePage(Model model,
                        @RequestParam(value = "txtName") String name,
                        @RequestParam(value = "txtCatName") long idc,
                        @RequestParam(value = "txtDetail") String detail,
                        @RequestParam(value = "txtQty") String qty,
                        @RequestParam(value = "txtPrice") String price,
                        @RequestParam(value = "txtNotes") String dep,
                        @RequestParam(value = "txtSlug") String slug,
                        @RequestParam(value = "file") MultipartFile file,
                        @RequestParam(value = "id") long id)
    {
        if(!name.equals("") && !dep.equals("") && !detail.equals("") && !qty.equals("") && !price.equals("") && !slug.equals("") && file !=null) {
            String fileName=ps.getById(id).getThumbnail();
            if(!file.isEmpty()){
                try {
                    Files.deleteIfExists(uploadPath.resolve(fileName));
                    fileName=String.valueOf(System.currentTimeMillis());
                    file.transferTo(uploadPath.resolve(fileName));
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            ps.editplant(id, name, detail, cs.getById(idc), dep, slug, qty, price, fileName);
            return "redirect:/adv/product";
        }else
        {
            return "redirect:/adv/product/ae?err=invalid";
        }

    }

    @RequestMapping(value = {"/ae/del"})
    public String plantdelPage(Model model, @RequestParam(value = "conf") String conf,
                                 @RequestParam(value = "id") long id)
    {
        if (conf.equalsIgnoreCase("true")){
            String fileName=ps.getById(id).getThumbnail();
            if(!ps.delPlant(id)){
                return "redirect:/adv/product?deler=true";
            }
            try {
                Files.deleteIfExists(uploadPath.resolve(fileName));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return "redirect:/adv/product";
    }
}
