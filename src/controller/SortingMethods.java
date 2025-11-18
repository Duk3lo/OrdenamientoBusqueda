package controller;

import model.Person;

public class SortingMethods {

    public void sortByNameWithBubble(Person[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j].getName().compareTo(arr[j + 1].getName()) > 0) {
                    Person temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
        System.out.println("Ordenado por Nombre (Ascendente) con Burbuja.");
    }

    public void sortByNameWithSelectionDes(Person[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int max_idx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j].getName().compareTo(arr[max_idx].getName()) > 0) {
                    max_idx = j;
                }
            }
            Person temp = arr[max_idx];
            arr[max_idx] = arr[i];
            arr[i] = temp;
        }
        System.out.println("Ordenado por Nombre (Descendente) con Selección.");
    }

    public void sortByAgeWithInsertion(Person[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Person key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].getAge() > key.getAge()) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println("Ordenado por Edad (Ascendente) con Inserción.");
    }

    public void sortByNameWithInsertion(Person[] arr) {
        int n = arr.length;
        for (int i = 1; i < n; i++) {
            Person key = arr[i];
            int j = i - 1;

            while (j >= 0 && arr[j].getName().compareTo(key.getName()) > 0) {
                arr[j + 1] = arr[j];
                j = j - 1;
            }
            arr[j + 1] = key;
        }
        System.out.println("Ordenado por Nombre (Ascendente) con Inserción.");
    }
}