import sc.analysis.*;
import sc.parser.*;
import sc.lexer.*;
import sc.node.*;
import java.io.*;
import sa.*;

public class Sc2Sa extends DepthFirstAdapter {
    private SaNode returnValue;
    private int indentation = 0;


    public void defaultIn(@SuppressWarnings("unused") Node node)
    {
	for(int i = 0; i < this.indentation; i++){System.out.print(" ");}
	this.indentation++;
	System.out.println("<" + node.getClass().getSimpleName() + ">");
    }

    public void defaultOut(@SuppressWarnings("unused") Node node)
    {
	this.indentation--;
	for(int i = 0; i < this.indentation; i++){System.out.print(" ");}
	System.out.println("</" + node.getClass().getSimpleName() + ">");
    }

    
    public SaNode getRoot(){
	return returnValue;
    }
    
    public void caseAE31E3(AE31E3 node)
    {
	SaExp op1 = null;
	SaExp op2 = null;
        inAE31E3(node);
	System.out.println("---------> addition");
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
	    op1 = (SaExp) this.returnValue;
        }
        if(node.getPlus() != null)
        {
            node.getPlus().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
	    op2 = (SaExp) this.returnValue;
        }
	this.returnValue = new SaExpAdd(op1, op2);
	outAE31E3(node);
    }
}
