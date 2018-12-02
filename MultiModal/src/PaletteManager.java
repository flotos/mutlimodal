public class PaletteManager {

	private PaletteCommunication paletteCommunication;
	
    private STATE_LIST state;
    private String shape;
    private String couleur;
    private int[] shapes;
    private int[] position;
	
	public PaletteManager(PaletteCommunication paletteCommunication) {
		this.state = STATE_LIST.PALETTE_VIDE;
		this.paletteCommunication = paletteCommunication;
	}
	
	public STATE_LIST getState() {
		return this.state;
	}
    
	public void choisirRectangle() {
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
			case OBJET_A_DEPLACER:
				break;
    	}
    }
    
    void choisirEllipse() {
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
				if(shape == "rectangle") {
					// Créer un rectangle
					paletteCommunication.creerRectangle(position[0], position[1], couleur);
				} else if(shape == "ellipse") {
					// Créer une ellipse
					paletteCommunication.creerEllipse(position[0], position[1], couleur);
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
    
    public void designerCouleur(String couleur) {
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
				if(shapes.length == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(shapes.length > 1) {
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
				if(shapes.length == 1) {
					state = STATE_LIST.PALETTE_VIDE;
				}
				if(shapes.length > 1) {
					state = STATE_LIST.ATTENTE_ORDRE;
				}
				// supprimer objet avec couleur
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
    			position[0] = x;
    			position[1] = y;
    			break;
    		case ATTENTE_SPECIFICATION:
    			position[0] = x;
    			position[1] = y;
    			break;
    		case ATTENTE_ORDRE:
    			break;
    		case PROCESSUS_SUPPRESSION:
    			position[0] = x;
    			position[1] = y;
    			break;
    		case PROCESSUS_DEPLACEMENT:
    			position[0] = x;
    			position[1] = y;
    			break;
			case OBJET_A_DEPLACER:
				break;
    	}
    }   
}