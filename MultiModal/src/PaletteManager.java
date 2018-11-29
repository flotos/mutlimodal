import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;
import java.awt.Event;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PaletteManager {

    private STATE_LIST state;
    private String shape;
    private String couleur;
    
    public static void main(String[] args) {
    	
	}
    
    private void choisirRectangle() {
    	switch(state) {
    		case PALETTE_VIDE:
    	    	state = STATE_LIST.ATTENTE_SPECIFICATION;
    	    	shape = "rectangle";
    			break;
    		case ATTENTE_SPECIFICATION:
    			break;
    		case ATTENTE_ORDRE:
    	    	state = STATE_LIST.ATTENTE_SPECIFICATION;
    	    	shape = "rectangle";
    			break;
    		case PROCESSUS_SUPPRESSION:
    			break;
    		case PROCESSUS_DEPLACEMENT:
    			break;
    	}
    }
    
    private void choisirEllipse() {
    	switch(state) {
			case PALETTE_VIDE:
		    	state = STATE_LIST.ATTENTE_SPECIFICATION;
		    	shape = "ellipse";
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
		    	state = STATE_LIST.ATTENTE_SPECIFICATION;
		    	shape = "ellipse";
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
    	}
    }
    
    private void designerPosition(int x, int y) {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				state = STATE_LIST.ATTENTE_ORDRE;
				if(shape == "rectangle") {
					// Créer un rectangle
				} else if(shape == "ellipse") {
					// Créer une ellipse
				}
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
    	}
    }
    
    private void designerCouleur(String couleur) {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				state = STATE_LIST.ATTENTE_SPECIFICATION;
				this.couleur = couleur;
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
    	}
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