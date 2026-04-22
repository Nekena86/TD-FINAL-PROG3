package org.td2.prog_3.Services;

import org.springframework.stereotype.Service;
import org.td2.prog_3.Exception.BadRequestException;
import org.td2.prog_3.Exception.NotFoundException;
import org.td2.prog_3.Model.Collectivity;
import org.td2.prog_3.Model.Member;
import org.td2.prog_3.Repository.CollectivityRepository;
import org.td2.prog_3.Repository.MemberRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CollectivityServices {

    private CollectivityRepository collectivityRepository;
    private MemberRepository memberRepository;

    public CollectivityServices(CollectivityRepository collectivityRepository,
                               MemberRepository memberRepository) {
        this.collectivityRepository = collectivityRepository;
        this.memberRepository = memberRepository;
    }

    @SuppressWarnings("unchecked")
    public Map<String, Object> createCollectivity(Map<String, Object> request) {

        Boolean autorisationFormelle = (Boolean) request.get("autorisationFormelle");
        if (autorisationFormelle == null || !autorisationFormelle) {
            throw new BadRequestException("Autorisation de la fédération requise");
        }

        List<Map<String, Object>> membresInit = (List<Map<String, Object>>) request.get("membres");

        if (membresInit == null || membresInit.size() < 10) {
            throw new BadRequestException("Au moins 10 membres requis");
        }

        long membresAnciens = 0;
        for (Map<String, Object> member : membresInit) {
            Integer anciennete = (Integer) member.get("ancienneteFederation");
            if (anciennete != null && anciennete >= 180) {
                membresAnciens++;
            }
        }

        if (membresAnciens < 5) {
            throw new BadRequestException("Au moins 5 membres doivent avoir une antériorité de 6 mois");
        }

        boolean hasPresident = false;
        boolean hasPresidentAdjoint = false;
        boolean hasTresorier = false;
        boolean hasSecretaire = false;

        for (Map<String, Object> member : membresInit) {
            String poste = (String) member.get("poste");
            if ("PRESIDENT".equals(poste)) hasPresident = true;
            if ("PRESIDENT_ADJOINT".equals(poste)) hasPresidentAdjoint = true;
            if ("TRESORIER".equals(poste)) hasTresorier = true;
            if ("SECRETAIRE".equals(poste)) hasSecretaire = true;
        }

        if (!(hasPresident && hasPresidentAdjoint && hasTresorier && hasSecretaire)) {
            throw new BadRequestException("Tous les postes spécifiques doivent être occupés");
        }

        String ville = (String) request.get("ville");
        String specialite = (String) request.get("specialite");

        if (ville == null || ville.trim().isEmpty()) {
            throw new BadRequestException("La ville est requise");
        }

        if (specialite == null || specialite.trim().isEmpty()) {
            throw new BadRequestException("La spécialité est requise");
        }

        Collectivity collectivity = new Collectivity();
        collectivity.setVille(ville);
        collectivity.setSpecialite(specialite);
        collectivity.setAutorisationFormelle(autorisationFormelle);

        String dateCreationStr = (String) request.get("dateCreation");
        if (dateCreationStr != null && !dateCreationStr.isEmpty()) {
            collectivity.setDateCreation(LocalDate.parse(dateCreationStr));
        } else {
            collectivity.setDateCreation(LocalDate.now());
        }
        collectivity.setStatut("ACTIVE");

        Collectivity savedCollectivity = collectivityRepository.save(collectivity);

        for (Map<String, Object> memberReq : membresInit) {
            Member member = new Member();
            member.setNom((String) memberReq.get("nom"));
            member.setPrenom((String) memberReq.get("prenom"));

            String dateNaissanceStr = (String) memberReq.get("dateNaissance");
            if (dateNaissanceStr != null) {
                member.setDateNaissance(LocalDate.parse(dateNaissanceStr));
            }

            member.setTelephone((String) memberReq.get("telephone"));
            member.setEmail((String) memberReq.get("email"));
            member.setAdresse((String) memberReq.get("adresse"));
            member.setMetier((String) memberReq.get("metier"));
            member.setDateAdhesion(LocalDate.now());
            member.setCollectivityId(savedCollectivity.getId());
            member.setPoste((String) memberReq.get("poste"));
            member.setAncienneteFederationJours((Integer) memberReq.get("ancienneteFederation"));

            memberRepository.save(member);
        }

        return savedCollectivity.toMap();
    }

    public Map<String, Object> assignIdentifiers(Long collectivityId, Map<String, Object> request) {

        Collectivity collectivity = collectivityRepository.findById(collectivityId);

        if (collectivity == null) {
            throw new NotFoundException("Collectivité non trouvée avec l'id: " + collectivityId);
        }

        if (collectivity.hasUniqueIdentifiers()) {
            throw new BadRequestException(
                    "La collectivité a déjà un numéro (" + collectivity.getNumero() +
                            ") et un nom (" + collectivity.getNom() + ") attribués. Ils ne peuvent plus être modifiés.");
        }

        String numero = (String) request.get("numero");
        String nom = (String) request.get("nom");

        if (numero == null || numero.trim().isEmpty()) {
            throw new BadRequestException("Le numéro est requis");
        }

        if (nom == null || nom.trim().isEmpty()) {
            throw new BadRequestException("Le nom est requis");
        }

        if (!numero.matches("^[A-Z0-9]{3,10}$")) {
            throw new BadRequestException("Le numéro doit contenir 3 à 10 caractères alphanumériques majuscules");
        }

        if (nom.length() < 3 || nom.length() > 100) {
            throw new BadRequestException("Le nom doit contenir entre 3 et 100 caractères");
        }

        if (collectivityRepository.existsByNumero(numero)) {
            throw new BadRequestException("Le numéro '" + numero + "' est déjà utilisé par une autre collectivité");
        }

        if (collectivityRepository.existsByNom(nom)) {
            throw new BadRequestException("Le nom '" + nom + "' est déjà utilisé par une autre collectivité");
        }

        collectivity.setNumero(numero);
        collectivity.setNom(nom);

        Collectivity updatedCollectivity = collectivityRepository.update(collectivity);

        return updatedCollectivity.toMap();
    }

    public Map<String, Object> getCollectivity(Long id) {
        Collectivity collectivity = collectivityRepository.findById(id);
        if (collectivity == null) {
            throw new NotFoundException("Collectivité non trouvée avec l'id: " + id);
        }
        return collectivity.toMap();
    }

    public List<Map<String, Object>> getAllCollectivities() {
        List<Collectivity> collectivities = collectivityRepository.findAll();
        List<Map<String, Object>> result = new ArrayList<>();
        for (Collectivity c : collectivities) {
            result.add(c.toMap());
        }
        return result;
    }
}

