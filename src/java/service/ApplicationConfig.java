/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import java.util.Set;
import javax.ws.rs.core.Application;

/**
 *
 * @author Amine
 */
@javax.ws.rs.ApplicationPath("webresources")
public class ApplicationConfig extends Application {

    @Override
    public Set<Class<?>> getClasses() {
        Set<Class<?>> resources = new java.util.HashSet<>();
        addRestResourceClasses(resources);
        return resources;
    }

    /**
     * Do not modify addRestResourceClasses() method.
     * It is automatically populated with
     * all resources defined in the project.
     * If required, comment out calling this method in getClasses().
     */
    private void addRestResourceClasses(Set<Class<?>> resources) {
        resources.add(service.MhAgcPublicationFacadeREST.class);
        resources.add(service.MhAgcPublicationInscritFacadeREST.class);
        resources.add(service.MhAgcRsvAnnulerFacadeREST.class);
        resources.add(service.MhAgenceFacadeREST.class);
        resources.add(service.MhCaisseFacadeREST.class);
        resources.add(service.MhChambreFacadeREST.class);
        resources.add(service.MhChargeSuppFacadeREST.class);
        resources.add(service.MhClientSdemFacadeREST.class);
        resources.add(service.MhCltFctFacadeREST.class);
        resources.add(service.MhCltSChFacadeREST.class);
        resources.add(service.MhCompteUserAFacadeREST.class);
        resources.add(service.MhCompteUserHFacadeREST.class);
        resources.add(service.MhConventionAgcChambreFacadeREST.class);
        resources.add(service.MhConventionAgcFacadeREST.class);
        resources.add(service.MhConventionFacadeREST.class);
        resources.add(service.MhDemandeCltFacadeREST.class);
        resources.add(service.MhDemandeConvAgcFacadeREST.class);
        resources.add(service.MhDemandeRFacadeREST.class);
        resources.add(service.MhHotelFacadeREST.class);
        resources.add(service.MhPartPubInscritFacadeREST.class);
        resources.add(service.MhPubFacadeREST.class);
        resources.add(service.MhPubPartViewFacadeREST.class);
        resources.add(service.MhPubParticulierFacadeREST.class);
        resources.add(service.MhReservationFacadeREST.class);
        resources.add(service.MhRsvvueglobalFacadeREST.class);
        resources.add(service.MhTabRsvWOFctFacadeREST.class);
        resources.add(service.MhUsersDroitsAgcFacadeREST.class);
        resources.add(service.MhUsersDroitsFacadeREST.class);
    }
    
}
