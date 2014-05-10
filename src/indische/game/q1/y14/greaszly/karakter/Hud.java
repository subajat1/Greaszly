package indische.game.q1.y14.greaszly.karakter;

import indische.game.q1.y14.greaszly.npc.Npc;
import indische.game.q1.y14.greaszly.platform.Platform;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

public class Hud {

	private Platform platfrom;

	private Texture buttonRed_tex, buttonYell_tex, buttonGreen_tex;
	private Rectangle buttonRed_rec, buttonYell_rec, buttonGreen_rec;
	private boolean buttonRed_stat, buttonYell_stat, buttonGreen_stat,
			staminaDrain;
	private float buttonScale = 0.65f;

	private Texture staminaBar_tex, greaseBar_tex;
	private Rectangle staminaBar_rec, greaseBar_rec;
	
	private boolean toss = false;

	public void setPlatfrom(Platform platfrom) {
		this.platfrom = platfrom;
	}

	public void create() {
		buttonRed_tex = new Texture(Gdx.files.internal("level/button1.png"));
		buttonRed_tex.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		buttonRed_rec = new Rectangle(Gdx.graphics.getWidth() - 150,
				Gdx.graphics.getHeight() / 2 + 120, buttonRed_tex.getWidth()
						* buttonScale, buttonRed_tex.getHeight() * buttonScale);
		buttonRed_stat = false;

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
		buttonGreen_stat = false;

		staminaBar_tex = new Texture(Gdx.files.internal("debug/blank.png"));
		staminaBar_rec = new Rectangle(20, 40, 20, 500);

		staminaDrain = false;

		greaseBar_tex = new Texture(Gdx.files.internal("debug/blankYell.png"));
		greaseBar_rec = new Rectangle(120, 25, 800, 20);

	}

	public void update(float stateTime) {
		// BUTTONs

	}

	public void updateGreaseHud() {
		if (Karakter.grease > 0) {
			Karakter.grease -= 0.1f;
			greaseBar_rec.width -= 0.1f;
		}

	}

	public void updateStaminaHud() {
		int x = Gdx.input.getX();
		int y = Gdx.input.getY();

		// RED
		if (Gdx.input.isTouched()
				&& buttonRed_rec.contains(x, Gdx.graphics.getHeight() - y)) {
			for (Npc npc : this.platfrom.npcs) {
				if (Karakter.originPosition > npc.getNpc_rec().x - 30
						&& Karakter.originPosition < npc.getNpc_rec().x + 30 && !toss) {
					
					buttonRed_stat = true;
					
					toss = true;

				}
			}

		} else {
			buttonRed_stat = false;
			toss = false;
		}

		// GREEN
		if (Gdx.input.isTouched()
				&& buttonGreen_rec.contains(x, Gdx.graphics.getHeight() - y)
				&& !staminaDrain) {

			buttonGreen_stat = true;

			if (Karakter.stamina < 10) {
				staminaDrain = true;
			}

			if (Karakter.stamina > 5 && !staminaDrain) {
				if (staminaBar_rec.height < 5)
					staminaBar_rec.height = 1;
				else
					staminaBar_rec.height -= 5;

				Karakter.stamina -= 1;

				Karakter.stamina_BoingVar = 100;
				Karakter.stamina_OrganVar = 0.5f;

				Platform.runVar_speed = 2;
				Karakter.stamina_positionBuffer = 4;

			} else if (Karakter.stamina > 0 && Karakter.stamina <= 5) {

				update_normal();
			}

		} else {
			buttonGreen_stat = false;
			if (Karakter.stamina > 70) {
				staminaDrain = false;
			}
			update_normal();
		}

	}

	private void update_normal() {
		Karakter.stamina_BoingVar = 250;
		Karakter.stamina_OrganVar = 1f;

		if (Karakter.stamina < 100) {
			staminaBar_rec.height += 1;
			Karakter.stamina += 0.2f;

			Platform.runVar_speed = 0;
			Karakter.stamina_positionBuffer = -2;
		} else {
			staminaBar_rec.height = 500;
			Karakter.stamina = 100;
		}
	}

	public void draw(SpriteBatch batch) {
		batch.draw(buttonRed_tex, buttonRed_rec.x, buttonRed_rec.y,
				buttonRed_rec.width, buttonRed_rec.height);

		if(toss){
			batch.draw(greaseBar_tex,50,50,50,50);
		}

		batch.draw(buttonYell_tex, buttonYell_rec.x, buttonYell_rec.y,
				buttonYell_rec.width, buttonYell_rec.height);
		batch.draw(buttonGreen_tex, buttonGreen_rec.x, buttonGreen_rec.y,
				buttonGreen_rec.width, buttonGreen_rec.height);

		batch.draw(staminaBar_tex, staminaBar_rec.x, staminaBar_rec.y,
				staminaBar_rec.width, staminaBar_rec.height);

		batch.draw(greaseBar_tex, greaseBar_rec.x, greaseBar_rec.y,
				greaseBar_rec.width, greaseBar_rec.height);

		batch.draw(buttonRed_tex, staminaBar_rec.x, staminaBar_rec.y + 40, 20,
				20);
		batch.draw(buttonYell_tex, staminaBar_rec.x, staminaBar_rec.y + 350,
				20, 20);
	}

}
