package com.kirichenko.dto;

/**
 * Created by user on 10.05.2018.
 */
public class AddressDTO {

    private String m_address = null;
    private String m_userId = null;

    public AddressDTO(String m_address, String m_userId) {
        this.m_address = m_address;
        this.m_userId = m_userId;
    }

    public String getM_address() {
        return m_address;
    }

    public void setM_address(String m_address) {
        this.m_address = m_address;
    }

    public String getM_userId() {
        return m_userId;
    }

    public void setM_userId(String m_userId) {
        this.m_userId = m_userId;
    }
}
