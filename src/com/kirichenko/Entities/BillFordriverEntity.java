package com.kirichenko.Entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "billFordriver", schema = "atg", catalog = "")
public class BillFordriverEntity {
    private int id;
    private Integer timeForDay;
    private DriverEntity driverByIdDriver;
    private Integer idBill;
    private Collection<BillEntity> billsById;

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
    @Column(name = "timeForDay")
    public Integer getTimeForDay() {
        return timeForDay;
    }

    public void setTimeForDay(Integer timeForDay) {
        this.timeForDay = timeForDay;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BillFordriverEntity that = (BillFordriverEntity) o;

        if (id != that.id) return false;
        if (timeForDay != null ? !timeForDay.equals(that.timeForDay) : that.timeForDay != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (timeForDay != null ? timeForDay.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "idDriver", referencedColumnName = "iddriver")
    public DriverEntity getDriverByIdDriver() {
        return driverByIdDriver;
    }

    public void setDriverByIdDriver(DriverEntity driverByIdDriver) {
        this.driverByIdDriver = driverByIdDriver;
    }

    @Basic
    @Column(name = "idBill")
    public Integer getIdBill() {
        return idBill;
    }

    public void setIdBill(Integer idBill) {
        this.idBill = idBill;
    }

    @OneToMany(mappedBy = "billFordriverByIdBillForDriver")
    public Collection<BillEntity> getBillsById() {
        return billsById;
    }

    public void setBillsById(Collection<BillEntity> billsById) {
        this.billsById = billsById;
    }
}
