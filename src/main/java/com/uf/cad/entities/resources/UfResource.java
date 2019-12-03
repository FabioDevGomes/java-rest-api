package com.uf.cad.entities.resources;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import com.uf.cad.entities.Uf;
import com.uf.cad.entities.repositories.UfRepository;

@RequestScoped
@Path("uf")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UfResource {
	
	@Inject
    UfRepository ufRepository;

    @GET
    public Response getAllUfs() {
        return Response.ok().entity(ufRepository.getAllUfs()).build();
    }

    @GET
    @Path("{id}")
    public Response getUfById(@PathParam("id") Long id) {
        return Response.ok().entity(ufRepository.findById(id)).build();
    }

    @POST
    public Response create(Uf uf, @Context UriInfo uriInfo) {
        ufRepository.create(uf);
		return Response.ok().build();
    }

    @PUT
    @Path("{id}")
    public Response update(@PathParam("id") Long id, Uf uf) {
        Uf updateUf = ufRepository.findById(id);

        updateUf.setNome(uf.getNome());
        updateUf.setSigla(uf.getSigla());

        return Response.ok().entity(uf).build();
    }

    @DELETE
    @Path("{id}")
    public Response delete(@PathParam("{id}") Long id) {
        Uf uf = ufRepository.findById(id);
        ufRepository.delete(uf);
        return Response.noContent().build();
    }

}
