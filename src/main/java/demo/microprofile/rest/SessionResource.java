package demo.microprofile.rest;

import demo.microprofile.model.Session;
import demo.microprofile.model.SessionStore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Optional;

@Path("/sessions")
@ApplicationScoped
public class SessionResource {

    @Inject
    private SessionStore sessionStore;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Session> allSessions() throws Exception {
        return sessionStore.getSessions();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Session createSession(final Session session) throws Exception {
        return sessionStore.save(session);
    }

    @GET
    @Path("/{sessionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSession(@PathParam("sessionId") final Integer sessionId) throws Exception {
        final Optional<Session> result = sessionStore.find(sessionId);

        if (result.isPresent())
            return Response.ok(result.get()).build();
        else
            return Response.status(404).build();

    }

    @PUT
    @Path("/{sessionId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSession(@PathParam("sessionId") final Integer sessionId, final Session session) throws Exception {
        final Optional<Session> updated = sessionStore.update(sessionId, session);
        if (updated.isPresent())
            return Response.ok(updated.get()).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Path("/{sessionId}")
    public Response deleteSession(@PathParam("sessionId") final Integer sessionId) throws Exception {
        final Optional<Session> removed = sessionStore.remove(sessionId);
        if (removed.isPresent())
            return Response.ok().build();
        else
            return Response.status(404).build();

    }
}
