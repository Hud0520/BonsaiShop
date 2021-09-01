package com.t9.bsshop.service;


import com.t9.bsshop.model.Order;
import com.t9.bsshop.repository.OrderRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

@Service
public class OrderService {
    @Autowired
    private OrderRepo or;


    public List<Order> getAllOrder(){
        return this.or.findAll(Sort.by(Sort.Direction.DESC,"id"));
    }

    public Object getById(long id){
        return this.or.getById(id);
    }



    public void editStatus(long id, String stt){
        Order ed = or.getById(id);
        ed.setStatus(stt);
        or.save(ed);
    }

    public List<Order> getAllBy(String st, String en, String stt) {
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<Order> all = or.findAll();
        if(!st.equalsIgnoreCase("")){

            Date date = null;
            try {
                date = f.parse(st);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Iterator<Order> i = all.iterator();
            while (i.hasNext()){
                if(i.next().getOrderDate().before(date)){
                    i.remove();
                }
            }
        }
        if(!en.equalsIgnoreCase("")){

            Date date = null;
            try {
                date = f.parse(en);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Iterator<Order> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getOrderDate().after(date)){
                    i.remove();
                }
            }
        }
        if(!stt.equalsIgnoreCase("")){
            Iterator<Order> i = all.iterator();
            while (i.hasNext()){
                if(!i.next().getStatus().equalsIgnoreCase(stt)){
                    i.remove();
                }
            }
        }
        return all;
    }

    public boolean del(long id){
        try {
            this.or.deleteById(id);
            return true;
        }catch (Exception ex){
            ex.printStackTrace();
            return false;
        }

    }

}
