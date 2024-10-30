package com.archi.project.main;

import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.services.UniteEnseignementService;

/**
 * Hello world!
 *
 */
public class Main 
{
    private static final UniteEnseignementInterface uniteEnseignementInterface = new UniteEnseignementService();
    public static void main( String[] args )
    {
        System.out.println(System.getProperty("java.class.path"));

        System.out.println(uniteEnseignementInterface.listUEs());

    }
}
