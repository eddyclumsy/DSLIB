package edu.uoc.ds.adt.nonlinear.graphs;

import edu.uoc.ds.adt.modelstest.Gender;
import edu.uoc.ds.adt.modelstest.Muppet;
import edu.uoc.ds.traversal.Iterator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class SocialNetworkWithDirectedGraphTest {
    DirectedGraph<Muppet,String > graph;

    @Before
    public void setUp() {
        graph = new DirectedGraphImpl<>();
    }

    @Test
    public void test1() {
        Muppet elmo = new Muppet("ELM1980", "Elmo", Gender.Male);
        Muppet piggy = new Muppet("PIG1974", "Miss Piggy", Gender.Female);
        Muppet kermit = new Muppet("KERM1955", "Kermit the Frog", Gender.Female);
        Muppet rowlf = new Muppet("Rowlf1962", "Rowlf the Dog", Gender.Female);

        Vertex<Muppet> vElmo = graph.newVertex(elmo);
        Vertex<Muppet> vPiggy = graph.newVertex(piggy);
        Vertex<Muppet> vKermit = graph.newVertex(kermit);
        Vertex<Muppet> vRowlf = graph.newVertex(rowlf);

        //Piggy is follower of Elmo
        Edge<String, Muppet> edge1a = graph.newEdge(vElmo, vPiggy);
        edge1a.setLabel("follower");
        //Elmo is followed of Piggy
        Edge<String, Muppet> edge1b = graph.newEdge(vPiggy, vElmo);
        edge1b.setLabel("followed");

        //Kermit is follower of Elmo
        Edge<String, Muppet> edge2a = graph.newEdge(vElmo, vKermit);
        edge2a.setLabel("follower");
        //Elmo is followed of Kermit
        Edge<String, Muppet> edge2b = graph.newEdge(vKermit, vElmo);
        edge2b.setLabel("followed");

        // Rowlf is follower of Elmo
        Edge<String, Muppet> edge3a = graph.newEdge(vElmo, vRowlf);
        edge3a.setLabel("follower");
        // Elmo is followed of Rowlf
        Edge<String, Muppet> edge3b = graph.newEdge(vRowlf, vElmo);
        edge3b.setLabel("followed");

        // Kermit is follower of Piggy
        Edge<String, Muppet> edge4a = graph.newEdge(vPiggy, vKermit);
        edge4a.setLabel("follower");
        // Piggy is followed of Kermit
        Edge<String, Muppet> edge4b = graph.newEdge(vKermit, vPiggy);
        edge4b.setLabel("followed");



        // ELMO
        DirectedVertexImpl<Muppet, String> _vElmo = (DirectedVertexImpl<Muppet, String>) graph.getVertex(elmo);

        Iterator<Edge<String, Muppet>> it = _vElmo.edges();

        DirectedEdge<String, Muppet> _edge1 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("follower", _edge1.getLabel());
        Assert.assertEquals("Elmo", _edge1.getVertexSrc().getValue().name());
        Assert.assertEquals("Miss Piggy", _edge1.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge2 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("follower", _edge2.getLabel());
        Assert.assertEquals("Elmo", _edge2.getVertexSrc().getValue().name());
        Assert.assertEquals("Kermit the Frog", _edge2.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge3 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("follower", _edge3.getLabel());
        Assert.assertEquals("Elmo", _edge3.getVertexSrc().getValue().name());
        Assert.assertEquals("Rowlf the Dog", _edge3.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge4 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("followed", _edge4.getLabel());
        Assert.assertEquals("Miss Piggy", _edge4.getVertexSrc().getValue().name());
        Assert.assertEquals("Elmo", _edge4.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge5 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("followed", _edge5.getLabel());
        Assert.assertEquals("Kermit the Frog", _edge5.getVertexSrc().getValue().name());
        Assert.assertEquals("Elmo", _edge5.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge6 = (DirectedEdge<String, Muppet>)it.next();
        Assert.assertEquals("followed", _edge6.getLabel());
        Assert.assertEquals("Rowlf the Dog", _edge6.getVertexSrc().getValue().name());
        Assert.assertEquals("Elmo", _edge6.getVertexDst().getValue().name());

        // MISS PIGGY
        DirectedVertexImpl<Muppet, String> _vPiggy = (DirectedVertexImpl<Muppet, String>) graph.getVertex(piggy);

        Iterator<Edge<String, Muppet>> it2 = _vPiggy.edges();
        DirectedEdge<String, Muppet> _edge7 = (DirectedEdge<String, Muppet>)it2.next();
        Assert.assertEquals("followed", _edge7.getLabel());
        Assert.assertEquals("Miss Piggy", _edge7.getVertexSrc().getValue().name());
        Assert.assertEquals("Elmo", _edge7.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge8 = (DirectedEdge<String, Muppet>)it2.next();
        Assert.assertEquals("follower", _edge8.getLabel());
        Assert.assertEquals("Miss Piggy", _edge8.getVertexSrc().getValue().name());
        Assert.assertEquals("Kermit the Frog", _edge8.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge9 = (DirectedEdge<String, Muppet>)it2.next();
        Assert.assertEquals("follower", _edge9.getLabel());
        Assert.assertEquals("Elmo", _edge9.getVertexSrc().getValue().name());
        Assert.assertEquals("Miss Piggy", _edge9.getVertexDst().getValue().name());

        DirectedEdge<String, Muppet> _edge10 = (DirectedEdge<String, Muppet>)it2.next();
        Assert.assertEquals("followed", _edge10.getLabel());
        Assert.assertEquals("Kermit the Frog", _edge10.getVertexSrc().getValue().name());
        Assert.assertEquals("Miss Piggy", _edge10.getVertexDst().getValue().name());
    }
}
