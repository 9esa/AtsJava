package com.kirichenko.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "bill", schema = "atg", catalog = "")
public class BillEntity {
    private int id;
    private Integer amount;
    private Integer price;
    private String comment;
    private String billDay;
    private DeliveryAddressEntity deliveryAddressByIdDeliveryAddress;
    private DaysEntity daysByIdDate;
    private BillFordriverEntity billFordriverByIdBillForDriver;

    public void setBillDay(String billDay) {
        this.billDay = billDay;
    }

    @Basic
    @Column(name = "billDay")
    public String getBillDay() {
        return billDay;
    }

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "amount")
    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    @Basic
    @Column(name = "price")
    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    @Basic
    @Column(name = "comment")
    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillEntity that = (BillEntity) o;

        if (id != that.id) return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (comment != null ? !comment.equals(that.comment) : that.comment != null) return false;
        if (billDay != null ? !billDay.equals(that.billDay) : that.billDay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (comment != null ? comment.hashCode() : 0);
        result = 31 * result + (billDay != null ? billDay.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idDeliveryAddress", referencedColumnName = "iddelivery_address")
    public DeliveryAddressEntity getDeliveryAddressByIdDeliveryAddress() {
        return deliveryAddressByIdDeliveryAddress;
    }

    public void setDeliveryAddressByIdDeliveryAddress(DeliveryAddressEntity deliveryAddressByIdDeliveryAddress) {
        this.deliveryAddressByIdDeliveryAddress = deliveryAddressByIdDeliveryAddress;
    }

    @ManyToOne
    @JoinColumn(name = "idDate", referencedColumnName = "iddays")
    public DaysEntity getDaysByIdDate() {
        return daysByIdDate;
    }

    public void setDaysByIdDate(DaysEntity daysByIdDate) {
        this.daysByIdDate = daysByIdDate;
    }

    @ManyToOne
    @JoinColumn(name = "idBillForDriver", referencedColumnName = "id")
    public BillFordriverEntity getBillFordriverByIdBillForDriver() {
        return billFordriverByIdBillForDriver;
    }

    public void setBillFordriverByIdBillForDriver(BillFordriverEntity billFordriverByIdBillForDriver) {
        this.billFordriverByIdBillForDriver = billFordriverByIdBillForDriver;
    }
}
