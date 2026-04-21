package hei.school.tdfinalprog3.model;

import java.time.LocalDate;
import java.util.List;

public class Collectivity {
    private Long id;
    private String name;
    private String city;
    private String specialty;
    private LocalDate creationDate;
    private List<Member> members;

    public Collectivity(Long id, String name, String city, String specialty, LocalDate creationDate, List<Member> members) {
        if (members.size() < 10) {
            throw new IllegalArgumentException("Une collectivité doit avoir au moins 10 membres");
        }

        this.id = id;
        this.name = name;
        this.city = city;
        this.specialty = specialty;
        this.creationDate = creationDate;
        this.members = members;
    }

    // GETTERS
    public Long getId() { return id; }
    public String getName() { return name; }
    public String getCity() { return city; }
    public String getSpecialty() { return specialty; }
    public LocalDate getCreationDate() { return creationDate; }
    public List<Member> getMembers() { return members; }
}
