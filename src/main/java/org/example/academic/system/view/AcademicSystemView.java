package org.example.academic.system.view;

import java.util.Scanner;
import org.example.academic.system.controller.AcademicSystemController;
import org.example.academic.system.controller.AuthenticationController;
import org.example.academic.system.exception.AcademicSystemException;
import org.example.academic.system.exception.AuthenticationException;
import org.example.academic.system.exception.AuthorizationException;
import org.example.academic.system.exception.InvalidNumericInputException;
import org.example.academic.system.model.AcademicClass;
import org.example.academic.system.model.Assessment;
import org.example.academic.system.model.Assignment;
import org.example.academic.system.model.Exam;
import org.example.academic.system.model.PracticalAssignment;
import org.example.academic.system.model.Role;
import org.example.academic.system.model.Seminar;
import org.example.academic.system.model.User;
import org.example.academic.system.service.AuthorizationService;
import org.example.academic.system.exception.InvalidMenuOptionException;
import org.example.academic.system.exception.KeyboardInputException;

public class AcademicSystemView {

    private final Scanner scanner;
    private final AuthenticationController authController;
    private final AcademicSystemController academicController;
    private final AuthorizationService authorizationService;

    public AcademicSystemView(AuthenticationController authController, AcademicSystemController academicController) {
        this.authController = authController;
        this.academicController = academicController;
        this.scanner = new Scanner(System.in);
        this.authorizationService = new AuthorizationService();
    }

    public void start() {

    while (true) {

        login();

        while (authController.getLoggedUser() != null) {

            try {

                showMenu();

                int option = readInt();

                User user =
                        authController.getLoggedUser();

                if (user.getRole() == Role.ADMIN) {

                    switch (option) {

                        case 1:
                            registerClass();
                            break;

                        case 2:
                            registerAssessment();
                            break;

                        case 3:
                            generateSummary();
                            break;

                        case 4:
                            generateWeight();
                            break;

                        case 5:
                            logout();
                            break;

                        case 9:
                            authController.logout();
                            System.out.println(
                                    "Sistema encerrado.");
                            System.exit(0);
                            break;

                        default:
                            throw new InvalidMenuOptionException(
                                    "Opção inválida.");
                    }

                } else {

                    switch (option) {

                        case 1:
                            registerAssessment();
                            break;

                        case 2:
                            generateSummary();
                            break;

                        case 3:
                            generateWeight();
                            break;

                        case 4:
                            logout();
                            break;

                        case 9:
                            authController.logout();
                            System.out.println(
                                    "Sistema encerrado.");
                            System.exit(0);
                            break;

                        default:
                            throw new InvalidMenuOptionException(
                                    "Opção inválida.");
                    }
                }

            } catch (KeyboardInputException e) {

                System.out.println(
                        "Erro: "
                        + e.getMessage());
            }
        }
    }
}

    private void showMenu() {

        User user = authController.getLoggedUser();

        System.out.println("\n===== MENU =====");

        if (user.getRole() == Role.ADMIN) {

            System.out.println("1 - Cadastrar turma");
            System.out.println("2 - Registrar avaliação");
            System.out.println("3 - Relatório resumido");
            System.out.println("4 - Relatório de pesos");
            System.out.println("5 - Logout");
            System.out.println("9 - Sair do sistema");

        } else {

            System.out.println("1 - Registrar avaliação");
            System.out.println("2 - Relatório resumido");
            System.out.println("3 - Relatório de pesos");
            System.out.println("4 - Logout");
            System.out.println("9 - Sair do sistema");
        }
    }

    private void login() {

        boolean authenticated = false;

        while (!authenticated) {

            System.out.println("\n===== LOGIN =====");

            System.out.print("Usuário: ");
            String username = scanner.nextLine();

            System.out.print("Senha: ");
            String password = scanner.nextLine();

            try {

                authenticated
                        = authController.login(
                                username,
                                password);

            } catch (AuthenticationException e) {

                System.out.println(
                        e.getMessage());
            }
        }

        System.out.println(
                "Login realizado com sucesso!");
    }

    public void administrativeOperation() {

        User user = authController.getLoggedUser();

        if (user.getRole() != Role.ADMIN) {
            throw new SecurityException(
                    "Acesso negado.");
        }

        // operação administrativa
    }

