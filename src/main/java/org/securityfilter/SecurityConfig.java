package org.securityfilter;

import java.util.*;

class SecurityConfig {

    private static final String ROLE_FO = "FO";
    private static final String ROLE_DG = "DG";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<String, List<String>>();

    static {
        init();
    }

    private static void init() {

        // Configure For "FO" Role.
        List<String> urlPatterns1 = new ArrayList<String>();

        urlPatterns1.add("/userInfo");
        urlPatterns1.add("/foMenu");
        urlPatterns1.add("/editMenu");
        urlPatterns1.add("/makingOrder");
        urlPatterns1.add("/foInfo");
        urlPatterns1.add("/chooseDG");

        mapConfig.put(ROLE_FO, urlPatterns1);

        // Configure For "DG" Role.
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/dgMenu");
        urlPatterns2.add("/dgInfo");

        mapConfig.put(ROLE_DG, urlPatterns2);
    }

    static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}