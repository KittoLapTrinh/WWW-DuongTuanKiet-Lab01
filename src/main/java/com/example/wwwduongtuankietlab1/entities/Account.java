package com.example.wwwduongtuankietlab1.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

import java.util.LinkedHashSet;
import java.util.Set;

@Entity
@Table(name = "account")
public class Account {
    @Id
    @Size(max = 255)
    @Column(name = "account_id", nullable = false)
    private String accountId;

    @Size(max = 255)
    @Column(name = "email")
    private String email;

    @Size(max = 255)
    @Column(name = "full_name")
    private String fullName;

    @Size(max = 255)
    @Column(name = "password")
    private String password;

    @Size(max = 255)
    @Column(name = "phone")
    private String phone;

    @NotNull
    @Column(name = "status", nullable = false)
    private Byte status;

    @OneToMany(mappedBy = "account")
    private Set<GrantAccess> grantAccesses = new LinkedHashSet<>();

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Set<GrantAccess> getGrantAccesses() {
        return grantAccesses;
    }

    public void setGrantAccesses(Set<GrantAccess> grantAccesses) {
        this.grantAccesses = grantAccesses;
    }

    public Account(String accountId, String email, String fullName, String password, String phone, Byte status, Set<GrantAccess> grantAccesses) {
        this.accountId = accountId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.status = status;
        this.grantAccesses = grantAccesses;
    }

    public Account(String accountId, String email, String fullName, String password, String phone, Byte status) {
        this.accountId = accountId;
        this.email = email;
        this.fullName = fullName;
        this.password = password;
        this.phone = phone;
        this.status = status;
    }

    public Account() {
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", email='" + email + '\'' +
                ", fullName='" + fullName + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", grantAccesses=" + grantAccesses +
                '}';
    }
}