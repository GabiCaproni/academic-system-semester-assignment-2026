package org.example.academic.system;


import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.view.AcademicSystemView;

public class Main {

    public static void main(String[] args) {

        AcademicSystemController controller =
                new AcademicSystemController();

        AcademicSystemView view =
                new AcademicSystemView(controller);

        view.start();
    }
}