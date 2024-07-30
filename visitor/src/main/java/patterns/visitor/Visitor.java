package patterns.visitor;

interface Visitor {
    void visit(Book book);
    void visit(Fruit fruit);
}
