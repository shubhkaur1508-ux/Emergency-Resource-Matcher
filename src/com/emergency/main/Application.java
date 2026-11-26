package com.emergency.main;

import com.emergency.database.RequestDatabase;
import com.emergency.database.RequestStatusStore;
import com.emergency.database.ResourceDatabase;
import com.emergency.database.ResourceIdStore;
import com.emergency.database.UserDatabase;
import com.emergency.exceptions.DuplicateResourceException;
import com.emergency.exceptions.DuplicateUserException;
import com.emergency.exceptions.InvalidRequestException;
import com.emergency.exceptions.InvalidResourceException;
import com.emergency.matching.ResourceMatcher;
import com.emergency.request.RequestHistory;
import com.emergency.request.RequestManager;
import com.emergency.resource.ResourceManager;
import com.emergency.threads.RequestProcessor;
import com.emergency.user.User;
import com.emergency.user.UserManager;

/**
 * Coordinates and controls the entire application lifecycle and workflow.
 */
public class Application {
    private InputHandler inputHandler;
    private UserDatabase userDatabase;
    private ResourceDatabase resourceDatabase;
    private RequestDatabase requestDatabase;
    private ResourceIdStore resourceIdStore;
    private RequestStatusStore requestStatusStore;

    private UserManager userManager;
    private ResourceManager resourceManager;
    private RequestHistory requestHistory;
    private RequestManager requestManager;
    private ResourceMatcher resourceMatcher;
    private RequestProcessor requestProcessor;

    public Application() {
        // Initialize databases and stores
        this.inputHandler = new InputHandler();
        this.userDatabase = new UserDatabase();
        this.resourceDatabase = new ResourceDatabase();
        this.requestDatabase = new RequestDatabase();
        this.resourceIdStore = new ResourceIdStore();
        this.requestStatusStore = new RequestStatusStore();

        // Initialize managers
        this.userManager = new UserManager(userDatabase);
        this.resourceManager = new ResourceManager(resourceDatabase, resourceIdStore);
        this.requestHistory = new RequestHistory();
        this.requestManager = new RequestManager(requestDatabase, requestStatusStore, requestHistory);
        this.resourceMatcher = new ResourceMatcher(resourceDatabase);
        this.requestProcessor = new RequestProcessor(requestDatabase, requestStatusStore, requestHistory, resourceMatcher);

        // Preload sample starter data for realistic demonstration
        loadSampleData();
    }

    private void loadSampleData() {
        try {
            // Seed a sample user
            userManager.registerUser("U101", "Rahul Sharma", "9876543210", "Delhi",
                    "Pooja Sharma", "Sister", "9876500001");

            // Seed sample resources
            resourceManager.addBloodDonor("RES-B1", "Dr. Amit Verma", "9123456780", "Delhi", "O+", 29);
            resourceManager.addOxygenSupplier("RES-O1", "Metro Oxygen Care", "9123456781", "Delhi", 50.0, 10);
            resourceManager.addAmbulance("RES-A1", "City Rapid Ambulance", "9123456782", "Delhi", "DL-01-AB-1234", "ICU Ambulance");
            resourceManager.addVolunteer("RES-V1", "Kavita Rao", "9123456783", "Delhi", "First Aid & CPR", "24/7");
        } catch (Exception e) {
            System.out.println("Warning: Seed data initialization failed: " + e.getMessage());
        }
    }

