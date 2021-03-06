import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Vector;

public class SymbolTableVisitor extends CLangDefaultVisitor {

    Vector<String> _data;
    Vector<String> _text;
    int stackIndex = 0;
    

    public static class SymbolTableEntry
    {
        public String nameId;
        public String typeId;
        public String functionName;
        public String functionType;
        public int offset;

        public SymbolTableEntry(String functionName, String functionType)
        {
            this.nameId = "";
            this.typeId = "";
            this.functionName = functionName;
            this.functionType = functionType;
        }
        public SymbolTableEntry(String nameId, String typeId,int offset)
        {
            this.nameId = nameId;
            this.typeId = typeId;
            this.functionName = "";
            this.functionType = "";
            this.offset = offset;
        }
    }


    HashMap<String, SymbolTableEntry> symbols = new HashMap<>();
   

    public SymbolTableVisitor() 
    {
        this._text = new Vector<>();
        this._data = new Vector<>();
    }

    public SymbolTableEntry resolveFunction(String s) 
    {
        return symbols.get(s);
    }

    public void putFunction(SymbolTableEntry s)
    {
        if(symbols.get(s.functionName)==null)
        {
            symbols.put(s.functionName, s);
            return;
        }
        System.err.println(String.format("Error: redeclaration of %s  with no linkage",s.nameId));     
        System.exit(1);
    }

    public SymbolTableEntry resolveId(String s) 
    {
        return symbols.get(s);
    }

    public void putId(SymbolTableEntry s)
    {
        if(symbols.get(s.nameId)==null)
        {
            symbols.put(s.nameId, s);
            return;
        }
        System.err.println(String.format("Error: redeclaration of %s  with no linkage",s.nameId));     
        System.exit(1);
    }
    public boolean isCondition(int s)
    {
        return(s==28||s==27||s==26);
    }
    @Override
    public Object visit(ASTStart node, Object data) {
        _text.add("GLOBAL _start");
        _text.add("_start:");
        _text.add("call main");
        _text.add("mov eax, 1");
        _text.add("xor ebx, ebx");
        _text.add("int 0x80");
        _text.add("");
        Object o = super.visit(node, data);

        System.out.println("SECTION .TEXT");
        for (String s : _text)
            System.out.println(s);
        return o;
    }
    @Override
    public Object visit(ASTlistExpressionDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTfunctionCall node, Object data) {
        if(node.firstToken.kind==CLang.ID
        && resolveFunction(node.firstToken.image)==null)
       {
       
           System.err.println(String.format("Error:Function %s undeclared  at %d : %d",
                                               node.firstToken.image,
                                               node.firstToken.beginLine,
                                               node.firstToken.beginColumn));
           System.exit(1);
       }
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTmulExpressionDef node, Object data) {
        data = node.children[0].jjtAccept(this, data);
        if (node.children.length > 1)
        {  
            String e= node.firstToken.next.image;
            if(e.equals("*"))
           {
                data = node.children[1].jjtAccept(this, data);
                _text.add("pop rbx");
                _text.add("pop rax");
                _text.add("mul rax; rbx");
                _text.add("push rax");
           }
          else if(e.equals("/"))
           {
                data = node.children[1].jjtAccept(this, data);
                _text.add("pop rbx");
                _text.add("pop rax");
                _text.add("div rax; rbx");
                _text.add("push rax");
           }
           else if(e.equals("%"))
           {
                data = node.children[1].jjtAccept(this, data);
                _text.add("pop rbx");
                _text.add("pop rax");
                _text.add("shl rax, rbx");
                _text.add("push rax");
           }     
        }
        return data;
    }
    @Override
    public Object visit(ASTbinaryBoolExpressionCompareDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTbinaryExpressionEqDef node, Object data) {
        data = node.children[0].jjtAccept(this, data);
        if (node.children.length > 1)
        {  
            String e= node.firstToken.next.image;
           
            if(e.equals("=="))
            {
               
           

           }
   
        }
        return data;
    }
    @Override
    public Object visit(ASTbinaryBoolExpressionAndDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTbinaryBoolExpressionOrDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTbinaryBoolOperatorCompareDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTboolExpressionDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTidExpressionDef node, Object data) {
        SymbolTableEntry e = resolveId(node.firstToken.image);
        if(e==null)
        {
            System.err.println(String.format("error:Variable %s undeclared (first use in this function) at %d : %d",
                                                node.firstToken.image,
                                                node.firstToken.beginLine,
                                                node.firstToken.beginColumn));
            System.exit(1);
        }
       
       
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTassignExpressionDef node, Object data) 
    { 
        if(isCondition(node.jjtGetParent().jjtGetParent().jjtGetParent().getId()))
            return super.visit(node, data);
        SymbolTableEntry e = resolveId(node.firstToken.image);
        boolean isInt = e.typeId.equals("int");
        _text.add(String.format("pop %s, %s  [rbp - %d]", isInt ? "eax" : "al", isInt ? "dword" : "byte", e.offset));
        Object o = super.visit(node, data);
        return o;
    }
    @Override
    public Object visit(ASTexpressionDef node, Object data)
    {

      
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTsourceCodeDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTparamListDef node, Object data)
     {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTparamDef node, Object data) 
    {
        Object res = super.visit(node, data);
        SymbolTableEntry e = new SymbolTableEntry(node.firstToken.next.image, node.firstToken.image, this.stackIndex);
        this.stackIndex += 4;
  
        putId(e);
        return res;
    }
    @Override
    public Object visit(ASTfunctionDef node, Object data)
     {
        SymbolTableEntry e = new SymbolTableEntry(node.firstToken.next.image, node.firstToken.image);
        _text.add(String.format("%s:" ,node.firstToken.next.image));
        _text.add("push rbp");
        _text.add("mov rbp, rsp");
        Object o = super.visit(node, data);
        _text.add("mov rsp, rbp");
        _text.add("pop rbp");
        _text.add("ret");
        return o;
        // return super.visit(node, data);
    }
    @Override
    public Object visit(ASTvarAssignDefInInit node, Object data) 
    {   
        
        return super.visit(node, data);
        
    }
    @Override
    public Object visit(ASTlistVarDefineDef node, Object data) 
    {
        Token temp=null;
        if(node.children.length > 0)
        {
            String typeT = node.firstToken.image;
            boolean isInt = typeT.equals("int");
            int memory= isInt ? 4 : 1;
            temp=node.firstToken.next;
            while(temp.kind != CLang.SEMICOLON)
            {
                this.stackIndex+=memory;  
                SymbolTableEntry e = new SymbolTableEntry(temp.image, typeT, this.stackIndex);
                putId(e);
              
                    data = node.children[0].jjtAccept(this, temp);
                    _text.add("pop rax");
                    _text.add(String.format("mov %s [rbp - %d], %s", isInt ? "dword" : "byte", e.offset, isInt ? "eax" : "al"));
                
                temp=temp.next;
                while(temp.image.equals("=")||temp.image.equals(",")||temp.kind==CLang.NUMBER)
                {
                    if(temp.kind==CLang.NUMBER)
                    {
                        _text.add(String.format("mov rax, %s", temp.image));
                        _text.add("push rax");
                    }
                    temp=temp.next;
                }
            
            }
        
        }
        return temp;      
    }
 
