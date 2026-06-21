package com.ng.clients;

import java.util.UUID;

public record ClientDto(UUID id, String name, String email, String phone, String address) {
    static ClientDto from(Client c) {
        return new ClientDto(c.id, c.name, c.email, c.phone, c.address);
    }
}

