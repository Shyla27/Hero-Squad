import org.junit.*;
import static org.junit.Assert.*;

public class HeroTest {

  @Test
  public void Hero_instantiatesCorrectly_true() {
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals(true, testHero instanceof Hero);
  }

  @Test
  public void getName_instantiatesWithName_String() {
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals("Superman", testHero.getName());
  }

  @Test
  public void getAge_instantiatesWithAge_int() {
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals(20, testHero.getAge());
  }

  @Test
  public void getPower_instantiatesWithPower_String() {
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals("Fly", testHero.getPower());
  }

  @Test
  public void getWeakness_instantiatesWithWeakness_String() {
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals("Kryptonite", testHero.getWeakness());
  }

  @Test
  public void all_returnsAllInstancesOfHero_true() {
    Hero firstHero = new Hero("Superman",20,"Fly","Kryptonite");
    Hero secondHero = new Hero("Ironman",50,"Fly","Drinking");
    assertEquals(true, Hero.all().contains(firstHero));
    assertEquals(true, Hero.all().contains(secondHero));
  }

  @Test
  public void clear_emptiesAllHeroesFromArrayList_0() {
    Hero.clear();
    assertEquals(Hero.all().size(), 0);
  }

  @Test
  public void getId_heroesInstantiatesWithAnID_1() {
    Hero.clear();
    Hero testHero = new Hero("Superman",20,"Fly","Kryptonite");
    assertEquals(1, testHero.getId());
  }

  @Test
  public void find_returnsHeroWithSameId_secondHero() {
    Hero secondHero = new Hero("Ironman", 50, "Fly", "Drinking");
    assertEquals(Hero.find(secondHero.getId()), secondHero);
  }

}
