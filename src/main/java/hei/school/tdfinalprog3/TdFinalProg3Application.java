package hei.school.tdfinalprog3;

import hei.school.tdfinalprog3.model.Member;
import hei.school.tdfinalprog3.model.Role;
import hei.school.tdfinalprog3.service.FederationService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.time.LocalDate;
import java.util.List;

@SpringBootApplication
public class TdFinalProg3Application {

    public static void main(String[] args) {
        SpringApplication.run(TdFinalProg3Application.class, args);

                Member sponsor = new Member(
                        1L, "Jean", "Rakoto", LocalDate.of(1990,1,1),
                        "MALE", "Tana", "Farmer", "0340000000",
                        "jean@mail.com", LocalDate.now(),
                        Role.CONFIRMED, null // ⚠️ lui n'a pas de parrain (cas initial)
                );

                Member m2 = new Member(
                        2L, "Paul", "Rabe", LocalDate.of(2000,5,5),
                        "MALE", "Toliara", "Farmer", "0320000000",
                        "paul@mail.com", LocalDate.now(),
                        Role.JUNIOR, sponsor
                );

                FederationService service = new FederationService();

                service.createCollectivity(
                        "AgriSud",
                        "Toliara",
                        "Culture",
                        List.of(m2, m2, m2, m2, m2, m2, m2, m2, m2, m2) // 10 membres
                );
            }
        }