    public void run() {
        System.out.println("==========================================================");
        System.out.println("  WELCOME TO EMERGENCY RESOURCE MATCHER (ACADEMIC JAVA)   ");
        System.out.println("==========================================================");

        boolean running = true;

        while (running) {
            Menu.displayMainMenu();
            int choice = inputHandler.readInt("Enter your choice: ");

            try {
                switch (choice) {
                    case 1:
                        handleUserRegistration();
                        break;
                    case 2:
                        userManager.displayAllUsers();
                        break;
                    case 3:
                        handleAddBloodDonor();
                        break;
                    case 4:
                        handleAddOxygenSupplier();
                        break;
                    case 5:
                        handleAddAmbulance();
                        break;
                    case 6:
                        handleAddVolunteer();
                        break;
                    case 7:
                        resourceManager.displayAllResources();
                        break;
                    case 8:
                        handleRegisterBloodRequest();
                        break;
                    case 9:
                        handleRegisterOxygenRequest();
                        break;
                    case 10:
                        handleRegisterAmbulanceRequest();
                        break;
                    case 11:
                        handleRegisterVolunteerRequest();
                        break;
                    case 12:
                        handleMatchEmergencyRequest();
                        break;
                    case 13:
                        requestManager.displayPendingRequests();
                        break;
                    case 14:
                        requestHistory.displayHistory();
                        break;
                    case 15:
                        requestStatusStore.displayAllStatuses();
                        break;
                    case 16:
                        System.out.println("Exiting Emergency Resource Matcher. Stay Safe!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option! Please enter a number between 1 and 16.");
                        break;
                }
            } catch (DuplicateResourceException e) {
                System.out.println("\n[ERROR: DUPLICATE RESOURCE] " + e.getMessage());
            } catch (DuplicateUserException e) {
                System.out.println("\n[ERROR: DUPLICATE USER] " + e.getMessage());
            } catch (InvalidResourceException e) {
                System.out.println("\n[ERROR: INVALID RESOURCE DATA] " + e.getMessage());
            } catch (InvalidRequestException e) {
                System.out.println("\n[ERROR: INVALID REQUEST] " + e.getMessage());
            } catch (Exception e) {
                System.out.println("\n[UNEXPECTED ERROR] " + e.getMessage());
            } finally {
                // Finally block demonstrating syllabus requirement
                System.out.println("--- Operation completed ---");
            }
        }

        inputHandler.close();
    }

    private void handleUserRegistration() throws DuplicateUserException {
        System.out.println("\n--- 1. Register User ---");
        String id = inputHandler.readString("Enter User ID (e.g. U102): ");
        String name = inputHandler.readString("Enter Full Name: ");
        String phone = inputHandler.readString("Enter Phone Number: ");
        String city = inputHandler.readString("Enter City: ");
        String contactName = inputHandler.readString("Enter Emergency Contact Name: ");
        String rel = inputHandler.readString("Enter Relationship: ");
        String contactPhone = inputHandler.readString("Enter Emergency Contact Phone: ");

        User newUser = userManager.registerUser(id, name, phone, city, contactName, rel, contactPhone);
        System.out.println("User registered successfully: " + newUser.toString());
    }

