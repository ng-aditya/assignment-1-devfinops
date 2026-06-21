package com.ng.clients;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "clients")
public class Client extends PanacheEntityBase {

    @Id
    public UUID id;

    @Column(name = "name", nullable = false)
    public String name;

    @Column(name = "email", nullable = false, unique = true)
    public String email;

    @Column(name = "phone", nullable = false)
    public String phone;

    @Column(name = "address", nullable = false)
    public String address;
}

