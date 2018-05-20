package com.kirichenko.Entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "driver", schema = "atg", catalog = "")
public class DriverEntity {
    private int iddriver;
    private String secondName;
    private String firstName;
    private String phone;
    private boolean remove;
    private Collection<BillFordriverEntity> billFordriversByIddriver;

    public void setRemove(boolean remove) {
        this.remove = remove;
    }

    @Basic
    @Column(name = "remove")
    public boolean isRemove() {
        return remove;
    }

    @Id
    @Column(name = "iddriver")
    public int getIddriver() {
        return iddriver;
    }

    public void setIddriver(int iddriver) {
        this.iddriver = iddriver;
    }

    @Basic
    @Column(name = "SecondName")
    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    @Basic
    @Column(name = "FirstName")
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

    @OneToMany(mappedBy = "driverByIdDriver")
    public Collection<BillFordriverEntity> getBillFordriversByIddriver() {
        return billFordriversByIddriver;
    }

    public void setBillFordriversByIddriver(Collection<BillFordriverEntity> billFordriversByIddriver) {
        this.billFordriversByIddriver = billFordriversByIddriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DriverEntity that = (DriverEntity) o;

        if (iddriver != that.iddriver) return false;
        if (remove != that.remove) return false;
        if (secondName != null ? !secondName.equals(that.secondName) : that.secondName != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (phone != null ? !phone.equals(that.phone) : that.phone != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddriver;
        result = 31 * result + (secondName != null ? secondName.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (phone != null ? phone.hashCode() : 0);
        result = 31 * result + (remove ? 1 : 0);
        return result;
    }
}
