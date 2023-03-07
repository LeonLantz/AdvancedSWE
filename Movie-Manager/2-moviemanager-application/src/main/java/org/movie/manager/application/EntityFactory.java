/*
Adopted/inspired by the lecture Software Engineering 4th semester DHBW 2022 by Mr. Lutz
 */
package org.movie.manager.application;
import org.movie.manager.domain.IPersistable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class EntityFactory {

    private HashMap<String, String> mapOfUnreferencedElements = new HashMap<>();

    private GenericEntityManager entityManager = null;

    private IPersistable persistableElement = null;
    private final String picture_path;

    public EntityFactory(GenericEntityManager em, String picture_path) {
        this.entityManager = em;
        this.picture_path = picture_path;
    }

    /**
     *
     * @param c the type of the class to be created
     * @param csvData the data read from a CSV file
     * @return the instance of the element just created
     * @throws Exception
     */
    public IPersistable createElement(Class<?> c, String[] csvData) throws Exception {

        if (c == null) {
            throw new IllegalArgumentException("Klasse muss angegeben werden ( Klasse ist null )!");
        }
        entityManager.persist( persistableElement );

        return persistableElement;
    }

    /**
     *
     * @param c
     * @param stringIDs
     * @return
     * @throws Exception
     */
    private List<IPersistable> getReferences(Class<?> c, String stringIDs) throws Exception {
        List<IPersistable> refs = new ArrayList<>();
        if( stringIDs == null  ||  stringIDs.isEmpty() ) throw new RuntimeException( "List of refs is empty or null" );

        String[] arrIDs = stringIDs.split(",");

        for( String sId: arrIDs ){
            if( !sId.isEmpty() && !(sId.indexOf( ' ' ) >= 0) ){
                IPersistable ae = entityManager.find(c, "getPrimaryKey", sId );
                if( ae != null )
                    refs.add( ae );
            }
        }

        return refs;
    }

    /**
     * for compatibility reasons, this method simply delegates to {@link #resolveUnreferencedReferences()}
     * @throws Exception
     */
    public void resolveUnresolvedReferences() throws Exception {
        this.resolveUnreferencedReferences();
    }

    /**
     * resolve the unresolved references stored in the local HashMap
     * @throws Exception
     */
    public void resolveUnreferencedReferences() throws Exception {
    }

}
