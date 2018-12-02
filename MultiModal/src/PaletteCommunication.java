import java.util.ArrayList;
import java.util.List;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;

public class PaletteCommunication {

	private Ivy bus;
	private Boolean allHaveResponded;
	
	public PaletteCommunication() {
		bus = new Ivy("MyIvyAgent", "monMessage", null);
		try {
			bus.start("127.255.255.255:2010");
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}
	
	public void creerRectangle(int x, int y, String couleur) {
		
		String rgbaColor = couleur == "rouge" ? "255:0:0" : "0:0:255";
		
		try {
			bus.sendMsg("Palette:CreerRectangle x=" + x + " y=" + y + " couleurFond:255:255:255:0 couleurContour="+rgbaColor);
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	public void creerEllipse(int x, int y, String couleur) {
		
		String rgbaColor = couleur == "rouge" ? "255:0:0" : "0:0:255";
		
		try {
			bus.sendMsg("Palette:CreerEllipse x=" + x + " y=" + y + " couleurFond:255:255:255:0 couleurContour="+rgbaColor);
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	public void deplacerObjet(int x, int y, String couleur, String shapeType) {
		
		String rgbaColor = couleur == "rouge" ? "255:0:0" : "0:0:255";
		allHaveResponded = false;
		// date de timeout
		List<Shape> listShape = new ArrayList<>();
		
		
		// On demande les infos des points sous le curseur
		try {
			bus.sendMsg("Palette:TesterPoint x="+x+"y="+y);
		} catch (IvyException e) {
			e.printStackTrace();
		}
		
		// On écoute la liste des objets nous répondant leur nom et on les stock
		try {
			bus.bindMsg("Palette:ResultatTesterPoint x=(.*) y=(.*) nom=(.*)", new IvyMessageListener() {
				public void receive(IvyClient client, String[] args) {
					listShape.add(new Shape(args[2], Integer.parseInt(args[0]), Integer.parseInt(args[1])));
				}
			});
		} catch (IvyException e) {
			e.printStackTrace();
		}
		
		// On Attends qu'ils aient tous finis de répondre
		try {
			bus.bindMsg("Palette:FinTesterPoint", new IvyMessageListener() {
				public void receive(IvyClient client, String[] args) {
					allHaveResponded = true;
				}
			});
			while(!allHaveResponded) {
			}
		} catch (IvyException e) {
			e.printStackTrace();
		}
		
		
		
		
//		if(!couleur.isEmpty()) {
//
//			// On demande la liste des objets d'une couleur spécifique
//			try {
//				bus.sendMsg("Palette:TesterCouleurContour couleur="+rgbaColor);
//			} catch (IvyException e) {
//				e.printStackTrace();
//			}
//
//			// On écoute la liste des objets nous répondant leur couleur
//			try {
//				bus.bindMsg("Palette:ResultatCouleurContour couleur=" + rgbaColor + " nom=(.*)", new IvyMessageListener() {
//					public void receive(IvyClient client, String[] args) {
//						
//					}
//				});
//			} catch (IvyException e) {
//				e.printStackTrace();
//			}
//		}
	}
}
