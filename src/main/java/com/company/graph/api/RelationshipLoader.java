package com.company.graph.api;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.company.graph.model.RelationType;
import com.company.graph.model.Person;
import com.company.graph.model.Relationship;

public class RelationshipLoader {
    public List<Person> loadPersons() {
        final String peopleDbFile = "build/resources/test/people.csv";
        try (Stream<String> lines = Files.lines(Paths.get(peopleDbFile))) {
            return lines
                    .map(line -> line.split(","))
                    .filter(txPerson -> txPerson.length==3)
                    .map(txPerson -> Person.builder().name(txPerson[0]).mail(txPerson[1]).age(Integer.valueOf(txPerson[2])).relationships(new HashSet<>()).build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
    public List<Relationship> loadRelationShips() {
        final String peopleDbFile = "build/resources/test/relationships.csv";
        try (Stream<String> lines = Files.lines(Paths.get(peopleDbFile))) {
            return lines
                    .map(line -> line.split(","))
                    .filter(txRelationship -> txRelationship.length == 3)
                    .map(txRelationship -> Relationship.builder().personMail(txRelationship[0]).type(RelationType.valueOf(txRelationship[1])).relatedPersonMail(txRelationship[2]).build())
                    .collect(Collectors.toList());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }
}
