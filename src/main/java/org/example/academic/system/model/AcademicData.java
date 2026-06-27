package org.example.academic.system.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class AcademicData implements Serializable {

private static final long serialVersionUID = 1L;

private List<AcademicClass> classes = new ArrayList<>();

private List<User> users = new ArrayList<>();


}
