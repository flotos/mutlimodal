import java.util.ArrayList;
import java.util.List;

import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;

public class PaletteCommunication {

	private Ivy bus;
	private Boolean allHaveResponded;
	private int hasResponded;
	
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
	
	public void supprimerObjet(int x, int y, String couleur, char shapeType) {
		Shape shapeToMove = getShapeBelowCursor(x, y, couleur, shapeType);
		
		try {
			bus.sendMsg("Palette:SupprimerObjet nom=" + shapeToMove.getName() + " x=" + x + " y=" + y);
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}
	
	public void deplacerObjet(Shape shapeToMove, int x, int y) {		
		try {
			bus.sendMsg("Palette:DeplacerObjetAbsolu nom=" + shapeToMove.getName() + " x=" + x + " y=" + y);
		} catch (IvyException e) {
			e.printStackTrace();
		}
	}

	public Shape getShapeBelowCursor(int x, int y, String couleur, char shapeType) {

		List<Shape> listShape = new ArrayList<>();
		List<Shape> listShapeWithColor = new ArrayList<>();
		String rgbaColor = couleur == "rouge" ? "255:0:0" : "0:0:255";
		allHaveResponded = false;
		hasResponded = 0;
		
		// On demande les infos des points sous le curseur
		try {
			bus.sendMsg("Palette:TesterPoint x=" + x + " y=" + y);
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
			
			// Should have a timeout
			while(!allHaveResponded) {
			}
		} catch (IvyException e) {
			e.printStackTrace();
		}
		
		if(couleur.isEmpty()) {
			for(Shape shape : listShape) {
				if(shape.getName().charAt(0) == shapeType) {
					return shape;
				}
			}
		} else {
			for(Shape shape : listShape) {
				// Demander les infos de chaque points, puis en trier ceux qui ont la forme demandée
				try {
					bus.sendMsg("Palette:TesterPoint nom=" + shape.getName());
				} catch (IvyException e) {
					e.printStackTrace();
				}
				try {
					bus.bindMsg("Palette:Info nom=(.*) x=(.*) y=(.*) longueur=(.*) hauteur=(.*) couleurFond=(.*) couleurContour=(.*)", new IvyMessageListener() {
						public void receive(IvyClient client, String[] args) {
							String stringColor = args[6].equals("255:0:0") ? "rouge" : "bleu";
							listShapeWithColor.add( new Shape(args[0], stringColor, Integer.parseInt(args[1]), Integer.parseInt(args[2])));
							hasResponded++;
						}
					});
					
					// Should have a timeout
					while(hasResponded< listShape.size()) {
					}
				} catch (IvyException e) {
					e.printStackTrace();
				}
			}
			for(Shape shape : listShapeWithColor) {
				if(shape.getName().charAt(0) == shapeType) {
					return shape;
				}
			}
		}
		return null;
	}
}
