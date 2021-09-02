package com.t9.bsshop.repository;


import com.t9.bsshop.model.Order;
import com.t9.bsshop.model.Report;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReportRepo extends CrudRepository<Order,Long> {

    @Query(value = "SELECT new com.t9.bsshop.model.Report(u.orderDate, count (DISTINCT u.id) ,sum(od.quantity*od.price)) " +
            " FROM  Order u INNER join OrderDetails  od ON u.id = od.order.id" +
            " WHERE u.status='Giao hàng thành công'" +
            " GROUP BY u.orderDate" +
            " ORDER BY u.orderDate DESC ")
    public List<Report> get();

    @Query(value = "SELECT o FROM Order o WHERE o.status = 'Đang chờ xử lý'")
    public List<Order> getNewOrder();

}
