package com.t9.bsshop.service;


import com.t9.bsshop.model.Account;
import com.t9.bsshop.repository.AccountRepo;
import com.t9.bsshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class AccService {
    @Autowired
    private AccountRepo ar;
    @Autowired
    private OrderRepo or;
    public List<Account> getAll(){
        return this.ar.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }
    public long getTotal(){
        return this.ar.count();
    }

    public Account getById(long id){
        return this.ar.getById(id);
    }



    public boolean delAcc(long id){
        try {
            ar.deleteById(id);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

    public List<Account> getAllBy(String name, String email, String add, String role) {
        List<Account> all = this.ar.findAll();

        if(!"".equalsIgnoreCase(name)){
            Iterator<Account> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getFullName().contains(name)){
                    i.remove();
                }
            }
        }
        if(!"".equalsIgnoreCase(email)){
            Iterator<Account> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getEmail().contains(email)){
                    i.remove();
                }
            }
        }

        if(!"".equalsIgnoreCase(add)){
            Iterator<Account> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getAddress().contains(add)) {
                    i.remove();
                }
            }
        }
        if(!"".equalsIgnoreCase(role)){
            Iterator<Account> i = all.iterator();
            boolean cd;
            if(Integer.parseInt(role) ==1){
                cd=true;
            }else{
                cd=false;
            }
            while (i.hasNext()){
                if((i.next().isAdmin() ^ cd)) {
                    i.remove();
                }
            }
        }
        return all;
    }

    public void addadm(String name, String email, String pass, String tel, String addr, String path) {
        Account ac  = new Account();
        ac.setAddress(addr);
        ac.setAdmin(true);
        ac.setAvatar(path);
        ac.setEmail(email);
        ac.setFullName(name);
        ac.setPassword(pass);
        ac.setTel(tel);
        ar.save(ac);
    }

    public void editadm(String id,String name, String email, String pass, String tel, String addr, String path) {
        Account ac = ar.getById(Long.parseLong(id));
        ac.setAddress(addr);
        ac.setAvatar(path);
        ac.setEmail(email);
        ac.setFullName(name);
        ac.setPassword(pass);
        ac.setTel(tel);
        ar.save(ac);
    }

}
