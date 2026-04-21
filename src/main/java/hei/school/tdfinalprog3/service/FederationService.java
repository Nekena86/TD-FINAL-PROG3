package hei.school.tdfinalprog3.service;

import hei.school.tdfinalprog3.model.Collectivity;
import hei.school.tdfinalprog3.model.Member;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FederationService {

    private List<Collectivity> collectivities = new ArrayList<>();

    // A - créer une collectivité
    public Collectivity createCollectivity(String name, String city, String specialty, List<Member> members) {
        Collectivity c = new Collectivity(
                System.currentTimeMillis()
                name,
                city,
                specialty,
                LocalDate.now(),
                members
        );
        collectivities.add(c);
        return c;
    }

    // B - ajouter un membre
    public Member addMember(Member member) {
        if (member == null) {
            throw new IllegalArgumentException("Membre invalide");
        }
        return member;
    }

}
