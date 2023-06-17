package com.example.mts.utils;

/**
 * Класс для валидации даннных.
 */
public class Validator {

    /**
     * Возвращает истину, если переданная строка является ip-адресом.
     * @param ip проверяемая строка.
     * @return истина, если переданная строка является ip-адресом.
     */
    public static boolean isIpValid(String ip) {
        return ip != null && ip.matches("^((25[0-5]|(2[0-4]|1\\d|[1-9]|)\\d)\\.?\\b){4}$");
    }

    /**
     * Возвращает истину, если переданная строка является MAC-адресом.
     * @param mac проверяемая строка.
     * @return истина, если переданная строка является MAC-адресом.
     */
    public static boolean isMacValid(String mac) {
        return mac != null && mac.matches("^([0-9A-Fa-f]{2}[:-]){5}([0-9A-Fa-f]{2})$");
    }
}
