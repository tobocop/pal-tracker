package io.pivotal.pal.tracker;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InMemoryTimeEntryRepository implements TimeEntryRepository {
    private Map<Long, TimeEntry> entryMap = new HashMap<>();
    private long currentId = 1L;

    public TimeEntry create(TimeEntry timeEntry) {
        long newId = this.currentId++;
        TimeEntry newEntry = new TimeEntry(
            newId,
            timeEntry.getProjectId(),
            timeEntry.getUserId(),
            timeEntry.getDate(),
            timeEntry.getHours()
        );
        this.entryMap.put(newId, newEntry);

        return newEntry;
    }

    public TimeEntry find(long id) {
        return this.entryMap.get(id);
    }

    public List<TimeEntry> list() {
        return List.copyOf(this.entryMap.values());
    }

    public TimeEntry update(long id, TimeEntry timeEntry) {
        TimeEntry newEntry = new TimeEntry(
            id,
            timeEntry.getProjectId(),
            timeEntry.getUserId(),
            timeEntry.getDate(),
            timeEntry.getHours()
        );
        this.entryMap.replace(id, newEntry);
        return this.entryMap.get(id);
    }

    public void delete(long id) {
        this.entryMap.remove(id);
    }
}
