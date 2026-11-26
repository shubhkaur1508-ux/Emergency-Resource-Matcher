package com.emergency.database;

import java.util.ArrayList;
import com.emergency.resource.Resource;

/**
 * In-memory storage layer for resources using ArrayList collection.
 */
public class ResourceDatabase {
    private ArrayList<Resource> resourceList;

    public ResourceDatabase() {
        this.resourceList = new ArrayList<Resource>();
    }

    public void addResource(Resource resource) {
        if (resource != null) {
            resourceList.add(resource);
        }
    }

    public ArrayList<Resource> getAllResources() {
        return resourceList;
    }

    public Resource findResourceById(String resourceId) {
        for (int i = 0; i < resourceList.size(); i++) {
            Resource r = resourceList.get(i);
            if (r.getResourceId().equalsIgnoreCase(resourceId)) {
                return r;
            }
        }
        return null;
    }

    public ArrayList<Resource> getResourcesByType(String resourceType) {
        ArrayList<Resource> filtered = new ArrayList<Resource>();
        for (int i = 0; i < resourceList.size(); i++) {
            Resource r = resourceList.get(i);
            if (r.getResourceType().equalsIgnoreCase(resourceType)) {
                filtered.add(r);
            }
        }
        return filtered;
    }

    public int getResourceCount() {
        return resourceList.size();
    }
}
