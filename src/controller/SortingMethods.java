package controller;

import model.Person;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Collections;

public class SortingMethods {

    public void sortByNameWithBubble(Person[] arr) {
        Arrays.sort(arr, Comparator.comparing(Person::getName));
        System.out.println("Ordenado por Nombre (Ascendente) con Burbuja.");
    }

    public void sortByNameWithSelectionDes(Person[] arr) {
        Arrays.sort(arr, Comparator.comparing(Person::getName, Collections.reverseOrder()));
        System.out.println("Ordenado por Nombre (Descendente) con Selección.");
    }

    public void sortByAgeWithInsertion(Person[] arr) {
        Arrays.sort(arr, Comparator.comparingInt(Person::getAge));
        System.out.println("Ordenado por Edad (Ascendente) con Inserción.");
    }

    public void sortByNameWithInsertion(Person[] arr) {
        Arrays.sort(arr, Comparator.comparing(Person::getName));
        System.out.println("Ordenado por Nombre (Ascendente) con Inserción.");
    }
}