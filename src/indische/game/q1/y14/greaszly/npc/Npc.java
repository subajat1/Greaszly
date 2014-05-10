package indische.game.q1.y14.greaszly.npc;

import indische.game.q1.y14.greaszly.platform.Platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Npc {
	private Texture npc_tex;
	private Rectangle npc_rec;
	private float npc_scale;
	private int npc_speed;
	
	public Rectangle getNpc_rec() {
		return npc_rec;
	}

	public Npc() {
		npc_tex = new Texture(Gdx.files.internal("npc/fries.png"));
		npc_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		npc_scale = .35f;
		npc_rec = new Rectangle(1400, 128, npc_tex.getWidth() * npc_scale,
				npc_tex.getHeight() * npc_scale);
		npc_speed = 4;
	}

	public void create() {

	}

	public void setNpc_rec(Rectangle npc_rec) {
		this.npc_rec = npc_rec;
	}

	public void dispose() {

	}

	public void update(float stateTime) {
		 npc_rec.x -= (npc_speed + Platform.runVar_speed);

		if (npc_rec.x < -1 * Gdx.graphics.getWidth())
			npc_rec.x = Gdx.graphics.getWidth();

	}

	public void draw(SpriteBatch batch) {
		batch.draw(npc_tex, npc_rec.x, npc_rec.y, npc_rec.width, npc_rec.height);
	}
}
