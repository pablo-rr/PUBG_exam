package es.salesianos.model;

import org.springframework.stereotype.Component;

@Component("pokemon")
//public class Pokemon extends AbstractPokeball{
public class Pokemon{
	private String name;
	private int health;
	private int power;
	private boolean dead;
	private boolean wild;
	
	public void heal(int ammount) {
		health += ammount;
	}
	
	public void damage(int ammount) {
		health -= ammount;
		if(health <= 0) {
			setDead(true);
		}
	}
	
	public void revive() {
		if(isDead()) {
			health = 1;
			setDead(false);
		}
	}
	
	public void attack(Pokemon target) {
		target.damage(power);
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public int getHealth() {
		return health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public int getPower() {
		return power;
	}
	
	public void setPower(int power) {
		this.power = power;
	}

	public boolean isDead() {
		return dead;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isWild() {
		return wild;
	}

	public void setWild(boolean status) {
		this.wild = status;
	}
}
