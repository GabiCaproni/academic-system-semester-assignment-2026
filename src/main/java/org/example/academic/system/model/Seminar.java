package org.example.academic.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Seminar extends Assessment {

    private String topic;

    public Seminar( String name, double weight, double value, String topic) {

        super(name, weight, value);
        this.topic = topic;
    }
}