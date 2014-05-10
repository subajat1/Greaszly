package indische.game.q1.y14.greaszly.karakter;

import indische.game.q1.y14.greaszly.MyGdxGame;
import indische.game.q1.y14.greaszly.platform.Platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.TimeUtils;

public class Karakter {

	public static float stamina;
	public static int stamina_BoingVar;
	public static float stamina_OrganVar;
	public static int stamina_positionBuffer;
	
	public static float grease;
	
	public static int originPosition;

	private Hud hud;
	private Platform platform;

	private Texture karakter_tex, dummy_tex;
	private Rectangle karakter_rec, dummy_rec;
	private float karakter_scale;
	private float boingBoingVar, organVar;

	private Texture face_e_neut_tex;
	private Rectangle face_e_rec;

	public float stateTime = 0, stateTimePrev = 0;

	public Animation tangKanan_ani, tangKiri_ani, kakiKanan_ani, kakiKiri_ani;
	public Texture tangKanan_tex, tangKiri_tex, kakiKanan_tex, kakiKiri_tex;
	public TextureRegion[] tangKanan_fra, tangKiri_fra, kakiKanan_fra,
			kakiKiri_fra;
	public TextureRegion tangKanan_frac, tangKiri_frac, kakiKanan_frac,
			kakiKiri_frac;
	public Rectangle tangKanan_rec, tangKiri_rec, kakiKanan_rec, kakiKiri_rec;

	public void create() {
		// Stats
		Karakter.stamina = 100;
		Karakter.stamina_BoingVar = 250;
		Karakter.stamina_OrganVar = 1;
		Karakter.stamina_positionBuffer = 0;
		
		Karakter.grease = 500;

		
		platform = new Platform();
		platform.create();

		hud = new Hud();
		hud.create();
		hud.setPlatfrom(platform);

		
		dummy_tex = new Texture(Gdx.files.internal("mockup/ingame.png"));
		dummy_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		dummy_rec = new Rectangle(0, 0, dummy_tex.getWidth(), 600);

		karakter_tex = new Texture(Gdx.files.internal("karakter/beef.png"));
		karakter_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		karakter_scale = 0.4f;
		karakter_rec = new Rectangle(70, 120, karakter_tex.getWidth()
				* karakter_scale, karakter_tex.getHeight() * karakter_scale);

		face_e_neut_tex = new Texture("emoticon/neutral.png");
		face_e_neut_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		float face_e_scale = 0.45f;
		face_e_rec = new Rectangle(karakter_rec.x + (karakter_rec.width / 2)
				- 10, karakter_rec.y + (karakter_rec.height / 3) + 0,
				face_e_neut_tex.getWidth() * face_e_scale,
				face_e_neut_tex.getHeight() * face_e_scale);

		createKarakterBodyAnimation();

		float limbs_scale = 0.4f;

		tangKanan_rec = new Rectangle(karakter_rec.x - 48, karakter_rec.y + 20,
				tangKanan_tex.getWidth() / 4 * limbs_scale,
				tangKanan_tex.getHeight() / 5 * limbs_scale);

		tangKiri_rec = new Rectangle(karakter_rec.x + 94, karakter_rec.y + 0,
				tangKiri_tex.getWidth() / 4 * limbs_scale,
				tangKiri_tex.getHeight() / 5 * limbs_scale);

		kakiKanan_rec = new Rectangle(karakter_rec.x - 10, karakter_rec.y - 54,
				tangKanan_tex.getWidth() / 4 * limbs_scale,
				tangKanan_tex.getHeight() / 5 * limbs_scale);

		kakiKiri_rec = new Rectangle(karakter_rec.x + 40, karakter_rec.y - 54,
				tangKanan_tex.getWidth() / 4 * limbs_scale,
				tangKanan_tex.getHeight() / 5 * limbs_scale);
	}

	public void dispose() {
		karakter_tex.dispose();
		karakter_rec = null;

		platform.dispose();
	}

	public void update() {
		stateTime = TimeUtils.millis() % 1000;
		boingBoingVar = stateTime / Karakter.stamina_BoingVar;
		organVar += Gdx.graphics.getDeltaTime() / Karakter.stamina_OrganVar;		
		
		hud.update(stateTime);
		hud.updateStaminaHud();
		hud.updateGreaseHud();

		platform.update(stateTime);
		
		karakter_rec.x += stamina_positionBuffer;
		tangKanan_rec.x += stamina_positionBuffer;
		tangKiri_rec.x += stamina_positionBuffer;
		kakiKanan_rec.x += stamina_positionBuffer;
		kakiKiri_rec.x += stamina_positionBuffer;
		face_e_rec.x += stamina_positionBuffer;
		
		if (karakter_rec.x <= 70) {

			stamina_positionBuffer = 0;
			karakter_rec.x = 70;
			tangKanan_rec.x = karakter_rec.x - 48;
			tangKiri_rec.x = karakter_rec.x + 94;
			kakiKanan_rec.x = karakter_rec.x - 10;
			kakiKiri_rec.x = karakter_rec.x + 40;
			face_e_rec.x = karakter_rec.x + (karakter_rec.width / 2) - 10;
		}
		
		originPosition = (int) (this.karakter_rec.x + this.karakter_rec.width);
	}

