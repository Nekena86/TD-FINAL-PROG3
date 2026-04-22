package org.td2.prog_3.Services;


import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Cotisation;
import org.td2.prog_3.Repository.CotisationRepository;
import org.td2.prog_3.Repository.MemberRepository;
import org.td2.prog_3.Repository.CollectivityRepository;

import java.time.LocalDate;

@Service
public class CotisationService {

    private CotisationRepository cotisationRepository;
    private MemberRepository memberRepository;
    private CollectivityRepository collectivityRepository;

    public CotisationService(CotisationRepository cotisationRepository,
                             MemberRepository memberRepository,
                             CollectivityRepository collectivityRepository) {
        this.cotisationRepository = cotisationRepository;
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
    }

    public Cotisation createCotisation(Double montant, Long membreId, Long collectiviteId, String type, String modePaiement) {
        if (!memberRepository.existsById(membreId)) {
            throw new NotFoundException("Membre non trouvé avec id: " + membreId);
        }
        if (!collectivityRepository.existsById(collectiviteId)) {
            throw new NotFoundException("Collectivité non trouvée avec id: " + collectiviteId);
        }

        Cotisation cotisation = new Cotisation();
        cotisation.setMontant(montant);
        cotisation.setDate(LocalDate.now());
        cotisation.setMembreId(membreId);
        cotisation.setCollectiviteId(collectiviteId);
        cotisation.setType(type);
        cotisation.setModePaiement(modePaiement);

        return cotisationRepository.save(cotisation);
    }
}
