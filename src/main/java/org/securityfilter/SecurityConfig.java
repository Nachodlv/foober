package org.securityfilter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class SecurityConfig {

    public static final String ROLE_FO = "FO";
    public static final String ROLE_DG = "DG";

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

        mapConfig.put(ROLE_FO, urlPatterns1);

        // Configure For "DG" Role.
        List<String> urlPatterns2 = new ArrayList<String>();

        urlPatterns2.add("/userInfo");
        urlPatterns2.add("/dgMenu");

        mapConfig.put(ROLE_DG, urlPatterns2);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }

}