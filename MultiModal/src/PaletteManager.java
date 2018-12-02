public class PaletteManager {

	private PaletteCommunication paletteCommunication;
	
    private STATE_LIST state;
    private String currentShape;
    private String currentColor;
    private int nbShapes;
    private int[] cursorPosition = {0, 0};
    private Shape shapeToMove;
	
	public PaletteManager(PaletteCommunication paletteCommunication) {
		this.state = STATE_LIST.PALETTE_VIDE;
		this.paletteCommunication = paletteCommunication;
		this.currentColor = "rouge";
		this.nbShapes = 0;
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
				state = STATE_LIST.ATTENTE_ORDRE;
				paletteCommunication.supprimerObjet(cursorPosition[0], cursorPosition[1], currentColor, 'E');
				nbShapes--;
				break;
			case PROCESSUS_DEPLACEMENT:
				state = STATE_LIST.ATTENTE_ORDRE;
				// Le dernier paramètre devrais être la première lettre du type de forme à bouger, mais notre automate ne le prends pas en compte.
				this.shapeToMove = paletteCommunication.getShapeBelowCursor(cursorPosition[0], cursorPosition[1], currentColor, 'E');
				break;
			case OBJET_A_DEPLACER:
				state = STATE_LIST.ATTENTE_ORDRE;
				// Déplacement
				paletteCommunication.deplacerObjet(shapeToMove, cursorPosition[0], cursorPosition[1]);
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
				currentColor = null;
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
				if(nbShapes == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(nbShapes > 1) {
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
				if(nbShapes == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(nbShapes > 1) {
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
				currentColor = null;
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