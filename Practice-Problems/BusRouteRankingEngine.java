public class BusRouteRankingEngine {

    static class BusRoute {
        private String routeCode;
        private String routeName;
        private int priority;

        public BusRoute(String routeCode, String routeName, int priority) {
            this.routeCode = routeCode;
            this.routeName = routeName;
            this.priority = priority;
        }

        public BusRoute(String routeCode, String routeName) {
            this(routeCode, routeName, 3);
        }

        int compareTo(BusRoute other) {
            if (this.priority != other.priority) {
                return other.priority - this.priority;
            }

            int codeResult =
                    this.routeCode.compareToIgnoreCase(other.routeCode);

            if (codeResult != 0) {
                return codeResult;
            }

            return this.routeName.length() - other.routeName.length();
        }
    }

    static BusRoute[] rankRoutes(BusRoute[] routes) {

        for (int i = 0; i < routes.length - 1; i++) {
            for (int j = 0; j < routes.length - 1 - i; j++) {

                if (routes[j].compareTo(routes[j + 1]) > 0) {
                    BusRoute temp = routes[j];
                    routes[j] = routes[j + 1];
                    routes[j + 1] = temp;
                }
            }
        }

        return routes;
    }

    public static void main(String[] args) {

        BusRoute[] routes = {
            new BusRoute("RT205L", "Airport Express", 3),
            new BusRoute("rt201j", "City Central", 4),
            new BusRoute("RT299T", "Night Service")
        };

        rankRoutes(routes);

        for (BusRoute route : routes) {
            System.out.println(route.routeCode);
        }
    }
}