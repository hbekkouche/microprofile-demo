package demo.microprofile.bootstrap;

import demo.microprofile.model.Session;
import demo.microprofile.model.Speaker;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.json.*;
import java.io.IOException;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;

@ApplicationScoped
public class BootstrapDataProducer {

    @Produces
    public List<Speaker> loadSpeakers() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL sessionsResource = BootstrapDataProducer.class.getResource("/speakers.json");

            final JsonReaderFactory factory = Json.createReaderFactory(null);
            final JsonReader reader = factory.createReader(sessionsResource.openStream());

            final JsonArray items = reader.readArray();

            // parse speaker objects
            final List<Speaker> speakers = new LinkedList<>();
            for (final JsonValue item : items) {
                final Speaker speaker = new Speaker(
                        ((JsonObject) item).getInt("id"),
                        ((JsonObject) item).getString("fullname"),
                        ((JsonObject) item).getString("link"),
                        ((JsonObject) item).getString("position"),
                        ((JsonObject) item).getString("photo")

                );
                speakers.add(speaker);
            }
            return speakers;

        } catch (final IOException e) {
            throw new RuntimeException("Failed to parse 'sessions.json'", e);
        }
    }

    @Produces
    public List<Session> loadSessions() {
        try {
            ClassLoader loader = Thread.currentThread().getContextClassLoader();
            URL sessionsResource = BootstrapDataProducer.class.getResource("/sessions.json");

            final JsonReaderFactory factory = Json.createReaderFactory(null);
            final JsonReader reader = factory.createReader(sessionsResource.openStream());

            final JsonArray items = reader.readArray();

            // parse session objects
            final List<Session> sessions = new LinkedList<>();
            for (final JsonValue item : items) {
                final Session session = new Session(
                        ((JsonObject) item).getInt("id"),
                        ((JsonObject) item).getString("title"),
                        ((JsonObject) item).getString("level"),
                        ((JsonObject) item).getInt("speakerId")
                );
                sessions.add(session);
            }
            return sessions;

        } catch (final IOException e) {
            throw new RuntimeException("Failed to parse 'sessions.json'", e);
        }
    }
}
