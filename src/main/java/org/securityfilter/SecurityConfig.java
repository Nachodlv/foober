package org.securityfilter;

import java.util.*;

class SecurityConfig {

    private static final String ROLE_FO = "FO";
    private static final String ROLE_DG = "DG";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Configure For "FO" Role.
        List<String> urlPatterns1 = new ArrayList<>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/foMenu");
        urlPatterns1.add("/editMenu");
        urlPatterns1.add("/makingOrder");
        urlPatterns1.add("/foInfo");
        urlPatterns1.add("/chooseDG");
        urlPatterns1.add("/orderInfo");
        urlPatterns1.add("/foOrderHistory");

        mapConfig.put(ROLE_FO, urlPatterns1);

        // Configure For "DG" Role.
        List<String> urlPatterns2 = new ArrayList<>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/dgMenu");
        urlPatterns2.add("/dgInfo");
        urlPatterns2.add("/dgOrderHistory");

        mapConfig.put(ROLE_DG, urlPatterns2);
    }

    static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}