package npc;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Npc {
	private Texture npc_tex;
	private Rectangle npc_rec;
	private float npc_scale;

	public void create() {
		npc_tex = new Texture(Gdx.files.internal("npc/fries.png"));
		npc_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		npc_scale = 0.4f;
		npc_rec = new Rectangle(100, 100, npc_tex.getWidth() * npc_scale,
				npc_tex.getHeight() * npc_scale);
	}

	public void dispose() {

	}

	public void update(float stateTime) {

	}

	public void draw(SpriteBatch batch) {
		batch.draw(npc_tex, npc_rec.x, npc_rec.y, npc_rec.width, npc_rec.height);
	}
}
