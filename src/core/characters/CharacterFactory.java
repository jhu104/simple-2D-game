package core.characters;

import core.World;

public class CharacterFactory {

	private World world;
	
	public CharacterFactory(World world) {
		this.world = world;
	}
	
	public Character createMonster(int health, int dmg) {
		return new Monster(world.nextX(),world.nextY(),world.getWorld(),health,dmg,world.getCharacters(),world.getPlayers());
	}
	
	public Player createPlayer(String name,int health,int dmg) {
		return new Player(name,world.nextX(),world.nextY(),world.getWorld(),health,dmg,world.getCharacters(),world.getPlayers());
	}
	
	public Character createHealer(int healAmount) {
		return new Healer(world.nextX(),world.nextY(),world.getWorld(),healAmount,world.getCharacters(),world.getPlayers());
	}
	
	public Character createAnimal() {
		return new Animal(world.nextX(),world.nextY(),world.getWorld(),world.getCharacters(),world.getPlayers());
	}
}
