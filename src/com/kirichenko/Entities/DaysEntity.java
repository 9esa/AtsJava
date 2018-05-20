package com.kirichenko.Entities;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "days", schema = "atg", catalog = "")
public class DaysEntity {
    private int iddays;
    private String currentDate;
    private Collection<BillEntity> billsByIddays;

    public DaysEntity(String currentDate) {
        this.currentDate = currentDate;
    }

    public DaysEntity(int iDay) {
        this.iddays = iDay;
    }

    public DaysEntity() {
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    @Id
    @Column(name = "iddays")
    public int getIddays() {
        return iddays;
    }

    public void setIddays(int iddays) {
        this.iddays = iddays;
    }

    @Basic
    @Column(name = "currentDate")
    public String getCurrentDate() {
        return currentDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DaysEntity that = (DaysEntity) o;

        if (iddays != that.iddays) return false;
        if (currentDate != null ? !currentDate.equals(that.currentDate) : that.currentDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = iddays;
        result = 31 * result + (currentDate != null ? currentDate.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "daysByIdDate")
    public Collection<BillEntity> getBillsByIddays() {
        return billsByIddays;
    }

    public void setBillsByIddays(Collection<BillEntity> billsByIddays) {
        this.billsByIddays = billsByIddays;
    }
}
