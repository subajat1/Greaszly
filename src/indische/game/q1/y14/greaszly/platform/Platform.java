package indische.game.q1.y14.greaszly.platform;

import indische.game.q1.y14.greaszly.MyGdxGame;
import indische.game.q1.y14.greaszly.npc.Npc;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Platform {
	private Texture platformBack_tex, platformMid_tex, platformFront_tex;
	private Rectangle platformBack_rec, platformMid_rec, platformFront_rec;
	private int platformBack_speed, platformMid_speed, platformFront_speed;
	
	public static int runVar_speed = 0;

	public List<Npc> npcs;

	public void create() {

		// platformBack_tex = new
		// Texture(Gdx.files.internal("debug/bgBackDebug.png"));
		platformBack_tex = new Texture(Gdx.files.internal("level/bgBack.png"));
		platformBack_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformBack_speed = 1;
		platformBack_rec = new Rectangle(-90, Gdx.graphics.getHeight() - 450,
				platformBack_tex.getWidth(), platformBack_tex.getHeight());

		// platformMid_tex = new
		// Texture(Gdx.files.internal("debug/bgMidDebug.png"));
		platformMid_tex = new Texture(Gdx.files.internal("level/bgMid.png"));
		platformMid_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformMid_speed = 2;
		platformMid_rec = new Rectangle(-90, 120, platformMid_tex.getWidth(),
				platformMid_tex.getHeight());

		// platformFront_tex = new
		// Texture(Gdx.files.internal("debug/bgFrontDebug.png"));
		platformFront_tex = new Texture(Gdx.files.internal("level/bgFront.png"));
		platformFront_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformFront_speed = 4;
		platformFront_rec = new Rectangle(-600, -34,
				platformFront_tex.getWidth(), platformFront_tex.getHeight());		

		npcs = new ArrayList<Npc>();
		npcs.add(new Npc());

	}

	public void update(float stateTime) {
		if (platformBack_rec.x <= -1 * (platformBack_rec.width - 1024) + (platformBack_speed + runVar_speed))
			platformBack_rec.x = 0;
		if (platformMid_rec.x <= -1 * (platformMid_rec.width - 1024) + (platformMid_speed + runVar_speed))
			platformMid_rec.x = 0;
		if (platformFront_rec.x <= -1 * (platformFront_rec.width - 1024) + (platformFront_speed + runVar_speed))
			platformFront_rec.x = 0;

		boolean test = true;

		// if (Gdx.input.isKeyPressed(Keys.SPACE)) {
		// test = true;
		// }else{
		// test = false;
		// }
		if (test) {
			platformBack_rec.x -= (platformBack_speed + runVar_speed);

			platformMid_rec.x -= (platformMid_speed + runVar_speed);

			platformFront_rec.x -= (platformFront_speed + runVar_speed);

		}

		for (Npc npc : npcs) {
			npc.update(stateTime);
		}

		

	}

	public void draw(SpriteBatch batch) {

		if (MyGdxGame.debug) {

		} else {

			batch.draw(platformBack_tex, platformBack_rec.x,
					platformBack_rec.y, platformBack_rec.width,
					platformBack_rec.height);

			batch.draw(platformMid_tex, platformMid_rec.x, platformMid_rec.y,
					platformMid_rec.width, platformMid_rec.height);

			batch.draw(platformFront_tex, platformFront_rec.x,
					platformFront_rec.y, platformFront_rec.width,
					platformFront_rec.height);

			// means -1 * Gdx.graphics.getWidth()

		}

		for (Npc npc : npcs) {
			npc.draw(batch);
		}

	}

	public void dispose() {

	}
}
