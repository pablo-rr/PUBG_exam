package es.salesianos.model;

import java.util.Random;

public class AbstractPokeball implements Pokeball{
	private static int catchChancePercentage;

	public int getCatchChancePercentage() {
		return catchChancePercentage;
	}

	public void setCatchChancePercentage(int catchChancePercentage) {
		AbstractPokeball.catchChancePercentage = catchChancePercentage;
	}

	@Override
	public void catchPokemon(Pokemon pokemon, AbstractTeam team) {
		Random random = new Random();
		int chance = random.nextInt(99)+1;
		if(chance <= catchChancePercentage) {	
			team.addMember(pokemon);
		}
	}

	
}
