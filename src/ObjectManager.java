
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<GameObject> objects;

	int score = 0;

	long enemyTimer = 0;
	int enemySpawnTime = 80;

	Random rand = new Random();
	Random rand2 = new Random();

	int ran = 125;
	int ran2 = 0;

	public ObjectManager() {
		objects = new ArrayList<GameObject>();
	}

	public void addObject(GameObject o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.update();
		}

		purgeObjects();
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			GameObject o = objects.get(i);
			o.draw(g);
		}
	}

	private void purgeObjects() {
		for (int i = 0; i < objects.size(); i++) {
			if (!objects.get(i).isAlive) {
				objects.remove(i);
			}
		}
	}

	public void manageEnemies() {
		if (System.currentTimeMillis() - enemyTimer >= enemySpawnTime) {
			ran2 = rand2.nextInt(10);
			if (ran2 % 2 == 0 && ran <= 220) {
				ran += 20;
			} else if (ran >= 90) {
				ran -= 20;
			}
			addObject(new Wall(ran, -50, 50, 50));
			addObject(new Wall(ran + 200, -50, 50, 50));
			enemyTimer = System.currentTimeMillis();
			score += 1;
		}
	}

	public void checkCollision() {
		for (int i = 0; i < objects.size(); i++) {
			for (int j = i + 1; j < objects.size(); j++) {
				GameObject o1 = objects.get(i);
				GameObject o2 = objects.get(j);

				if (o1.collisionBox.intersects(o2.collisionBox)) {
					if ((o1 instanceof Wall && o2 instanceof Racecar)
							|| (o2 instanceof Wall && o1 instanceof Racecar)) {
						o1.isAlive = false;
						o2.isAlive = false;
					}

				}
			}
		}
	}

	public int getScore() {
		return score;
	}

	public void setScore(int s) {
		score = s;
	}

	public void reset() {
		objects.clear();
	}
}
