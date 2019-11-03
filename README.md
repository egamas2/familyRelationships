# FamilyRelationships
Project to explore family relationships. 

Complete the following exercises:

Exercise 1
---

Please implement code and data structures that read the files
```
src/test/java/resources/people.csv
src/test/java/resources/relationships.csv
```
and use them to build an in-memory data structure that represents the people in the file and their relationships with each other.

Exercise 2 - Validate correct people loaded
---

Write a test to validate that you have loaded the expected number of people.

Exercise 3 - Validate correct relationships loaded
---

Write a test to validate that the following people have the correct expected number of connections to other people

Bob (4 relationships)
Jenny (3 relationships)
Nigel (2 relationships)
Alan (0 relationships)

Exercise 4 - Write a method that calculates the size of the extended family
---

Write a method which, when passed the object representing a particular person, returns an int representing the size of their extended family including themselves. Their extended family includes anyone connected to them by a chain of family relationships of any length, so your solution will need to work for arbitrarily deep extended families. It should not count their friends. Write tests that validate this returns the correct result for the families of:

Jenny (4 family members)
Bob (4 family members)

Requirements:
---
In memory database. No strong error checking is needed


