package patterns.dto.model.entity;

import java.util.Objects;

public class Ship  {

	private String title;
	private Customer owner;

	public Ship(String title){
		this.title = title;
	}

	public String getTitle(){
		return this.title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String toString(){
		return String.format("{title: %s}", getTitle());
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Ship ship = (Ship) o;
		return Objects.equals(title, ship.title)
				&& Objects.equals(owner, ship.owner);
	}

	@Override
	public int hashCode() {
		return Objects.hash(title, owner);
	}
}
