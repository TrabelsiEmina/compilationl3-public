//package sa;

import sc.analysis.*;
import sc.parser.*;
import sc.lexer.*;
import sc.node.*;

import java.io.*;

import sa.*;

public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;
//    private int indentation = 0;
//
//
//    public void defaultIn(@SuppressWarnings("unused") Node node)
//    {
//        for(int i = 0; i < this.indentation; i++){System.out.print(" ");}
//        this.indentation++;
//        System.out.println("<" + node.getClass().getSimpleName() + ">");
//    }
//
//    public void defaultOut(@SuppressWarnings("unused") Node node)
//    {
//        this.indentation--;
//        for(int i = 0; i < this.indentation; i++){System.out.print(" ");}
//        System.out.println("</" + node.getClass().getSimpleName() + ">");
//    }
//


    public SaNode getRoot() {
        return returnValue;
    }

    @Override
//    p = ldvo ldf;
    public void caseAP(AP node) {
        SaLDec variables = null;
        SaLDec fonctions = null;
        inAP(node);
        System.out.println("---------------------->sc: programme");
        if (node.getLdvo() != null) {
            node.getLdvo().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if (node.getLdf() != null) {
            node.getLdf().apply(this);
            fonctions = (SaLDec) this.returnValue;
        }
        this.returnValue = new SaProg(variables, fonctions);
        outAP(node);
    }

/*

    @Override
    //ldvo = {ldv} ldv pvirg
    public void caseALdvLdvo(ALdvLdvo node) {
        inALdvLdvo(node);
        if (node.getLdv() != null) {
            node.getLdv().apply(this);
        }
        if (node.getPvirg() != null) {
            node.getPvirg().apply(this);
        }
        outALdvLdvo(node);
    }
*/

/*
    @Override
    //ldvo = {rien}
    public void caseARienLdvo(ARienLdvo node) {
        inARienLdvo(node);
        outARienLdvo(node);
    }
*/

  /*  @Override
    //ldv = {dv} dv ldvb ;
    public void caseADvLdv(ADvLdv node) {
        inADvLdv(node);
        if (node.getDv() != null) {
            node.getDv().apply(this);
        }
        if (node.getLdvb() != null) {
            node.getLdvb().apply(this);
        }
        outADvLdv(node);
    }
*/
/*
    @Override
        //ldv = {rien} ;
    public void caseARienLdv(ARienLdv node) {
        inARienLdv(node);
        outARienLdv(node);
    }


    @Override
    //ldvb = {ldvb} virg dv ldvb  ;
    public void caseALdvbLdvb(ALdvbLdvb node) {
        inALdvbLdvb(node);
        if (node.getVirg() != null) {
            node.getVirg().apply(this);
        }
        if (node.getDv() != null) {
            node.getDv().apply(this);
        }
        if (node.getLdvb() != null) {
            node.getLdvb().apply(this);
        }
        outALdvbLdvb(node);
    }


    @Override
    //ldvb = {rien}  ;
    public void caseARienLdvb(ARienLdvb node) {
        inARienLdvb(node);
        outARienLdvb(node);
    }


    @Override
    //ldf = {ldf} df ldf ;
    public void caseALdfLdf(ALdfLdf node) {
        inALdfLdf(node);
        if (node.getDf() != null) {
            node.getDf().apply(this);
        }
        if (node.getLdf() != null) {
            node.getLdf().apply(this);
        }
        outALdfLdf(node);
    }


    @Override
    //ldf = {rien} ;
    public void caseARienLdf(ARienLdf node) {
        inARienLdf(node);
        outARienLdf(node);
    }
*/

    @Override
    //df = id po ldv pf ldvo ibloc;
    public void caseADf(ADf node) {
        String nom = "";
        SaLDec parametres = null;
        SaLDec variables = null;
        SaInst corps = null;
        inADf(node);
        System.out.println("---------------------->sc: declaration de fonction");
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = this.returnValue.toString();
        }
        if (node.getPo() != null) {
            node.getPo().apply(this);
        }
        if (node.getLdv() != null) {
            node.getLdv().apply(this);
            parametres = (SaLDec) this.returnValue;
        }
        if (node.getPf() != null) {
            node.getPf().apply(this);
        }
        if (node.getLdvo() != null) {
            node.getLdvo().apply(this);
            variables = (SaLDec) this.returnValue;
        }
        if (node.getIbloc() != null) {
            node.getIbloc().apply(this);
            corps = (SaInst) this.returnValue;
        }
        this.returnValue = new SaDecFonc(nom,parametres,variables,corps);
        outADf(node);
    }


    @Override
    //ibloc = acco li accf ;
    public void caseAIbloc(AIbloc node) {
        SaLInst val = null;
        inAIbloc(node);
        System.out.println("---------------------->sc: bloc inst");
        if (node.getAcco() != null) {
            node.getAcco().apply(this);
        }
        if (node.getLi() != null) {
            node.getLi().apply(this);
            val = (SaLInst)this.returnValue;
        }
        if (node.getAccf() != null) {
            node.getAccf().apply(this);
        }
        this.returnValue = new SaInstBloc(val);
        outAIbloc(node);
    }

