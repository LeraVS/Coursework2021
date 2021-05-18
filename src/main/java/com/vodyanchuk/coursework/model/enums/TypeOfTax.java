package com.vodyanchuk.coursework.model.enums;

public enum TypeOfTax {
        SINGLETAX("Единый налог"), INCOMETAX("Подоходный налог"), TAXUNDERSIMPLIFIEDSYSTEM("УСН");

        private final String typeOfTax;

        TypeOfTax(String typeOfTax) {
            this.typeOfTax = typeOfTax;
        }

        public String getTypeOfTax() {
            return typeOfTax;
        }

    @Override
    public String toString() {
        return typeOfTax;
    }
}
