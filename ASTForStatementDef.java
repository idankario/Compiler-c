/* Generated By:JJTree: Do not edit this line. ASTForStatementDef.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTForStatementDef extends SimpleNode {
  public ASTForStatementDef(int id) {
    super(id);
  }

  public ASTForStatementDef(CLang p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CLangVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=f1e89f0875b328f31c2b2b3e75f4b9c0 (do not edit this line) */
