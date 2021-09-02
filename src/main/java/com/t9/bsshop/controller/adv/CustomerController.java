package com.t9.bsshop.controller.adv;


import com.t9.bsshop.service.AccService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Controller
@RequestMapping(value={"/adv/customer"})
public class CustomerController {
    @Autowired
    private AccService as;

    @RequestMapping (value={""}, method = RequestMethod.POST)
    public ModelAndView showCustomerPage(Model model,
                                         @RequestParam(value = "txtName") String name,
                                         @RequestParam(value = "txtEmail") String email,
                                         @RequestParam(value = "txtrole") String role,
                                         @RequestParam(value = "txtAddress") String add)
    {
        ModelAndView mv = new ModelAndView("adv/customer/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(!name.equalsIgnoreCase("") || !email.equalsIgnoreCase("") || !add.equalsIgnoreCase("") || !role.equalsIgnoreCase("")) {
            String[] srearh = new String[]{name,role.equals(1) ? "true" : role.equals("") ? "" : "false",email,add};
            model.addAttribute("search",srearh);
            model.addAttribute("accs", as.getAllBy(name, email, add, role));
        }else{
            model.addAttribute("accs",as.getAll());
        }
        return mv;
    }

    @RequestMapping (value={""})
    public ModelAndView showCustomerPage(Model model)
    {
        ModelAndView mv = new ModelAndView("adv/customer/index");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed"};
        model.addAttribute("accs", as.getAll());
        mv.addObject("menuactive",activemenu);
        return mv;
    }
    @RequestMapping(value = {"/ae/del"})
    public String plantdelPage(Model model, @RequestParam(value = "conf") String conf,
                               @RequestParam(value = "id") String id)
    {
        if (conf.equalsIgnoreCase("true")){
            if(!as.delAcc(Integer.parseInt(id)))
            {
                return "redirect:/adv/customer?deler=true";
            };
        }
        return "redirect:/adv/customer";
    }

    @RequestMapping(value = {"/ae"})
    public ModelAndView aePage(Model model, @RequestParam(value = "id", required = false) String id,
                                  @RequestParam(value = "err", required = false) String err){

        ModelAndView mv = new ModelAndView("adv/customer/ae");
        String[] activemenu={"collapsed","collapsed","collapsed","collapsed","","collapsed","collapsed","collapsed"};
        mv.addObject("menuactive",activemenu);
        if(id!=null) {
            model.addAttribute("ed", "edit");
            model.addAttribute("acc",as.getById(Long.parseLong(id)));
        }else{
            model.addAttribute("acc", null);
            model.addAttribute("ed", "add");
        }
        if("invalid".equalsIgnoreCase(err)){
            model.addAttribute("err","true");
        }
        return  mv;
    }
    @RequestMapping(value = {"/ae/add"})
    public String cAddPage(Model model, @RequestParam(value = "txtName") String name,
                           @RequestParam(value = "txtEmail") String email,
                           @RequestParam(value = "txtPass") String pass,
                           @RequestParam(value = "txtPass2") String pass2,
                           @RequestParam(value = "txtTel") String tel,
                           @RequestParam(value = "txtAdd") String addr,
                           @RequestParam(value = "fAvt") File favt,
                           @RequestParam(value = "typ") int typ)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(email) && !"".equalsIgnoreCase(pass) &&
        pass.equalsIgnoreCase(pass2) && !"".equalsIgnoreCase(tel) && !"".equalsIgnoreCase(addr) && favt != null){
            String path="assets/img/"+favt.getName();
            try {
                BufferedImage bImage = ImageIO.read(favt);
                ImageIO.write(bImage,"jpg",new File(System.getProperty("user.dir")+"/src/main/webapp/assets/img/"+favt.getName()));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            as.addadm(name,email,pass,tel,addr,favt.getName());
            switch (typ){
                case 1: return "redirect:/adv/customer";
                case 2: return "redirect:/adv/customer/ae";
                default: return "redirect:/adv/";
            }
        }else{
            return "redirect:/adv/customer/ae?err=invalid";
        }
    }

    @RequestMapping(value = {"/ae/edit"})
    public String cEditPage(Model model, @RequestParam(value = "txtName") String name,
                            @RequestParam(value = "txtEmail") String email,
                            @RequestParam(value = "txtPass") String pass,
                            @RequestParam(value = "txtPass2") String pass2,
                            @RequestParam(value = "txtTel") String tel,
                            @RequestParam(value = "txtAdd") String addr,
                            @RequestParam(value = "fAvt") File favt,
                            @RequestParam(value = "txtId") String id)
    {
        if(!"".equalsIgnoreCase(name) && !"".equalsIgnoreCase(email) && !"".equalsIgnoreCase(pass) &&
                pass.equalsIgnoreCase(pass2) && !"".equalsIgnoreCase(tel) && !"".equalsIgnoreCase(addr) && favt != null){

            try {
                FileOutputStream fo = new FileOutputStream(System.getProperty("user.dir")+"/src/main/webapp/assets/img/"+favt.getName());
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            as.editadm(id,name,email,pass,tel,addr,favt.getAbsolutePath());
            return "redirect:/adv/customer";
    }else{
        return "redirect:/adv/customer/ae?err=invalid";
    }
    }

}
