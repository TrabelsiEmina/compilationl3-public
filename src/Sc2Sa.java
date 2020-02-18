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


    public void caseAE32E3(AE32E3 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE32E3(node);
        System.out.println("---------> soustraction");
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getMoins() != null)
        {
            node.getMoins().apply(this);
        }
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
            op2= (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpSub(op1, op2);
        outAE32E3(node);
    }



    
    public void caseAE41E4(AE41E4 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE41E4(node);
        System.out.println("---------> mutliplication");
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getMult() != null)
        {
            node.getMult().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpMult(op1, op2);
        outAE41E4(node);
    }



    public void caseAE42E4(AE42E4 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE42E4(node);
        System.out.println("---------> division");
        if(node.getE4() != null)
        {
            node.getE4().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getDiv() != null)
        {
            node.getDiv().apply(this);
        }
        if(node.getE5() != null)
        {
            node.getE5().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpDiv(op1, op2);
        outAE42E4(node);
    }


    public void caseAE1E(AE1E node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE1E(node);
        System.out.println("---------> ou");
        if(node.getE() != null)
        {
            node.getE().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getOu() != null)
        {
            node.getOu().apply(this);
        }
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpOr(op1, op2);
        outAE1E(node);
    }




    public void caseAE11E1(AE11E1 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE11E1(node);
        System.out.println("---------> et");
        if(node.getE1() != null)
        {
            node.getE1().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getEt() != null)
        {
            node.getEt().apply(this);
        }
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpAnd(op1, op2);
        outAE11E1(node);
    }

    public void caseAE21E2(AE21E2 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE21E2(node);
        System.out.println("---------> egal");
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getEgal() != null)
        {
            node.getEgal().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpEqual(op1, op2);
        outAE21E2(node);
    }
    
    public void caseAE22E2(AE22E2 node)
    {
        SaExp op1 = null;
	    SaExp op2 = null;
        inAE22E2(node);
        System.out.println("---------> inf");
        if(node.getE2() != null)
        {
            node.getE2().apply(this);
            op1 = (SaExp) this.returnValue;
        }
        if(node.getInf() != null)
        {
            node.getInf().apply(this);
        }
        if(node.getE3() != null)
        {
            node.getE3().apply(this);
            op2 = (SaExp) this.returnValue;
        }
        this.returnValue = new SaExpInf(op1, op2);
        outAE22E2(node);
    }
    public void caseAItq(AItq node)
    {
        SaExp exp = null;
	    SaInst ib = null;
        System.out.println("---------> tant que");
        inAItq(node);
        if(node.getTq() != null)
        {
            node.getTq().apply(this);
        }
        if(node.getE() != null)
        {
            node.getE().apply(this);
            exp = (SaExp) this.returnValue;
        }
        if(node.getFr() != null)
        {
            node.getFr().apply(this);
        }
        if(node.getIbloc() != null)
        {
            node.getIbloc().apply(this);
            ib = (SaInst) this.returnValue;
        }
        this.returnValue = new SaInstTantQue(exp, ib);
        outAItq(node);
    }
}