/*
    @Override
    // i = {ibloc} ibloc
    public void caseAIblocI(AIblocI node) {
        inAIblocI(node);
        if (node.getIbloc() != null) {
            node.getIbloc().apply(this);
        }
        outAIblocI(node);
    }*/

    @Override
    //li = {li} i li ;
    public void caseALiLi(ALiLi node) {
        SaInst tete = null;
        SaLInst queue = null;
        inALiLi(node);
        System.out.println("---------------------->sc: list inst");
        if (node.getI() != null) {
            node.getI().apply(this);
            tete = (SaInst)this.returnValue;
        }
        if (node.getLi() != null) {
            node.getLi().apply(this);
            queue = (SaLInst)this.returnValue;
        }
        this.returnValue = new SaLInst(tete, queue);
        outALiLi(node);
    }

/*    @Override
    //li = {rien} ;
    public void caseARienLi(ARienLi node) {
        inARienLi(node);
        outARienLi(node);
    }*/

    @Override
    //i = {iaff} iaff pvirg
    public void caseAIaffI(AIaffI node) {
        SaVar variable = null;
        SaExp valeur = null;
        inAIaffI(node);
        System.out.println("---------------------->sc: inst affect");
        if (node.getIaff() != null) {
            node.getIaff().apply(this);
            variable = (SaVar) this.returnValue;
        }
        if (node.getPvirg() != null) {
            node.getPvirg().apply(this);
            valeur = (SaExp) this.returnValue;
        }
        this.returnValue = new SaInstAffect(variable, valeur);
        outAIaffI(node);
    }


    @Override
    //i = {isi} isi
    public void caseAIsiI(AIsiI node) {
        SaExp test = null;
        SaInst alors = null;
        SaInst sinon = null;
        inAIsiI(node);
        System.out.println("---------------------->cs: inst si");
        if (node.getIsi() != null) {
            node.getIsi().apply(this);
            alors = (SaInst)this.returnValue;
        }
        this.returnValue = new SaInstSi(test, alors, sinon);
        outAIsiI(node);
    }


    @Override
    // isi = {si} si e alors ibloc
    public void caseASiIsi(ASiIsi node) {
        SaExp test = null;
        SaInst alors = null;
        SaInst sinon = null;
        inASiIsi(node);
        System.out.println("---------------------->sc: inst si alors bloc");
        if (node.getSi() != null) {
            node.getSi().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
            test = (SaExp)this.returnValue;
        }
        if (node.getAlors() != null) {
            node.getAlors().apply(this);
        }
        if (node.getIbloc() != null) {
            node.getIbloc().apply(this);
            alors = (SaInst)this.returnValue;
        }
        this.returnValue = new SaInstSi(test, alors, sinon);
        outASiIsi(node);
    }

    @Override
    // isi = {sinon} si e alors [ibloc1]: ibloc sinon [ibloc2]: ibloc;
    public void caseASinonIsi(ASinonIsi node) {
        SaExp test = null;
        SaInst alors = null;
        SaInst sinon = null;
        inASinonIsi(node);
        System.out.println("----------------------> inst si alors bloc1 sinon bloc2");
        if (node.getSi() != null) {
            node.getSi().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
            test = (SaExp) this.returnValue;
        }
        if (node.getAlors() != null) {
            node.getAlors().apply(this);
        }
        if (node.getIbloc1() != null) {
            node.getIbloc1().apply(this);
            alors = (SaInst)this.returnValue;
        }
        if (node.getSinon() != null) {
            node.getSinon().apply(this);
        }
        if (node.getIbloc2() != null) {
            node.getIbloc2().apply(this);
            sinon = (SaInst)this.returnValue;
        }
        this.returnValue = new SaInstSi(test, alors, sinon);
        outASinonIsi(node);
    }

