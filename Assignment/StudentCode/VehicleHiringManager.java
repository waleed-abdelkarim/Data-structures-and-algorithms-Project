public class VehicleHiringManager {
	LocatorMap<String> vh;

	public VehicleHiringManager() {
		vh = new TreeLocatorMap<String>();
	}

	// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return vh;
	}

	// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
		vh = locatorMap;
	}

	// Inserts the vehicle id at location loc if it does not exist and returns true.
	// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
		Pair<Boolean, Integer> pa1 = vh.add(id, loc);
		return pa1.first;
	}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		Pair<Boolean, Integer> pa1 = vh.move(id, loc);
		return pa1.first;
	}

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		Pair<Boolean, Integer> pa1 = vh.remove(id);
		return pa1.first;
	}

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		return vh.getLoc(id).first;
	}

	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
		Location lowerLeft = new Location(loc.x - r, loc.y - r);
		Location upperRight = new Location(loc.x + r, loc.y + r);
		Pair<List<String>, Integer> pair = vh.getInRange(lowerLeft, upperRight);
		return pair.first;
	}
}
