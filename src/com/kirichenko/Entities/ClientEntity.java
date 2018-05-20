package com.kirichenko.Entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "client", schema = "atg", catalog = "")
public class ClientEntity {
    private int idclient;
    private String secondName;
    private String firstName;
    private String phone;
    private String sale;
    private Collection<DeliveryAddressEntity> deliveryAddressesByIdclient;

    public ClientEntity(boolean emptyInit) {
        firstName = "";
        secondName = "";
        phone = "";
        sale = "";
    }


    public ClientEntity() {
    }


    @Id
    @Column(name = "idclient")
    public int getIdclient() {
        return idclient;
    }

    public void setIdclient(int idclient) {
        this.idclient = idclient;
    }

    @Basic
    @Column(name = "secondName")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "firstName")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Basic
    @Column(name = "sale")
    public String getSale() {
        return sale;
    }

    public void setSale(String sale) {
        this.sale = sale;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ClientEntity that = (ClientEntity) o;

        if (idclient != that.idclient) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;
        if (sale != null ? !sale.equals(that.sale) : that.sale != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idclient;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (sale != null ? sale.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "clientByClientId")
    public Collection<DeliveryAddressEntity> getDeliveryAddressesByIdclient() {
        return deliveryAddressesByIdclient;
    }

    public void setDeliveryAddressesByIdclient(Collection<DeliveryAddressEntity> deliveryAddressesByIdclient) {
        this.deliveryAddressesByIdclient = deliveryAddressesByIdclient;
    }
}
