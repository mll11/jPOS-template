package com.simplytapp.iso8583.utility;

import java.io.IOException;

import org.bouncycastle.util.encoders.Hex;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.iso.packager.ISO93APackager;
import org.jpos.iso.packager.ISO93BPackager;

public class BuildISOMessage {

    public static void main(String[] args) throws IOException, ISOException {
        // Create Packager based on XML that contain DE type
        GenericPackager packager = new GenericPackager("basic.xml");
        ISO87APackager iso87APackager = new ISO87APackager();
        ISO87BPackager iso87BPackager = new ISO87BPackager();
        ISO93APackager iso93APackager = new ISO93APackager();

        // Create ISO Message
        ISOMsg isoMsg = new ISOMsg();

        // Test Data v1987
        /*
        isoMsg.setPackager(iso87APackager);
        isoMsg.setMTI("0200");
        isoMsg.set(3, "201234");
        isoMsg.set(4, "10000");
        isoMsg.set(7, "110722180");
        isoMsg.set(11, "123456");
        isoMsg.set(44, "A5DFGR");
        isoMsg.set(105, "ABCDEFGHIJ 1234567890");
        */

        // MC Terminal Simulator v1993
        isoMsg.setPackager(iso93APackager);
        isoMsg.setMTI("1100");
        isoMsg.set(2, "5164123785712487");
        isoMsg.set(3, "000000");
        isoMsg.set(4, "000000000100");
        isoMsg.set(14, "1702");
        isoMsg.set(22, "A00101A03346");
        isoMsg.set(24, "100");
        isoMsg.set(35, "5164123785712487D17021011408011015360");
        isoMsg.set(37, "000000000411");
        isoMsg.set(40, "101");
        isoMsg.set(41, "02001101");
        isoMsg.set(45, "B5164123785712487^SUPPLIED/NOT^17021011408011015360");
        isoMsg.set(49, "978");

        // print the DE list
        logISOMsg(isoMsg);

        // Get and print the output result
        byte[] data = isoMsg.pack();
        System.out.println("RESULT : " + new String(data));
    }

    private static void logISOMsg(ISOMsg msg) {
        System.out.println("----ISO MESSAGE-----");
        try {
            System.out.println("  MTI : " + msg.getMTI());
            for (int i = 1; i <= msg.getMaxField(); i++) {
                if (msg.hasField(i)) {
                    System.out.println("    Field-" + i + " : " + msg.getString(i));
                }
            }
        }
        catch (ISOException e) {
            e.printStackTrace();
        }
        finally {
            System.out.println("--------------------");
        }

    }

}
