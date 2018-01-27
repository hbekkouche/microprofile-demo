package demo.microprofile.model;

import demo.microprofile.bootstrap.BootstrapDataProducer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class SessionStore {

    @Inject
    BootstrapDataProducer bootstrapData;

    private final ConcurrentHashMap<Integer, Session> storage = new ConcurrentHashMap<>();

    public Session save(final Session session) {
        storage.put(session.getId(), session);
        return session;
    }

    @PostConstruct
    private void initStore() {
        bootstrapData.loadSessions()
                .forEach(bootstrap -> storage.put(bootstrap.getId(), bootstrap));

    }

    public Collection<Session> getSessions() {
        return storage.values();
    }

    public Optional<Session> find(final Integer sessionId) {
        final Session result = storage.get(sessionId);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    public Optional<Session> update(final Integer sessionId, final Session session) {
        final Optional<Session> existing = find(sessionId);
        if (existing.isPresent()) {
            session.setId(sessionId);
            storage.put(sessionId, session);
        }
        return existing;
    }

    public Optional<Session> remove(final Integer sessionId) {
        final Optional<Session> existing = find(sessionId);
        if (existing.isPresent()) {
            storage.remove(existing.get().getId());
        }
        return existing;
    }

}
