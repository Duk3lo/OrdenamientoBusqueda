package controller;

import model.Person;
import view.View;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;

public class Controller {
    private View view;
    private List<Person> persons;
    private SortingMethods sortingMethods;
    private SearchMethods searchMethods;

    private int currentSortCriterion = 0;

    public Controller(View view, SortingMethods sortingMethods, SearchMethods searchMethods) {
        this.view = view;
        this.sortingMethods = sortingMethods;
        this.searchMethods = searchMethods;
        this.persons = new ArrayList<>();
    }

    public void addPerson(Person p) {
        if (p != null) {
            this.persons.add(p);
            currentSortCriterion = 0;
        }
    }

    public void inputPersons() {
        System.out.print("Ingrese cantidad de Personas a añadir: ");
        String countStr = view.inputSearchValue(3);
        int n;
        try {
            n = Integer.parseInt(countStr.trim());
        } catch (NumberFormatException e) {
            System.out.println("Entrada inválida. No se ingresará ninguna persona.");
            return;
        }

        for (int i = 0; i < n; i++) {
            System.out.println("--- Ingresando Persona #" + (i + 1) + " ---");
            addPerson(view.inputPerson());
        }
        System.out.println(n + " persona(s) ingresada(s) correctamente.");
    }

    public void sortPersons() {
        if (persons.isEmpty()) {
            System.out.println("No hay personas para ordenar.");
            return;
        }
        Person[] arr = persons.toArray(new Person[0]);
        int option = view.selectSortingMethod();

        switch (option) {
            case 1:
                sortingMethods.sortByNameWithBubble(arr);
                currentSortCriterion = 2;
                break;
            case 2:
                sortingMethods.sortByNameWithSelectionDes(arr);
                currentSortCriterion = 3;
                break;
            case 3:
                sortingMethods.sortByAgeWithInsertion(arr);
                currentSortCriterion = 1;
                break;
            case 4:
                sortingMethods.sortByNameWithInsertion(arr);
                currentSortCriterion = 2;
                break;
            default:
                System.out.println("No se realizó el ordenamiento.");
                return;
        }
        this.persons = new ArrayList<>(Arrays.asList(arr));
        view.displayPersons(arr);
    }

    public void searchPerson() {
        if (persons.isEmpty()) {
            System.out.println("No hay personas para buscar.");
            return;
        }

        int criterion = view.selectSearchCriterion();
        if (criterion < 1)
            return;

        Person[] arr = persons.toArray(new Person[0]);
        boolean isOrderedCorrectly = false;

        if (criterion == 1) {
            isOrderedCorrectly = searchMethods.isSortedByAge(arr);
        } else if (criterion == 2) {
            isOrderedCorrectly = searchMethods.isSortedByName(arr) && currentSortCriterion == 2;
        }

        if (!isOrderedCorrectly) {
            System.out.println("\n--- Alerta de Ordenamiento ---");
            System.out.println("El arreglo no está ordenado por el criterio seleccionado.");
            System.out.println("Se procederá a ordenar antes de buscar.");

            if (criterion == 1) {
                sortingMethods.sortByAgeWithInsertion(arr);
                currentSortCriterion = 1;
            } else {
                sortingMethods.sortByNameWithInsertion(arr);
                currentSortCriterion = 2;
            }
            this.persons = new ArrayList<>(Arrays.asList(arr));
        }

        String searchValue = view.inputSearchValue(criterion);

        Person foundPerson = null;
        if (criterion == 1) {
            try {
                int ageToSearch = Integer.parseInt(searchValue.trim());
                foundPerson = searchMethods.binarySearchByAge(arr, ageToSearch, view);
            } catch (NumberFormatException e) {
                System.out.println("Error: La edad debe ser un número entero.");
            }
        } else if (criterion == 2) {
            foundPerson = searchMethods.binarySearchByName(arr, searchValue, view);
        }

        view.displaySearchResult(foundPerson);
    }

    public void start() {
        int option;
        do {
            option = view.showMenu();
            switch (option) {
                case 1:
                    inputPersons();
                    break;
                case 2:
                    sortPersons();
                    break;
                case 3:
                    searchPerson();
                    break;
                case 4:
                    view.displayPersons(persons.toArray(new Person[0]));
                    break;
                case 0:
                    System.out.println("Saliendo del programa. ¡Hasta luego!");
                    break;
                case -1:
                    break;
                default:
                    System.out.println("Opción no válida. Por favor, intente de nuevo."); 
            }
        } while (option != 0);
    }
}