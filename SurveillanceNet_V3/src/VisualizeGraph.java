
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

// jung library
import edu.uci.ics.jung.algorithms.layout.*;
import edu.uci.ics.jung.algorithms.shortestpath.DistanceStatistics;
import edu.uci.ics.jung.graph.*;
import edu.uci.ics.jung.visualization.*;
import edu.uci.ics.jung.visualization.decorators.ToStringLabeller;
import edu.uci.ics.jung.visualization.renderers.Renderer.VertexLabel;



public class VisualizeGraph  extends JFrame{
	
	private JPanel panel;
	private JPanel gpanel; // graph panel
	private JTextField diameterField;
	private Graph<String, String> graph; // the graph that shows network
	private ArrayList<Suspect> vertices = new ArrayList<>();
	private ArrayList<String> edges = new ArrayList<>();


	public VisualizeGraph(Registry aregistry) {
		Registry registry = aregistry;
		panel = new JPanel();
		gpanel = new JPanel();
		diameterField = new JTextField();
		graph = new UndirectedSparseMultigraph<>();
		vertices = registry.getAllSusp(); // suspects will be vertices on graph
		
		
		// add vertices
		  for(Suspect suspect: vertices) {
			  graph.addVertex(suspect.getCodeName());
		  }
		  
		 //  add edges  , must be undirected and unique (A - B equals B - A ) so we add the edge once.
		  for(int i=0; i<vertices.size(); i++) {
			    for(Suspect s: vertices.get(i).getPartners()) {
			        String edge = vertices.get(i).getCityOfActivity() + "-" + s.getCityOfActivity();
			        String reverseEdge = s.getCityOfActivity() + "-" + vertices.get(i).getCityOfActivity();
			        if(!edges.contains(edge)&& !edges.contains(reverseEdge)) {
			            edges.add(edge);
			            graph.addEdge(edge,s.getCodeName(),vertices.get(i).getCodeName());
			        }
			    }
			}
		  
		  
		  // layout for the graph , can also use FRLayout , KKLayout , SpringLayout 
		Layout<String, String> layout = new CircleLayout<String ,String>(graph);
		layout.setSize(new Dimension(400,300));
		
	
		
		// create vizualization 
		BasicVisualizationServer<String, String> vv = new BasicVisualizationServer<>(layout);
		
		vv.setPreferredSize(new Dimension(450,450));
		
		//appear the label(code name) of vertices and we chose position to be .
		vv.getRenderContext().setVertexLabelTransformer(new ToStringLabeller<>());
		vv.getRenderer().getVertexLabelRenderer().setPosition(VertexLabel.Position.SE);
		
		// label of edges shows connection between cities that suspects are active
		
		//vv.getRenderContext().setEdgeLabelTransformer(new ToStringLabeller<>());
		
		
		
		
		
		// diameter method of jung library
		double diameter = DistanceStatistics.diameter(graph);
		diameterField.setText("Diameter = " + diameter);
		diameterField.setEditable(false);
		diameterField.setBackground(Color.WHITE);
	   
	
	    
	    gpanel.add(vv);
        
	    panel.setLayout(new BorderLayout());
		panel.add(gpanel,BorderLayout.CENTER);
		panel.add(diameterField , BorderLayout.SOUTH);

	
	    this.setSize(500,500);
		this.setResizable(false);
		this.setVisible(true);
		this.setContentPane(panel);
		this.setTitle("Suspects Network");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		
	}



 
}


