package c3a;

import java.io.*;
import java.util.*;
import sa.*;
import c3a.*;


public class Sa2c3a extends SaDepthFirstVisitor <C3aOperand> {
    private C3a c3a;

    public Sa2c3a(SaNode root){
        c3a = new C3a();
        root.accept(this);
    }
    public  C3aOperand visit(SaProg node)
    {
        defaultIn(node);
        if(node.getVariables() != null)
            node.getVariables().accept(this);
        if(node.getFonctions() != null)
            node.getFonctions().accept(this);
        defaultOut(node);
        return null;


    }

    // DEC -> var id taille
    public  C3aOperand visit(SaDecTab node){
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaExp node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    // EXP -> entier
    public  C3aOperand visit(SaExpInt node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }
    public  C3aOperand visit(SaExpVar node)
    {
        defaultIn(node);
        node.getVar().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaInstEcriture node)
    {
        defaultIn(node);
        node.getArg().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaInstTantQue node)
    {
        defaultIn(node);
        node.getTest().accept(this);
        node.getFaire().accept(this);
        defaultOut(node);
        return null;
    }
    public  C3aOperand visit(SaLInst node)
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null) node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    // DEC -> fct id LDEC LDEC LINST
    public  C3aOperand visit(SaDecFonc node)
    {
        defaultIn(node);
        if(node.getParametres() != null) node.getParametres().accept(this);
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);
        defaultOut(node);
        return null;
    }

    // DEC -> var id
    public  C3aOperand visit(SaDecVar node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaInstAffect node)
    {
        defaultIn(node);
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        defaultOut(node);
        return null;
    }

    // LDEC -> DEC LDEC
    // LDEC -> null
    public  C3aOperand visit(SaLDec node)
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null) node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaVarSimple node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaAppel node)
    {
        defaultIn(node);
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaExpAppel node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    // EXP -> add EXP EXP
    public C3aOperand visit(SaExpAdd node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = newTemp();

        result = SaExpAdd(op1 ,op2);

        ajouteInst(new C3ainstAdd(op1,op2,result," "));
        defaultOut(node);
        return result;
    }

    // EXP -> sub EXP EXP
    public  C3aOperand visit(SaExpSub node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = node.newTemp();

        result= SaExpAdd(op1 ,op2);
        ajouteInst(new C3ainstSub(op1,op2,result," "));

        defaultOut(node);
        return result;
    }

    // EXP -> mult EXP EXP
    public  C3aOperand visit(SaExpMult node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = node.newTemp();

        result= SaExpAdd(op1 ,op2);
        ajouteInst(new C3ainstMult(op1,op2,result," "));

        defaultOut(node);
        return result;
    }

    // EXP -> div EXP EXP
    public  C3aOperand visit(SaExpDiv node)
    {
        defaultIn(node);
        C3aOperand op1 = node.getOp1().accept(this);
        C3aOperand op2 = node.getOp2().accept(this);
        C3aOperand result = node.newTemp();

        result= SaExpAdd(op1 ,op2);
        ajouteInst(new C3ainstDiv(op1,op2,result," "));

        defaultOut(node);
        return result;
    }

    // EXP -> inf EXP EXP
    public  C3aOperand visit(SaExpInf node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    // EXP -> eq EXP EXP
    public  C3aOperand visit(SaExpEqual node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    // EXP -> and EXP EXP
    public  C3aOperand visit(SaExpAnd node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }


    // EXP -> or EXP EXP
    public  C3aOperand visit(SaExpOr node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        node.getOp2().accept(this);
        defaultOut(node);
        return null;
    }

    // EXP -> not EXP
    public  C3aOperand visit(SaExpNot node)
    {
        defaultIn(node);
        node.getOp1().accept(this);
        defaultOut(node);
        return null;
    }


    public  C3aOperand visit(SaExpLire node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaInstBloc node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }

    public  C3aOperand visit(SaInstSi node)
    {
        defaultIn(node);
        node.getTest().accept(this);
        node.getAlors().accept(this);
        if(node.getSinon() != null) node.getSinon().accept(this);
        defaultOut(node);
        return null;
    }

    // INST -> ret EXP
    public  C3aOperand visit(SaInstRetour node)
    {
        defaultIn(node);
        node.getVal().accept(this);
        defaultOut(node);
        return null;
    }


    public  C3aOperand visit(SaLExp node)
    {
        defaultIn(node);
        node.getTete().accept(this);
        if(node.getQueue() != null)
            node.getQueue().accept(this);
        defaultOut(node);
        return null;
    }
    public  C3aOperand visit(SaVarIndicee node)
    {
        defaultIn(node);
        node.getIndice().accept(this);
        defaultOut(node);
        return null;
    }

}