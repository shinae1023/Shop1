package jpabook.jpashop.domain;

import jakarta.persistence.*;
import jpabook.jpashop.domain.Item.Item;
import lombok.Getter;
import lombok.Setter;

import static jakarta.persistence.FetchType.*;

@Entity
@Getter @Setter
public class OrderItem {

    @Id @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "item_id")
    private Item item;

    @ManyToOne(fetch = LAZY) //하나의 주문에 여러 아이템이 들어갈 수 있으므로 다대일
    //주문 아이템이 다이므로 연관관계 주인
    @JoinColumn(name = "order_id")
    private Order order;

    private int orderPrice; //주문시 가격
    private int count;//주문시 수량
}
