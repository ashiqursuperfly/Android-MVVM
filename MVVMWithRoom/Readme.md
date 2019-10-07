### Setting Up ROOM
##### 1. Add the latest gradle dependencies
##### 2. Create the Classes for the tables with @Entity Annotation, remember to make an attribute @PrimaryKey
##### 3. Create DAO interfaces for the entities with @Dao annotation (in general we need a DAO interface for each of the entities)
##### 4. After all entities are done, create the Database singleton. Inside the database singleton make abstract methods return your DAOs like this:
```java
    public abstract NoteDAO getNoteDao();
    public abstract PersonDAO getPersonDao();
```
#####  We should use always use these abstract methods to do db operations(that we specified in the DAO) e.g: getNoteDao().insert(new Note())
### Do not bother about the implementations of these abstract methods, room provides implementations for these methods.

 