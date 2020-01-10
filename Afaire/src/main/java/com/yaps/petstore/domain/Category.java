package com.yaps.petstore.domain;

import com.yaps.petstore.exception.*;

import java.io.Serializable;
import java.util.Collection;

/**
 * This class represents a Category in the catalog of the YAPS company.
 * The catalog is divided into categories. Each one divided into products
 * and each product in items..
 */
public final class Category implements Serializable {

    // ======================================
    // =             Attributes             =
    // ======================================
    private String _id;
    private String _name;
    private String _description;
    private Collection _products;

    // ======================================
    // =            Constructors            =
    // ======================================
    public Category() {
    }

    public Category(final String id) {
        _id = id;
    }

    public Category(final String id, final String name, final String description) {
        _id = id;
        _name = name;
        _description = description;
    }

    // ======================================
    // =            Private methods         =
    // ======================================

    // ======================================
    // =         Getters and Setters        =
    // ======================================
    public String getId() {
        return _id;
    }

    public String getName() {
        return _name;
    }

    public void setName(final String name) {
        _name = name;
    }

    public String getDescription() {
        return _description;
    }

    public void setDescription(final String description) {
        _description = description;
    }

    public Collection getProducts() {
        return _products;
    }

    /**
     * This method checks the integrity of the object data.
     *
     * @throws CheckException if data is invalid
     */
    public void checkData() throws CheckException {
        if (_name == null || "".equals(_name))
            throw new CheckException("Invalid name");
        if (_description == null || "".equals(_description))
            throw new CheckException("Invalid description");
    }

    public String toString() {
        final StringBuffer buf = new StringBuffer();
        buf.append("\n\tCategory {");
        buf.append("\n\t\tId=").append(_id);
        buf.append("\n\t\tName=").append(_name);
        buf.append("\n\t\tDescription=").append(_description);
        buf.append("\n\t}");
        return buf.toString();
    }
}
