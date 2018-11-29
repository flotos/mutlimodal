import fr.dgac.ivy.Ivy;
import fr.dgac.ivy.IvyClient;
import fr.dgac.ivy.IvyException;
import fr.dgac.ivy.IvyMessageListener;

public class Multimodal {

	public static void sdfg(String[] args) throws IvyException {
		// TODO Auto-generated method stub
		Ivy bus = new Ivy("MyIvyAgent", "monMessage", null);
		bus.start("127.255.255.255:2010");
		bus.bindMsg("^MyIvyAgent Send=(.*)", new IvyMessageListener() {
			public void receive(IvyClient client, String[] args) {
				System.out.println(client.toString() + args[0]);
			}
		});
	}

}
