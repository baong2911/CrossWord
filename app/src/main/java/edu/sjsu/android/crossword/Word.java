package edu.sjsu.android.crossword;

public class Word {
    private int id;
    private String name;
    private String definition;

    public Word(int id, String name, String definition) {
        this.id = id;
        this.name = name;
        this.definition = definition;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDefinition() {
        return definition;
    }

    public int getNameLength() { return name.length(); }
}