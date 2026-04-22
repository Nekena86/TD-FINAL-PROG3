package org.td2.prog_3.Services;

import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.BadRequestException;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Collectivity;
import org.td2.prog_3.Model.Member;
import org.td2.prog_3.Repository.CollectivityRepository;
import org.td2.prog_3.Repository.MemberRepository;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class MemberServices{

    private MemberRepository memberRepository;
    private CollectivityRepository collectivityRepository;

    public MemberServices(MemberRepository memberRepository, CollectivityRepository collectivityRepository) {
        this.memberRepository = memberRepository;
        this.collectivityRepository = collectivityRepository;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createMember(Map<String, Object> request) {

        Long collectivityId = ((Number) request.get("collectivityId")).longValue();
        Collectivity collectivity = collectivityRepository.findById(collectivityId);

        if (collectivity == null) {
            throw new NotFoundException("Collectivité non trouvée avec l'id: " + collectivityId);
        }

        String telephone = (String) request.get("telephone");
        if (memberRepository.existsByTelephone(telephone)) {
            throw new BadRequestException("Un membre avec ce numéro de téléphone existe déjà");
        }

        String email = (String) request.get("email");
        if (memberRepository.existsByEmail(email)) {
            throw new BadRequestException("Un membre avec cet email existe déjà");
        }

        List<Long> parrainIds = (List<Long>) request.get("parrains");
        List<Member> parrains = memberRepository.findAllById(parrainIds);

        if (parrains == null || parrains.size() < 2) {
            throw new BadRequestException("Au moins 2 parrains sont requis");
        }

        for (Member parrain : parrains) {
            if (!"MEMBRE_CONFIRME".equals(parrain.getPoste())) {
                throw new BadRequestException(
                        "Le parrain " + parrain.getNom() + " " + parrain.getPrenom() +
                                " n'est pas un membre confirmé");
            }

            long ancienneteJours = ChronoUnit.DAYS.between(parrain.getDateAdhesion(), LocalDate.now());
            if (ancienneteJours <= 90) {
                throw new BadRequestException(
                        "Le parrain " + parrain.getNom() + " " + parrain.getPrenom() +
                                " a une ancienneté insuffisante (" + ancienneteJours + " jours, besoin > 90)");
            }
        }

        long parrainsDeLaCollectivite = 0;
        for (Member parrain : parrains) {
            if (collectivityId.equals(parrain.getCollectivityId())) {
                parrainsDeLaCollectivite++;
            }
        }

        long autresParrains = parrains.size() - parrainsDeLaCollectivite;

        if (parrainsDeLaCollectivite < autresParrains) {
            throw new BadRequestException(
                    "Le nombre de parrains issus de la collectivité cible (" + parrainsDeLaCollectivite +
                            ") doit être au moins égal au nombre d'autres parrains (" + autresParrains + ")");
        }

        String referencePaiement = (String) request.get("referencePaiement");
        if (referencePaiement == null || referencePaiement.isEmpty()) {
            throw new BadRequestException("Paiement requis: frais d'adhésion (50000 Ar) + cotisations annuelles");
        }

        Member member = new Member();
        member.setNom((String) request.get("nom"));
        member.setPrenom((String) request.get("prenom"));

        String dateNaissanceStr = (String) request.get("dateNaissance");
        if (dateNaissanceStr != null) {
            member.setDateNaissance(LocalDate.parse(dateNaissanceStr));
        }

        member.setTelephone(telephone);
        member.setEmail(email);
        member.setAdresse((String) request.get("adresse"));
        member.setMetier((String) request.get("metier"));
        member.setDateAdhesion(LocalDate.now());
        member.setCollectivityId(collectivityId);
        member.setPoste("MEMBRE_JUNIOR");
        member.setStatut("ACTIF");

        Member savedMember = memberRepository.save(member);

        return savedMember.toMap();
    }

    public Map<String, Object> getMember(Long id) {
        Member member = memberRepository.findById(id);
        if (member == null) {
            throw new NotFoundException("Membre non trouvé avec l'id: " + id);
        }
        return member.toMap();
    }

    public List<Map<String, Object>> getMembersByCollectivity(Long collectivityId) {
        Collectivity collectivity = collectivityRepository.findById(collectivityId);
        if (collectivity == null) {
            throw new NotFoundException("Collectivité non trouvée avec l'id: " + collectivityId);
        }

        List<Member> members = memberRepository.findByCollectivityId(collectivityId);
        List<Map<String, Object>> result = new ArrayList<>();
        for (Member m : members) {
            result.add(m.toMap());
        }
        return result;
    }
}