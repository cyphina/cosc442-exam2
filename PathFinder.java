package game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;

public class PathFinder {
    private PathFinderData pathFinderData = new PathFinderData();
	private ArrayList<Point> open;
    private ArrayList<Point> closed;
    
    public PathFinder() {
    	this.open = new ArrayList<Point>();
        this.closed = new ArrayList<Point>();
        pathFinderData.setParents(new HashMap<Point, Point>());
        pathFinderData.setTotalCost(new HashMap<Point, Integer>());
    }
    
    public int heuristicCost(Point from, Point to) {
        return Math.max(Math.abs(from.x - to.x), Math.abs(from.y - to.y));
    }

    public ArrayList<Point> findPath(Creature creature, Point start, Point end, int maxTries) {
        open.clear();
        closed.clear();
        pathFinderData.getParents().clear();
        pathFinderData.getTotalCost().clear();
    	
        open.add(start);
        
        for (int tries = 0; tries < maxTries && open.size() > 0; tries++){
            Point closest = getClosestPoint(end);
            
            open.remove(closest);
            closed.add(closest);

            if (closest.equals(end))
                return createPath(start, closest);
            else
                checkNeighbors(creature, end, closest);
        }
        return null;
    }

	private Point getClosestPoint(Point end) {
		Point closest = open.get(0);
		for (Point other : open){
		    if (pathFinderData.totalCost(other, end, this) < pathFinderData.totalCost(closest, end, this))
		        closest = other;
		}
		return closest;
	}

	private void checkNeighbors(Creature creature, Point end, Point closest) {
		for (Point neighbor : closest.neighbors8()) {
		    if (closed.contains(neighbor)
		    		|| !creature.canEnter(neighbor.x, neighbor.y, creature.z)
		    		&& !neighbor.equals(end))
		        continue;
			
		    if (open.contains(neighbor))
				pathFinderData.reParentNeighborIfNecessary(closest, neighbor, this.open);
		    else
		        pathFinderData.reParentNeighbor(closest, neighbor, this.open);
		}
	}

	private ArrayList<Point> createPath(Point start, Point end) {
		ArrayList<Point> path = new ArrayList<Point>();

		while (!end.equals(start)) {
		    path.add(end);
		    end = pathFinderData.getParents().get(end);
		}

		Collections.reverse(path);
		return path;
	}
}
