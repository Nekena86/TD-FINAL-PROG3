package hei.school.tdfinalprog3.model;

import java.time.LocalDate;
public class Member {

    private Long id;
    private String firstName;
    private String lastName;
    private LocalDate birthDate;
    private String gender;
    private String address;
    private String job;
    private String phone;
    private String email;
    private LocalDate membershipDate;
    private Role role;

    private Member sponsor; // parrain

    public Member(Long id, String firstName, String lastName, LocalDate birthDate,
                  String gender, String address, String job, String phone,
                  String email, LocalDate membershipDate, Role role, Member sponsor) {

        if (sponsor == null) {
            throw new IllegalArgumentException("Un parrain est obligatoire");
        }

        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
        this.gender = gender;
        this.address = address;
        this.job = job;
        this.phone = phone;
        this.email = email;
        this.membershipDate = membershipDate;
        this.role = role;
        this.sponsor = sponsor;
    }

    // GETTERS
    public Long getId() { return id; }
    public String getFirstName() { return firstName; }
    public String getLastName() { return lastName; }
    public Role getRole() { return role; }
}