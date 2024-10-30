package com.archi.project.main;

import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.metier.services.GroupeService;

/**
 * Hello world!
 *
 */
public class Main 
{
    private static final GroupeInterface groupeInterface = new GroupeService();
    public static void main( String[] args )
    {
        System.out.println(System.getProperty("java.class.path"));
        
        System.out.println(groupeInterface.listGroupes());

    }
}
