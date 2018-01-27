package demo.microprofile.model;

import demo.microprofile.bootstrap.BootstrapDataProducer;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@ApplicationScoped
public class SpeakerStore {

    @Inject
    BootstrapDataProducer bootstrapData;

    private final ConcurrentHashMap<Integer, Speaker> storage = new ConcurrentHashMap<>();

    public Speaker save(final Speaker speaker) {
        storage.put(speaker.getId(), speaker);
        return speaker;
    }

    @PostConstruct
    private void initStore() {
        bootstrapData.loadSpeakers()
                .forEach(bootstrap -> storage.put(bootstrap.getId(), bootstrap));

    }

    public Collection<Speaker> getSpeakers() {
        return storage.values();
    }

    public Optional<Speaker> find(final Integer speakerId) {
        final Speaker result = storage.get(speakerId);
        return result != null ? Optional.of(result) : Optional.empty();
    }

    public Optional<Speaker> update(final Integer speakerId, final Speaker speaker) {
        final Optional<Speaker> existing = find(speakerId);
        if (existing.isPresent()) {
            speaker.setId(speakerId);
            storage.put(speakerId, speaker);
        }
        return existing;
    }

    public Optional<Speaker> remove(final Integer speakerId) {
        final Optional<Speaker> existing = find(speakerId);
        if (existing.isPresent()) {
            storage.remove(existing.get().getId());
        }
        return existing;
    }

}
