public class PaletteManager {

	private PaletteCommunication paletteCommunication;
	
    private STATE_LIST state;
    private String currentShape;
    private String currentColor;
    private int[] currentShapes;
    private int[] cursorPosition = {0, 0};
	
	public PaletteManager(PaletteCommunication paletteCommunication) {
		this.state = STATE_LIST.PALETTE_VIDE;
		this.paletteCommunication = paletteCommunication;
		this.currentColor = "rouge";
	}
	
	public STATE_LIST getState() {
		return this.state;
	}
    
	public void choisirRectangle() {
    	switch(state) {
    		case PALETTE_VIDE:
    	    	state = STATE_LIST.ATTENTE_SPECIFICATION;
    	    	currentShape = "rectangle";
    			break;
    		case ATTENTE_SPECIFICATION:
    			break;
    		case ATTENTE_ORDRE:
    	    	state = STATE_LIST.ATTENTE_SPECIFICATION;
    	    	currentShape = "rectangle";
    			break;
    		case PROCESSUS_SUPPRESSION:
    			break;
    		case PROCESSUS_DEPLACEMENT:
    			break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    void choisirEllipse() {
    	switch(state) {
			case PALETTE_VIDE:
		    	state = STATE_LIST.ATTENTE_SPECIFICATION;
		    	currentShape = "ellipse";
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
		    	state = STATE_LIST.ATTENTE_SPECIFICATION;
		    	currentShape = "ellipse";
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void designerPosition() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				state = STATE_LIST.ATTENTE_ORDRE;
				if(currentShape == "rectangle") {
					// Créer un rectangle
					paletteCommunication.creerRectangle(cursorPosition[0], cursorPosition[1], currentColor);
				} else if(currentShape == "ellipse") {
					// Créer une ellipse
					paletteCommunication.creerEllipse(cursorPosition[0], cursorPosition[1], currentColor);
				}
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				state = STATE_LIST.ATTENTE_ORDRE;
				// Déplacement
				break;
    	}
    }
    
    public void designerCouleur(String currentColor) {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				state = STATE_LIST.ATTENTE_SPECIFICATION;
				this.currentColor = currentColor;
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void choisirActionSupprimer() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
				state = STATE_LIST.PROCESSUS_SUPPRESSION;
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void choisirObjetASupprimer() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				if(currentShapes.length == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(currentShapes.length > 1) {
					state = STATE_LIST.ATTENTE_ORDRE;
				}
				// supprimer objet
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void choisirObjetASupprimerAvecCouleur() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				if(currentShapes.length == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(currentShapes.length > 1) {
					state = STATE_LIST.ATTENTE_ORDRE;
				}
				// supprimer objet avec currentColor
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void choisirActionDeplacer() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
				state = STATE_LIST.PROCESSUS_DEPLACEMENT;
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    public void choisirObjetADeplacer() {
    	switch(state) {
			case PALETTE_VIDE:
				break;
			case ATTENTE_SPECIFICATION:
				break;
			case ATTENTE_ORDRE:
				break;
			case PROCESSUS_SUPPRESSION:
				break;
			case PROCESSUS_DEPLACEMENT:
				state = STATE_LIST.OBJET_A_DEPLACER;
				break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
	public void setCurrentPosition(int x, int y) {
    	switch(state) {
    		case PALETTE_VIDE:
    			cursorPosition[0] = x;
    			cursorPosition[1] = y;
    			break;
    		case ATTENTE_SPECIFICATION:
    			cursorPosition[0] = x;
    			cursorPosition[1] = y;
    			break;
    		case ATTENTE_ORDRE:
    			break;
    		case PROCESSUS_SUPPRESSION:
    			cursorPosition[0] = x;
    			cursorPosition[1] = y;
    			break;
    		case PROCESSUS_DEPLACEMENT:
    			cursorPosition[0] = x;
    			cursorPosition[1] = y;
    			break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }   
}