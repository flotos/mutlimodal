import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import java.awt.Event;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public enum STATE_LIST {
		PALETTE_VIDE, ATTENTE_SPECIFICATION, ATTENTE_ORDRE,
		PROCESSUS_SUPPRESSION,
		PROCESSUS_DEPLACEMENT
};

public class PaletteManager {

    private String state;
    
    public static void main(String[] args) {
    	
	}
    
    private void choisirRectangle() {
    	state = STATE_LIST.ATT
    }
    
    private void choisirEllipse() {
    	
    }
    
    private void designerPosition() {
    	
    }
    
    private void designerCouleur() {
    	
    }
    
    private void choisirActionSupprimer() {
    	
    }
    
    private void choisirObjetASupprimer() {
    	
    }
    
    private void choisirObjetASupprimerAvecCouleur() {
    	
    }
    
    private void choisirActionDeplacer() {
    	
    }
    
    private void choisirObjetADeplacer() {
    	
    }
    
    private void choisirPositionADeplacer() {
    	
    }
    
}