/* Generated By:JJTree: Do not edit this line. ASTlistVarDefineDef.java Version 7.0 */
/* JavaCCOptions:MULTI=true,NODE_USES_PARSER=false,VISITOR=true,TRACK_TOKENS=true,NODE_PREFIX=AST,NODE_EXTENDS=,NODE_FACTORY=,SUPPORT_CLASS_VISIBILITY_PUBLIC=true */
public
class ASTlistVarDefineDef extends SimpleNode {
  public ASTlistVarDefineDef(int id) {
    super(id);
  }

  public ASTlistVarDefineDef(CLang p, int id) {
    super(p, id);
  }


  /** Accept the visitor. **/
  public Object jjtAccept(CLangVisitor visitor, Object data) {

    return
    visitor.visit(this, data);
  }
}
/* JavaCC - OriginalChecksum=6ec1122376a99b1593dc7c5130840f33 (do not edit this line) */