	public void draw(SpriteBatch batch) {
		batch.begin();

		if (MyGdxGame.debug)
			batch.draw(dummy_tex, dummy_rec.x, dummy_rec.y, dummy_rec.width,
					dummy_rec.height);

		platform.draw(batch);

		tangKiri_frac = tangKiri_ani.getKeyFrame(organVar, true);
		batch.draw(tangKiri_frac, tangKiri_rec.x, tangKiri_rec.y,
				tangKiri_rec.width, tangKiri_rec.height);

		kakiKiri_frac = kakiKiri_ani.getKeyFrame(organVar, true);
		batch.draw(kakiKiri_frac, kakiKiri_rec.x, kakiKiri_rec.y,
				kakiKiri_rec.width, kakiKiri_rec.height);

		// <body>
		float boing = (float) (1 - Math.sin(boingBoingVar * Math.PI));
		if (boing < 0)
			boing *= -1;
		batch.draw(karakter_tex, karakter_rec.x, karakter_rec.y,
				karakter_rec.width, karakter_rec.height - ((float) (boing * 8)));

		// <face>
		batch.draw(face_e_neut_tex, face_e_rec.x, face_e_rec.y
				- ((float) (boing * 8)), face_e_rec.width, face_e_rec.height);

		tangKanan_frac = tangKanan_ani.getKeyFrame(organVar, true);
		batch.draw(tangKanan_frac, tangKanan_rec.x, tangKanan_rec.y,
				tangKanan_rec.width, tangKanan_rec.height);

		kakiKanan_frac = kakiKanan_ani.getKeyFrame(organVar, true);
		batch.draw(kakiKanan_frac, kakiKanan_rec.x, kakiKanan_rec.y,
				kakiKanan_rec.width, kakiKanan_rec.height);

		hud.draw(batch);

		batch.end();
	}

	private void createKarakterBodyAnimation() {

		float animationVar = 0.04f;

		// tangan kanan
		tangKanan_tex = new Texture(
				Gdx.files.internal("karakter/tangankanan.png"));
		tangKanan_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmptangkanan = TextureRegion.split(tangKanan_tex,
				tangKanan_tex.getWidth() / 4, tangKanan_tex.getHeight() / 5);
		tangKanan_fra = new TextureRegion[20];
		int index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				tangKanan_fra[index++] = tmptangkanan[i][j];
			}
		}
		tangKanan_ani = new Animation(animationVar, tangKanan_fra);

		// tangan kiri
		tangKiri_tex = new Texture(
				Gdx.files.internal("karakter/tangankiri.png"));
		tangKiri_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmptangkiri = TextureRegion.split(tangKiri_tex,
				tangKiri_tex.getWidth() / 4, tangKiri_tex.getHeight() / 5);
		tangKiri_fra = new TextureRegion[20];
		index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				tangKiri_fra[index++] = tmptangkiri[i][j];
			}
		}
		tangKiri_ani = new Animation(animationVar, tangKiri_fra);

		// kaki kanan
		kakiKanan_tex = new Texture(
				Gdx.files.internal("karakter/kakikanan.png"));
		kakiKanan_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmpkakikanan = TextureRegion.split(kakiKanan_tex,
				kakiKanan_tex.getWidth() / 4, kakiKanan_tex.getHeight() / 5);
		kakiKanan_fra = new TextureRegion[20];
		index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				kakiKanan_fra[index++] = tmpkakikanan[i][j];
			}
		}
		kakiKanan_ani = new Animation(animationVar, kakiKanan_fra);

		// kaki kiri
		kakiKiri_tex = new Texture(Gdx.files.internal("karakter/kakikiri.png"));
		kakiKiri_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		TextureRegion[][] tmpkakikiri = TextureRegion.split(kakiKiri_tex,
				kakiKiri_tex.getWidth() / 4, kakiKiri_tex.getHeight() / 5);
		kakiKiri_fra = new TextureRegion[20];
		index = 0;
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 4; j++) {
				kakiKiri_fra[index++] = tmpkakikiri[i][j];
			}
		}
		kakiKiri_ani = new Animation(animationVar, kakiKiri_fra);
	}
}
