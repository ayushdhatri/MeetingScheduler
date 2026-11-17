package models;

import util.IdGenerator;

public class User {
    private final String id;

    private final String name;

    private final String email;

    public User(String name, String email){
        this.id = IdGenerator.generateId();
        this.name = name;
        this.email = email;
    }

    public String getId(){
        return this.id;
    }

    public String getEmail(){
        return this.email;
    }

    public String getName(){
        return this.name;
    }
}
