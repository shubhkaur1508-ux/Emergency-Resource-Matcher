package com.emergency.database;

import java.util.HashSet;
import java.util.Set;

/**
 * In-memory storage layer using Set collection to maintain unique resource IDs
 * and prevent duplicate resource registration.
 */
public class ResourceIdStore {
    private Set<String> registeredIds;

    public ResourceIdStore() {
        this.registeredIds = new HashSet<String>();
    }

    public boolean addResourceId(String resourceId) {
        if (resourceId == null) {
            return false;
        }
        return registeredIds.add(resourceId.toUpperCase());
    }

    public boolean containsId(String resourceId) {
        if (resourceId == null) {
            return false;
        }
        return registeredIds.contains(resourceId.toUpperCase());
    }

    public boolean removeResourceId(String resourceId) {
        if (resourceId == null) {
            return false;
        }
        return registeredIds.remove(resourceId.toUpperCase());
    }

    public Set<String> getAllIds() {
        return registeredIds;
    }

    public int size() {
        return registeredIds.size();
    }
}