/*
    @Override
    //i = {itq} itq
    public void caseAItqI(AItqI node) {
        SaExp test = null;
        SaInst faire = null;
        inAItqI(node);
        System.out.println("---------------------->sc: inst tant que");
        if (node.getItq() != null) {
            node.getItq().apply(this);
            faire = (SaInst) this.returnValue;
        }
        this.returnValue = new SaInstTantQue(test, faire);
        outAItqI(node);
    }
*/

    @Override
    //itq =  tq e fr ibloc;
    public void caseAItq(AItq node) {
        SaExp test = null;
        SaInst faire = null;
        inAItq(node);
        System.out.println("---------------------->sc: inst tant que");
        if (node.getTq() != null) {
            node.getTq().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
            test = (SaExp)this.returnValue;
        }
        if (node.getFr() != null) {
            node.getFr().apply(this);
        }
        if (node.getIbloc() != null) {
            node.getIbloc().apply(this);
            faire = (SaInst) this.returnValue;
        }
        this.returnValue = new SaInstTantQue(test, faire);
        outAItq(node);
    }


    @Override
    //iret = ret e pvirg;
    public void caseAIret(AIret node) {
        SaExp val = null;
        inAIret(node);
        System.out.println("---------------------->sc: inst retour");
        if (node.getRet() != null) {
            node.getRet().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
            val =  (SaExp) this.returnValue;
        }
        if (node.getPvirg() != null) {
            node.getPvirg().apply(this);
        }
        this.returnValue = new SaInstRetour(val);
        outAIret(node);
    }

    @Override
    // i = {iret} iret
    public void caseAIretI(AIretI node) {
        SaExp val = null;
        inAIretI(node);
        System.out.println("----------------------> inst retour");
        if (node.getIret() != null) {
            node.getIret().apply(this);
            val =  (SaExp) this.returnValue;
        }
        this.returnValue = new SaInstRetour(val);
        outAIretI(node);
    }


    @Override
    //iecr = ecrire po e pf pvirg;
    public void caseAIecr(AIecr node) {
        SaExp val = null;
        inAIecr(node);
        System.out.println("---------------------->sc: inst ecrire");
        if (node.getEcrire() != null) {
            node.getEcrire().apply(this);
        }
        if (node.getPo() != null) {
            node.getPo().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
            val =  (SaExp) this.returnValue;
        }
        if (node.getPf() != null) {
            node.getPf().apply(this);
        }
        if (node.getPvirg() != null) {
            node.getPvirg().apply(this);
        }
        this.returnValue = new SaInstEcriture(val);
        outAIecr(node);
    }

    @Override
    // i = {ecrire} iecr ;
    public void caseAEcrireI(AEcrireI node) {
        SaExp val = null;
        inAEcrireI(node);
        System.out.println("---------------------->sc: inst ecrire");
        if (node.getIecr() != null) {
            node.getIecr().apply(this);
            val =  (SaExp) this.returnValue;
        }
        this.returnValue = new SaInstEcriture(val);
        outAEcrireI(node);
    }



    @Override
    // i= {iapp} iapp
    public void caseAIappI(AIappI node) {
        String nom = "";
        SaLExp args = null;
        inAIappI(node);
        System.out.println("---------------------->sc: inst appel");
        if (node.getIapp() != null) {
            node.getIapp().apply(this);
            nom =((SaAppel)this.returnValue).getNom();
            args = (SaLExp)this.returnValue;
        }
        this.returnValue = new  SaAppel(nom, args);
        outAIappI(node);
    }


    @Override
    //iapp = app pvirg;
    public void caseAIapp(AIapp node) {
        String nom = "";
        SaLExp args = null;
        inAIapp(node);
        System.out.println("---------------------->sc: inst appel");
        if (node.getApp() != null) {
            node.getApp().apply(this);
            nom =((SaAppel)this.returnValue).getNom();
            args = (SaLExp)this.returnValue;
        }
        if (node.getPvirg() != null) {
            node.getPvirg().apply(this);
        }
        this.returnValue = new  SaAppel(nom, args);
        outAIapp(node);
    }





    @Override
    // dv = {tab} entier id crocho nombre crochf;
    public void caseATabDv(ATabDv node) {
        String nom = "";
        int taille = 0;
        inATabDv(node);
        System.out.println("---------------------->sc: declaration tableau");
        if (node.getEntier() != null) {
            node.getEntier().apply(this);
        }
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = ((SaDecTab)this.returnValue).getNom();
        }
        if (node.getCrocho() != null) {
            node.getCrocho().apply(this);
        }
        if (node.getNombre() != null) {
            node.getNombre().apply(this);
            taille = ((SaDecTab)this.returnValue).getTaille();
        }
        if (node.getCrochf() != null) {
            node.getCrochf().apply(this);
        }
        this.returnValue = new SaDecTab(nom, taille);
        outATabDv(node);
    }

    @Override
    // var  = {tabexp} id crocho e crochf;
    public void caseATabexpVar(ATabexpVar node) {
        String nom = null;
        int taille = 0;
        inATabexpVar(node);
        System.out.println("---------------------->sc: modification tableau");
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = ((SaDecTab)this.returnValue).getNom();
        }

        if (node.getCrocho() != null) {
            node.getCrocho().apply(this);
        }

        if (node.getE() != null) {
            node.getE().apply(this);
            taille = ((SaDecTab)this.returnValue).getTaille();
        }

        if (node.getCrochf() != null) {
            node.getCrochf().apply(this);
        }

        returnValue = new SaDecTab(nom, taille);
        outATabexpVar(node);
    }



    @Override
    //dv = {int} entier id
    public void caseAIntDv(AIntDv node) {
        String nom = null;
        inAIntDv(node);
        System.out.println("---------------------->sc: declaration variable");
        if (node.getEntier() != null) {
            node.getEntier().apply(this);
        }
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = ((SaDecVar)this.returnValue).getNom();
        }
        this.returnValue = new SaDecVar(nom);
        outAIntDv(node);
    }

    @Override
    //var = {var} id
    public void caseAVarVar(AVarVar node) {
        String nom = null;
        inAVarVar(node);
        System.out.println("---------------------->sc: variable");
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = ((SaDecVar)this.returnValue).getNom();
        }
        this.returnValue = new SaDecVar(nom);
        outAVarVar(node);
    }

    @Override
    //  e = {or   } e   ou   se1
    public void caseAOrE(AOrE node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAOrE(node);
        System.out.println("----------------------> or");
        if (node.getE() != null) {
            node.getE().apply(this);
            op1 = (SaExp)this.returnValue;
        }
        if (node.getOu() != null) {
            node.getOu().apply(this);
        }
        if (node.getSe1() != null) {
            node.getSe1().apply(this);
            op2 = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpOr(op1, op2);
        outAOrE(node);
    }
/*
    @Override
    //  e = {e  } se1;
    public void caseAEE(AEE node) {
        inAEE(node);
        if (node.getSe1() != null) {
            node.getSe1().apply(this);
        }
        outAEE(node);
    }*/


    @Override
    // se1 = {and  } se1 et   se2
    public void caseAAndSe1(AAndSe1 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAAndSe1(node);
        System.out.println("----------------------> and");
        if (node.getSe1() != null) {
            node.getSe1().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getEt() != null) {
            node.getEt().apply(this);
        }
        if (node.getSe2() != null) {
            node.getSe2().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpAnd(op1, op2);
        outAAndSe1(node);
    }

/*    @Override
    //se1 = {e  } se2;
    public void caseAESe1(AESe1 node) {
        inAESe1(node);
        if (node.getSe2() != null) {
            node.getSe2().apply(this);
        }
        outAESe1(node);
    }*/

    @Override
    //se2 = {egal } se2 egal se3
    public void caseAEgalSe2(AEgalSe2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAEgalSe2(node);
        System.out.println("----------------------> egal");
        if (node.getSe2() != null) {
            node.getSe2().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getEgal() != null) {
            node.getEgal().apply(this);
        }
        if (node.getSe3() != null) {
            node.getSe3().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpEqual(op1, op2);
        outAEgalSe2(node);
    }

    @Override
    //se2 = {inf} se2 inf   se3
    public void caseAInfSe2(AInfSe2 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAInfSe2(node);
        System.out.println("----------------------> inf");
        if (node.getSe2() != null) {
            node.getSe2().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getInf() != null) {
            node.getInf().apply(this);
        }
        if (node.getSe3() != null) {
            node.getSe3().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpInf(op1, op2);
        outAInfSe2(node);
    }

  /*  @Override
    //se2 = {e} se3;
    public void caseAESe2(AESe2 node) {
        inAESe2(node);
        if (node.getSe3() != null) {
            node.getSe3().apply(this);
        }
        outAESe2(node);
    }*/


    @Override
    //se3 = {add  } se3 plus se4
    public void caseAAddSe3(AAddSe3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAAddSe3(node);
        System.out.println("----------------------> add");
        if (node.getSe3() != null) {
            node.getSe3().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getPlus() != null) {
            node.getPlus().apply(this);
        }
        if (node.getSe4() != null) {
            node.getSe4().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpAdd(op1, op2);
        outAAddSe3(node);
    }

    @Override
    //se3 = {sub} se3 moins se4
    public void caseASubSe3(ASubSe3 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inASubSe3(node);
        System.out.println("----------------------> sub");
        if (node.getSe3() != null) {
            node.getSe3().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getMoins() != null) {
            node.getMoins().apply(this);
        }
        if (node.getSe4() != null) {
            node.getSe4().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpSub(op1, op2);
        outASubSe3(node);
    }

   /* @Override
    //se3 = {e} se4;
    public void caseAESe3(AESe3 node) {
        inAESe3(node);
        if (node.getSe4() != null) {
            node.getSe4().apply(this);
        }
        outAESe3(node);
    }*/

    @Override
    //se4 = {mult } se4 mult se5
    public void caseAMultSe4(AMultSe4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inAMultSe4(node);
        System.out.println("----------------------> mult");
        if (node.getSe4() != null) {
            node.getSe4().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getMult() != null) {
            node.getMult().apply(this);
        }
        if (node.getSe5() != null) {
            node.getSe5().apply(this);
            op2  = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpMult(op1, op2);
        outAMultSe4(node);
    }

    @Override
    //se4 = {div} se4 div   se5
    public void caseADivSe4(ADivSe4 node) {
        SaExp op1 = null;
        SaExp op2 = null;
        inADivSe4(node);
        System.out.println("----------------------> div");
        if (node.getSe4() != null) {
            node.getSe4().apply(this);
            op1  = (SaExp)this.returnValue;
        }
        if (node.getDiv() != null) {
            node.getDiv().apply(this);
        }
        if (node.getSe5() != null) {
            node.getSe5().apply(this);
            op2 = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpDiv(op1, op2);
        outADivSe4(node);
    }
/*

    @Override
    //se4 = {e} se5;
    public void caseAESe4(AESe4 node) {
        inAESe4(node);
        if (node.getSe5() != null) {
            node.getSe5().apply(this);
        }
        outAESe4(node);
    }
*/

    @Override
    //se5 = {not  } non
    public void caseANotSe5(ANotSe5 node) {
        SaExp op1 = null;
        inANotSe5(node);
        System.out.println("----------------------> not");
        if (node.getNon() != null) {
            node.getNon().apply(this);
        }
        if (node.getSe5() != null) {
            node.getSe5().apply(this);
            op1 = (SaExp)this.returnValue;
        }
        this.returnValue = new SaExpNot(op1);
        outANotSe5(node);
    }

/*
    @Override
    //se5 = {e  } se6;
    public void caseAESe5(AESe5 node) {
        inAESe5(node);
        if (node.getSe6() != null) {
            node.getSe6().apply(this);
        }
        outAESe5(node);
    }
*/

//    @Override
//    //se6 = {poepf} po  e    pf
//    public void caseAPoepfSe6(APoepfSe6 node) {
//        inAPoepfSe6(node);
//        if (node.getPo() != null) {
//            node.getPo().apply(this);
//        }
//        if (node.getE() != null) {
//            node.getE().apply(this);
//        }
//        if (node.getPf() != null) {
//            node.getPf().apply(this);
//        }
//        outAPoepfSe6(node);
//    }

    @Override
    // se6 = {nb } nombre
    public void caseANbSe6(ANbSe6 node) {
        int val = 0;
        inANbSe6(node);
        System.out.println("----------------------> eNombre");
        if (node.getNombre() != null) {
            node.getNombre().apply(this);
            val = ((SaExpInt)this.returnValue).getVal();
        }
        this.returnValue = new SaExpInt(val);
        outANbSe6(node);
    }

    @Override
    //se6 = {app} app
    public void caseAAppSe6(AAppSe6 node) {
        SaAppel val = null;
        inAAppSe6(node);
        System.out.println("----------------------> eAppelFonc");
        if (node.getApp() != null) {
            node.getApp().apply(this);
            val = (SaAppel)this.returnValue;
        }
        this.returnValue = new SaExpAppel(val);
        outAAppSe6(node);
    }

    @Override
    //se6 = {var} var
    public void caseAVarSe6(AVarSe6 node) {
        SaVar op1 = null;
        inAVarSe6(node);
        System.out.println("----------------------> eVar");
        if (node.getVar() != null) {
            node.getVar().apply(this);
            op1 = (SaVar)this.returnValue;
        }
        this.returnValue = new SaExpVar(op1);
        outAVarSe6(node);
    }

    @Override
    //se6 = {ilire} lire po pf ;
    public void caseAIlireSe6(AIlireSe6 node) {
        inAIlireSe6(node);
        System.out.println("----------------------> eLire");
        if (node.getLire() != null) {
            node.getLire().apply(this);
        }
        if (node.getPo() != null) {
            node.getPo().apply(this);
        }
        if (node.getPf() != null) {
            node.getPf().apply(this);
        }
        this.returnValue = new SaExpLire();
        outAIlireSe6(node);
    }

    @Override
    // le =  {e} e suitele
    public void caseAELe(AELe node) {
        SaExp tete = null;
        SaLExp queue = null;
        inAELe(node);
        System.out.println("----------------------> liste expression");
        if (node.getE() != null) {
            node.getE().apply(this);
            tete = (SaExp)this.returnValue;
        }
        if (node.getSuitele() != null) {
            node.getSuitele().apply(this);
            queue = (SaLExp)this.returnValue;
        }
        this.returnValue = new SaLExp(tete, queue);
        outAELe(node);
    }

/*    @Override
    // LE  = {rien} ;
    public void caseARienLe(ARienLe node) {
        inARienLe(node);
        outARienLe(node);
    }*/

/*
    @Override
    //suitele = {restele} virg e suitele
    public void caseAResteleSuitele(AResteleSuitele node) {
        inAResteleSuitele(node);
        if (node.getVirg() != null) {
            node.getVirg().apply(this);
        }
        if (node.getE() != null) {
            node.getE().apply(this);
        }
        if (node.getSuitele() != null) {
            node.getSuitele().apply(this);
        }
        outAResteleSuitele(node);
    }
*/

    /*
    @Override
    //suitele = {rien} ;
    public void caseARienSuitele(ARienSuitele node) {
        inARienSuitele(node);
        outARienSuitele(node);
    }
*/

    @Override
    // app = id po le pf;
    public void caseAApp(AApp node) {
        String nom = "";
        SaLExp arguments = null;
        inAApp(node);
        System.out.println("----------------------> Appel de fonction");
        if (node.getId() != null) {
            node.getId().apply(this);
            nom = this.returnValue.toString();
//            nom = ((SaAppel)this.returnValue).toString();
        }
        if (node.getPo() != null) {
            node.getPo().apply(this);
        }
        if (node.getLe() != null) {
            node.getLe().apply(this);
            arguments = (SaLExp) this.returnValue;
        }
        if (node.getPf() != null) {
            node.getPf().apply(this);
        }
        this.returnValue = new SaAppel(nom, arguments);
        outAApp(node);
    }
}