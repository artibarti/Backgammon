package com.artibarti.backgammon.utils;

import javafx.scene.Node;
import java.util.HashMap;
import java.util.Map;

/**
 * Helper class to keep selections and design patterns organized
 */
public class DesignManager
{
    private class Element
    {
        Node node;
        String resource;

        public Element(Node node, String resource)
        {
            this.node = node;
            this.resource = resource;
        }

        public void refresh()
        {
            node.getStyleClass().clear();
            node.getStyleClass().add(resource);
        }
    }

    private Map<String, Element> elements;

    public DesignManager()
    {
        this.elements = new HashMap<>();
    }

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

    public void dropStyle(String name)
    {
        if (elements.keySet().stream().anyMatch(p -> p == name))
        {
            elements.get(name).node.getStyleClass().clear();
            elements.remove(name);
        }
    }
}
