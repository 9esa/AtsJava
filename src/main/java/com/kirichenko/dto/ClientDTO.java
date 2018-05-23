package com.kirichenko.dto;

/**
 * Created by user on 12.05.2018.
 */
public class ClientDTO {

    private String m_userId = null;
    private String m_userData = null;

    public ClientDTO(String m_userId, String m_userData) {
        this.m_userId = m_userId;
        this.m_userData = m_userData;
    }

    public String getM_userData() {
        return m_userData;
    }

    public void setM_userData(String m_userData) {
        this.m_userData = m_userData;
    }

    public String getM_userId() {
        return m_userId;
    }

    public void setM_userId(String m_userId) {
        this.m_userId = m_userId;
    }

}
