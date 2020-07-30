package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.Collider;
import bytesmyth.games.edpg.actor.GameActor;

public abstract class Trigger extends GameObject {
	
	private boolean activated;
	
	public Trigger(float x, float y, float width, float height, Stage s, Stage c) {
		super(OBJECT_TYP.Trigger, x, y, width, height, s, c);
		this.collider.setPhysical(false);
		
		this.activated = false;
	}
	public Trigger(float x, float y, Stage s, Stage c) {
		this(x, y, 50f, 50f, s, c);
	}
	public Trigger(Stage s, Stage c) { this(0f, 0f, s, c); }
	
	//Behavior to execute upon FaxModulo entering and exiting the trigger
	public abstract boolean activate();
	public abstract boolean deactivate();
	
	public boolean isActivated() {
		return this.activated;
	}
	
	@Override
	public void setCollider(Collider collider) {
		super.setCollider(collider);
		this.collider.setPhysical(false);
	}
	
	@Override
	public void act(float dt) {
		for (GameActor actor : GameActor.getList(this.collider.getStage(), Collider.class)) {
			Collider col = (Collider)actor;
			if (col.getAttached() != null && FaxModulo.class.isInstance(col.getAttached())) {
				FaxModulo fm = (FaxModulo)(col.getAttached());
				if (!this.activated && this.collider.overlaps(fm.getCollider())) {
					this.activated = true;
					fm.justActivated(this);
					this.activate();
					return;
				}
				else if (this.activated && !this.collider.overlaps(fm.getCollider())) {
					this.activated = false;
					fm.justDeactivated(this);
					this.deactivate();
					return;
				}
			}
		}
	}
	
}
