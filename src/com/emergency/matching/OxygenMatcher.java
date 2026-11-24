package com.emergency.matching;

import java.util.ArrayList;
import com.emergency.database.ResourceDatabase;
import com.emergency.request.OxygenRequest;
import com.emergency.resource.OxygenSupplier;
import com.emergency.resource.Resource;

/**
 * Handles matching logic for oxygen requests with available oxygen suppliers.
 */
public class OxygenMatcher {

    public OxygenSupplier matchOxygenSupplier(OxygenRequest request, ResourceDatabase db) {
        ArrayList<Resource> suppliers = db.getResourcesByType("OXYGEN_SUPPLIER");
        String city = request.getCity();

        // 1st Priority: Same city with available cylinders
        for (int i = 0; i < suppliers.size(); i++) {
            OxygenSupplier s = (OxygenSupplier) suppliers.get(i);
            if (s.isAvailable() && s.getAvailableCylinders() > 0 && s.getCity().equalsIgnoreCase(city)) {
                return s;
            }
        }

        // 2nd Priority: Any city with available cylinders
        for (int i = 0; i < suppliers.size(); i++) {
            OxygenSupplier s = (OxygenSupplier) suppliers.get(i);
            if (s.isAvailable() && s.getAvailableCylinders() > 0) {
                return s;
            }
        }

        return null;
    }
}
