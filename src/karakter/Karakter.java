package karakter;

import Level.Platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Karakter {
	Platform platform;
	
	private Texture karakter_tex, dummy_tex;
	private Rectangle karakter_rec, dummy_rec;
	private float karakter_scale;
	private float boingBoingVar, organVar;

	private Texture karakter_e_neut_tex;
	private Rectangle karakter_e_rec;

	public float stateTime = 0, stateTimePrev = 0;

	public Animation tangKanan_ani, tangKiri_ani, kakiKanan_ani, kakiKiri_ani;
	public Texture tangKanan_tex, tangKiri_tex, kakiKanan_tex, kakiKiri_tex;
	public TextureRegion[] tangKanan_fra, tangKiri_fra, kakiKanan_fra,
			kakiKiri_fra;
	public TextureRegion tangKanan_frac, tangKiri_frac, kakiKanan_frac,
			kakiKiri_frac;
	public Rectangle tangKanan_rec, tangKiri_rec, kakiKanan_rec, kakiKiri_rec;

	public void create() {
		platform = new Platform();
		platform.create();
		
		karakter_tex = new Texture(Gdx.files.internal("karakter/beef.png"));
		karakter_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		karakter_scale = 0.20f;
		karakter_rec = new Rectangle(
				(float) (Gdx.graphics.getWidth() / 2 - karakter_tex.getWidth()
						* karakter_scale / 2),
				(float) (Gdx.graphics.getHeight() / 2)
						- karakter_tex.getHeight() * karakter_scale / 2,
				karakter_tex.getWidth() * karakter_scale,
				karakter_tex.getHeight() * karakter_scale);

		dummy_tex = new Texture(Gdx.files.internal("karakter/steak0001.png"));
		dummy_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		float scale = 0.2f;
		dummy_rec = new Rectangle(
				(float) (Gdx.graphics.getWidth() / 2 - dummy_tex.getWidth()
						* scale / 2), (float) (Gdx.graphics.getHeight() / 2)
						- dummy_tex.getHeight() * scale / 2,
				dummy_tex.getWidth() * scale, dummy_tex.getHeight() * scale);

		karakter_e_neut_tex = new Texture("emoticon/neutral.png");
		karakter_e_neut_tex.setFilter(TextureFilter.Linear,
				TextureFilter.Linear);
		float e_scale = 0.25f;
		karakter_e_rec = new Rectangle(karakter_rec.x
				+ (karakter_rec.width / 2) - 12, karakter_rec.y
				+ (karakter_rec.height / 3) + 4, karakter_e_neut_tex.getWidth()
				* e_scale, karakter_e_neut_tex.getHeight()
				* e_scale);

		createKarakterBodyAnimation();
	}

	private void createKarakterBodyAnimation() {

		float animationVar = 0.25f;

		// tangan kanan
		tangKanan_tex = new Texture(
				Gdx.files.internal("karakter/tangkanan.png"));
		tangKanan_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmptangkanan = TextureRegion.split(tangKanan_tex,
				tangKanan_tex.getWidth() / 4, tangKanan_tex.getHeight() / 6);
		tangKanan_fra = new TextureRegion[24];
		int index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				tangKanan_fra[index++] = tmptangkanan[j][i];
			}
		}
		tangKanan_ani = new Animation(animationVar, tangKanan_fra);

		// tangan kiri
		tangKiri_tex = new Texture(Gdx.files.internal("karakter/tangkiri.png"));
		tangKiri_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmptangkiri = TextureRegion.split(tangKiri_tex,
				tangKiri_tex.getWidth() / 4, tangKiri_tex.getHeight() / 6);
		tangKiri_fra = new TextureRegion[24];
		index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				tangKiri_fra[index++] = tmptangkiri[j][i];
			}
		}
		tangKiri_ani = new Animation(animationVar, tangKiri_fra);

		// kaki kanan
		kakiKanan_tex = new Texture(
				Gdx.files.internal("karakter/kakikanan.png"));
		kakiKanan_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmpkakikanan = TextureRegion.split(kakiKanan_tex,
				kakiKanan_tex.getWidth() / 4, kakiKanan_tex.getHeight() / 6);
		kakiKanan_fra = new TextureRegion[24];
		index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				kakiKanan_fra[index++] = tmpkakikanan[j][i];
			}
		}
		kakiKanan_ani = new Animation(animationVar, kakiKanan_fra);

		// kaki kiri
		kakiKiri_tex = new Texture(Gdx.files.internal("karakter/kakikiri.png"));
		kakiKiri_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmpkakikiri = TextureRegion.split(kakiKiri_tex,
				kakiKiri_tex.getWidth() / 4, kakiKiri_tex.getHeight() / 6);
		kakiKiri_fra = new TextureRegion[24];
		index = 0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 6; j++) {
				kakiKiri_fra[index++] = tmpkakikiri[j][i];
			}
		}
		kakiKiri_ani = new Animation(animationVar, kakiKiri_fra);
	}

	public void dispose() {
		karakter_tex.dispose();
		karakter_rec = null;
		
		platform.dispose();
	}

	public void update() {
		stateTime = TimeUtils.millis() % 1000;
		boingBoingVar = stateTime / 250;
		organVar = stateTime / 200;
		
		platform.update(stateTime);
		
	}

	public void draw(SpriteBatch batch) {
		batch.begin();
		
		platform.draw(batch);
		

		// organVar = 0;

//		batch.draw(dummy_tex, dummy_rec.x - 150, dummy_rec.y - 15,
//				dummy_rec.width, dummy_rec.height);

		tangKiri_frac = tangKiri_ani.getKeyFrame(organVar, true);
		batch.draw(tangKiri_frac, karakter_rec.x + 45, karakter_rec.y + 20, 70,
				40);

		kakiKiri_frac = kakiKiri_ani.getKeyFrame(organVar, true);
		batch.draw(kakiKiri_frac, karakter_rec.x + 21, karakter_rec.y - 32, 70,
				40);

		// <body>
		float boing = (float) (1 - Math.sin(boingBoingVar * Math.PI));
		if (boing < 0)
			boing *= -1;
		batch.draw(karakter_tex, karakter_rec.x, karakter_rec.y,
				karakter_rec.width, karakter_rec.height - ((float) (boing * 8)));

		// <face>
		batch.draw(karakter_e_neut_tex, karakter_e_rec.x, karakter_e_rec.y - ((float) (boing * 8)),
				karakter_e_rec.width, karakter_e_rec.height);

		tangKanan_frac = tangKanan_ani.getKeyFrame(organVar, true);
		batch.draw(tangKanan_frac, karakter_rec.x - 23, karakter_rec.y + 13,
				70, 40);

		kakiKanan_frac = kakiKanan_ani.getKeyFrame(organVar, true);
		batch.draw(kakiKanan_frac, karakter_rec.x, karakter_rec.y - 29, 70, 40);

		batch.end();
	}
}
