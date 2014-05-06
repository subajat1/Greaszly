package Level;

import npc.Npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
	private Texture platformBack_tex, platformMid_tex, platformFront_tex;
	private Rectangle platformBack_rec, platformMid_rec, platformFront_rec;
	private float platformBack_scale, platformMid_scale, platformFront_scale;
	private int platformBack_speed, platformMid_speed, platformFront_speed;

	private Texture buttonRed_tex, buttonYell_tex, buttonGreen_tex;
	private Rectangle buttonRed_rec, buttonYell_rec, buttonGreen_rec;
	private float buttonScale = 0.4f;

	private Npc npc1;
	
	private boolean debug = false;

	public void create() {
		platformBack_tex = new Texture(Gdx.files.internal("level/bgBack.png"));
		platformBack_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformBack_scale = 1f;
		platformBack_speed = 3;
		platformBack_rec = new Rectangle(0, 0, platformBack_tex.getWidth()
				* platformBack_scale, platformBack_tex.getHeight()
				* platformBack_scale);

		platformMid_tex = new Texture(Gdx.files.internal("level/bgMid.png"));
		platformMid_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformMid_scale = 1f;
		platformMid_speed = 4;
		platformMid_rec = new Rectangle(0, 0, platformMid_tex.getWidth()
				* platformMid_scale, platformMid_tex.getHeight()
				* platformMid_scale);

		platformFront_tex = new Texture(Gdx.files.internal("level/bgFront.png"));
		platformFront_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformFront_scale = 1f;
		platformFront_speed = 5;
		platformFront_rec = new Rectangle(0, 0, platformFront_tex.getWidth()
				* platformFront_scale, platformFront_tex.getHeight()
				* platformFront_scale);

		buttonRed_tex = new Texture(Gdx.files.internal("level/button1.png"));
		buttonRed_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonRed_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 - 150, buttonRed_tex.getWidth()
						* buttonScale, buttonRed_tex.getHeight() * buttonScale);

		buttonYell_tex = new Texture(Gdx.files.internal("level/button2.png"));
		buttonYell_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonYell_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 + 0, buttonYell_tex.getWidth()
						* buttonScale, buttonYell_tex.getHeight() * buttonScale);

		buttonGreen_tex = new Texture(Gdx.files.internal("level/button3.png"));
		buttonGreen_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonGreen_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 + 150, buttonGreen_tex.getWidth()
						* buttonScale, buttonGreen_tex.getHeight()
						* buttonScale);

		npc1 = new Npc();
		npc1.create();
	}

	public void update(float stateTime) {
		platformBack_rec.x -= platformBack_speed;

		platformMid_rec.x -= platformMid_speed;

		platformFront_rec.x -= platformFront_speed;

		int x = Gdx.input.getX();
		int y = Gdx.input.getY();
		
		npc1.update(stateTime);		

		// BUTTONs

		if (Gdx.input.isTouched()) {

			if (buttonGreen_rec.contains(x, Gdx.graphics.getHeight() - y)) {
				debug = true;
			} else if (buttonRed_rec.contains(x, Gdx.graphics.getHeight() - y)) {
				debug = true;
			} else if (buttonYell_rec.contains(x, Gdx.graphics.getHeight() - y)) {
				debug = true;
			} else {
				debug = false;
			}
		}else{
			debug = false;
		}
	}

	public void draw(SpriteBatch batch) {

		batch.draw(platformBack_tex, platformBack_rec.x, platformBack_rec.y);
		if (platformBack_rec.x < -1
				* (platformBack_rec.width - Gdx.graphics.getWidth()))
			platformBack_rec.x = 0;

		batch.draw(platformMid_tex, platformMid_rec.x, platformMid_rec.y);
		if (platformMid_rec.x < -1
				* (platformMid_rec.width - Gdx.graphics.getWidth()))
			platformMid_rec.x = 0;

		batch.draw(platformFront_tex, platformFront_rec.x, platformFront_rec.y,
				platformFront_rec.width, 200);
		if (platformFront_rec.x < -1
				* (platformFront_rec.width - Gdx.graphics.getWidth()))
			platformFront_rec.x = 0;

		batch.draw(buttonRed_tex, buttonRed_rec.x, buttonRed_rec.y,
				buttonRed_rec.width, buttonRed_rec.height);
		batch.draw(buttonYell_tex, buttonYell_rec.x, buttonYell_rec.y,
				buttonYell_rec.width, buttonYell_rec.height);
		batch.draw(buttonGreen_tex, buttonGreen_rec.x, buttonGreen_rec.y,
				buttonGreen_rec.width, buttonGreen_rec.height);

		if (debug)
			npc1.draw(batch);
		
	}

	public void dispose() {

	}
}
