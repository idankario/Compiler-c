/* Generated By:JavaCC: Do not edit this line. CLangDefaultVisitor.java Version 7.0.5 */
public class CLangDefaultVisitor implements CLangVisitor{
  public Object defaultVisit(SimpleNode node, Object data){
    node.childrenAccept(this, data);
    return data;
  }
  public Object visit(SimpleNode node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTidExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTconstExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTlistExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTfunctionCall node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTunaryExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTmulExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTaddExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTbinaryBoolExpressionCompareDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTbinaryExpressionEqDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTbinaryBoolExpressionAndDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTbinaryBoolExpressionOrDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTbinaryBoolOperatorCompareDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTboolExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTassignExpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTexpressionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTvarAssignDefInInit node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTlistVarDefineDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTvarDefineDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTStatementBlockDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTparamDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTparamListDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTfunctionDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTsourceCodeDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTStart node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTconstExpressionOrBinaryDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTReturnStatementDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTWhileStatementDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTForStatementDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTIfStatementDef node, Object data){
    return defaultVisit(node, data);
  }
  public Object visit(ASTStatementDef node, Object data){
    return defaultVisit(node, data);
  }
}
/* JavaCC - OriginalChecksum=aa97ad8f2b68b0f1db2436f022bb8666 (do not edit this line) */
