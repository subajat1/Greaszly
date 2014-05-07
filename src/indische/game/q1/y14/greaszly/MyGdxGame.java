package indische.game.q1.y14.greaszly;

import indische.game.q1.y14.greaszly.karakter.Karakter;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class MyGdxGame implements ApplicationListener {
	private OrthographicCamera camera;
	private SpriteBatch batch;

	private Karakter karakter;

	public static boolean debug = false;

	@Override
	public void create() {
		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();

		camera = new OrthographicCamera();
		camera.setToOrtho(false, w, h);

		batch = new SpriteBatch();

		karakter = new Karakter();
		karakter.create();
	}

	@Override
	public void dispose() {
		batch.dispose();
		karakter.dispose();
	}

	private void update() {
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		if (Gdx.input.isKeyPressed(Keys.V)) {
			if (MyGdxGame.debug)
				MyGdxGame.debug = false;
			else
				MyGdxGame.debug = true;
		}

		karakter.update();
	}

	private void draw() {
		batch.setProjectionMatrix(camera.combined);

		karakter.draw(batch);
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void render() {
		update();
		draw();
	}
}