    @Override
    public Object visit(ASTvarDefineDef node, Object data) 
    {
        boolean isInt = node.firstToken.image.equals("int");
        if (isInt)
            this.stackIndex+=4;
        else
            this.stackIndex++;

        SymbolTableEntry e = new SymbolTableEntry(node.firstToken.next.image, node.firstToken.image, this.stackIndex);
   
        putId(e);
        if (node.children.length > 0)
        {
            data = node.children[0].jjtAccept(this, data);
            _text.add("pop rax");
            _text.add(String.format("mov %s [rbp - %d], %s", isInt ? "dword" : "byte", e.offset, isInt ? "eax" : "al"));
        }
        return data;
    }

    @Override
    public Object visit(ASTStatementBlockDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTStatementDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTconstExpressionOrBinaryDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTReturnStatementDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTWhileStatementDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTForStatementDef node, Object data) {
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTunaryExpressionDef node, Object data) {
        
        return super.visit(node, data);
    }
    @Override
    public Object visit(ASTaddExpressionDef node, Object data) {
        data = node.children[0].jjtAccept(this, data);
        if (node.children.length > 1)
        {  
            String e= node.firstToken.next.image;
            if(e.equals("+"))
           {
                data = node.children[1].jjtAccept(this, data);
                _text.add("pop rbx");
                _text.add("pop rax");
                _text.add("add rax, rbx");
                _text.add("push rax");
           }
          else if(e.equals("-"))
           {
                data = node.children[1].jjtAccept(this, data);
                _text.add("pop rbx");
                _text.add("pop rax");
                _text.add("sub rax, rbx");
                _text.add("push rax");
           }  
        }

        return data;
    }
    @Override
    public Object visit(ASTIfStatementDef node, Object data) {
        _text.add("sffsf");
        Object o = super.visit(node, data);
        _text.add("sffsf");
   
        return o;
    }

    @Override
    public Object visit(ASTconstExpressionDef node, Object data) {
       
        if (node.firstToken.kind == CLang.ID)
        {
            SymbolTableEntry e = resolveId(node.firstToken.image);
            if (e == null)
            {
                System.err.println(String.format("Variable %s is not defined at %d : %d",
                                                    node.firstToken.image,
                                                    node.firstToken.beginLine,
                                                    node.firstToken.beginColumn));
                System.exit(-1);
            }
            else
            {
                boolean isInt = e.typeId.equals("int");
                _text.add(String.format("mov %s, %s [rbp - %d]", isInt ? "eax" : "al", 
                                        isInt ? "dword" : "byte", e.offset));

                _text.add("push rax");
                return super.visit(node, data);
            }
        }
        if (node.firstToken.kind == CLang.NUMBER)
        {
            _text.add(String.format("mov rax, %s", node.firstToken.image));
            _text.add("push rax");
        }
        return super.visit(node, data);
    }



    
    public static void main(String[] args) throws FileNotFoundException, ParseException {
        new CLang(new FileInputStream(args[0]));

        CLang.Start();

        System.out.println("Parsing succeeded, begin compiling");
        

        CLang.jjtree.rootNode().jjtAccept(new SymbolTableVisitor(), null);
    }
}