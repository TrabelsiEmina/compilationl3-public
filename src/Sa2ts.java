//package ts

import sa.*;
import ts.*;

public class Sa2ts extends SaDepthFirstVisitor <Void>{
    private SaNode saRootNode;
    private Ts tableGlobale;
    private Ts tableLocaleCourante;

    public Sa2ts(SaNode saRootNode){
        this.saRootNode = saRootNode;
        this.tableGlobale = new Ts();
        this.tableLocaleCourante = null;
    }

    public Ts getTableGlobale() { return this.tableGlobale; }

    //    Void visit(SaDecVar node)
    // public Void visit(SaDecVar node) {
    //     String nom = node.getNom();
    //     int taille = 1;
    //     this.defaultIn(node);
    //     System.out.println("-------------->ts-- variables ");
    //     this.tableGlobale.addVar(nom, taille);
    //     this.defaultOut(node);
    //     return null;
    // }

        public Void visit(SaDecVar node) {
            String nom = node.getNom();
            int taille = 1;
            defaultIn(node);
            System.out.println("-------------->ts--declaration variables ");
            if (this.tableLocaleCourante == null)
                this.tableLocaleCourante = this.tableGlobale;
            if (!this.tableLocaleCourante.variables.containsKey(nom))
                node.tsItem = this.tableLocaleCourante.addVar(nom, taille);
            defaultOut(node);
            System.out.println(this.tableGlobale);
            return null;
        }

    //    Void visit(SaDecTab node)

        public Void visit(SaDecTab node){
            String nom = node.getNom();
            int taille = node.getTaille();
            defaultIn(node);
            System.out.println("-------------->ts--declaration tableau ");
            this.tableLocaleCourante = this.tableGlobale;
            if (!this.tableLocaleCourante.variables.containsKey(nom))
                node.tsItem = this.tableLocaleCourante.addVar(nom, taille);
            defaultOut(node);
            System.out.println(this.tableGlobale);
            return null;
        }

        //    Void visit(SaDecFonc node)
        public Void visit(SaDecFonc node) {
            String nom = node.getNom();
            int nbArgs = 0;
            Ts ts = new Ts();
            defaultIn(node);
            System.out.println("-------------->ts-- declaration fonction");
            this.tableLocaleCourante = this.tableGlobale;
            if (!this.tableLocaleCourante.variables.containsKey(nom))
                node.tsItem = this.tableGlobale.addFct(nom, nbArgs, ts, node);
            defaultOut(node);
            return null;
        }
// //////////////////////////////////////////////////////////

    //    //    Void visit(SaVarSimple node)
        public Void visit(SaVarSimple node){
            String nom = node.getNom();
            defaultIn(node);
            System.out.println("-------------->ts-- appel variable simple ");
            if (this.tableLocaleCourante.variables.containsKey(nom))
                node.tsItem = this.tableLocaleCourante.getVar(nom);
            if (this.tableGlobale.variables.containsKey(nom))
                node.tsItem = this.tableLocaleCourante.getVar(nom);
            defaultOut(node);
            System.out.println(this.tableGlobale);
            return null;
        }

        //    Void visit(SaVarIndicee node)
        public Void visit(SaVarIndicee node){
            String nom = node.getNom();
            SaExp indice = node.getIndice();
            defaultIn(node);
            System.out.println("-------------->ts-- appel tableau ");
            if (this.tableGlobale.variables.containsKey(nom))
                node.tsItem = this.tableLocaleCourante.getVar(nom);
            defaultOut(node);
            System.out.println(this.tableGlobale);
            return null;
        }

        //    Void visit(SaAppel node)
        public Void visit(SaAppel node)
        {
            String nom = node.getNom();
            defaultIn(node);
            System.out.println("-------------->ts-- appel fonction");
            if (this.tableGlobale.fonctions.containsKey(nom)) {
                TsItemFct item = this.tableGlobale.getFct(nom);
                int decompteArgs = item.nbArgs;
                this.tableLocaleCourante = item.getTable();
                while (node.getArguments() != null) {
                    decompteArgs --;
                    node.getArguments().accept(this);
                }
                if (decompteArgs != 0)
                    return null;
                node.tsItem = item;
            }
            defaultOut(node);
            return null;
        }
}