package view;

import java.util.Scanner;
import model.Person;
import java.util.InputMismatchException;

public class View {
    private Scanner scanner;

    public View() {
        this.scanner = new Scanner(System.in);
    }

    public int showMenu() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Ingresar Personas");
        System.out.println("2. Ordenar Personas");
        System.out.println("3. Buscar Persona (Búsqueda Binaria)");
        System.out.println("4. Mostrar Personas");
        System.out.println("0. Salir");
        System.out.print("Seleccione una opción: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            return option;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            return -1;
        }
    }

    public Person inputPerson() {
        String name = inputName();
        int age = inputAge();
        return new Person(name, age);
    }

    public int inputAge() {
        int age = -1;
        boolean valid = false;
        do {
            System.out.print(" Edad: ");
            try {
                age = scanner.nextInt();
                scanner.nextLine();
                if (age >= 0) {
                    valid = true;
                } else {
                    System.out.println("La edad no puede ser negativa. Intente nuevamente.");
                }
            } catch (InputMismatchException e) {
                scanner.nextLine();
                System.out.println("Entrada inválida. Por favor, ingrese un número entero para la edad.");
            }
        } while (!valid);
        return age;
    }

    public String inputName() {
        System.out.print(" Nombre: ");
        return scanner.nextLine();
    }

    public int selectSortingMethod() {
        System.out.println("\n--- Métodos de Ordenamiento ---");
        System.out.println("1. Por Nombre (Burbuja)");
        System.out.println("2. Por Nombre Descendente (Selección)");
        System.out.println("3. Por Edad (Inserción)");
        System.out.println("4. Por Nombre (Inserción)");
        System.out.print("Seleccione un método: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option >= 1 && option <= 4)
                return option;
            System.out.println("Opción de ordenamiento inválida.");
            return -1;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            return -1;
        }
    }

    public int selectSearchCriterion() {
        System.out.println("\n--- Criterio de Búsqueda ---");
        System.out.println("1. Por Edad");
        System.out.println("2. Por Nombre");
        System.out.print("Seleccione un criterio: ");
        try {
            int option = scanner.nextInt();
            scanner.nextLine();
            if (option >= 1 && option <= 2)
                return option;
            System.out.println("Opción de criterio de búsqueda inválida.");
            return -1;
        } catch (InputMismatchException e) {
            scanner.nextLine();
            System.out.println("Entrada inválida. Por favor, ingrese un número.");
            return -1;
        }
    }

    public void displayPersons(Person[] persons) {
        if (persons == null || persons.length == 0) {
            System.out.println("No hay personas ingresadas.");
            return;
        }
        System.out.println("\n--- Listado de Personas (" + persons.length + " en total) ---");
        for (Person p : persons) {
            System.out.println(p.toString());
        }
    }

    public void displaySearchResult(Person person) {
        if (person != null) {
            System.out.println("\n--- Búsqueda Exitosa ---");
            System.out.println("Persona encontrada: " + person.toString());
        } else {
            System.out.println("\n--- Búsqueda Fallida ---");
            System.out.println("No se encontró una persona con el criterio buscado.");
        }
    }

    public String inputSearchValue(int criterion) {
        if (criterion == 1) {
            System.out.print("Ingrese la edad a buscar: ");
        } else if (criterion == 2) {
            System.out.print("Ingrese el nombre a buscar: ");
        }
        return scanner.nextLine();
    }

    public void showSearchState(String subArreglo, int bajo, int alto, int centro, String valorCentro,
            String direccion) {
        System.out.println(subArreglo);
        System.out.println("bajo=" + bajo + " alto=" + alto + " centro=" + centro + " valorCentro=" + valorCentro
                + " --> " + direccion + "");
    }
}