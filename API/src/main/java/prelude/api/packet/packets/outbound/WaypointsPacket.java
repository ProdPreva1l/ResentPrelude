package prelude.api.packet.packets.outbound;

import prelude.api.packet.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WaypointsPacket extends OutboundPacket {
    public static final String RESENT_WAYPOINTS_PACKET_FORMAT =
            "{" +
                    "\"packet_receiver\":\"waypoints_data\"," +
                    "\"message\":\"%message%\"" +
            "}";

    private List<Waypoint> waypoints = new ArrayList<>();

    public WaypointsPacket() {
        super(WaypointsPacket.class);
    }

    @Override
    public String serialize() {
        StringBuilder b = new StringBuilder();

        int iMax = waypoints.size() - 1;
        if (iMax != -1) {
            for (int i = 0; ; i++) {
                b.append(waypoints.get(i).toString());
                if (i == iMax)
                    break;
                b.append(",");
            }
        } else {
            b.append("{}");
        }

        return RESENT_WAYPOINTS_PACKET_FORMAT
                .replace("%message%", b.toString());
    }

    private WaypointsPacket(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
    }

    @Override
    public WaypointsPacketBuilder builder() {
        return new WaypointsPacketBuilder();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof WaypointsPacket)) return false;
        WaypointsPacket that = (WaypointsPacket) o;
        return Objects.equals(waypoints, that.waypoints);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(waypoints);
    }

    public static class WaypointsPacketBuilder extends OutboundPacketBuilder<WaypointsPacket> {
        private final List<Waypoint> waypoints = new ArrayList<>();

        private WaypointsPacketBuilder() {}

        public WaypointsPacketBuilder addWaypoint(Waypoint waypoint) {
            waypoints.add(waypoint);
            return this;
        }

        public WaypointsPacketBuilder addWaypoints(List<Waypoint> waypoints) {
            this.waypoints.addAll(waypoints);
            return this;
        }

        public WaypointsPacketBuilder removeWaypoint(Waypoint waypoint) {
            waypoints.remove(waypoint);
            return this;
        }

        public WaypointsPacketBuilder removeWaypoints(List<Waypoint> waypoints) {
            this.waypoints.removeAll(waypoints);
            return this;
        }

        @Override
        public WaypointsPacket build() {
            return new WaypointsPacket(waypoints);
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof WaypointsPacketBuilder)) return false;
            WaypointsPacketBuilder that = (WaypointsPacketBuilder) o;
            return Objects.equals(waypoints, that.waypoints);
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(waypoints);
        }

        @Override
        public String toString() {
            return "ResentWaypointsPacketBuilder:" + new WaypointsPacket(waypoints).serialize();
        }
    }

    public static class Waypoint {
        private final String name;
        private final int x;
        private final int y;
        private final int z;

        public Waypoint(String name, int x, int y, int z) {
            this.name = name;
            this.x = x;
            this.y = y;
            this.z = z;
        }

        @Override
        public String toString() {
            return "{x=" + x + ",y=" + y + ",z=" + z + ",name=" + name + "}";
        }
    }
}