    private void handleAddBloodDonor() throws DuplicateResourceException, InvalidResourceException {
        System.out.println("\n--- 3. Add Blood Donor ---");
        String id = inputHandler.readString("Enter Resource ID (e.g. RES-B2): ");
        String name = inputHandler.readString("Enter Donor Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String bloodGroup = inputHandler.readString("Enter Blood Group (A+, A-, B+, B-, AB+, AB-, O+, O-): ");
        int age = inputHandler.readInt("Enter Donor Age (18-65): ");

        resourceManager.addBloodDonor(id, name, phone, city, bloodGroup, age);
        System.out.println("Blood donor registered successfully!");
    }

    private void handleAddOxygenSupplier() throws DuplicateResourceException, InvalidResourceException {
        System.out.println("\n--- 4. Add Oxygen Supplier ---");
        String id = inputHandler.readString("Enter Resource ID (e.g. RES-O2): ");
        String name = inputHandler.readString("Enter Supplier / Agency Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        double capacity = inputHandler.readDouble("Enter Cylinder Capacity (Liters): ");
        int cylinders = inputHandler.readInt("Enter Available Cylinder Count: ");

        resourceManager.addOxygenSupplier(id, name, phone, city, capacity, cylinders);
        System.out.println("Oxygen supplier registered successfully!");
    }

    private void handleAddAmbulance() throws DuplicateResourceException, InvalidResourceException {
        System.out.println("\n--- 5. Add Ambulance ---");
        String id = inputHandler.readString("Enter Resource ID (e.g. RES-A2): ");
        String name = inputHandler.readString("Enter Driver / Service Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String vehicleNo = inputHandler.readString("Enter Vehicle Registration Number: ");
        String type = inputHandler.readString("Enter Ambulance Type (Basic / ICU / Cardiac): ");

        resourceManager.addAmbulance(id, name, phone, city, vehicleNo, type);
        System.out.println("Ambulance registered successfully!");
    }

    private void handleAddVolunteer() throws DuplicateResourceException, InvalidResourceException {
        System.out.println("\n--- 6. Add Volunteer ---");
        String id = inputHandler.readString("Enter Resource ID (e.g. RES-V2): ");
        String name = inputHandler.readString("Enter Volunteer Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String skill = inputHandler.readString("Enter Skill Area (e.g. First Aid, Counseling, Driving): ");
        String shift = inputHandler.readString("Enter Availability Shift (e.g. Day, Night, 24/7): ");

        resourceManager.addVolunteer(id, name, phone, city, skill, shift);
        System.out.println("Volunteer registered successfully!");
    }

    private void handleRegisterBloodRequest() throws InvalidRequestException {
        System.out.println("\n--- 8. Register Blood Request ---");
        String reqId = inputHandler.readString("Enter Request ID (e.g. REQ-B1): ");
        String name = inputHandler.readString("Enter Requester / Patient Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String urgency = inputHandler.readString("Enter Urgency Level (CRITICAL / HIGH / MEDIUM): ");
        String bloodGroup = inputHandler.readString("Enter Required Blood Group (e.g. O+, A+, etc.): ");
        int units = inputHandler.readInt("Enter Units of Blood Needed: ");

        requestManager.registerBloodRequest(reqId, name, phone, city, urgency, bloodGroup, units);
        System.out.println("Blood request queued successfully in LinkedList!");
    }

    private void handleRegisterOxygenRequest() throws InvalidRequestException {
        System.out.println("\n--- 9. Register Oxygen Request ---");
        String reqId = inputHandler.readString("Enter Request ID (e.g. REQ-O1): ");
        String name = inputHandler.readString("Enter Requester / Patient Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String urgency = inputHandler.readString("Enter Urgency Level (CRITICAL / HIGH / MEDIUM): ");
        double liters = inputHandler.readDouble("Enter Required Liters: ");
        boolean rx = inputHandler.readBoolean("Is doctor's prescription verified?");

        requestManager.registerOxygenRequest(reqId, name, phone, city, urgency, liters, rx);
        System.out.println("Oxygen request queued successfully in LinkedList!");
    }

    private void handleRegisterAmbulanceRequest() throws InvalidRequestException {
        System.out.println("\n--- 10. Register Ambulance Request ---");
        String reqId = inputHandler.readString("Enter Request ID (e.g. REQ-A1): ");
        String name = inputHandler.readString("Enter Requester / Patient Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String urgency = inputHandler.readString("Enter Urgency Level (CRITICAL / HIGH / MEDIUM): ");
        String condition = inputHandler.readString("Enter Patient Condition: ");
        String pickup = inputHandler.readString("Enter Pickup Address: ");
        String hospital = inputHandler.readString("Enter Target Hospital: ");

        requestManager.registerAmbulanceRequest(reqId, name, phone, city, urgency, condition, pickup, hospital);
        System.out.println("Ambulance request queued successfully in LinkedList!");
    }

    private void handleRegisterVolunteerRequest() throws InvalidRequestException {
        System.out.println("\n--- 11. Register Volunteer Request ---");
        String reqId = inputHandler.readString("Enter Request ID (e.g. REQ-V1): ");
        String name = inputHandler.readString("Enter Requester / Coordinator Name: ");
        String phone = inputHandler.readString("Enter Contact Phone: ");
        String city = inputHandler.readString("Enter City: ");
        String urgency = inputHandler.readString("Enter Urgency Level (CRITICAL / HIGH / MEDIUM): ");
        String task = inputHandler.readString("Enter Task Description: ");
        int count = inputHandler.readInt("Enter Required Number of Volunteers: ");

        requestManager.registerVolunteerRequest(reqId, name, phone, city, urgency, task, count);
        System.out.println("Volunteer request queued successfully in LinkedList!");
    }

    private void handleMatchEmergencyRequest() {
        System.out.println("\n--- 12. Match Emergency Request (Multithreaded) ---");
        requestProcessor.processNextPendingRequest();
    }
}
