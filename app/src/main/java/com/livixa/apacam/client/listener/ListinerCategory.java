package com.livixa.apacam.client.listener;

/**
 * The Enum ListinerCategory.
 */
public enum ListinerCategory {

    /**
     * The vault.
     */

    ON_BACK;

    /**
     * Gets the.
     *
     * @param index the index
     * @return the tab type
     */
    public static ListinerCategory get(int index) {

        return ListinerCategory.values()[index];
    }
}
