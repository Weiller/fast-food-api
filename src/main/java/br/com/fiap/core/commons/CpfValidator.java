package br.com.fiap.core.commons;

public class CpfValidator {

    public static boolean isValid(String cpf) {
        if (cpf == null) {
            return false;
        }

        return hasValidCheckDigits(cpf);
    }

    private static boolean hasValidCheckDigits(String cpf) {
        if (cpf.length() != 11) {
            return false;
        }

        int firstCheckDigit = calculateCheckDigit(cpf.substring(0, 9));
        int secondCheckDigit = calculateCheckDigit(cpf.substring(0, 10));

        return firstCheckDigit == Character.getNumericValue(cpf.charAt(9)) &&
                secondCheckDigit == Character.getNumericValue(cpf.charAt(10));
    }

    private static int calculateCheckDigit(String digits) {
        int sum = 0;
        for (int i = 0; i < digits.length(); i++) {
            sum += Character.getNumericValue(digits.charAt(i)) * (digits.length() + 1 - i);
        }

        int remainder = sum % 11;
        return remainder < 2 ? 0 : 11 - remainder;
    }
}