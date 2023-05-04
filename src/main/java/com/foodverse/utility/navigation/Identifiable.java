package com.foodverse.utility.navigation;

public interface Identifiable {

    default String getId() {
        String className = getClass().getSimpleName();
        String parentClassName = getClass().getSuperclass().getSimpleName();
        String[] words = className.split("(?=\\p{Upper})");
        String id = String.join(" ", words);
        id = id.replace(parentClassName, "").trim();
        id = id.replaceAll("\\s+", " ");
        return id;
    }

}
