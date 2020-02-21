package ts;
import java.io.*;
import java.util.*;
import sa.*;

public class Sa2ts extends SaDepthFirstVisitor <Void>{
    private Ts tableGlobale;
    private Ts tableLocaleCourante;
    enum Context {LOCAL, PARAM, GLOBAL};
    private Sa2ts.Context context;


    public Sa2ts()
    {
        tableGlobale = new Ts();
        tableLocaleCourante = null;
        context = Context.GLOBAL;
    }


    // DEC -> var id
    public void visit(SaDecVar node)
    {

        defaultIn(node);
       if(context == Context.GLOBAL) {
           if (tableGlobale.getVar(node.getNom()) == null) {
               tableGlobale.addVar(node.getNom());
           }
           else {
               System.err.println("attention il existe déjà une variable globale qui a pour nom " + node.getNom());
               System.exit(1);
           }
       }
        else{
            if(tableLocaleCourante.getVar(node.getNom()) != null) {
                System.err.println("attention il existe déjà une variable ou un parametre qui a pour nom " + node.getNom());
                System.exit(1);
            }
            if(context = Context.PARAM) {
                tableLocaleCourante.addParam(node.getNom());
            }
            else {
                tableLocaleCourante.addVar(node.getNom(), 1);
            }
        }


        defaultOut(node);
        return null;
    }
    // DEC -> var id taille
    public void visit(SaDecTab node){
        defaultIn(node);
       /* if(tableGlobale.getTableLocale(node.getNom()) == null){
            tableLocaleCourante = new Ts();
            tableGlobale.
        }
        else {
            System.error.println("attention la fonction " + node.getNom() + "est déjà définie");
            System.exit(1);

        }*/
        defaultOut(node);
        return null;
    }
    // DEC -> fct id LDEC LDEC LINST
    public void visit(SaDecFonc node)
    {
        defaultIn(node);
        if(tableGlobale.getFct(node.getNom()) == null){
            tableLocaleCourante = new Ts();
            int nbParam = node.getParametres().length();
            tableGlobale.addFct(node.getNom(), nbParam, tableLocaleCourante, node);
        }
        else {
            System.error.println("attention la fonction " + node.getNom() + "est déjà définie");
            System.exit(1);

        }

        context = Context.PARAM;
        if(node.getParametres() != null) node.getParametres().accept(this);
        context = Context.LOCAL;
        if(node.getVariable() != null) node.getVariable().accept(this);
        node.getCorps().accept(this);
        context = Context.GLOBAL;
        defaultOut(node);
        return null;
    }
    public void visit(SaVarSimple node)
    {
        defaultIn(node);
        defaultOut(node);
        return null;
    }
    public void visit(SaVarIndicee node)
    {
        defaultIn(node);
        node.getIndice().accept(this);
        defaultOut(node);
        return null;
    }

    public void visit(SaAppel node)
    {
        defaultIn(node);
        if(node.getArguments() != null) node.getArguments().accept(this);
        defaultOut(node);
        return null;
    }



}