    private void registerAssessment() {

    try {

        if (academicController.getClasses().isEmpty()) {

            System.out.println(
                    "Nenhuma turma cadastrada.");
            return;
        }

        System.out.println(
                "\nTurmas disponíveis:");

        for (int i = 0;
                i < academicController.getClasses().size();
                i++) {

            AcademicClass academicClass =
                    academicController.getClasses().get(i);

            System.out.println(
                    (i + 1)
                    + " - "
                    + academicClass.getName());
        }

        System.out.print(
                "Escolha a turma: ");

        int classIndex =
                readInt() - 1;
        
        if (classIndex < 0
                || classIndex >= academicController.getClasses().size()) {

            System.out.println("Turma inexistente.");
            return;
        }

        AcademicClass selectedClass
                = academicController.getClasses().get(classIndex);

        System.out.println("\nTipo de avaliação:");
        System.out.println("1 - Prova");
        System.out.println("2 - Trabalho Prático");
        System.out.println("3 - Seminário");
        System.out.println("4 - Assignment");
        int type = readInt();;

        System.out.print("Nome: ");
        String name = scanner.nextLine();

        System.out.print("Peso: ");
        double weight = readDouble();

        System.out.print("Valor: ");
        double value = readDouble();

        Assessment assessment = null;

        switch (type) {

            case 1:

                assessment
                        = new Exam(name, weight, value);
                break;

            case 2:

                System.out.print("Tecnologia: ");
                String technology
                        = scanner.nextLine();

                assessment
                        = new PracticalAssignment(name, weight, value, technology);
                break;

            case 3:

                System.out.print("Tema: ");
                String topic
                        = scanner.nextLine();

                assessment
                        = new Seminar(name, weight, value, topic);
                break;

            case 4:

                assessment = new Assignment(
                        name,
                        weight,
                        value);
                break;

            default:

                System.out.println("Tipo inválido.");
                return;
        }

        try {

            academicController.registerAssessment(
                    selectedClass,
                    assessment);

            System.out.println(
                    "[LOG] Avaliação cadastrada na turma "
                    + selectedClass.getCode());

            System.out.println(
                    "Avaliação cadastrada com sucesso!");

        } catch (AcademicSystemException e) {

            System.out.println(
                    "Erro: " + e.getMessage());
        }    } catch (KeyboardInputException e) {

        System.out.println(
                "Erro: "
                + e.getMessage());

    } catch (AcademicSystemException e) {

        System.out.println(
                "Erro: "
                + e.getMessage());
    }
}
    

    private void registerClass() {

        try {

            authorizationService.authorize(
                    authController.getLoggedUser(),
                    Role.ADMIN);

        } catch (AuthorizationException e) {

            System.out.println(e.getMessage());
            return;
        }

        try {

            System.out.print("Código da turma: ");
            String code = scanner.nextLine();

            System.out.print("Nome da turma: ");
            String name = scanner.nextLine();

            AcademicClass academicClass
                    = new AcademicClass(code, name);

            academicController.registerClass(
                    academicClass);

            System.out.println(
                    "Turma cadastrada com sucesso!");

        } catch (AcademicSystemException e) {

            System.out.println(
                    "Erro: " + e.getMessage());
        }
    }

    private void generateSummary() {

        System.out.println(
                "[LOG] Relatório resumido gerado por "
                + authController.getLoggedUser().getRole());

        String report
                = academicController.generateSummary();

        System.out.println(
                "\n===== RELATÓRIO RESUMIDO =====");

        System.out.println(report);
    }

    private void generateWeight() {

        System.out.println(
                "[LOG] Relatório de peso gerado por "
                + authController.getLoggedUser().getRole());

        String report
                = academicController.generateWeightReport();

        System.out.println(
                "\n===== RELATÓRIO DE PESOS =====");

        System.out.println(report);
    }

    private void logout() {

        User user = authController.getLoggedUser();

        System.out.println(
                "[LOG] Usuário "
                + user.getUsername()
                + " realizou logout.");

        authController.logout();

        System.out.println(
                "Logout realizado com sucesso.");
    }

    private int readInt()
        throws InvalidNumericInputException {

    try {

        return Integer.parseInt(
                scanner.nextLine());

    } catch (NumberFormatException e) {

        throw new InvalidNumericInputException(
                "Digite um número válido.");
    }
}

    private double readDouble()
        throws InvalidNumericInputException {

    try {

        return Double.parseDouble(
                scanner.nextLine());

    } catch (NumberFormatException e) {

        throw new InvalidNumericInputException(
                "Digite um valor numérico válido.");
    }
}

}
