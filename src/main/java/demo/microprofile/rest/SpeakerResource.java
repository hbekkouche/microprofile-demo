package demo.microprofile.rest;

import demo.microprofile.model.Speaker;
import demo.microprofile.model.SpeakerStore;
import org.eclipse.microprofile.metrics.annotation.Timed;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.Collection;
import java.util.Optional;

@Path("/speakers")
@ApplicationScoped
public class SpeakerResource {

    @Inject
    private SpeakerStore speakerStore;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Collection<Speaker> allSpeakers() throws Exception {
        return speakerStore.getSpeakers();
    }


    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Speaker createSpeaker(final Speaker speaker) throws Exception {
        return speakerStore.save(speaker);
    }

    @GET
    @Path("/{speakerId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response retrieveSpeaker(@PathParam("speakerId") final Integer  speakerId) throws Exception {
        final Optional<Speaker> result = speakerStore.find(speakerId);

        if (result.isPresent())
            return Response.ok(result.get()).build();
        else
            return Response.status(404).build();

    }

    @PUT
    @Path("/{speakerId}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateSpeaker(@PathParam("speakerId") final Integer speakerId, final Speaker speaker) throws Exception {
        final Optional<Speaker> updated = speakerStore.update(speakerId, speaker);
        if (updated.isPresent())
            return Response.ok(updated.get()).build();
        else
            return Response.status(404).build();
    }

    @DELETE
    @Path("/{speakerId}")
    public Response deleteSpeaker(@PathParam("speakerId") final Integer speakerId) throws Exception {
        final Optional<Speaker> removed = speakerStore.remove(speakerId);
        if (removed.isPresent())
            return Response.ok().build();
        else
            return Response.status(404).build();

    }
}
