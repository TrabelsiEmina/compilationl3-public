public class Sc2sa extends DepthFirstAdapter {
    private SaNode returnValue;

    // p = ldvo ldf
    public void caseSaProg(SaProg node){
        node.getVariables().accept(this);
        SaLDec var = (SaLDec) this.returnValue;
        node.getFonctions().accept(this);            SaLDec fct = (SaLDec) this.returnValue;
        this.returnValue = new SaProg(var,fct);
    }
/*
    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }

    // exp3 = {plus} exp3 plus exp4
    public SaExp caseAPlusExp3(APlusExp3 node){
        SaExp op1 = node.getExp3().apply(this);
        SaExp op2 = node.getExp4().apply(this);
        return new SaExpAdd(op1, op2);
    }
    */
}