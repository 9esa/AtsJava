package com.kirichenko.Entities;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by user on 17.05.2018.
 */
@Entity
@Table(name = "journal_calls", schema = "atg", catalog = "")
public class JournalCallsEntity {
    private int id;
    private String dateCall;
    private String phoneCall;
    private String destinationCall;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "dateCall")
    public String getDateCall() {
        return dateCall;
    }

    public void setDateCall(String dateCall) {
        this.dateCall = dateCall;
    }

    @Basic
    @Column(name = "phoneCall")
    public String getPhoneCall() {
        return phoneCall;
    }

    public void setPhoneCall(String phoneCall) {
        this.phoneCall = phoneCall;
    }

    @Basic
    @Column(name = "destinationCall")
    public String getDestinationCall() {
        return destinationCall;
    }

    public void setDestinationCall(String destinationCall) {
        this.destinationCall = destinationCall;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        JournalCallsEntity that = (JournalCallsEntity) o;

        if (id != that.id) return false;
        if (dateCall != null ? !dateCall.equals(that.dateCall) : that.dateCall != null) return false;
        if (phoneCall != null ? !phoneCall.equals(that.phoneCall) : that.phoneCall != null) return false;
        if (destinationCall != null ? !destinationCall.equals(that.destinationCall) : that.destinationCall != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (dateCall != null ? dateCall.hashCode() : 0);
        result = 31 * result + (phoneCall != null ? phoneCall.hashCode() : 0);
        result = 31 * result + (destinationCall != null ? destinationCall.hashCode() : 0);
        return result;
    }
}
