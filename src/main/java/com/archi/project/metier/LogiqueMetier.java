package com.archi.project.metier;

import com.archi.project.interfaces.EleveInterface;
import com.archi.project.interfaces.GroupeInterface;
import com.archi.project.interfaces.SujetInterface;
import com.archi.project.interfaces.UniteEnseignementInterface;
import com.archi.project.metier.services.EleveService;
import com.archi.project.metier.services.GroupeService;
import com.archi.project.metier.services.SujetService;
import com.archi.project.metier.services.UniteEnseignementService;

public class LogiqueMetier {
	
	private final GroupeInterface groupeService;
	  private final UniteEnseignementInterface ueService;
	    private final SujetInterface sujetService;
	    private final EleveInterface eleveService;

	    public LogiqueMetier() {
	        this.groupeService = new GroupeService();
	        this.ueService = new UniteEnseignementService();
	        this.sujetService = new SujetService();
	        this.eleveService = new EleveService();
	    }

	    public GroupeInterface getGroupeService() {
	        return groupeService;
	    }

	    public UniteEnseignementInterface getUeService() {
	        return ueService;
	    }

	    public SujetInterface getSujetService() {
	        return sujetService;
	    }

	    public EleveInterface getEleveService() {
	        return eleveService;
	    }

}
