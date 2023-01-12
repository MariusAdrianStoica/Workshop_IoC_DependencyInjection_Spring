package se.lexicon.sequencer;

public class StudentIdGenerator {

    private static int sequencer=0;

public static int nextId(){

    return ++sequencer;
}
}
