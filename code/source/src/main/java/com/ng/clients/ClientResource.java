package com.ng.clients;

import io.quarkus.logging.Log;
import io.smallrye.common.annotation.RunOnVirtualThread;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import java.util.List;

@Path("/clients")
@Consumes(MediaType.APPLICATION_JSON)
public class ClientResource {

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @RunOnVirtualThread
    public List<ClientDto> listClients() {
        Log.info("listing all clients");
        return Client.<Client>listAll().stream().map(ClientDto::from).toList();
    }

}
