
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;

public class ObjectManager {
	ArrayList<Object> objects;
	
	private int score = 0;
	
	long enemyTimer = 0;
	int enemySpawnTime = 1000;
	
	public ObjectManager() {
		objects = new ArrayList<Object>();
	}

	public void addObject(Object o) {
		objects.add(o);
	}

	public void update() {
		for (int i = 0; i < objects.size(); i++) {
			Object o = objects.get(i);
			o.update();
		}
		
//		purgeObjects();	
	}

	public void draw(Graphics g) {
		for (int i = 0; i < objects.size(); i++) {
			Object o = objects.get(i);
			o.draw(g);
		}
	}

//	private void purgeObjects() {
//		for (int i = 0; i < objects.size(); i++) {
//			if (!objects.get(i).isAlive) {
//				objects.remove(i);
//			}
//		}
//	}

//	public void manageEnemies(){
//		if(System.currentTimeMillis() - enemyTimer >= enemySpawnTime){
//			addObject(new Alien(new Random().nextInt(LeagueInvaders.WIDTH), 0, 50, 50));
//			enemyTimer = System.currentTimeMillis();
//		}
//	}
//
//	public void checkCollision() {
//		for (int i = 0; i < objects.size(); i++) {
//			for (int j = i + 1; j < objects.size(); j++) {
//				Object o1 = objects.get(i);
//				Object o2 = objects.get(j);
//				
//				if(o1.collisionBox.intersects(o2.collisionBox)){
//					if((o1 instanceof Alien && o2 instanceof Projectile) ||
//					   (o2 instanceof Alien && o1 instanceof Projectile)){
//						score++;
//						System.out.println(score);
//						o1.isAlive = false;
//						o2.isAlive = false;
//					}
//					else if((o1 instanceof Alien && o2 instanceof Rocketship) ||
//							(o2 instanceof Alien && o1 instanceof Rocketship)){
//						o1.isAlive = false;
//						o2.isAlive = false;
//					}
//	
//				}
//			}
//		}
//	}
	
	public int getScore(){
		return score;
	}
	
	public void setScore(int s){
		score = s;
	}
	
	public void reset(){
		objects.clear();
	}
}
