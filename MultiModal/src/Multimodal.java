import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;

public class Multimodal {
	
	public static void main(String[] args) throws IvyException {

		PaletteCommunication paletteCommunication = new PaletteCommunication();
		PaletteManager paletteManager = new PaletteManager(paletteCommunication);
		
		Ivy bus = new Ivy("MyIvyAgent", "monMessage", null);
		bus.start("127.255.255.255:2010");
		bus.bindMsg("Palette:MouseMoved x=(.*) y=(.*)", new IvyMessageListener() {
			public void receive(IvyClient client, String[] args) {
				paletteManager.setCurrentPosition(Integer.parseInt(args[1]), Integer.parseInt(args[2]));
			}
		});
		bus.bindMsg("MyIvyAgent Send=(.*)", new IvyMessageListener() {
			public void receive(IvyClient client, String[] args) {
				switch(args[0]) {
				// Gestual
					case "ellipse":
						paletteManager.choisirEllipse();
						break;
					case "rectangle":
						paletteManager.choisirRectangle();
						break;
						
				// Vocal
					case "deplacer":
						paletteManager.choisirActionDeplacer();
						break;
					case "supprimer":
						paletteManager.choisirActionSupprimer();
						break;
					case "la":
					case "ici":
						paletteManager.designerPosition();
						break;
					case "rouge":
						paletteManager.designerCouleur("rouge");
						break;
					case "bleu":
						paletteManager.designerCouleur("bleu");
						break;
				}
				System.out.println("New state : " + paletteManager.getState());
			}
		});
	}
}
