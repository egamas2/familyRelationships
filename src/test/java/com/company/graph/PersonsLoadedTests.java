package com.company.graph;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.BeforeClass;
import org.junit.Test;

import com.company.graph.api.PersonsDb;
import com.company.graph.api.RelationshipLoader;

public class PersonsLoadedTests {

    private static PersonsDb personsDb;

    @BeforeClass
    public static void loadDb() {
        RelationshipLoader loader = new RelationshipLoader();
        personsDb = PersonsDb.builder().persons(loader.loadPersons()).relationships(loader.loadRelationShips()).build();
        personsDb.connectEveryone();
    }

    @Test
    public void loadPersonsTest() {
        assertEquals("Assert that 12 persons are loaded", 12, personsDb.getPersons().size());
        assertEquals("Assert that 16 relationships are loaded", 16, personsDb.getRelationships().size());
    }

    @Test
    public void checkRelationshipsTest(){
        assertEquals("Bob (4 relationships)", 4, personsDb.getPersonsByName().get("Bob").getRelationships().size());
        assertEquals("Jenny (3 relationships)", 3, personsDb.getPersonsByName().get("Jenny").getRelationships().size());
        assertEquals("Nigel (2 relationships)", 2, personsDb.getPersonsByName().get("Nigel").getRelationships().size());
        assertEquals("Alan (0 relationships)", 0, personsDb.getPersonsByName().get("Alan").getRelationships().size());
    }

    @Test
    public void extendedFamilyTest(){
        assertEquals("Jenny (4 family members)", 4, personsDb.countFamily(personsDb.getPersonsByName().get("Jenny")));
        assertEquals("Bob (4 family members)", 4, personsDb.countFamily(personsDb.getPersonsByName().get("Bob")));
    }
}
