package com.kirichenko.Entities;

import javax.persistence.*;
import java.util.Collection;

/**
 * Created by user on 14.05.2018.
 */
@Entity
@Table(name = "permission", schema = "atg", catalog = "")
public class PermissionEntity {
    private int idpermission;
    private String textPermission;
    private Collection<UsersEntity> userssByIdpermission;

    @Id
    @Column(name = "idpermission")
    public int getIdpermission() {
        return idpermission;
    }

    public void setIdpermission(int idpermission) {
        this.idpermission = idpermission;
    }

    @Basic
    @Column(name = "textPermission")
    public String getTextPermission() {
        return textPermission;
    }

    public void setTextPermission(String textPermission) {
        this.textPermission = textPermission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PermissionEntity that = (PermissionEntity) o;

        if (idpermission != that.idpermission) return false;
        if (textPermission != null ? !textPermission.equals(that.textPermission) : that.textPermission != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idpermission;
        result = 31 * result + (textPermission != null ? textPermission.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "permissionByPermission")
    public Collection<UsersEntity> getUserssByIdpermission() {
        return userssByIdpermission;
    }

    public void setUserssByIdpermission(Collection<UsersEntity> userssByIdpermission) {
        this.userssByIdpermission = userssByIdpermission;
    }
}
