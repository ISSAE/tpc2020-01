package com.yaps.petstore.persistence;

import com.yaps.petstore.domain.Category;
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
final public class CategoryDAO extends DataAccessObject {

    private static final String HASHTABLE_FILE_NAME = "persistentCategory.ser";

    // ======================================
    // =            Constructors            =
    // ======================================
    public CategoryDAO() {
        super(HASHTABLE_FILE_NAME);
    }


    // ======================================
    // =           Business methods         =
    // ======================================
    /**
     * Given a category id this methods loads all the attributes of a Category object.
     * The category id cannot be null or empty.
     *
     * @return the found Caregory
     * @param id category identifier
     * @throws ObjectNotFoundException thrown if the category id is not found
     * @throws CheckException  is thrown if id is invalid
     */
    public Category find(final String id) throws ObjectNotFoundException, CheckException {
        // Checks data integrity
        checkId(id);
        return (Category)super.select(id);
        }

    /**
     * This methods returns all Category objects from the system.
     *
     * @return a collection of Category objects
     * @throws ObjectNotFoundException thrown if the product id is not found
     */
    public Collection findAll() throws FinderException {
        return super.selectAll();
    }

    /**
     * When all the Category attibutes are set, calling this method will save the object
     * into a persistent layer. This method checks that the mandatory attributes
     * are set (category id, name and description).
     *
     * @throws DuplicateKeyException is thrown if a category with the same id
     *                               already exists in the system
     * @throws CreateException       is thrown if a mandatory attribute is not set
     *                               or a system failure is occurs
     * @throws CheckException        is thrown if invalid data is found
     */
    public void insert(final Category category) throws CreateException, CheckException, DuplicateKeyException {
        // Checks data integrity
        category.checkData();
    	super.insert(category, category.getId());
    }

    /**
     * When all the Category attributes are set, calling this method will update the object
     * into a hashmap. This method checks that the mandatory attributes
     * are set (category id, name and description).
     *
     * @throws UpdateException is thrown if a mandatory attribute is not set
     *                         or a system failure is occurs
     * @throws CheckException  is thrown if invalid data is found
     * @throws DuplicateKeyException 
     * @throws ObjectNotFoundException 
     */
    public void update(final Category category) throws UpdateException, CheckException, ObjectNotFoundException, DuplicateKeyException {
    	String id = category.getId();
    	checkId(id);
    	category.checkData();
    	super.update(category, id);
    }

    /**
     * Calling this method will remove the object from the hashmap.
     * The category id cannot be null or empty.
     *
     * @throws ObjectNotFoundException thrown if the category id is invalid or a system failure is occurs
     * @throws CheckException  is thrown if invalid data is found
     */
    public void remove(final String id) throws ObjectNotFoundException {
    	super.remove(id);
    }

    
}
