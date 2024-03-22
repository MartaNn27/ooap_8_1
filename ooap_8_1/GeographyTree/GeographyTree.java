import java.util.*;

// Клас, що представляє географічну назву
class Location {
    String name;
    List<Location> children;

    public Location(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    // Додавання нащадків
    public void addChild(Location child) {
        children.add(child);
    }

    // Метод для визначення, чи має вузол нащадків
    public boolean hasChildren() {
        return !children.isEmpty();
    }
}

// Клас-ітератор для обходу в ширину
class BreadthFirstIterator implements Iterator<Location> {
    private Queue<Location> queue;

    public BreadthFirstIterator(Location root) {
        queue = new LinkedList<>();
        queue.add(root);
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Location next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }
        Location current = queue.remove();
        // Додавання всіх нащадків поточного вузла до черги
        for (Location child : current.children) {
            queue.add(child);
        }
        return current;
    }
}

public class GeographyTree {
    public static void main(String[] args) {
        // Створення дерева з географічними назвами
        Location country = new Location("Україна");
        Location region1 = new Location("Київська область");
        Location region2 = new Location("Львівська область");
        Location district1 = new Location("Оболонський район");
        Location district2 = new Location("Печерський район");
        Location district3 = new Location("Залізничний район");
        Location district4 = new Location("Шевченківський район");
        Location city1 = new Location("Київ");
        Location city2 = new Location("Львів");
        Location street1 = new Location("Вулиця Хрещатик");
        Location street2 = new Location("Майдан");
        Location street3 = new Location("Вулиця Личаківська");
        Location street4 = new Location("Вулиця Городоцька");
        // Додавання елементів до дерева
        country.addChild(region1);
        country.addChild(region2);
        region1.addChild(district1);
        region1.addChild(district2);
        region1.addChild(district3);
        region1.addChild(district4);
        district1.addChild(city1);
        district2.addChild(city1);
        district3.addChild(city1);
        district4.addChild(city1);
        city1.addChild(street1);
        city1.addChild(street2);
        city2.addChild(street3);
        city2.addChild(street4);

        // Використання ітератора для обходу дерева в ширину
        BreadthFirstIterator iterator = new BreadthFirstIterator(country);
        while (iterator.hasNext()) {
            Location location = iterator.next();
            System.out.println(location.name);
        }
    }
}

