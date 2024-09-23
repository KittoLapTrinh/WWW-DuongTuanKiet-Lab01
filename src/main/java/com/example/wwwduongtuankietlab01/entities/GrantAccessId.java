package com.example.wwwduongtuankietlab01.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.Objects;

@Embeddable
public class GrantAccessId implements java.io.Serializable {
    private static final long serialVersionUID = -2590484903066334178L;
    @Size(max = 255)
    @NotNull
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Size(max = 255)
    @NotNull
    @Column(name = "role_id", nullable = false)
    private String roleId;

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrantAccessId entity = (GrantAccessId) o;
        return Objects.equals(this.accountId, entity.accountId) &&
                Objects.equals(this.roleId, entity.roleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(accountId, roleId);
    }

}