package com.t9.bsshop.service;


import com.t9.bsshop.model.Order;
import com.t9.bsshop.model.Plant;
import com.t9.bsshop.model.Report;
import com.t9.bsshop.repository.OrderRepo;
import com.t9.bsshop.repository.ProductRepo;
import com.t9.bsshop.repository.ReportRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class ReportService {
    @Autowired
    private ReportRepo rr;
    @Autowired
    private OrderRepo or;
    @Autowired
    private ProductRepo pr;

    public List<Report> get(String st, String en ){
        SimpleDateFormat f = new SimpleDateFormat("yyyy-MM-dd");
        List<Report> all = rr.get();
        if(!st.equalsIgnoreCase("")){

            Date date = null;
            try {
                date = f.parse(st);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            Iterator<Report> i = all.iterator();
            while (i.hasNext()){
                if(i.next().getRpDate().before(date)){
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
            Iterator<Report> i = all.iterator();
            while (i.hasNext()){
                if(i.next().getRpDate().after(date)){
                    i.remove();
                }
            }
        }
        return all;
    }

    public List<Order> getOrderMonth(){
        Date now = new Date();
        Calendar calendar = Calendar.getInstance();
        return or.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .filter(i -> i.getOrderDate().toLocalDate().getMonthValue() == (calendar.get(Calendar.MONTH)+1)
                )
                .collect(Collectors.toList());
    }

    public List<Plant> getHotPlant(List<Order> o){
        List<Plant> tmp = pr.findAll();
        List<Plant> rs = new ArrayList<>();
        List<List<Plant>> pl = o.stream().map(x -> x.getOrderDetails().stream().map(y -> y.getPlant()).collect(Collectors.toList())).collect(Collectors.toList());
        for(List<Plant> li : pl){
            for(Plant i : li){
                rs.add(i);
            }
        }
        return rs;
    }
    public List<Plant> getOldPlant(){
        List<Plant> rs = pr.findAll();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<List<Plant>> pl = or.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .filter(i -> i.getOrderDate().toLocalDate().getYear() == calendar.get(Calendar.YEAR))
                .collect(Collectors.toList())
                .stream().map(x -> x.getOrderDetails().stream().map(y -> y.getPlant()).collect(Collectors.toList())).collect(Collectors.toList());;
        for(List<Plant> li : pl){
            for(Plant i : li){
                if(rs.contains(i)){
                    rs.remove(i);
                }
            }
        }
        return rs;
    }

    public ArrayList getSumOrder() {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        List<Order> lsOder = or.findAll(Sort.by(Sort.Direction.DESC, "id")).stream()
                .filter(i -> i.getOrderDate().toLocalDate().getYear() == calendar.get(Calendar.YEAR))
                .collect(Collectors.toList());

        ArrayList rs= new ArrayList();
        ArrayList rs1= new ArrayList();
        ArrayList rs2= new ArrayList();
        ArrayList rs3= new ArrayList();
        for(int i =1; i<=calendar.get(Calendar.MONTH)+1; i++){
            int finalI = i;
            //Hợp lệ
            rs1.add(lsOder.stream()
                .filter(item -> item.getOrderDate().toLocalDate().getMonthValue() == finalI && (!item.getStatus().equals(Order.Status.CANCELED.toString()) && !item.getStatus().equals(Order.Status.DENIED.toString())))
                .reduce(0l,(sum,details)->sum+1, Long::sum));
            //Không hợp lệ
            rs3.add(lsOder.stream()
                    .filter(item -> item.getOrderDate().toLocalDate().getMonthValue() == finalI && (item.getStatus().equals(Order.Status.CANCELED.toString() ) || item.getStatus().equals(Order.Status.DENIED.toString())))
                    .reduce(0l,(sum,details)->sum+1, Long::sum));
            //Doanh thu
            rs2.add(lsOder.stream()
                    .filter(item -> item.getOrderDate().toLocalDate().getMonthValue() == finalI && (!item.getStatus().equals(Order.Status.CANCELED.toString() ) && !item.getStatus().equals(Order.Status.DENIED.toString())))
                    .reduce(0l,(sum,details)->sum+details.getSum(), Long::sum));
        }
        rs.add(rs1);
        rs.add(rs2);
        rs.add(rs3);
        return rs;
    }


    public List<Order> getnewOrder() {
        return rr.getNewOrder();
    }
}