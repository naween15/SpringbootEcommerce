//package com.example.completeecommerce.Entity;
//
//import lombok.AllArgsConstructor;
//import lombok.Getter;
//import lombok.NoArgsConstructor;
//import lombok.Setter;
//
//import javax.persistence.Entity;
//import javax.persistence.GeneratedValue;
//import javax.persistence.GenerationType;
//import javax.persistence.Id;
//
//@Entity
//@Getter
//@Setter
//@NoArgsConstructor
//public class Order {
//    @Id
//    @GeneratedValue(strategy = GenerationType.AUTO)
//    private Long id;
//    private String orderFullName;
//    private String orderFullOrder;
//    private String orderContactNumber;
//    private String orderStatus;
//    private int orderAmount;
//
//    public Order(String orderFullName, String orderFullOrder, String orderContactNumber, String orderStatus, int orderAmount) {
//        this.orderFullName = orderFullName;
//        this.orderFullOrder = orderFullOrder;
//        this.orderContactNumber = orderContactNumber;
//        this.orderStatus = orderStatus;
//        this.orderAmount = orderAmount;
//    }
//}
