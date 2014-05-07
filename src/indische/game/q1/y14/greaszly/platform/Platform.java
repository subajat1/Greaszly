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

	private Texture buttonRed_tex, buttonYell_tex, buttonGreen_tex;
	private Rectangle buttonRed_rec, buttonYell_rec, buttonGreen_rec;
	private float buttonScale = 0.65f;

	private Npc npc1;

	private List<Npc> npcs;

	public void create() {

//		platformBack_tex = new Texture(Gdx.files.internal("debug/bgBackDebug.png"));
		platformBack_tex = new Texture(Gdx.files.internal("level/bgBack.png"));
		platformBack_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformBack_speed = 2;
		platformBack_rec = new Rectangle(-90, Gdx.graphics.getHeight() - 450,
				platformBack_tex.getWidth(), platformBack_tex.getHeight());

//		platformMid_tex = new Texture(Gdx.files.internal("debug/bgMidDebug.png"));
		platformMid_tex = new Texture(Gdx.files.internal("level/bgMid.png"));
		platformMid_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformMid_speed = 4;
		platformMid_rec = new Rectangle(-90, 120, platformMid_tex.getWidth(),
				platformMid_tex.getHeight());

//		platformFront_tex = new Texture(Gdx.files.internal("debug/bgFrontDebug.png"));
		platformFront_tex = new Texture(Gdx.files.internal("level/bgFront.png"));
		platformFront_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		platformFront_speed = 8;
		platformFront_rec = new Rectangle(-600, -34,
				platformFront_tex.getWidth(), platformFront_tex.getHeight());

		buttonRed_tex = new Texture(Gdx.files.internal("level/button1.png"));
		buttonRed_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonRed_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 + 120, buttonRed_tex.getWidth()
						* buttonScale, buttonRed_tex.getHeight() * buttonScale);

		buttonYell_tex = new Texture(Gdx.files.internal("level/button2.png"));
		buttonYell_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonYell_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 - 15, buttonYell_tex.getWidth()
						* buttonScale, buttonYell_tex.getHeight() * buttonScale);

		buttonGreen_tex = new Texture(Gdx.files.internal("level/button3.png"));
		buttonGreen_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonGreen_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 - 150, buttonGreen_tex.getWidth()
						* buttonScale, buttonGreen_tex.getHeight()
						* buttonScale);

		npc1 = new Npc();
		npc1.create();

		npcs = new ArrayList<Npc>();
		npcs.add(new Npc());

	}

	public void update(float stateTime) {
		if (platformBack_rec.x <= -1 * (platformBack_rec.width - 1024))
			platformBack_rec.x = 0;
		if (platformMid_rec.x <= -1 * (platformMid_rec.width - 1024))
			platformMid_rec.x = 0;
		if (platformFront_rec.x <= -1 * (platformFront_rec.width - 1024))
			platformFront_rec.x = 0;
		
		
		boolean test = true;
		
//		if (Gdx.input.isKeyPressed(Keys.SPACE)) {
//		test = true;
//		}else{
//			test = false;
//		}
		if(test){
		platformBack_rec.x -= platformBack_speed;

		platformMid_rec.x -= platformMid_speed;

		platformFront_rec.x -= platformFront_speed;

		}
		npc1.update(stateTime);

		for (Npc npc : npcs) {
			npc.update(stateTime);
		}

		// BUTTONs
		
//		int x = Gdx.input.getX();
//		int y = Gdx.input.getY();

		// if (Gdx.input.isTouched()) {
		//
		// if (buttonGreen_rec.contains(x, Gdx.graphics.getHeight() - y)) {
		// debug = true;
		// } else if (buttonRed_rec.contains(x, Gdx.graphics.getHeight() - y)) {
		// debug = true;
		// } else if (buttonYell_rec.contains(x, Gdx.graphics.getHeight() - y))
		// {
		// debug = true;
		// } else {
		// debug = false;
		// }
		// } else {
		// debug = false;
		// }
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

		batch.draw(buttonRed_tex, buttonRed_rec.x, buttonRed_rec.y,
				buttonRed_rec.width, buttonRed_rec.height);
		batch.draw(buttonYell_tex, buttonYell_rec.x, buttonYell_rec.y,
				buttonYell_rec.width, buttonYell_rec.height);
		batch.draw(buttonGreen_tex, buttonGreen_rec.x, buttonGreen_rec.y,
				buttonGreen_rec.width, buttonGreen_rec.height);

		for (Npc npc : npcs) {
			npc.draw(batch);
		}
	}

	public void dispose() {

	}
}
