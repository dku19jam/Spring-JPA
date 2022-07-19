package jpabook.jpashop.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "order_status")
public enum OrderStatus {
    ORDER, CANCEL
}