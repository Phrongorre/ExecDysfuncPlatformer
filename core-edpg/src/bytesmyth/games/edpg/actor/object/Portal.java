package bytesmyth.games.edpg.actor.object;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.Stage;

import bytesmyth.games.edpg.ExecDysfuncPlatformer;
import bytesmyth.games.edpg.actor.RoundCollider;

public class Portal extends Trigger {
	
	private Class<? extends Screen> nextScreen;
	
	public Portal(Class<? extends Screen> screen, float x, float y, Stage s) {
		super(x, y, s);
		
		this.setCollider(new RoundCollider(x, y, 50f, 50f, s));
		
		this.loadTexture("portal.png");
		this.setSize(this.animator.getKeyFrame(0f).getRegionWidth(), this.animator.getKeyFrame(0f).getRegionHeight());
		
		this.nextScreen = screen;
	}
	public Portal(Class<? extends Screen> screen, Stage s) { this(screen, 0f, 0f, s); }
	
	@Override
	public boolean activate() {
		try {
			ExecDysfuncPlatformer.setActiveScreen(this.nextScreen.newInstance());
			return true;
		} catch (Exception e) {}
		return false;
	}
	
	@Override
	public boolean deactivate() { return true; }
	
}
