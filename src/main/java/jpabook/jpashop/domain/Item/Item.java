package jpabook.jpashop.domain.Item;

import jakarta.persistence.*;

import jpabook.jpashop.domain.Category;
import jpabook.jpashop.exception.NotEnoughStockException;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@Getter @Setter
public abstract class Item {

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;
    private int price;
    private int stockQuantitiy;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    //비즈니스 로직
    public void addStock(int quantity){//stock 증가
        this.stockQuantitiy += quantity;
    }

    public void removeStock(int quantitiy){
        int restStock = this.stockQuantitiy -quantitiy;
        if(restStock <0){
            throw new NotEnoughStockException("need more stock");
        }
        this.stockQuantitiy = restStock;
    }
}
