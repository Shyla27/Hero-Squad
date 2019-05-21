import java.util.ArrayList;
import java.util.List;

public class Hero {
  private String mName;
  private int mAge;
  private String mPower;
  private String mWeakness;
  private static List<Hero> instances = new ArrayList<Hero>();
  private int mId;

  public Hero(String name, int age, String power, String weakness) {
    mName = name;
    mAge = age;
    mPower = power;
    mWeakness = weakness;
    instances.add(this);
    mId = instances.size();

  }

  public String getName() {
    return mName;
  }

  public int getAge() {
    return mAge;
  }

  public String getPower() {
    return mPower;
  }

  public String getWeakness() {
    return mWeakness;
  }

  public static List<Hero> all() {
    return instances;
  }

  public static void clear() {
    instances.clear();
  }

  public int getId() {
    return mId;
  }

  public static Hero find(int id) {
    return instances.get(id-1);
  }

}
