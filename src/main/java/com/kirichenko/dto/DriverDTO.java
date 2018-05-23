package com.kirichenko.dto;

/**
 * Created by user on 13.05.2018.
 */
public class DriverDTO {

    private String m_driverId = null;
    private String m_driverData = null;


    public DriverDTO(String m_driverId, String m_driverData) {
        this.m_driverId = m_driverId;
        this.m_driverData = m_driverData;
    }

    public String getM_driverId() {
        return m_driverId;
    }

    public void setM_driverId(String m_driverId) {
        this.m_driverId = m_driverId;
    }

    public String getM_driverData() {
        return m_driverData;
    }

    public void setM_driverData(String m_driverData) {
        this.m_driverData = m_driverData;
    }
}
