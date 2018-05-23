package com.kirichenko.Entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "delivery_address", schema = "atg", catalog = "")
public class DeliveryAddressEntity {
    private int iddeliveryAddress;
    private String address;
    private String isMain;
    private Collection<BillEntity> billsByIddeliveryAddress;
    private ClientEntity clientByClientId;

    @Id
    @Column(name = "iddelivery_address")
    public int getIddeliveryAddress() {
        return iddeliveryAddress;
    }

    public void setIddeliveryAddress(int iddeliveryAddress) {
        this.iddeliveryAddress = iddeliveryAddress;
    }

    @Basic
    @Column(name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Basic
    @Column(name = "isMain")
    public String getIsMain() {
        return isMain;
    }

    public void setIsMain(String isMain) {
        this.isMain = isMain;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryAddressEntity that = (DeliveryAddressEntity) o;

        if (iddeliveryAddress != that.iddeliveryAddress) return false;
        if (address != null ? !address.equals(that.address) : that.address != null) return false;
        if (isMain != null ? !isMain.equals(that.isMain) : that.isMain != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddeliveryAddress;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (isMain != null ? isMain.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "deliveryAddressByIdDeliveryAddress")
    public Collection<BillEntity> getBillsByIddeliveryAddress() {
        return billsByIddeliveryAddress;
    }

    public void setBillsByIddeliveryAddress(Collection<BillEntity> billsByIddeliveryAddress) {
        this.billsByIddeliveryAddress = billsByIddeliveryAddress;
    }

    @ManyToOne
    @JoinColumn(name = "clientId", referencedColumnName = "idclient")
    public ClientEntity getClientByClientId() {
        return clientByClientId;
    }

    public void setClientByClientId(ClientEntity clientByClientId) {
        this.clientByClientId = clientByClientId;
    }
}
