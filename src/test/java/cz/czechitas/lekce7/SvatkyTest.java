package cz.czechitas.lekce7;

import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;
import java.time.Month;
import java.time.MonthDay;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;

/**
 * @author Filip Jirsák
 */
class SvatkyTest {

    /**
     * Testuje metodu {@link Svatky#vratKdyMaSvatek(String)}
     */
    @Test
    void vratKdyMaSvatek() {
        Svatky svatky = new Svatky();
        assertEquals(MonthDay.of(5, 18), svatky.vratKdyMaSvatek("Nataša"));
        assertNull(svatky.vratKdyMaSvatek("Eva"));
    }

    @Test
    void vratKdyMaSvatekMonika() {
        Svatky svatky = new Svatky();
        MonthDay kdyMaSvatekMonika = svatky.vratKdyMaSvatek("Monika");
        //System.out.println(kdyMaSvatekMonika);
        assertEquals(MonthDay.of(5, 21), kdyMaSvatekMonika);
    }

    /**
     * Testuje metodu {@link Svatky#jeVSeznamu(String)}
     */
    @Test
    void jeVSeznamu() {
        //test ma mit tri casti: 1)Arrange 2)Act 3)Assert- porovnam podminky
        //Arrange
        Svatky svatky = new Svatky();
        String jmenoJeVSeznamu = "Alexej";
        String jmenoNeniVSeznamu = "Adam";

        //Act
        boolean resaultJeVSeznamu = svatky.jeVSeznamu(jmenoJeVSeznamu);
        boolean resaultNeniVSeznamu = svatky.jeVSeznamu(jmenoNeniVSeznamu);

        //Assert
        assertTrue(resaultJeVSeznamu, "Ocekavali jsme ze" + jmenoJeVSeznamu + "je v seznamu jmen");
        assertFalse(resaultNeniVSeznamu, "Ocekavali jsme ze" + jmenoNeniVSeznamu + "je v seznamu jmen");
        //TODO Otestovat, že najde v seznamu existující jméno a nenajde neexistující jméno
    }

    /**
     * Testuje metodu {@link Svatky#getPocetJmen()}
     *
     * @return
     */
    @Test
    void getPocetJmen() {
        Svatky svatky = new Svatky();
        int pocetJmen = svatky.getPocetJmen();

        svatky.pridejSvatek("Jan", 1, 1);
        svatky.pridejSvatek("Marie", 8, 9);
        svatky.pridejSvatek("Petr", 29, 6);

        assertEquals(pocetJmen + 3, svatky.getPocetJmen(), "Počet jmen by měl být o 3 větší");

        //TODO Otestovat, že vrací počet jmen, která máme v seznamu
    }

    /**
     * Testuje metodu {@link Svatky#getSeznamJmen()}
     */
    @Test
    void getSeznamJmen() {
        Svatky svatky = new Svatky();

        svatky.pridejSvatek("Jan", 1, 1);
        svatky.pridejSvatek("Marie", 8, 9);
        svatky.pridejSvatek("Petr", 29, 6);

        Set<String> seznamJmen = svatky.getSeznamJmen();

        assertEquals(svatky.getPocetJmen(), seznamJmen.size(), "Seznam jmen by měl mít stejný počet položek jako celkový počet jmen");
        //TODO Zkontrolovat, že seznam jmen má správný počet položek.
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, int)}
     */
    @Test
    void pridejSvatekDenMesicInt() {
        Svatky svatky = new Svatky();

        svatky.pridejSvatek("NovýSvátek", 1, 1);

        assertTrue(svatky.jeVSeznamu("NovýSvátek"));

        assertEquals(MonthDay.of(1, 1), svatky.vratKdyMaSvatek("NovýSvátek"));
    }

    //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den


    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, int, Month)}
     */
    @Test
    void pridrejSvatekDenMesicMonth() {
        Svatky svatky = new Svatky();

        svatky.pridejSvatek("NovýSvátek", 1, Month.JANUARY);

        assertTrue(svatky.jeVSeznamu("NovýSvátek"));

        assertEquals(MonthDay.of(1, 1), svatky.vratKdyMaSvatek("NovýSvátek"));

        //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
    }

    /**
     * Testuje metodu {@link Svatky#pridejSvatek(String, MonthDay)}
     */
    @Test
    void pridejSvatekMonthDay() {
        Svatky svatky = new Svatky();

        svatky.pridejSvatek("NovýSvátek", MonthDay.of(1,1));

        assertTrue(svatky.jeVSeznamu("NovýSvátek"));

        assertEquals(MonthDay.of(1, 1), svatky.vratKdyMaSvatek("NovýSvátek"));
        //TODO Otestuje, že je jméno v seznamu svátků a že má přiřazen správný den
    }

    /**
     * Testuje metodu {@link Svatky#smazSvatek(String)}
     */
    @Test
    void smazatSvatek() {
        Svatky svatky = new Svatky();

        svatky.pridejSvatek("Jan", 1, 1);
        svatky.pridejSvatek("Marie", 8, 9);
        svatky.pridejSvatek("Petr", 29, 6);

        int puvodniPocetJmen = svatky.getPocetJmen();

        svatky.smazSvatek("Marie");

        int novyPocetJmen = svatky.getPocetJmen();

        assertEquals(puvodniPocetJmen - 1, novyPocetJmen, "Pocet puvodnich jmen bude po smazani jednoho jmena stejny jako nový počet jmen.");
        //TODO Zkontrolovat, že po smazání bude počet svátků odpovídat novému počtu.
    }
}
