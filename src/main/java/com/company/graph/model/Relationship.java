package com.company.graph.model;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode(of = {"person","relatedPerson","type"})
@ToString(of = {"person","relatedPerson","type"})
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Relationship {
    private Person person;
    private Person relatedPerson;
    private RelationType type;

//  "Transient"  values to populate the pojo from the file
    private String personMail;
    private String relatedPersonMail;
}
