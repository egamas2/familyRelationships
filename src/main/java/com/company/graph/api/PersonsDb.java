package com.company.graph.api;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import com.company.graph.model.Relationship;
import com.company.graph.model.Person;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonsDb {

    private List<Person> persons;
    private List<Relationship> relationships;
    private Map<String, Person> personsByMail;
    private Map<String, Person> personsByName;

    public void connectEveryone() {
        if (persons != null && relationships != null) {
            personsByMail = persons.stream().collect(Collectors.toMap(Person::getMail, Function.identity()));
            relationships.forEach(r -> {
                final Person person = personsByMail.get(r.getPersonMail());
                if (person != null) {
                    r.setPerson(person);
                    person.getRelationships().add(r);
                }
                final Person relatedPerson = personsByMail.get(r.getRelatedPersonMail());
                if (relatedPerson != null) {
                    r.setRelatedPerson(relatedPerson);
                    relatedPerson.getRelationships().add(r);
                }
            });
            personsByName = persons.stream().collect(Collectors.toMap(Person::getName, Function.identity()));
        }
    }

    public int countFamily(Person person){ return getAllFamily(person).size(); }

    private List<Person> getAllFamily(Person person) {
        final Set<Person> allFamily = new HashSet<>();
        final Set<Person> pendingToVisit = new HashSet<>();
        allFamily.add(person);
        pendingToVisit.add(person);
        Person personToCheck = person;
        do {
            pendingToVisit.addAll(personToCheck.getDirectFamily());
            personToCheck = pickupNextRelative(allFamily, pendingToVisit);
            if (personToCheck != null) {
                allFamily.add(personToCheck);
            }
        } while (personToCheck != null);
        return new ArrayList<>(allFamily);
    }

    private Person pickupNextRelative(Set<Person> allFamily, Set<Person> pendingToVisit) {
        for (Person person : pendingToVisit) {
            if (!allFamily.contains(person)) {
                return person;
            }
        }
        return null;
    }

}
