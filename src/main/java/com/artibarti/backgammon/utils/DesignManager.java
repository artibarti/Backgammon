package com.artibarti.backgammon.utils;

import javafx.scene.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to keep selections and design patterns organized.
 */
public class DesignManager
{
    /**
     * A class to store a {@link Node} and a resource as a {@link String}.
     */
    private class Element
    {
        /**
         * The node to apply the design resource on.
         */
        Node node;

        /**
         * The resource which can be an element of the styles.xml file.
         */
        String resource;

        /**
         * Constructor.
         *
         * @param node The node of the design element.
         * @param resource The resource of the design element.
         */
        public Element(Node node, String resource)
        {
            this.node = node;
            this.resource = resource;
        }

        /**
         * Refresh / apply the {@link Element#resource} on the {@link Element#node}.
         */
        public void refresh()
        {
            node.getStyleClass().clear();
            node.getStyleClass().add(resource);
        }
    }

    /**
     * A {@link Map} for storing a name as a {@link String} and an {@link Element} .
     */
    private Map<String, Element> elements;

    /**
     * Constructor.
     */
    public DesignManager()
    {
        this.elements = new HashMap<>();
    }

    /**
     * Set or append an {@link Element} to the {@link DesignManager#elements} list.
     * The method invoke {@link Element#refresh()} for all elements in the {@link DesignManager#elements} list.
     * @param name The name of the design element.
     * @param node The node for the element.
     * @param resource The resource for the element.
     */
    public void setStyle(String name, Node node, String resource)
    {
        if (elements.keySet().stream().anyMatch(p -> p == name))
        {
            elements.get(name).node.getStyleClass().clear();
            elements.get(name).node = node;
            elements.get(name).resource = resource;
        }
        else
        {
            elements.put(name, new Element(node, resource));
        }

        for (String s : elements.keySet())
        {
            elements.get(s).refresh();
        }
    }

    /**
     * Drop an element from the {@link DesignManager#elements} list.
     * @param name The name the design element to drop.
     */
    public void dropStyle(String name)
    {
        if (elements.keySet().stream().anyMatch(p -> p == name))
        {
            elements.get(name).node.getStyleClass().clear();
            elements.remove(name);
        }
    }
}
