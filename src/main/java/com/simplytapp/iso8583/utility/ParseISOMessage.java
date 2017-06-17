package com.simplytapp.iso8583.utility;

import java.io.IOException;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.jpos.iso.packager.ISO87APackager;
import org.jpos.iso.packager.ISO87BPackager;
import org.jpos.iso.packager.ISO93APackager;

public class ParseISOMessage {
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
        final String data = "0200B2200000001000000000000000800000201234000000010000011072218012345606A5DFGR021ABCDEFGHIJ 1234567890";
        */

        // From MC Terminal Simulator
        // MC Terminal Simulator v1993
        isoMsg.setPackager(iso93APackager);
        final String data = "110070040500298880001651641237857124870000000000000001001702A00101A03346100375164123785712487D170210114080110153600000000004111010200110151B5164123785712487^SUPPLIED/NOT^17021011408011015360978";

        // Print Input Data
        System.out.println("DATA : " + data);

        isoMsg.unpack(data.getBytes());

        // print the DE list
        logISOMsg(isoMsg);
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
