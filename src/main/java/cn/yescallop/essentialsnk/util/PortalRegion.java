package cn.yescallop.essentialsnk.util;

import com.sk89q.worldedit.Vector;

import cn.nukkit.level.Level;
import cn.nukkit.level.Position;

public class PortalRegion {

    private final Position minimumPoint, maximumPoint;
    private final String name, address;
    public PortalRegion(Vector min, Vector max, Level level, String name, String address) {
        minimumPoint = new Position(min.getX(), min.getY(), min.getZ(), level);
        maximumPoint = new Position(max.getX(), max.getY(), max.getZ(), level);
        this.name = name;
        this.address = address;
    }
    public PortalRegion(Position min, Position max, String name, String address) {
        minimumPoint = min;
        maximumPoint = max;
        this.name = name;
        this.address = address;
    }

    public boolean inRegion(Position pos) {
        double x = pos.getX();
        double y = pos.getY();
        double z = pos.getZ();
        if (minimumPoint == null) {
            return false;
        }
        if (maximumPoint == null) {
            return false;
        }        
        return x < getMaximumPoint().getX() && x > getMinimumPoint().getX() && z < getMaximumPoint().getZ()
                && z > getMinimumPoint().getZ() && y < getMaximumPoint().getY() && y > getMinimumPoint().getY();
    }

    public Position getMaximumPoint() {
        return maximumPoint;
    }

    public Position getMinimumPoint() {
        return minimumPoint;
    }
    
    public String getAddress() {
        return address;
    }
    
    public String getName() {
        return name;
    }

}
