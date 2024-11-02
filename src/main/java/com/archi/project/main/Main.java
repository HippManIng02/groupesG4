package com.archi.project.main;

import com.archi.project.ihm.MainApplication;
import com.archi.project.metier.LogiqueMetier;

public class Main 
{
    public static void main( String[] args )
    {
    	LogiqueMetier logiqueMetier = new LogiqueMetier();
    	new MainApplication(logiqueMetier);
    }
}
