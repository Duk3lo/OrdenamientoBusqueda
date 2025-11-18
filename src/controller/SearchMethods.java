package controller;

import model.Person;
import view.View;

public class SearchMethods {
    
    public boolean isSortedByAge(Person[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getAge() > arr[i + 1].getAge()) {
                return false;
            }
        }
        return true;
    }
    
    public boolean isSortedByName(Person[] arr) {
        if (arr == null || arr.length < 2) {
            return true;
        }
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i].getName().compareTo(arr[i + 1].getName()) > 0) {
                return false;
            }
        }
        return true;
    }

    public Person binarySearchByAge(Person[] arr, int ageBuscada, View view) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            int midValue = arr[mid].getAge();

            StringBuilder sb = new StringBuilder();
            for (int i = low; i <= high; i++) {
                sb.append(arr[i].getAge()).append(" | ");
            }

            String direction = "";
            if (midValue == ageBuscada) {
                direction = "ENCONTRADO";
            } else if (ageBuscada > midValue) {
                direction = "DERECHA";
            } else {
                direction = "IZQUIERDA";
            }

            view.showSearchState(sb.toString(), low, high, mid, String.valueOf(midValue), direction);

            if (midValue == ageBuscada) {
                int firstOccurrence = mid;
                while (firstOccurrence > low && arr[firstOccurrence - 1].getAge() == ageBuscada) {
                    firstOccurrence--;
                }
                return arr[firstOccurrence];
            } else if (ageBuscada > midValue) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }

    public Person binarySearchByName(Person[] arr, String nameBuscado, View view) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            String midName = arr[mid].getName();
            int comparison = nameBuscado.compareTo(midName);

            StringBuilder sb = new StringBuilder();
            for (int i = low; i <= high; i++) {
                sb.append(arr[i].getName()).append(" | ");
            }

            String direction = "";
            if (comparison == 0) {
                direction = "ENCONTRADO";
            } else if (comparison > 0) {
                direction = "DERECHA";
            } else {
                direction = "IZQUIERDA";
            }

            view.showSearchState(sb.toString(), low, high, mid, midName, direction);

            if (comparison == 0) {
                int firstOccurrence = mid;
                while (firstOccurrence > low && arr[firstOccurrence - 1].getName().equals(nameBuscado)) {
                    firstOccurrence--;
                }
                return arr[firstOccurrence];
            } else if (comparison > 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        return null;
    }
}