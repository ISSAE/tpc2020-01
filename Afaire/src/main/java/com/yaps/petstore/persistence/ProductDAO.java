package com.yaps.petstore.persistence;

import com.yaps.petstore.domain.Product;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

import java.util.Collection;

/**
 * This class uses a HashTable to store product objects in it and serializes the hashmap.
 */
final public class ProductDAO extends DataAccessObject {

    private static final String HASHTABLE_FILE_NAME = "persistentProduct.ser";

    // ======================================
    // =            Constructors            =
    // ======================================
    public ProductDAO() {
        super(HASHTABLE_FILE_NAME);
    }


    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Given a product id this methods loads all the attributes of a Product object.
     * The product id cannot be null or empty.
     *
     * @return the found Product
     * @param id product identifier
     * @throws ObjectNotFoundException thrown if the product id is not found
     * @throws CheckException          is thrown if invalid data is found
     */
    public Product find(final String id) throws ObjectNotFoundException, CheckException {
        // Checks data integrity
        checkId(id);
        return (Product)super.select(id);
    }

    /**
     * This methods returns all Product objects from the system.
     *
     * @return a collection of Product objects
     * @throws ObjectNotFoundException thrown if the product id is not found
     */
    public Collection findAll() throws FinderException {
        final Collection products = super.selectAll();
        return products;
    }

    /**
     * When all the Product attibutes are set, calling this method will save the object
     * into a persistent layer. This method checks that the mandatory attributes
     * are set (product id, name and description).
     *
     * @throws DuplicateKeyException is thrown if a product with the same id
     *                               already exists in the system
     * @throws CheckException        is thrown if invalid data is found
     */
    public void insert(final Product product) throws DuplicateKeyException, CheckException {
        // Checks data integrity
        product.checkData();
    	super.insert(product, product.getId());
    }

    /**
     * When all the Product attibutes are set, calling this method will update the object
     * into a hashmap. This method checks that the mandatory attributes
     * are set (product id, name and description).
     *
     * @throws ObjectNotFoundException is thrown if the product id is not found
     * @throws CheckException  is thrown if invalid data is found
     * @throws DuplicateKeyException 
     */
    public void update(final Product product) throws ObjectNotFoundException, CheckException, DuplicateKeyException {
    	String id = product.getId();
    	checkId(id);
    	product.checkData();
    	super.update(product, id);
    }

    /**
     * Calling this method will remove the object from the hashmap.
     * The product id cannot be null or empty.
     *
     * @throws ObjectNotFoundException thrown if the product id is invalid or a system failure is occurs
     * @throws CheckException  is thrown if invalid data is found
     */
    public void remove(final String id) throws ObjectNotFoundException {
        super.remove(id);
    }


}
