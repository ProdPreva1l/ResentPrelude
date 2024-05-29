package prelude.network.packets.clientbound;

import prelude.network.ClientBoundPacketBuilder;
import prelude.network.ClientBoundPacket;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class WaypointsPacket extends ClientBoundPacket {
    private List<Waypoint> waypoints = new ArrayList<>();

    public WaypointsPacket() {
        super(WaypointsPacket.class);
    }

    private WaypointsPacket(List<Waypoint> waypoints) {
        this.waypoints = waypoints;
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

        return GENERIC_PACKET_FORMAT
                .replace("%receiver%", "waypoints-data")
                .replace("%message%", b.toString());
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

    public static class WaypointsPacketBuilder extends ClientBoundPacketBuilder<WaypointsPacket> {
        private final List<Waypoint> waypoints = new ArrayList<>();

        private WaypointsPacketBuilder() {}

        public WaypointsPacketBuilder waypoint(Waypoint waypoint) {
            waypoints.add(waypoint);
            return this;
        }

        public WaypointsPacketBuilder waypoints(List<Waypoint> waypoints) {
            this.waypoints.addAll(waypoints);
            return this;
        }

        @Override
        public WaypointsPacketBuilder receiver(String receiver) {
            throw new RuntimeException("You can't set receiver for Waypoints Packet!");
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
            return Objects.equals(waypoints, that.waypoints) && Objects.equals(receiver, that.receiver);
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
