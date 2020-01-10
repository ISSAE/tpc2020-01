package com.yaps.petstore.persistence;

import java.util.Collection;

import com.yaps.petstore.domain.Item;
import com.yaps.petstore.exception.CheckException;
import com.yaps.petstore.exception.CreateException;
import com.yaps.petstore.exception.DuplicateKeyException;
import com.yaps.petstore.exception.FinderException;
import com.yaps.petstore.exception.ObjectNotFoundException;
import com.yaps.petstore.exception.RemoveException;
import com.yaps.petstore.exception.UpdateException;

/**
 * This class uses a HashTable to store customer objects in it and serializes the hashmap.
 */
public final class ItemDAO extends DataAccessObject {

    // ======================================
    // =             Attributes             =
    // ======================================
    private static final String HASHTABLE_FILE_NAME = "persistentItem.ser";

    // ======================================
    // =            Constructors            =
    // ======================================
    public ItemDAO() {
        super(HASHTABLE_FILE_NAME);
    }
        
    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Given a item id this methods loads all the attributes of a Item object.
     * The item id cannot be null or empty.
     *
     * @return the found Item
     * @param id item identifier
     * @throws ObjectNotFoundException thrown if the item id is not found
     */
    public Item find(final String id) throws ObjectNotFoundException, CheckException {
        // Checks data integrity
        checkId(id);
    	return (Item) super.select(id);
    }

    /**
     * This methods returns all Item objects from the system.
     *
     * @return a collection of Item objects
     * @throws ObjectNotFoundException thrown if the product id is not found
     */
    public Collection findAll() throws ObjectNotFoundException {
    	return super.selectAll();
    }

    /**
     * When all the Item attibutes are set, calling this method will save the object
     * into a persistent layer. This method checks that the mandatory attributes
     * are set (item id, name and description).
     *
     * @param item Item Object to be inserted into the hashtable
     * @throws DuplicateKeyException is thrown if a item with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a mandatory attribute is not set
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if invalid data is found
     */
    public void insert(final Item item) throws CreateException, CheckException, DuplicateKeyException {

        // Checks data integrity
        item.checkData();

        // Uses the DAO to access the persistent layer
        super.insert(item, item.getId());
    }

    /**
     * When all the Item attibutes are set, calling this method will update the object
     * into a hashmap. This method checks that the mandatory attributes
     * are set (item id, name and description).
     *
     * @param item Item Object to be inserted into the hashtable
     * @throws UpdateException is thrown if a mandatory attribute is not set
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if invalid data is found
     * @throws DuplicateKeyException 
     * @throws ObjectNotFoundException 
     */
    public void update(final Item item) throws UpdateException, CheckException, ObjectNotFoundException, DuplicateKeyException {
    	String id = item.getId();
    	checkId(id);
    	item.checkData();
    	super.update(item, id);
    }

    /**
     * Calling this method will remove the object from the hashmap.
     * The item id cannot be null or empty.
     *
     * @param item Item Object to be inserted into the hashtable
     * @throws RemoveException thrown if the item id is invalid or a system failure is occurs
     * @throws CheckException  is thrown if invalid data is found
     */
    public void remove(final String id) throws ObjectNotFoundException{
    	super.remove(id);
    }



}