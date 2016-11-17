package minantak.my20_48.game.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import minantak.my20_48.game.Game;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "MY 20_48";
		config.width = 360;
		config.height = 640;
		new LwjglApplication(new Game(), config);
	}
}

