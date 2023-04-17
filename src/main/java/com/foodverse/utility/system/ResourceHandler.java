package com.foodverse.utility.system;

import java.io.InputStream;
import java.net.URL;

public final class ResourceHandler {

    private ResourceHandler() {}

    public static URL loadResourceAsURL(String fileName) {
        return ResourceHandler.class.getResource(String.format("/%s", fileName));
    }

    public static InputStream loadResourceAsStream(String fileName) {
        return ResourceHandler.class.getResourceAsStream(String.format("/%s", fileName));
    }

}
