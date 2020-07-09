package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.actor.BoxCollider;
import bytesmyth.games.edpg.actor.Collider;
import bytesmyth.games.edpg.actor.GameActor;

public class GameObject extends GameActor {
	
	/*** Enumerations ***/
	
	public static enum OBJECT_TYP {
		Basic,
		Trigger,
		Physics,
		Static
	}
	
	/*** Fields ***/
	
	private final OBJECT_TYP type;
	protected Collider collider;
	
	/*** Constructors ***/
	
	public GameObject(OBJECT_TYP type, float x, float y, float width, float height) {
		super(x, y);
		
		this.type = type;
		this.collider = new BoxCollider(x, y, width, height);
	}
	public GameObject(OBJECT_TYP type, float x, float y) {
		this(type, x, y, 50f, 50);
	}
	public GameObject(float x, float y, float width, float height) {
		this(OBJECT_TYP.Basic, x, y, width, height);
	}
	public GameObject(float x, float y) {
		this(OBJECT_TYP.Basic, x, y);
	}
	public GameObject() { this(0f, 0f); }
	
	public GameObject(OBJECT_TYP type, float x, float y, float width, float height, Stage s) {
		super(x, y, s);
		
		this.type = type;
		this.collider = new BoxCollider(x, y, width, height, s);
	}
	public GameObject(OBJECT_TYP type, float x, float y, Stage s) {
		this(type, x, y, 50f, 50f, s);
	}
	public GameObject(float x, float y, float width, float height, Stage s) {
		this(OBJECT_TYP.Basic, x, y, width, height, s);
	}
	public GameObject(float x, float y, Stage s) {
		this(OBJECT_TYP.Basic, x, y, s);
	}
	public GameObject(Stage s) { this(0f, 0f, s); }
	
	/*** Methods ***/
	
	//Getters
	
	public OBJECT_TYP getObjectType() {
		return this.type;
	}
	
	public Collider getCollider() {
		return this.collider;
	}
	
	//Setters
	
	public void setCollider(Collider collider) {
		if (this.collider != null) this.collider.remove();
		this.collider = collider;
		this.setSize(collider.getWidth(), collider.getHeight());
		this.collider.setPosition(this.getX(), this.getY());
	}
	
	//Overridden (Inherited or Required)
	
	@Override
	public void moveBy(float x, float y) {
		super.moveBy(x, y);
		if (this.collider != null) this.collider.moveBy(x, y);
	}
	
	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		if (this.collider != null) this.collider.setPosition(x, y);
	}
	
	@Override
	public void setPosition(float x, float y, int alignment) {
		super.setPosition(x, y, alignment);
		if (this.collider != null) this.collider.setPosition(x, y, alignment);
	}
	
	@Override
	public void setSize(float width, float height) {
		super.setSize(width, height);
		if (this.collider != null) this.collider.setSize(width, height);
	}
	
	@Override
	public void setWidth(float width) {
		super.setWidth(width);
		if (this.collider != null) this.collider.setWidth(width);
	}
	
	@Override
	public void setHeight(float height) {
		super.setHeight(height);
		if (this.collider != null) this.collider.setHeight(height);
	}
	
}