package com.company.graph.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"mail"})
@ToString(of = "name")
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Person {
    private String name;
    private String mail;
    private Integer age;
    private HashSet<Relationship> relationships;

    public Set<Person> getDirectFamily() {
        final List<Person> personsFromThisPerson = relationships.stream()
                .filter(r -> RelationType.FAMILY == r.getType())
                .map(Relationship::getRelatedPerson).distinct().collect(Collectors.toList());
        final List<Person> personsToThisPerson = personsFromThisPerson.stream()
                .flatMap(p -> p.getRelationships().stream())
                .filter(r -> r.getRelatedPerson().equals(this) && RelationType.FAMILY == r.getType())
                .map(Relationship::getPerson).distinct().collect(Collectors.toList());
        HashSet<Person> result = new HashSet<>(personsFromThisPerson);
        result.addAll(personsToThisPerson);
        return result;
    }

}
