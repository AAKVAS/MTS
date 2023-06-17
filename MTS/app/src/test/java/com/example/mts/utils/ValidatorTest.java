package com.example.mts.utils;

import org.junit.Assert;
import org.junit.Test;

public class ValidatorTest {

    @Test
    public void isIpValid_ValidIp_ReturnsTrue() {
        String ip = "199.23.56.89";
        boolean result = Validator.isIpValid(ip);
        Assert.assertTrue(result);
    }

    @Test
    public void isIpValid_ValidIpWithFour255Octets_ReturnsTrue() {
        String ip = "255.255.255.255";
        boolean result = Validator.isIpValid(ip);
        Assert.assertTrue(result);
    }

    @Test
    public void isIpValid_ValidIpWithFourZeroes_ReturnsTrue() {
        String ip = "0.0.0.0";
        boolean result = Validator.isIpValid(ip);
        Assert.assertTrue(result);
    }

    @Test
    public void isIpValid_InvalidIpWith256Octet_ReturnsFalse() {
        String ip = "256.23.56.89";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_InvalidIpWithThreeOctets_ReturnsFalse() {
        String ip = "255.23.56.";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_InvalidIpWithMinusOctet_ReturnsFalse() {
        String ip = "-25.23.56.56";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_NotIp_ReturnsFalse() {
        String ip = "d125tw";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_EmptyString_ReturnsFalse() {
        String ip = "";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_Null_ReturnsFalse() {
        String ip = null;
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isIpValid_ValidIpWithFiveOctets_ReturnsFalse() {
        String ip = "79.75.12.25.1";
        boolean result = Validator.isIpValid(ip);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_ValidMacWithSymbolsFrom0ToB_ReturnsTrue() {
        String mac = "01:23:45:67:89:AB";
        boolean result = Validator.isMacValid(mac);
        Assert.assertTrue(result);
    }

    @Test
    public void isMacValid_ValidMacWithSymbolsFromBToFWithCapitalAndLowers_ReturnsTrue() {
        String mac = "BC:DE:Fa:bc:de:ff";
        boolean result = Validator.isMacValid(mac);
        Assert.assertTrue(result);
    }

    @Test
    public void isMacValid_ValidMacWithDashSeparator_ReturnsTrue() {
        String mac = "00-11-22-33-44-55";
        boolean result = Validator.isMacValid(mac);
        Assert.assertTrue(result);
    }

    @Test
    public void isMacValid_ValidMacWithDashAndColonSeparators_ReturnsTrue() {
        String mac = "00:11-22:33:44-55";
        boolean result = Validator.isMacValid(mac);
        Assert.assertTrue(result);
    }

    @Test
    public void isMacValid_ValidMacWithFiveOctets_ReturnsFalse() {
        String mac = "00:11:22:33:44";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_ValidMacWithSixOctets_ReturnsFalse() {
        String mac = "00:11:22:33:44:55:66";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_ValidMacWithShortOctet_ReturnsFalse() {
        String mac = "0:11:21:33:44:55";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_ValidMacWithLongOctet_ReturnsFalse() {
        String mac = "000:11:21:33:44:55";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_ValidMacWithInvalidLetter_ReturnsFalse() {
        String mac = "G0:11:21:33:44:55";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_NotMac_ReturnsFalse() {
        String mac = "hello";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_EmptyString_ReturnsFalse() {
        String mac = "";
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }

    @Test
    public void isMacValid_Null_ReturnsFalse() {
        String mac = null;
        boolean result = Validator.isMacValid(mac);
        Assert.assertFalse(result);
    }
}