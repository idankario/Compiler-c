/* Generated By:JJTree: Do not edit this line. ASTboolExpressionDef.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTboolExpressionDef extends SimpleNode {
  public ASTboolExpressionDef(int id) {
    super(id);
  }

  public ASTboolExpressionDef(CLang p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CLangVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=c1f976dddcd9828d18b0e22adbaea589 (do not edit this line) */
