package violin.emulator;

import sofia.app.Screen;
import android.content.Intent;


// -------------------------------------------------------------------------
/**
 *  Creates an opening screen to summarize the game
 *
 *  @author Joe
 *  @version Dec 9, 2012
 */

public class OpenScreen extends Screen
{

    public void mainButton()
    {
        Intent x = new Intent(this, ViolinEmulatorScreen.class);
        startActivity(x);
        finish();
    }


}
