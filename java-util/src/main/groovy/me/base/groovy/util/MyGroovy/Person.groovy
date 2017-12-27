package me.base.groovy.util.MyGroovy

class Person {
    String name;
    Integer age;

    public Person() {}

    public Person(name, age) {
        this.name = name;
        this.age = age;
    }

    def getJob() {
        return "Groovy devr"
    }

    def setJob() {
        return "Good ${Job}"
    }


    def someMethod() {
        "method called"
    }


    String annotherMethod() {
        'another method called'
    }

    def thiedMethod(param1) {
        '$param1 passed'
    }

    static String fourthMethod(String param1) {
        "$param1 passed"
    }

    @Override
    String toString() {
        return "Name=${name}, age = ${age}";
    }
}
